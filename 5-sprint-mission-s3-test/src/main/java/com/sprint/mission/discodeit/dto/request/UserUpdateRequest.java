package com.sprint.mission.discodeit.dto.request;

import io.micrometer.common.lang.Nullable;

import java.util.UUID;

public record UserUpdateRequest(

        UUID userId,
        String newUsername,
        String newEmail,
        String newPassword,
        @Nullable
        UUID newProfileId


) {
}
