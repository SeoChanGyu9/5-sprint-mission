package com.sprint.mission.discodeit.dto.request;

import com.sprint.mission.discodeit.entity.ChannelType;

public record ChannelCreateRequest(
        ChannelType channelType,
        String name,
        String description
) {
}
