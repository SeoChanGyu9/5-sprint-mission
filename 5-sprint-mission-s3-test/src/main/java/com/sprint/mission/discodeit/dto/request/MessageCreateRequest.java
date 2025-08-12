package com.sprint.mission.discodeit.dto.request;

import com.sprint.mission.discodeit.entity.BinaryContent;
import io.micrometer.common.lang.Nullable;

import java.util.List;
import java.util.UUID;

public record MessageCreateRequest(
        String content,
        UUID channelId,
        UUID authorId,
        @Nullable
        List<BinaryContent> attachments
) {
}
