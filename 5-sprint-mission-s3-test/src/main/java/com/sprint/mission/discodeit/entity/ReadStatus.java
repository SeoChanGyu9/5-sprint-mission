package com.sprint.mission.discodeit.entity;


import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class ReadStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    //
    private UUID userId;
    private UUID channelId;
    private Instant lastRead;

    public ReadStatus(UUID userId, UUID channelId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.userId = userId;
        this.channelId = channelId;
        this.lastRead = Instant.now();
    }

    public void update(Instant lastRead){
        this.lastRead = lastRead;
    }
}
