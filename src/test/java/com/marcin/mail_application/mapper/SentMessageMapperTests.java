package com.marcin.mail_application.mapper;

import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.domain.SentMessage;
import com.marcin.mail_application.domain.SentMessageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SentMessageMapperTests {

    @InjectMocks
    private SentMessageMapper mapper;

    @Test
    public void mapToSentMessageTest() {
        //Given
        Message message = new Message(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");
        //When
        SentMessage sentMessage = mapper.mapToSentMessage(message);
        //Then
        assertEquals(message.getEmail(), sentMessage.getEmail());
        assertEquals(message.getTitle(), sentMessage.getTitle());
        assertEquals(message.getContent(), sentMessage.getContent());
        assertEquals(message.getMagicNumber(), sentMessage.getMagicNumber());
    }

    @Test
    public void mapToSentMessageDtoTest() {
        //Given
        SentMessage sentMessage = new SentMessage(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber", LocalDate.now());
        //When
        SentMessageDto sentMessageDto = mapper.mapToSentMessageDto(sentMessage);
        //Then
        assertEquals(sentMessage.getId(), sentMessageDto.getId());
        assertEquals(sentMessage.getEmail(), sentMessageDto.getEmail());
        assertEquals(sentMessage.getTitle(), sentMessageDto.getTitle());
        assertEquals(sentMessage.getContent(), sentMessageDto.getContent());
        assertEquals(sentMessage.getMagicNumber(), sentMessageDto.getMagicNumber());
        assertEquals(sentMessage.getWhenWasSent(), sentMessageDto.getWhenWasSent());
    }

    @Test
    public void mapToMessageDtoListTest() {
        //Given
        SentMessage sentMessage1 = new SentMessage(1L, "TestEmail1", "TestTitle1", "TestContent1", "TestMagicNumber1", LocalDate.now());
        SentMessage sentMessage2 = new SentMessage(2L, "TestEmail2", "TestTitle2", "TestContent2", "TestMagicNumber2", LocalDate.now());
        List<SentMessage> sentMessageList = new ArrayList<>();
        sentMessageList.add(sentMessage1);
        sentMessageList.add(sentMessage2);
        //When
        List<SentMessageDto> sentMessageDtoList = mapper.mapToSentMessageDto(sentMessageList);
        //Then
        assertEquals(sentMessageList.get(0).getId(), sentMessageDtoList.get(0).getId());
        assertEquals(sentMessageList.get(0).getEmail(), sentMessageDtoList.get(0).getEmail());
        assertEquals(sentMessageList.get(0).getTitle(), sentMessageDtoList.get(0).getTitle());
        assertEquals(sentMessageList.get(0).getContent(), sentMessageDtoList.get(0).getContent());
        assertEquals(sentMessageList.get(0).getMagicNumber(), sentMessageDtoList.get(0).getMagicNumber());
        assertEquals(sentMessageList.get(0).getWhenWasSent(), sentMessageDtoList.get(0).getWhenWasSent());

        assertEquals(sentMessageList.get(1).getId(), sentMessageDtoList.get(1).getId());
        assertEquals(sentMessageList.get(1).getEmail(), sentMessageDtoList.get(1).getEmail());
        assertEquals(sentMessageList.get(1).getTitle(), sentMessageDtoList.get(1).getTitle());
        assertEquals(sentMessageList.get(1).getContent(), sentMessageDtoList.get(1).getContent());
        assertEquals(sentMessageList.get(1).getMagicNumber(), sentMessageDtoList.get(1).getMagicNumber());
        assertEquals(sentMessageList.get(1).getWhenWasSent(), sentMessageDtoList.get(1).getWhenWasSent());

        assertEquals(sentMessageList.size(), sentMessageDtoList.size());
    }
}
