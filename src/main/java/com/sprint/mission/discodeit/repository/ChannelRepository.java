package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChannelRepository {
    Channel save(Channel channel);

    Optional<Channel> findById(UUID id);

    List<Channel> findAll();

    long count();

    Channel delete(UUID id);

    boolean existsById(UUID id);

    Channel update(Channel channel);
}
