package com.conseller.conseller.utils;

import com.conseller.conseller.utils.dto.EmailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async
    public boolean sendEmail(EmailResponse emailResponse) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        String info = "<h3 style='color:blue;'> conseller 에서 새로 발급한 임시 비밀번호 입니다.</h3>"
                + "<p>" + emailResponse.getUserName() + "님의 임시 비밀번호는 " + emailResponse.getUserPassword() + "입니다.</p>"
                +"<br>"
                +"<p>로그인 후 개인정보 변경에서 비밀번호를 변경해주세요.</p>";

        mimeMessageHelper.setTo(emailResponse.getUserEmail());
        mimeMessageHelper.setText(info, true);
        mimeMessageHelper.setSubject("임시 비밀번호 발급");
        mimeMessageHelper.setFrom("ekclstkfka@gmail.com");

        log.info("send email to " + emailResponse.getUserName());

        try {
            javaMailSender.send(message);
            log.info("successfully sent email to " + emailResponse.getUserName());
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

}
