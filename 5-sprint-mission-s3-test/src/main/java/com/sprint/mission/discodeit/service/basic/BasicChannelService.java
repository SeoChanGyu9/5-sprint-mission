package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.request.ChannelCreateRequest;
import com.sprint.mission.discodeit.dto.request.ChannelFindRequest;
import com.sprint.mission.discodeit.dto.request.ChannelPrivateCreateRequest;
import com.sprint.mission.discodeit.dto.request.ChannelUpdateRequest;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service("basicChannelService")
@RequiredArgsConstructor
public class BasicChannelService implements ChannelService {
    private final ChannelRepository channelRepository;
    private final ReadStatusRepository readStatusRepository;
    private final MessageRepository messageRepository;

    //        PRIVATE 채널과 PUBLIC 채널을 생성하는 메소드를 분리합니다.
//[ ] 분리된 각각의 메소드를 DTO를 활용해 파라미터를 그룹화합니다.
    //        PUBLIC 채널을 생성할 때에는 기존 로직을 유지합니다.
    @Override
    public Channel create(ChannelCreateRequest channelCreateRequest) {
        Channel channel = new Channel(ChannelType.PUBLIC, channelCreateRequest.name(), channelCreateRequest.description());
        return channelRepository.save(channel);
    }

    public Channel create_private(ChannelPrivateCreateRequest channelPrivateCreateRequest){
//                PRIVATE 채널을 생성할 때:
        //[ ] name과 description 속성은 생략합니다.
        Channel channel = new Channel(ChannelType.PRIVATE, channelPrivateCreateRequest.userIdList());
//[ ] 채널에 참여하는 User의 정보를 받아 User 별 ReadStatus 정보를 생성합니다.
        for(UUID userId : channelPrivateCreateRequest.userIdList()){
            readStatusRepository.save(new ReadStatus(userId, channel.getId()));
        }

        return channelRepository.save(channel);
    }

    @Override
    public ChannelFindRequest find(UUID channelId) {
//        DTO를 활용하여:
//[ ] 해당 채널의 가장 최근 메시지의 시간 정보를 포함합니다.
//[ ] PRIVATE 채널인 경우 참여한 User의 id 정보를 포함합니다.
        Channel findChannel = channelRepository.findById(channelId)
                        .orElseThrow(() -> new NoSuchElementException("Channel with id " + channelId + " not found"));
        List<Message> recentMessageList = messageRepository.findByChannelId(channelId);
        Instant recentMessageTime = recentMessageList.get(recentMessageList.size() - 1).getCreatedAt();
        if(findChannel.getType().equals(ChannelType.PUBLIC)){
            return new ChannelFindRequest(channelId, findChannel.getCreatedAt(), findChannel.getUpdatedAt(), findChannel.getType(), findChannel.getName(), findChannel.getDescription(),null, recentMessageTime);
        } else {
            return new ChannelFindRequest(channelId, findChannel.getCreatedAt(), findChannel.getUpdatedAt(), findChannel.getType(), null, null,findChannel.getUserIdList(), recentMessageTime);
        }


    }

    @Override
    public List<ChannelFindRequest> findAllByUserId(UUID userId) {
//        DTO를 활용하여:
//[ ] 해당 채널의 가장 최근 메시지의 시간 정보를 포함합니다.
//[ ] PRIVATE 채널인 경우 참여한 User의 id 정보를 포함합니다.
//[ ] 특정 User가 볼 수 있는 Channel 목록을 조회하도록 조회 조건을 추가하고, 메소드 명을 변경합니다. findAllByUserId
//[ ] PUBLIC 채널 목록은 전체 조회합니다.
//[ ] PRIVATE 채널은 조회한 User가 참여한 채널만 조회합니다.
        List<ChannelFindRequest> findAllChannels = new ArrayList<>();

        List<Channel> channelList = channelRepository.findAll();
        for(Channel channel : channelList){
            List<Message> recentMessageList = messageRepository.findByChannelId(channel.getId());
            Instant recentMessageTime = recentMessageList.get(recentMessageList.size() - 1).getCreatedAt();

            if(channel.getType().equals(ChannelType.PUBLIC)){
                findAllChannels.add(new ChannelFindRequest(channel.getId(), channel.getCreatedAt(), channel.getUpdatedAt(), channel.getType(), channel.getName(), channel.getDescription(),null, recentMessageTime));
            } else if(channel.isEntry(userId)){
                findAllChannels.add(new ChannelFindRequest(channel.getId(), channel.getCreatedAt(), channel.getUpdatedAt(), channel.getType(), null, null,channel.getUserIdList(), recentMessageTime));
            }
        }
        return findAllChannels;

    }

    @Override
    public Channel update(ChannelUpdateRequest channelUpdateRequest) {
//        [ ] DTO를 활용해 파라미터를 그룹화합니다.
//        수정 대상 객체의 id 파라미터, 수정할 값 파라미터
//[ ] PRIVATE 채널은 수정할 수 없습니다.
        if(channelUpdateRequest.type().equals(ChannelType.PUBLIC)){
            Channel channel = channelRepository.findById(channelUpdateRequest.channelId())
                    .orElseThrow(() -> new NoSuchElementException("Channel with id " + channelUpdateRequest.channelId() + " not found"));
            channel.update(channelUpdateRequest.newName(), channelUpdateRequest.newDescription());
            return channelRepository.save(channel);
        }
        else
            throw new NoSuchElementException("Channel type PRIVATE !");

    }

    @Override
    public void delete(UUID channelId) {
//        관련된 도메인도 같이 삭제합니다.
//        Message, ReadStatus
        if (!channelRepository.existsById(channelId)) {
            throw new NoSuchElementException("Channel with id " + channelId + " not found");
        }
        channelRepository.deleteById(channelId);

        messageRepository.findByChannelId(channelId).forEach(message -> {messageRepository.deleteById(message.getId());});
        readStatusRepository.findByChannelId(channelId).forEach(readStatus -> {readStatusRepository.deleteById(readStatus.getId());});


    }
}
