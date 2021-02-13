package com.marcin.mail_application.service;

import com.marcin.mail_application.domain.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void testSendEmail() {
        //Given
        Message message = new Message(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(message.getEmail());
        mailMessage.setSubject(message.getTitle());
        mailMessage.setText(message.getContent());

        //When
        emailService.send(message);

        //Then
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
