package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.request.ChannelCreateRequest;
import com.sprint.mission.discodeit.dto.request.ChannelFindRequest;
import com.sprint.mission.discodeit.dto.request.ChannelPrivateCreateRequest;
import com.sprint.mission.discodeit.dto.request.ChannelUpdateRequest;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    Channel create(ChannelCreateRequest channelCreateRequest);
    Channel create_private(ChannelPrivateCreateRequest channelPrivateCreateRequest);
    ChannelFindRequest find(UUID channelId);
    List<ChannelFindRequest> findAllByUserId(UUID userId);
    Channel update(ChannelUpdateRequest channelUpdateRequest);
    void delete(UUID channelId);
}
