package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message implements Comparable<Message>{
    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    private UUID channelId;
    private UUID fromId;
    private String content;

    public Message(UUID channelId, UUID fromId, String content) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();

        this.channelId = channelId;
        this.fromId = fromId;
        this.content = content;
        //Date currentDate = new Date(createdAt);
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

    public UUID getFromId() {
        return fromId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", channelId='").append(channelId).append('\'');
        sb.append(", fromId='").append(fromId).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Message o) {
        return Long.compare(this.createdAt,o.createdAt);
    }
}
