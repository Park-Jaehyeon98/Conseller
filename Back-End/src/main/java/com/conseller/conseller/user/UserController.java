package com.conseller.conseller.user;

import com.conseller.conseller.user.dto.SignUpDto;
import com.conseller.conseller.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpDto signUpDto) {

        userService.register(signUpDto);

        return ResponseEntity.ok()
                .build();
    }
}
