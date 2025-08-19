package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.request.MessageCreateRequest;
import com.sprint.mission.discodeit.dto.request.MessageUpdateRequest;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("basicMessageService")
@RequiredArgsConstructor
public class BasicMessageService implements MessageService {
    private final MessageRepository messageRepository;
    //
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final BinaryContentRepository binaryContentRepository;

    @Override
    public Message create(MessageCreateRequest messageCreateRequest) {
//        선택적으로 여러 개의 첨부파일을 같이 등록할 수 있습니다.
//[ ] DTO를 활용해 파라미터를 그룹화합니다.
        if (!channelRepository.existsById(messageCreateRequest.channelId())) {
            throw new NoSuchElementException("Channel not found with id " + messageCreateRequest.channelId());
        }
        if (!userRepository.existsById(messageCreateRequest.authorId())) {
            throw new NoSuchElementException("Author not found with id " + messageCreateRequest.authorId());
        }
        Message message;
        if (messageCreateRequest.attachments() != null) {
            List<UUID> attachmentIds = new ArrayList<>();
            for(BinaryContent binaryContent: messageCreateRequest.attachments()){
                binaryContentRepository.save(binaryContent);
                attachmentIds.add(binaryContent.getId());
            }
            message = new Message(messageCreateRequest.content(), messageCreateRequest.channelId(), messageCreateRequest.authorId(), attachmentIds);
        } else {
            message = new Message(messageCreateRequest.content(), messageCreateRequest.channelId(), messageCreateRequest.authorId());
        }

        return messageRepository.save(message);
    }

    @Override
    public Message find(UUID messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException("Message with id " + messageId + " not found"));
    }

    @Override
    public List<Message> findAllByChannelId(UUID channelId) {
//         특정 Channel의 Message 목록을 조회하도록 조회 조건을 추가하고, 메소드 명을 변경합니다. findallByChannelId

        return messageRepository.findAll().stream()
                .filter(m -> m.getChannelId().equals(channelId))
                .toList();
    }

    @Override
    public Message update(MessageUpdateRequest messageUpdateRequest) {
//        DTO를 활용해 파라미터를 그룹화합니다.
//        수정 대상 객체의 id 파라미터, 수정할 값 파라미터
        Message message = messageRepository.findById(messageUpdateRequest.messageId())
                .orElseThrow(() -> new NoSuchElementException("Message with id " + messageUpdateRequest.messageId() + " not found"));
        message.update(messageUpdateRequest.newContent());
        return messageRepository.save(message);
    }

    @Override
    public void delete(UUID messageId) {
//        관련된 도메인도 같이 삭제합니다.
//        첨부파일(BinaryContent)
        if (!messageRepository.existsById(messageId)) {
            throw new NoSuchElementException("Message with id " + messageId + " not found");
        }
        messageRepository.deleteById(messageId);
        Optional<Message> message = messageRepository.findById(messageId);
        for(UUID attachmentId : message.get().getAttachmentIds()) {
            binaryContentRepository.deleteById(attachmentId);
        }


    }


}
