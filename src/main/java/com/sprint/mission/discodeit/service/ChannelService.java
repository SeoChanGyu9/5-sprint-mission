package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    UUID create(String channelName, String creatorId, String password);
    List<Channel> find(String channelName);
    List<Channel> findAll();
    boolean update(UUID channelId, String channelName, String odPw, String nwPw);
    boolean delete(UUID channelId, String creatorId, String password);
}
