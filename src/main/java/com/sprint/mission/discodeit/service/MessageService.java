package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    Message create(UUID channelId, UUID fromId, String content);
    Message find(UUID messageId);
    List<Message> findAll();
    boolean update(UUID id, String content);
    boolean delete(UUID id);

}
