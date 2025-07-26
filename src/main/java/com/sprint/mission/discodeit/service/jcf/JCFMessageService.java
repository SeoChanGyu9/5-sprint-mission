package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.*;

public class JCFMessageService implements MessageService {

    private final Map<UUID, Message> messages;
    private final static MessageService jcfMs = new JCFMessageService();

    private JCFMessageService() {
        messages = new HashMap<>();
    }

    @Override
    public Message create(UUID channelId, UUID fromId, String content) {
        Message newMessage = new Message(channelId,  fromId, content);
        messages.put(newMessage.getId(), newMessage);
        return newMessage;
    }

    @Override
    public Message find(UUID messageId) {
        return messages.get(messageId);
    }

    @Override
    public List<Message> findAll() {
        return new ArrayList<>(messages.values());
    }

    @Override
    public boolean update(UUID id, String content) {
        if(messages.containsKey(id)) {
            messages.get(id).update(content);
            return true;
        }
        return false;
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
}
