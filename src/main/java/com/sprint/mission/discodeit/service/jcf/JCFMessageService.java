package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;

import java.util.*;

public class JCFMessageService implements MessageService {

    private final Map<UUID, Message> messages;
    private final static MessageService jcfMs = new JCFMessageService();

    private JCFMessageService() {
        messages = new HashMap<>();
    }

    @Override
    public Message create(UUID channelId, UUID fromUserId, String content) {
        ChannelService jcfChannelService = JCFChannelService.getInstance();
        UserService jcfUserService = JCFUserService.getInstance();


        if(jcfChannelService.find(channelId)==null){
            throw new IllegalArgumentException("Channel ID: " + channelId + " not found.");
        }
        if(jcfUserService.find(fromUserId)==null){
            throw new IllegalArgumentException("User ID: " + fromUserId + " not found.");
        }


        Message newMessage = new Message(channelId,  fromUserId, content);
        messages.put(newMessage.getId(), newMessage);
        return newMessage;
    }

    @Override
    public Message find(UUID messageId) {
        return messages.get(messageId);
    }

    @Override
    public List<Message> findAll() {
        return List.copyOf(messages.values());
    }

    @Override
    public boolean update(UUID id, String content) {
        if(messages.containsKey(id)) {
            messages.get(id).update(content);
            return true;
        } else {
            // id가 존재하지 않는 것이 비정상적인 상황일 경우 예외 발생
            throw new IllegalArgumentException("Message with ID " + id + " not found.");
        }
    }

    @Override
    public boolean delete(UUID id) {
        if(messages.containsKey(id)) {
            messages.remove(id);
            return true;
        }

        return false;
    }

    public static MessageService getInstance(){
        return jcfMs;
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }
}
