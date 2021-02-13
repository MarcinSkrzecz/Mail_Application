package com.marcin.mail_application.controller;

import com.marcin.mail_application.domain.SentMessage;
import com.marcin.mail_application.domain.SentMessageDto;
import com.marcin.mail_application.mapper.SentMessageMapper;
import com.marcin.mail_application.service.SentMessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SentMessageController.class)
public class SentMessageControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SentMessageMapper mapper;
    @MockBean
    private SentMessageService service;

    @Test
    public void testGetEmptySentMessages() throws Exception {
        //Given
        List<SentMessage> sentMessageList = new ArrayList<>();
        List<SentMessageDto> sentMessageDtoList = new ArrayList<>();

        when(service.getAllSentMessages()).thenReturn(sentMessageList);
        when(mapper.mapToSentMessageDto(sentMessageList)).thenReturn(sentMessageDtoList);

        //When & Then
        mockMvc.perform(get("/api/sent_messages").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetSentMessages() throws Exception {
        //Given
        List<SentMessageDto> sentMessageDtoList = new ArrayList<>();
        sentMessageDtoList.add(new SentMessageDto(1L, "test@test.pl","testTitle",
                "testContent","123", LocalDate.now()));

        List<SentMessage> sentMessageList = new ArrayList<>();
        sentMessageList.add(new SentMessage(1L, "test@test.pl","testTitle",
                "testContent","123", LocalDate.now()));

        when(service.getAllSentMessages()).thenReturn(sentMessageList);
        when(mapper.mapToSentMessageDto(sentMessageList)).thenReturn(sentMessageDtoList);

        //When & Then
        mockMvc.perform(get("/api/sent_messages").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is("test@test.pl")))
                .andExpect(jsonPath("$[0].title", is("testTitle")))
                .andExpect(jsonPath("$[0].content", is("testContent")))
                .andExpect(jsonPath("$[0].magicNumber", is("123")));
    }

    @Test
    public void testGetMessagesByEmail() throws Exception {
        //Given
        List<SentMessage> sentMessageList = new ArrayList<>();
        sentMessageList.add(new SentMessage(1L, "TestEmail", "TestTitle",
                "TestContent", "TestMagicNumber", LocalDate.now()));
        List<SentMessageDto> sentMessageDtoList = new ArrayList<>();
        sentMessageDtoList.add(new SentMessageDto(1L, "TestEmail", "TestTitle",
                "TestContent", "TestMagicNumber", LocalDate.now()));

        when(mapper.mapToSentMessageDto(sentMessageList)).thenReturn(sentMessageDtoList);
        when(service.getSentMessagesByEmail(any())).thenReturn(sentMessageList);

        //When & Then
        mockMvc.perform(get("/api/sent_messages/{emailValue}", "TestEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", "TestEmail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is("TestEmail")))
                .andExpect(jsonPath("$[0].title", is("TestTitle")))
                .andExpect(jsonPath("$[0].content", is("TestContent")))
                .andExpect(jsonPath("$[0].magicNumber", is("TestMagicNumber")));
    }

    @Test
    public void testDeleteSentMessage() throws Exception {
        mockMvc.perform(delete("/api/sent_message/{sentMessageId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("sentMessageId", "1"))
                .andExpect(status().isOk());
    }
}

