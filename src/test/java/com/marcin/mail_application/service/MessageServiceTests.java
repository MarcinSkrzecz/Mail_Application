package com.marcin.mail_application.service;

import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.domain.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class MessageServiceTests {

    @InjectMocks
    private MessageService service;
    @Mock
    private MessageRepository repository;

    @Test
    public void testGetAllMessages() {
        //Given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        //When & Then
        assertEquals(0, service.getAllMessages().size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetMessagesByEmail() {
        //Given
        when(repository.findByEmail(any())).thenReturn(new ArrayList<>());

        //When & Then
        assertEquals(0, service.getMessagesByEmail(any()).size());
        verify(repository, times(1)).findByEmail(any());
    }

    @Test
    public void testFindMessageById() {
        //Given
        Message message = new Message(1L, "testEmail1@test.pl", "TestTitle1", "TestContent1","123");

        when(repository.findById(any())).thenReturn(Optional.of(message));

        //When
        Message searchedMessage = service.getMessageById(1L);

        //Then
        assertEquals("testEmail1@test.pl", searchedMessage.getEmail());

        verify(repository, times(2)).findById(1L);
    }

    @Test
    public void testDeleteMessage() {
        //Given
        //When
        service.deleteMessage(1L);
        //Then
        verify(repository, times(1)).deleteById(1L);
    }
}
