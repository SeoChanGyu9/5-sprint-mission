package com.sprint.mission.discodeit.dto.request;

import io.micrometer.common.lang.Nullable;

import java.util.UUID;

public record UserFindRequest(

        UUID userId,
        String username,
        String email,

        @Nullable
        UUID profileId,
        Boolean isOnline


) {
}
