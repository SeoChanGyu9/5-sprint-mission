package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JCFChannelService implements ChannelService {

    private final Map<String, Channel> channels;

    public JCFChannelService() {
        this.channels = new HashMap<>();
    }
    @Override
    public void create(String channelName, String creatorId, String password) {
        Channel newChannel = new Channel(channelName, creatorId, password);
        channels.put(channelName, newChannel);
    }

    @Override
    public List<Channel> find(String channelName) {
        List<Channel> findChannels = new ArrayList<>();
        for (Map.Entry<String, Channel> entry : channels.entrySet()) {
            if (entry.getKey().equals(channelName)) {
                findChannels.add(entry.getValue());
            }
        }

        return findChannels;
    }

    @Override
    public List<Channel> findAll() {
        return new ArrayList<>(channels.values());
    }

    @Override
    public boolean update(String channelName, String odPw, String nwPw) {
        if(channels.containsKey(channelName)) {
            if(channels.get(channelName).getPassword().equals(odPw)) {
                channels.get(channelName).update(nwPw);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(String channelName, String creatorId, String password) {
        if(channels.containsKey(channelName)) {
            if(channels.get(channelName).getPassword().equals(password)) {
                channels.remove(channelName);
                return true;
            }
        }

        return false;
    }
}
