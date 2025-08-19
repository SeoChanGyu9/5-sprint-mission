package com.sprint.mission.discodeit.entity;

import java.io.Serializable;
import java.util.UUID;

public class Message implements Comparable<Message>, Serializable {
    private final UUID id;
    private final Long createdAt;
    private Long updatedAt;

    private UUID channelId;
    private UUID fromUserId;
    private String content;

    public Message(UUID id, Long createdAt, UUID channelId, UUID fromUserId, String content) {
        this.id = id;
        this.createdAt = createdAt;
        this.channelId = channelId;
        this.fromUserId = fromUserId;
        this.content = content;
    }

    public Message(UUID channelId, UUID fromUserId, String content) {
        this(UUID.randomUUID(), System.currentTimeMillis(), channelId, fromUserId, content);
    }

    public void update(String content) {
        this.updatedAt = System.currentTimeMillis();
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public UUID getChannelId() {
        return channelId;
    }

    public UUID getFromUserId() {
        return fromUserId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", channelId=" + channelId +
                ", fromUserId=" + fromUserId +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int compareTo(Message o) {
        return Long.compare(this.createdAt,o.createdAt);
    }
}
