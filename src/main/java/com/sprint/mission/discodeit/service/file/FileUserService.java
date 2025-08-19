package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class FileUserService implements UserService {

    UserRepository repo;

    public FileUserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User create(String username, String password, UserStatus status) {
        User user = new User(username, password, status);
        return repo.save(user);
    }

    @Override
    public User find(UUID id) {
        User user = repo.findById(id).orElse(null);
        if(user == null){
            throw new NoSuchElementException("User with id " + id + " not found");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public boolean delete(UUID userId, String password) {
        User deleteUser = repo.delete(userId);
        System.out.println("userId: "+deleteUser.getId() + " deleted");
        return true;
    }

    @Override
    public boolean update(UUID userId, String username, String odPwd, String nwPwd, UserStatus status) {
        User updateUser = this.find(userId);
        if(updateUser == null){
            throw new NoSuchElementException("User with id " + userId + " not found");
        }
        if(updateUser.getPassword().equals(odPwd)){
            updateUser.update(username, nwPwd, status);
            repo.update(updateUser);
            return true;
        } else{
            throw new IllegalStateException("Passwords do not match");
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return repo.existsById(id);
    }

    public long count(){
        return repo.count();
    }
}
