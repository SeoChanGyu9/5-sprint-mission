package com.codeit.rest.Controller;


import com.codeit.rest.dto.common.ApiResponse;
import com.codeit.rest.dto.user.UserCreateRequest;
import com.codeit.rest.entity.User;
import com.codeit.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // @RequestBody : 대상 객체가 json, xml로 구성되었을때 자동으로 파싱해주는 어노테이션
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody UserCreateRequest user) {
        User createdUser = userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(createdUser, "사용자가 정상적으로 생성되었습니다."));
    }

    //@PathVariable: 경로상의 식별자가 있는 경우 매개변수로 가져오는 어노테이션
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(userService.findById(id)));
    }

}
