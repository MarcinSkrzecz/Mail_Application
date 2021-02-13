package com.marcin.mail_application.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SentMessageTests {
    @Autowired
    private SentMessageRepository repository;

    @Test
    public void testCreateDeleteMessage() {
        //Given
        SentMessage message = new SentMessage(1L, "testEmail1@test.pl", "TestTitle1", "TestContent1","123", LocalDate.now());
        //When
        repository.save(message);
        //Then
        assertTrue(repository.findById(message.getId()).isPresent());
        //CleanUp
        repository.deleteById(message.getId());
        assertFalse(repository.findById(message.getId()).isPresent());
    }
}

