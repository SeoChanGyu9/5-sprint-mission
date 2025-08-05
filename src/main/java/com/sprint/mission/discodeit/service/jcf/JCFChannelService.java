package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelStatus;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.*;

public class JCFChannelService implements ChannelService {

    private final Map<UUID, Channel> channels;
    private final static ChannelService jcfCs = new JCFChannelService();


    private JCFChannelService() {
        this.channels = new HashMap<>();
    }
    @Override
    public Channel create(String channelName, String creatorId, String password, ChannelStatus status) {
        Channel newChannel = new Channel(channelName, creatorId, password, status);
        channels.put(newChannel.getId(), newChannel);
        return newChannel;
    }

    @Override
    public Channel find(UUID channelId) {
        Channel findChannel=null;
//        for (Map.Entry<UUID, Channel> entry : channels.entrySet()) {
//            if (entry.getKey().equals(channelName)) {
//                findChannels.add(entry.getValue());
//            }
//        }
        if(channels.containsKey(channelId)){
            findChannel = channels.get(channelId);
        }

        return findChannel;
    }

    @Override
    public List<Channel> findAll() {
        return List.copyOf(channels.values());
    }

    @Override
    public boolean update(UUID channelId, String channelName, String odPw, String nwPw, ChannelStatus status) {
        if(channels.containsKey(channelId)) {
            if(channels.get(channelId).getPassword().equals(odPw)) {
                channels.get(channelId).update(channelName, nwPw, status);
                return true;
            }else{
                throw new IllegalArgumentException("Incorrect password");
            }
        }else {
            throw new IllegalArgumentException("ChannelID: " + channelId + " not found.");
        }
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

    @Override
    public boolean existsById(UUID channelId) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }
}
