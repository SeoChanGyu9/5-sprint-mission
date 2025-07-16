package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;

import java.util.List;

//도메인 모델 별 CRUD(생성, 읽기, 모두 읽기, 수정, 삭제)
// 기능을 인터페이스로 선언하세요.
public interface UserService {
    void create(String userId, String username, String password, UserStatus status);
    List<User> find(String username);
    List<User> findAll();
    boolean update(String userId, String username, String odPwd, String nwPwd, UserStatus status);
    boolean delete(String userId, String password);

}
