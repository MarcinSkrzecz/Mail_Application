package com.marcin.mail_application.service;

import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.domain.SentMessage;
import com.marcin.mail_application.domain.SentMessageRepository;
import com.marcin.mail_application.mapper.SentMessageMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SentMessageServiceTests {

    @InjectMocks
    private SentMessageService service;
    @Mock
    private SentMessageRepository repository;
    @Mock
    private SentMessageMapper mapper;

    @Test
    public void testGetAllSentMessages() throws Exception {
        //Given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        //When & Then
        assertEquals(0, service.getAllSentMessages().size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetSentMessagesByEmail() throws Exception {
        //Given
        when(repository.findByEmail(any())).thenReturn(new ArrayList<>());

        //When & Then
        assertEquals(0, service.getSentMessagesByEmail(any()).size());
        verify(repository, times(1)).findByEmail(any());
    }

    @Test
    public void testCreateMessage() throws Exception{
        //Given
        Message message = new Message("TestEmail@gmail.com", "TestTitle", "TestContent", "123");
        SentMessage sentMessage = new SentMessage("TestEmail@gmail.com", "TestTitle", "TestContent", "123");

        when(mapper.mapToSentMessage(any())).thenReturn(sentMessage);

        //When
        service.createSentMessage(message);
        //Then
        verify(repository, times(1)).save(sentMessage);
    }

    @Test
    public void testDeleteSentMessage() {
        //Given
        //When
        service.deleteSentMessage(1L);
        //Then
        verify(repository, times(1)).deleteById(1L);
    }
}
