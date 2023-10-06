package com.conseller.conseller.image;

import com.conseller.conseller.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final S3Service s3Service;
    private final UserService userService;

    @PostMapping("/{userIdx}/profile")
    public ResponseEntity<Void> uploadUserProfile(@PathVariable Long userIdx, @RequestPart("file") MultipartFile file) throws IOException {
        //S3 서버에 이미지를 업로드 한다.
        String url = s3Service.uploadFile(file);

        //url을 저장한다.
        userService.uploadProfile(userIdx, url);

        return ResponseEntity.ok().build();
    }

}
