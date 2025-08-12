package com.sprint.mission.discodeit.entity;

import io.micrometer.common.lang.Nullable;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.time.Instant;
import java.util.UUID;


@Getter
@ToString
public class BinaryContent implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Instant createdAt;

    private String fileName;
    private String contentType; // jpg, ... 확장자
    private Long size;
    private byte[] bytes;
    //
    UUID userId;
    @Nullable   //프로필이미지면 NULL
    UUID messageId;


    public BinaryContent(String fileName, String contentType, Long size, byte[] bytes, UUID userId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.bytes = bytes;
        this.userId = userId;
    }

    public BinaryContent(String fileName, String contentType, Long size, byte[] bytes, UUID userId, UUID messageId) {
        this(fileName, contentType, size, bytes, userId);
        this.messageId = messageId;
    }

}
