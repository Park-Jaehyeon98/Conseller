package com.conseller.conseller.gifticon;

import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.gifticon.dto.request.GifticonRegisterRequest;
import com.conseller.conseller.gifticon.dto.response.ImageUrlsResponse;
import com.conseller.conseller.gifticon.service.GifticonService;
import com.conseller.conseller.image.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gifticon")
public class GifticonController {

    private final GifticonService gifticonService;
    private final S3Service s3Service;

    @PostMapping("/{userIdx}")
    public ResponseEntity<Void> registGifticon(@RequestBody GifticonRegisterRequest gifticonRequest
            ,@RequestPart(name = "originalFile") MultipartFile originalFile
            ,@RequestPart(name = "cropFile") MultipartFile cropFile) throws IOException {

        String allImageUrl = s3Service.uploadFile(originalFile);
        String dataImageUrl = null;

        if (!cropFile.isEmpty()) {
            s3Service.uploadFile(cropFile);
        }

        gifticonService.registGifticon(gifticonRequest, allImageUrl, dataImageUrl);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/{gifticonIdx}")
    public ResponseEntity<GifticonResponse> getGifticon(@PathVariable long gifticonIdx) {
        return ResponseEntity.ok()
                .body(gifticonService.getGifticonResponse(gifticonIdx));
    }

    @DeleteMapping("/{gifticonIdx}")
    public ResponseEntity<Void> deleteGifticon(@PathVariable long gifticonIdx) {

        //기프티콘 엔티티를 삭제하고 url을 리턴한다.
        ImageUrlsResponse urls = gifticonService.deleteGifticon(gifticonIdx);

        //기프티콘 이미지 파일을 삭제한다.
        s3Service.deleteFile(urls);

        return ResponseEntity.ok()
                .build();
    }
}
