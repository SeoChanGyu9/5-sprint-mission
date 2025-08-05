package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.service.UserService;

import java.util.*;

public class JCFUserService implements UserService {

    private final Map<UUID, User> users;
    private final static UserService jcfUs = new JCFUserService();

    private JCFUserService() {
        this.users = new HashMap<>();
    }

    @Override
    public User create(String username, String password, UserStatus status) {
        User newUser = new User(username, password, status);
        users.put(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public User find(UUID id) {
        if(users.containsKey(id)){
            return users.get(id);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(users.values());
    }

    @Override
    public boolean update(UUID userId, String username, String odPwd, String nwPwd, UserStatus status) {
        if(users.containsKey(userId)) {
            if(users.get(userId).getPassword().equals(odPwd)) {
                users.get(userId).update(username, nwPwd, status);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(UUID userId, String password) {
        if(users.containsKey(userId)) {
            if(users.get(userId).getPassword().equals(password)) {
                users.remove(userId);
                return true;
            }
        }
        return false;
    }

    public static UserService getInstance(){
        return jcfUs;
    }

    @Override
    public boolean existsById(UUID id) {
        return users.containsKey(id);
    }

    @Override
    public long count() {
        return users.size();
    }
}
