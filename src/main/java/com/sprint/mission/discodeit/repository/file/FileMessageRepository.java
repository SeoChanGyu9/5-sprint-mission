package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileMessageRepository implements MessageRepository {
    private final String DIRECTORY;
    private final String EXTENSTION;

    public FileMessageRepository() {
        this.DIRECTORY = "MESSAGE";
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
    public Message save(Message message) {
        Path path = Paths.get(DIRECTORY, message.getId() + EXTENSTION);
        try(FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(message);
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public Optional<Message> findById(UUID id) {
        Message message = null;
        Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
        try(FileInputStream fis = new FileInputStream(path.toFile());
            ObjectInputStream oos = new ObjectInputStream(fis)){
            message = (Message)oos.readObject();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(message);
    }

    @Override
    public List<Message> findAll() {
        List<Message> messageList = new ArrayList<>();
        Path path = Paths.get(DIRECTORY);
        for(File files : path.toFile().listFiles()){
            try(FileInputStream fis = new FileInputStream(files);
                ObjectInputStream oos = new ObjectInputStream(fis)){
                messageList.add((Message)oos.readObject());
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }

        return List.copyOf(messageList);
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

    @Override
    public Message delete(UUID id) {
        Optional<Message> message = this.findById(id);
        if(message.isPresent()) {
            Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
            path.toFile().delete();
        } else{
            throw new IllegalArgumentException();
        }
        return message.get();
    }

    @Override
    public boolean existsById(UUID id) {
        Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
        return Files.exists(path);
    }

    @Override
    public Message update(Message message) {
        Path path = Paths.get(DIRECTORY, message.getId() + EXTENSTION);
        try(FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(message);
        } catch(Exception e){
            e.printStackTrace();
        }
        return message;
    }
}
