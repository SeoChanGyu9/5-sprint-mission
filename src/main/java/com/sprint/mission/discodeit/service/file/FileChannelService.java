package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelStatus;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class FileChannelService implements ChannelService {

    ChannelRepository repo;

    public FileChannelService(ChannelRepository repo) {
        this.repo = repo;
    }

    @Override
    public Channel create(String channelName, String creatorId, String password, ChannelStatus status) {
        Channel channel = new Channel(channelName, creatorId, password, status);
        return repo.save(channel);
    }

    @Override
    public Channel find(UUID channelId) {
        Channel channel = repo.findById(channelId).orElse(null);
        if(channel == null){
            throw new NoSuchElementException("Channel with id " + channelId + " not found");
        }
        return channel;
    }

    @Override
    public List<Channel> findAll() {
        return repo.findAll();
    }

    @Override
    public boolean update(UUID channelId, String channelName, String odPw, String nwPw, ChannelStatus status) {
        Channel updateChannel = this.find(channelId);
        if(updateChannel == null){
            throw new NoSuchElementException("Channel with id " + channelId + " not found");
        }
        if(updateChannel.getPassword().equals(odPw)){
            updateChannel.update(channelName, nwPw, status);
            repo.update(updateChannel);
            return true;
        } else{
            throw new IllegalStateException("Passwords do not match");
        }
    }

    @Override
    public boolean delete(UUID channelId, String creatorId, String password) {
        Channel deleteChannel = repo.delete(channelId);
        System.out.println("channelId: "+deleteChannel.getId() + " deleted");
        return true;
    }

    @Override
    public boolean existsById(UUID channelId) {
        return repo.existsById(channelId);

    }

    @Override
    public long count() {
        return repo.count();

    }
}
