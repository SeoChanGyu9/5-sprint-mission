package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    void create(String channelName, String fromId, String content);
    List<Message> find(String channelName, String fromId);
    List<Message> findAll();
    boolean update(UUID id, String content);
    boolean delete(UUID id);

}
