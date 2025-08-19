package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileUserRepository implements UserRepository {
    private final String DIRECTORY;
    private final String EXTENSTION;

    public FileUserRepository() {
        this.DIRECTORY = "USER";
        this.EXTENSTION = ".ser";
        Path path = Paths.get(DIRECTORY);
        if(!path.toFile().exists()) {
            try {
                Files.createDirectories(path);
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public User save(User user) {
        Path path = Paths.get(DIRECTORY, user.getId() + EXTENSTION);
        try(FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(user);
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        User user = null;
        Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
        try(FileInputStream fis = new FileInputStream(path.toFile());
        ObjectInputStream oos = new ObjectInputStream(fis)){
            user = (User)oos.readObject();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        Path path = Paths.get(DIRECTORY);
        for(File files : path.toFile().listFiles()){
            try(FileInputStream fis = new FileInputStream(files);
                ObjectInputStream oos = new ObjectInputStream(fis)){
                userList.add((User)oos.readObject());
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }

        return List.copyOf(userList);
    }

    @Override
    public User delete(UUID id) {
        Optional<User> user = this.findById(id);
        if(user.isPresent()) {
            Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
            path.toFile().delete();
        } else{
            throw new IllegalArgumentException();
        }
        return user.get();
    }

    @Override
    public User update(User user) {
        Path path = Paths.get(DIRECTORY, user.getId() + EXTENSTION);
        try(FileOutputStream fos = new FileOutputStream(path.toFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(user);
        } catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean existsById(UUID id) {
        Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
        return Files.exists(path);
    }

    @Override
    public long count() {
        Path path = Paths.get(DIRECTORY);
        try{
            return Files.list(path).count();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
