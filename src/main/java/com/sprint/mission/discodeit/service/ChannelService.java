package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelStatus;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    Channel create(String channelName, String creatorId, String password, ChannelStatus status);
    Channel find(UUID channelId);
    List<Channel> findAll();
    boolean update(UUID channelId, String channelName, String odPw, String nwPw, ChannelStatus status);
    boolean delete(UUID channelId, String creatorId, String password);
    boolean existsById(UUID channelId);
    long count();
}
