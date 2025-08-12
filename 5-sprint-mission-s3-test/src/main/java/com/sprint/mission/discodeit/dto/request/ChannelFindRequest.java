package com.sprint.mission.discodeit.dto.request;

import com.sprint.mission.discodeit.entity.ChannelType;
import io.micrometer.common.lang.Nullable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ChannelFindRequest(
        UUID id,
        Instant createdAt,
        @Nullable
        Instant updateAt,
        ChannelType type,
        @Nullable
        String name,
        @Nullable
        String description,
        @Nullable
        List<UUID> userIdList,

        Instant recentMessage

) {
}
