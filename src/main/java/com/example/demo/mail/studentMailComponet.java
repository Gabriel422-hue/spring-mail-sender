package com.example.demo.mail;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import com.example.demo.mail.mailMessage.MailMessage;
import com.example.demo.model.Student;

@Component
public class studentMailComponet extends mailComponent{

    private TemplateEngine templateEngine;
    
    public studentMailComponet (JavaMailSender javaMailSender, TemplateEngine templateEngine ){
        super(javaMailSender);
        this.templateEngine = templateEngine;
    };

    public void sendSimpleWelcomeEmail(Student student) {
         MailMessage mailMessage = MailMessage.builder()
                    .to(student.getEmail())
                    .from("gabriel.victor.test@gmail.com")
                    .subject("Test de envio de email")
                    .message(String.format("Olá, %s! Espero que tenha gostado da Aula!", student.getName()))
                    .build();
                    
        this.sendSimpleMail(mailMessage);
    } 
    
    public void sendWelcomeEmail (Student student){
        
        String TemplateHTML = this.templateEngine.process(template, context)

         MailMessage mailMessage = MailMessage.builder()
                    .to(student.getEmail())
                    .from("gabriel.victor.test@gmail.com")
                    .subject("Test de envio de email")
                    .addAttachment("Apresentacao.pptx", new ClassPathResource("static/docs/Apresentacao_Spring_Mail_Thymeleaf.pptx"))
                    .bodyFiles(bodyFiles:" ", new ClassPathResource("static/images/Logo_ExpertsClub.png")
                    .message("")
                    .build();

        this.sendAvdancedMail(mailMessage);

        
    } 
}
