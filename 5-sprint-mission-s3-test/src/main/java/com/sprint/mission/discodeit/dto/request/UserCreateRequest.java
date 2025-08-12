package com.sprint.mission.discodeit.dto.request;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;

import java.util.UUID;

public record UserCreateRequest(

        //UUID userId,
        String username,
        String email,
        String password,

        @Nullable
        UUID profileId


) {
}
