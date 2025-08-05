package com.sprint.mission.discodeit.entity;

import java.io.Serializable;
import java.util.UUID;

public class Channel implements Comparable<Channel>, Serializable {
    private final UUID id;
    private final Long createdAt;
    private Long updatedAt;

    private String channelName;
    private String creatorId;
    private String password;
    private ChannelStatus status; //PUBLIC, PRIVATE

    public Channel(UUID id, Long createdAt, String channelName, String creatorId, String password, ChannelStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.channelName = channelName;
        this.creatorId = creatorId;
        this.password = password;
        this.status = status;
    }

    public Channel(String channelName, String creatorId, String password, ChannelStatus status) {
        this(UUID.randomUUID(), System.currentTimeMillis(), channelName, creatorId, password, status);
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

    public String getChannelName() {
        return channelName;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getPassword() {
        return password;
    }

    public ChannelStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", channelName='" + channelName + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void update(String channelName, String password, ChannelStatus status) {
        this.updatedAt = System.currentTimeMillis();
        this.channelName = channelName;
        this.password = password;
        this.status = status;
    }

    @Override
    public int compareTo(Channel o) {
        return channelName.compareTo(o.channelName);
    }
}
