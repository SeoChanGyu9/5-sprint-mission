package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.request.AuthLoginRequest;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("basicAuthService")
@RequiredArgsConstructor
public class BasicAuthService implements AuthService {
    private final UserRepository userRepository;

    @Override
    public User login(AuthLoginRequest request) {
        User user = userRepository.findByUsername(request.username());
        if(user != null){
            if(user.getPassword().equals(request.password())){
                return user;
            }
        }
        return null;
    }
}
