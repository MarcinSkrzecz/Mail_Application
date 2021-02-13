package com.marcin.mail_application.mapper;

import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.domain.MessageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MessageMapperTests {

    @InjectMocks
    private MessageMapper mapper;

    @Test
    public void mapToMessageTest() {
        //Given
        MessageDto messageDto = new MessageDto(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");
        //When
        Message message = mapper.mapToMessage(messageDto);
        //Then
        assertEquals(messageDto.getId(), message.getId());
        assertEquals(messageDto.getEmail(), message.getEmail());
        assertEquals(messageDto.getTitle(), messageDto.getTitle());
        assertEquals(messageDto.getContent(), message.getContent());
        assertEquals(messageDto.getMagicNumber(), message.getMagicNumber());
    }

    @Test
    public void mapToMessageDtoTest() {
        //Given
        Message message = new Message(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");
        //When
        MessageDto messageDto = mapper.mapToMessageDto(message);
        //Then
        assertEquals(messageDto.getId(), message.getId());
        assertEquals(messageDto.getEmail(), message.getEmail());
        assertEquals(messageDto.getTitle(), messageDto.getTitle());
        assertEquals(messageDto.getContent(), message.getContent());
        assertEquals(messageDto.getMagicNumber(), message.getMagicNumber());
    }

    @Test
    public void mapToMessageDtoListTest() {
        //Given
        Message message1 = new Message(1L, "TestEmail1", "TestTitle1", "TestContent1", "TestMagicNumber1");
        Message message2 = new Message(2L, "TestEmail2", "TestTitle2", "TestContent2", "TestMagicNumber2");
        List<Message> messageList = new ArrayList<>();
        messageList.add(message1);
        messageList.add(message2);
        //When
        List<MessageDto> messageDtoList = mapper.mapToMessageDto(messageList);
        //Then
        assertEquals(messageList.get(0).getId(), messageDtoList.get(0).getId());
        assertEquals(messageList.get(0).getEmail(), messageDtoList.get(0).getEmail());
        assertEquals(messageList.get(0).getTitle(), messageDtoList.get(0).getTitle());
        assertEquals(messageList.get(0).getContent(), messageDtoList.get(0).getContent());
        assertEquals(messageList.get(0).getMagicNumber(), messageDtoList.get(0).getMagicNumber());

        assertEquals(messageList.get(1).getId(), messageDtoList.get(1).getId());
        assertEquals(messageList.get(1).getEmail(), messageDtoList.get(1).getEmail());
        assertEquals(messageList.get(1).getTitle(), messageDtoList.get(1).getTitle());
        assertEquals(messageList.get(1).getContent(), messageDtoList.get(1).getContent());
        assertEquals(messageList.get(1).getMagicNumber(), messageDtoList.get(1).getMagicNumber());

        assertEquals(messageList.size(), messageDtoList.size());
    }
}
