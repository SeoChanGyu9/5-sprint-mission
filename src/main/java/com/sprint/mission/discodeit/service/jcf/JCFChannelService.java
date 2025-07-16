package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.UserService;

import java.util.*;

public class JCFChannelService implements ChannelService {

    private final Map<UUID, Channel> channels;
    private final static ChannelService jcfCs = new JCFChannelService();


    private JCFChannelService() {
        this.channels = new HashMap<>();
    }
    @Override
    public Channel create(String channelName, String creatorId, String password) {
        Channel newChannel = new Channel(channelName, creatorId, password);
        channels.put(newChannel.getId(), newChannel);
        return newChannel;
    }

    @Override
    public List<Channel> find(String channelName) {
        List<Channel> findChannels = new ArrayList<>();
        for (Map.Entry<UUID, Channel> entry : channels.entrySet()) {
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
    public boolean update(UUID channelId, String channelName, String odPw, String nwPw) {
        if(channels.containsKey(channelId)) {
            if(channels.get(channelId).getPassword().equals(odPw)) {
                channels.get(channelId).update(channelName, nwPw);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(UUID channelId, String creatorId, String password) {
        if(channels.containsKey(channelId)) {
            if(channels.get(channelId).getPassword().equals(password)) {
                channels.remove(channelId);
                return true;
            }
        }

        return false;
    }

    public static ChannelService getInstance(){
        return jcfCs;
    }
}
