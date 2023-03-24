package com.internship.management.servicies;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public Boolean sendMailWithAttachment(String to, String subject, String body, String fileToAttach)throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new  MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("abu.oblie@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(fileToAttach));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        try {
            mailSender.send(mimeMessage);
            System.out.println("mail successfully send with attachment");
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }

    }

    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("abu.oblie@gmail.com");
        message.setSubject(subject);
        message.setText(body);

        try {
            mailSender.send(message);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());

        }
    }
}
