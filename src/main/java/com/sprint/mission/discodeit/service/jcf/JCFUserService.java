package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.service.UserService;

import java.util.*;

public class JCFUserService implements UserService {

    private final Map<String, User> users;

    public JCFUserService() {
        this.users = new HashMap<>();
    }

    @Override
    public void create(String userId, String username, String password, UserStatus status) {
        User newUser = new User(userId, username, password, status);
        users.put(userId, newUser);
    }

    @Override
    public List<User> find(String username) {
        List<User> searchedUser = new ArrayList<>();
        for(Map.Entry<String, User> entry : users.entrySet()) {
            if(entry.getValue().getUsername().contains(username)) {
                searchedUser.add(entry.getValue());
            }
        }
        return searchedUser;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean update(String userId, String username, String odPwd, String nwPwd, UserStatus status) {
        if(users.containsKey(userId)) {
            if(users.get(userId).getPassword().equals(odPwd)) {
                users.get(userId).update(username, nwPwd, status);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String userId, String password) {
        if(users.containsKey(userId)) {
            if(users.get(userId).getPassword().equals(password)) {
                users.remove(userId);
                return true;
            }
        }
        return false;
    }
}
