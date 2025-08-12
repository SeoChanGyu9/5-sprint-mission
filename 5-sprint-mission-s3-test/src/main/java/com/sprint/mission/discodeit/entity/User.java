package com.sprint.mission.discodeit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    //
    private String username;
    private String email;
    @JsonIgnore //JSON 직렬화시 응답에서 제외됨
    private String password;

    private UUID profileId; // 프로필 이미지 아이디?


    public User(String username, String email, String password) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        //
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String username, String email, String password, UUID profileId) {
//        this.id = UUID.randomUUID();
//        this.createdAt = Instant.now();
//        //
//        this.username = username;
//        this.email = email;
//        this.password = password;
        this(username, email, password);
        this.profileId = profileId;
    }


    public void update(String newUsername, String newEmail, String newPassword, UUID newProfileId) {
        boolean anyValueUpdated = false;
        if (newUsername != null && !newUsername.equals(this.username)) {
            this.username = newUsername;
            anyValueUpdated = true;
        }
        if (newEmail != null && !newEmail.equals(this.email)) {
            this.email = newEmail;
            anyValueUpdated = true;
        }
        if (newPassword != null && !newPassword.equals(this.password)) {
            this.password = newPassword;
            anyValueUpdated = true;
        }

        if(newProfileId != null && !newProfileId.equals(this.profileId)) {
            this.profileId = newProfileId;
        }

        if (anyValueUpdated) {
            this.updatedAt = Instant.now();
        }

    }
}
