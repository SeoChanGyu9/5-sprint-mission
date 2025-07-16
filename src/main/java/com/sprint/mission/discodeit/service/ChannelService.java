package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;

public interface ChannelService {
    void create(String channelName, String creatorId, String password);
    List<Channel> find(String channelName);
    List<Channel> findAll();
    boolean update(String channelName, String odPw, String nwPw);
    boolean delete(String channelName, String creatorId, String password);
}
