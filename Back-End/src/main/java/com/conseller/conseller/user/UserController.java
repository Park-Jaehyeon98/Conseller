package com.conseller.conseller.user;

import com.conseller.conseller.user.dto.response.InfoValidationDto;
import com.conseller.conseller.user.dto.request.SignUpDto;
import com.conseller.conseller.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpDto signUpDto) {
        log.info("유저 회원가입 호출");
        userService.register(signUpDto);
        
        return ResponseEntity.ok()
                .build();
    }
    
    @GetMapping("/{userNickname}")
    public ResponseEntity<Object> checkNickname(@PathVariable String userNickname) {

        InfoValidationDto infoValidationDto = userService.checkNickname(userNickname);

        return ResponseEntity.ok()
                .body(infoValidationDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> checkId(@PathVariable String userId) {
        InfoValidationDto infoValidationDto = userService.checkId(userId);

        return ResponseEntity.ok()
                .body(infoValidationDto);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<Object> checkEmail(@PathVariable String userEmail) {
        InfoValidationDto infoValidationDto = userService.checkEmail(userEmail);

        return ResponseEntity.ok()
                .body(infoValidationDto);
    }

    @GetMapping("/{userPhoneNumber}")
    public ResponseEntity<Object> checkPhoneNumber(@PathVariable String userPhoneNumber) {
        InfoValidationDto infoValidationDto = userService.checkPhoneNumber(userPhoneNumber);

        return ResponseEntity.ok()
                .body(infoValidationDto);
    }
}
