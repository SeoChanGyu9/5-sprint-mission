package com.sprint.mission.discodeit.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class UserStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    //
    private UUID userId;
    private Instant lastConnect;

    public UserStatus(UUID userId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.userId = userId;
        this.lastConnect = Instant.now();

    }

    public void update() {
        lastConnect = Instant.now();
        updatedAt = Instant.now();
    }

    //마지막 접속 시간이 현재 시간으로부터 5분 이내이면 현재 접속 중인 유저로 간주합니다
    public boolean isOnline(){
        Instant now = Instant.now();
        Duration duration = Duration.between(lastConnect, now);
        return duration.toMinutes() <= 5;
    }
}
