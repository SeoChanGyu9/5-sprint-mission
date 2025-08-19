package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;

import java.util.List;
import java.util.UUID;

//도메인 모델 별 CRUD(생성, 읽기, 모두 읽기, 수정, 삭제)
// 기능을 인터페이스로 선언하세요.
public interface UserService {
    User create(String username, String password, UserStatus status);
    User find(UUID id);
    List<User> findAll();
    boolean update(UUID userId, String username, String odPwd, String nwPwd, UserStatus status);
    boolean delete(UUID userId, String password);
    boolean existsById(UUID id);
    long count();
}
