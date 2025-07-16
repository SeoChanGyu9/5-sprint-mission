package com.sprint.mission.discodeit.entity;

import java.util.List;
import java.util.UUID;

public class Channel implements Comparable<Channel>{
    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    private String channelName;
    private String creatorId;
    private String password;

    public Channel(String channelName, String creatorId, String password) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();

        this.channelName = channelName;
        this.creatorId = creatorId;
        this.password = password;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Channel{");
        sb.append("id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", channelName='").append(channelName).append('\'');
        sb.append(", creatorId='").append(creatorId).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void update(String channelName, String password) {
        this.updatedAt = System.currentTimeMillis();
        this.channelName = channelName;
        this.password = password;
    }

    @Override
    public int compareTo(Channel o) {
        return channelName.compareTo(o.channelName);
    }
}
