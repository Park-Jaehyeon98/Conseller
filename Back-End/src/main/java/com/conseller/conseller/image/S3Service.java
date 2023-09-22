package com.conseller.conseller.image;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

   public String uploadFile(MultipartFile file) throws IOException {
       String fileName = file.getOriginalFilename();

       //파일 형식 구하기
       String ext = fileName.split("\\.")[1];
       String contentType = "";

       //content type을 지정해서 올려주지 않으면 자동으로 "application/octet-stream"으로 고정이 되서 링크 클릭시 웹에서 열리는게 아니라 자동 다운이 시작됨.
       switch (ext) {
           case "jpg":
           case "jpeg":
               contentType = "image/jpeg";
               break;
           case "png":
               contentType = "image/png";
               break;
           case "txt":
               contentType = "text/plain";
               break;
           case "csv":
               contentType = "text/csv";
               break;
       }

       try {
           ObjectMetadata metadata = new ObjectMetadata();
           metadata.setContentType(contentType);
           metadata.setContentLength(file.getSize());
           //multipartfile을 putObject를 통해 S3에 업로드
           //bucket 버킷 이름, fileName 파일 이름, multipartFile.getInputStream() - multipartFile을 InputStream형태로 변환
           amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), metadata)
                   .withCannedAcl(CannedAccessControlList.PublicRead));
           //위에 withCannedAcl 이 접근할 수 있도록 권한 부여.
       } catch (AmazonServiceException e) {
           e.printStackTrace();
       } catch (SdkClientException e) {
           e.printStackTrace();
       }

       return amazonS3.getUrl(bucket, fileName).toString();
   }
}
