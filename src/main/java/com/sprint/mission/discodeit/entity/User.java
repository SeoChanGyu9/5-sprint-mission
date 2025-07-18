package com.sprint.mission.discodeit.entity;

import java.util.StringJoiner;
import java.util.UUID;

public class User implements Comparable<User>{
    private UUID id;
    private Long createdAt; //Long은 null이 존재할수있음
    private Long updatedAt;

    private String username;
    private String password;
    private UserStatus status;

    public User(String username, String password, UserStatus status) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();

        this.username = username;
        this.password = password;
        this.status = status;
    }

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
//update

    public void update(String username, String password, UserStatus status) {
        this.updatedAt = System.currentTimeMillis();
        this.username = username;
        this.password = password;
        this.status = status;
    }
}
