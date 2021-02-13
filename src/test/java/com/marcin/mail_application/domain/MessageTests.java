package com.marcin.mail_application.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MessageTests {
    @Autowired
    private MessageRepository repository;

    @Test
    public void testCreateUpdateDeleteMessage() {
        //Given
        Message message = new Message(1L, "testEmail1@test.pl", "TestTitle1", "TestContent1","123");
        //When
        repository.save(message);
        //Then
        assertTrue(repository.findById(message.getId()).isPresent());
        //Update
        message.setEmail("testEmail2@test.pl");
        message.setTitle("TestTitle2");
        message.setContent("TestContent2");
        message.setMagicNumber("321");
        repository.save(message);
        //Then
        assertTrue(repository.findById(message.getId()).isPresent());
        assertEquals(message.getEmail(), "testEmail2@test.pl");
        assertEquals(message.getTitle(), "TestTitle2");
        assertEquals(message.getContent(), "TestContent2");
        assertEquals(message.getMagicNumber(), "321");
        //CleanUp
        repository.deleteById(message.getId());
        assertFalse(repository.findById(message.getId()).isPresent());
    }
}
