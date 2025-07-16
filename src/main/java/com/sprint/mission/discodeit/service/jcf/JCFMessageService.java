package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.*;

public class JCFMessageService implements MessageService {

    private final Map<UUID, Message> messages;

    public JCFMessageService() {
        messages = new HashMap<>();
    }

    @Override
    public void create(String channelName, String fromId, String content) {
        Message newMessage = new Message(channelName,  fromId, content);
        messages.put(newMessage.getId(), newMessage);
    }

    @Override
    public List<Message> find(String channelName, String fromId) {
        List<Message> findMessage = new ArrayList<>();
        for (Map.Entry<UUID, Message> entry : messages.entrySet()) {
            if(entry.getValue().getChannelName().equals(channelName)) {
                if(entry.getValue().getFromId().equals(fromId)) {
                    findMessage.add(entry.getValue());
                }
            }
        }
        return findMessage;
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
}
