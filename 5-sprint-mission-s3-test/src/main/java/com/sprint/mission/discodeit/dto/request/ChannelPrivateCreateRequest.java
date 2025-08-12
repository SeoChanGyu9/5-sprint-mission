package com.sprint.mission.discodeit.dto.request;

import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public record ChannelPrivateCreateRequest(
        ChannelType channelType,
        List<UUID> userIdList   //채널에 참가하는 유저들

) {
}
