package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.request.UserCreateRequest;
import com.sprint.mission.discodeit.dto.request.UserFindRequest;
import com.sprint.mission.discodeit.dto.request.UserUpdateRequest;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    //User create(String username, String email, String password);
    User create(UserCreateRequest userDTO);
    UserFindRequest find(UUID userId);
    List<UserFindRequest> findAll();
    User update(UserUpdateRequest userUpdateRequest);
    void delete(UUID userId, UUID userStatusId, UUID userProfileId);
}
