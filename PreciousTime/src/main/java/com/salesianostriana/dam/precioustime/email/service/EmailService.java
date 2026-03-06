package com.salesianostriana.dam.precioustime.email.service;

import com.salesianostriana.dam.precioustime.email.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(EmailDTO dto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(dto.receiver());
        helper.setSubject(dto.title());

        Context context = new Context();
        context.setVariable("message", dto.message());
        String contentHTML = templateEngine.process("email", context);

        helper.setText(contentHTML, true);
        javaMailSender.send(message);
    }

}
