package com.conseller.conseller.user;

import com.conseller.conseller.user.dto.response.InfoValidationRequest;
import com.conseller.conseller.user.dto.request.SignUpRequest;
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
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("유저 회원가입 호출");
        userService.register(signUpRequest);
        
        return ResponseEntity.ok()
                .build();
    }
    
    @GetMapping("/{userNickname}")
    public ResponseEntity<Object> checkNickname(@PathVariable String userNickname) {

        InfoValidationRequest infoValidationRequest = userService.checkNickname(userNickname);

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> checkId(@PathVariable String userId) {
        InfoValidationRequest infoValidationRequest = userService.checkId(userId);

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<Object> checkEmail(@PathVariable String userEmail) {
        InfoValidationRequest infoValidationRequest = userService.checkEmail(userEmail);

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }

    @GetMapping("/{userPhoneNumber}")
    public ResponseEntity<Object> checkPhoneNumber(@PathVariable String userPhoneNumber) {
        InfoValidationRequest infoValidationRequest = userService.checkPhoneNumber(userPhoneNumber);

        return ResponseEntity.ok()
                .body(infoValidationRequest);
    }
}
