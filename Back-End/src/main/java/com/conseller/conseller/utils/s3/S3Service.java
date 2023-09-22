package com.conseller.conseller.utils.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Provider s3Provider;

    @Transactional
    public void createTeam(String name, MultipartFile file) {
        String url = "";
        if(file != null)  url = s3Provider.uploadFileToS3(file, "static/team-image");

    }
}
