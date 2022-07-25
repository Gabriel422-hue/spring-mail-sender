package com.example.demo.mail;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Slf4j
@AllArgsConstructor

public abstract class mailComponent {

    private JavaMailSender javaMailSender;

    protected void sendSimpleMail(mailMessage.MailMessage mailMessage) {
        log.info("Send email.");

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                 simpleMailMessage.setTo(mailMessage.getTo());
                 simpleMailMessage.setFrom(mailMessage.getFrom());
                 simpleMailMessage.setSubject(mailMessage.getSubject());
                 simpleMailMessage.setText(mailMessage.getMessage());

                 this.javaMailSender.send(simpleMailMessage);
                 log.info("Simple email sent successfully");

        } catch (Exception e) {
            log.error("Error when tried to send the email");

        }
    }
    protected void sendAvdancedMail(mailMessage.MailMessage mailMessage) {
        log.info("Send email.");

        try {
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setFrom(mailMessage.getFrom(), "Gabriel Victor");
                helper.setTo(mailMessage.getTo());
                helper.setSubject(mailMessage.getSubject());
                helper.setText(mailMessage.getMessage(), true);

            for (Map.Entry<String, ClassPathResource> map : mailMessage.getAttachments().entrySet()) {
                helper.addAttachment(map.getKey(), map.getValue());
            }

            for (Map.Entry<String, ClassPathResource> map : mailMessage.getBodyFiles().entrySet()) {
                helper.addInline(map.getKey(), map.getValue());
            }

            this.javaMailSender.send(mimeMessage);
            log.info("Advanced email sent successfully.");

        } catch (Exception e) {
            log.error("Error when tried to send the email");

        }
    }

    }



