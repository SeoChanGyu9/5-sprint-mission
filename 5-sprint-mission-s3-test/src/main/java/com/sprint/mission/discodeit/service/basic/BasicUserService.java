package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.request.UserCreateRequest;
import com.sprint.mission.discodeit.dto.request.UserFindRequest;
import com.sprint.mission.discodeit.dto.request.UserUpdateRequest;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.repository.file.FileUserStatusRepository;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service("basicUserService")
@RequiredArgsConstructor
public class BasicUserService implements UserService {
    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final BinaryContentRepository binaryContentRepository;

    @Override
    public User create(UserCreateRequest userDTO) {
//[ ] DTO를 활용해 파라미터를 그룹화합니다.
//      유저를 등록하기 위해 필요한 파라미터, 프로필 이미지를 등록하기 위해 필요한 파라미터 등

//[ ] username과 email은 다른 유저와 같으면 안됩니다.
        if(userRepository.findByUsername(userDTO.username()) != null){
            throw new IllegalArgumentException("Username already exists");
        }
        if(userRepository.findByEmail(userDTO.email()) != null){
            throw new IllegalArgumentException("Email already exists");
        }

        User user;
//[ ] 선택적으로 프로필 이미지를 같이 등록할 수 있습니다.
        if(userDTO.profileId() != null){
            user = new User(userDTO.username(), userDTO.email(), userDTO.password(),userDTO.profileId());
        }else{
            user = new User(userDTO.username(), userDTO.email(), userDTO.password());
        }

        //[ ] UserStatus를 같이 생성합니다.
        UserStatus userStatus = new UserStatus(user.getId());
        userStatusRepository.save(userStatus);

        return userRepository.save(user);
    }

    @Override
    public UserFindRequest find(UUID userId) {
//        DTO를 활용하여:
//[ ] 사용자의 온라인 상태 정보를 같이 포함하세요.
//[ ] 패스워드 정보는 제외하세요.
        //return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
        UserStatus userStatus = userStatusRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("UserStatus with id " + userId + " not found"));

        return new UserFindRequest(user.getId(), user.getUsername(), user.getEmail(), user.getProfileId(), userStatus.isOnline());
    }

    @Override
    public List<UserFindRequest> findAll() {
        //        DTO를 활용하여:
//[ ] 사용자의 온라인 상태 정보를 같이 포함하세요.
//[ ] 패스워드 정보는 제외하세요.
//        return userRepository.findAll();

        List<User> userList = userRepository.findAll();
        List<UserFindRequest> userFindRequestList = new ArrayList<>();
        for(User user : userList){
            UserStatus userStatus = userStatusRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("UserStatus with id " + user.getId() + " not found"));
            userFindRequestList.add(new UserFindRequest(user.getId(), user.getUsername(), user.getEmail(), user.getProfileId(), userStatus.isOnline()));
        }
        return userFindRequestList;

    }

    @Override
    public User update(UserUpdateRequest userUpdateRequest) {
//        [ ] 선택적으로 프로필 이미지를 대체할 수 있습니다.
//[ ] DTO를 활용해 파라미터를 그룹화합니다.
//        수정 대상 객체의 id 파라미터, 수정할 값 파라미터
        User user = userRepository.findById(userUpdateRequest.userId())
                .orElseThrow(() -> new NoSuchElementException("User with id " + userUpdateRequest.userId() + " not found"));
        user.update(userUpdateRequest.newUsername(), userUpdateRequest.newEmail(), userUpdateRequest.newPassword(), userUpdateRequest.newProfileId());
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID userId, UUID userStatusId, UUID userProfileId) {
//        [ ] 관련된 도메인도 같이 삭제합니다.
//        BinaryContent(프로필), UserStatus
        if (!userRepository.existsById(userId)) {
            throw new NoSuchElementException("User with id " + userId + " not found");
        }
        userRepository.deleteById(userId);

        if (!userStatusRepository.existsById(userStatusId)) {
            throw new NoSuchElementException("UserStatus with id " + userStatusId + " not found");
        }
        userStatusRepository.deleteById(userStatusId);

        if (!binaryContentRepository.existsById(userProfileId)) {
            throw new NoSuchElementException("BinaryContent with id " + userProfileId + " not found");
        }
        binaryContentRepository.deleteById(userProfileId);

    }
}
