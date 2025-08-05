package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileChannelRepository implements ChannelRepository {
    private final String DIRECTORY;
    private final String EXTENSTION;

    public FileChannelRepository() {
        this.DIRECTORY = "CHANNEL";
        this.EXTENSTION = ".ser";
        Path path = Paths.get(DIRECTORY);
        if (!path.toFile().exists()) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public Channel save(Channel channel) {
        Path path = Paths.get(DIRECTORY, channel.getId() + EXTENSTION);
        try (FileOutputStream fos = new FileOutputStream(path.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(channel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return channel;
    }

    @Override
    public Optional<Channel> findById(UUID id) {
        Channel channel = null;
        Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
        try (FileInputStream fis = new FileInputStream(path.toFile());
             ObjectInputStream oos = new ObjectInputStream(fis)) {
            channel = (Channel) oos.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(channel);
    }

    @Override
    public List<Channel> findAll() {
        List<Channel> userList = new ArrayList<>();
        Path path = Paths.get(DIRECTORY);
        for (File files : path.toFile().listFiles()) {
            try (FileInputStream fis = new FileInputStream(files);
                 ObjectInputStream oos = new ObjectInputStream(fis)) {
                userList.add((Channel) oos.readObject());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        return List.copyOf(userList);
    }

    @Override
    public long count() {
        Path path = Paths.get(DIRECTORY);
        try {
            return Files.list(path).count();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Channel delete(UUID id) {
        Optional<Channel> channel = this.findById(id);
        if (channel.isPresent()) {
            Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
            path.toFile().delete();
        } else {
            throw new IllegalArgumentException();
        }
        return channel.get();
    }

    @Override
    public boolean existsById(UUID id) {
        Path path = Paths.get(DIRECTORY, id.toString() + EXTENSTION);
        return Files.exists(path);
    }

    @Override
    public Channel update(Channel channel) {
        Path path = Paths.get(DIRECTORY, channel.getId() + EXTENSTION);
        try (FileOutputStream fos = new FileOutputStream(path.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(channel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channel;
    }
}
