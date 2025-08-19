package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class FileMessageService implements MessageService {
    MessageRepository repo;

    public FileMessageService(MessageRepository repo) {
        this.repo = repo;
    }

    @Override
    public Message create(UUID channelId, UUID fromId, String content) {
        Message message = new Message(channelId, fromId, content);
        return repo.save(message);
    }

    @Override
    public Message find(UUID messageId) {
        Message message = repo.findById(messageId).orElse(null);
        if(message == null){
            throw new NoSuchElementException("Message with id " + messageId + " not found");
        }
        return message;
    }

    @Override
    public List<Message> findAll() {
        return repo.findAll();

    }

    @Override
    public boolean update(UUID id, String content) {
        Message updateMessage = this.find(id);
        if(updateMessage == null){
            throw new NoSuchElementException("Message with id " + id + " not found");
        }
        updateMessage.update(content);
        repo.update(updateMessage);
        return true;

    }

    @Override
    public boolean delete(UUID id) {
        Message deleteMessage = repo.delete(id);
        System.out.println("messageId: "+deleteMessage.getId() + " deleted");
        return true;
    }

    @Override
    public boolean existsById(UUID id) {
        return repo.existsById(id);

    }

    @Override
    public long count() {
        return repo.count();
    }
}
