package com.marcin.mail_application.controller;

import com.google.gson.Gson;
import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.domain.MessageDto;
import com.marcin.mail_application.mapper.MessageMapper;
import com.marcin.mail_application.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageMapper mapper;
    @MockBean
    private MessageService service;

    @Test
    public void testGetEmptyMessages() throws Exception {
        //Given
        List<Message> messageList = new ArrayList<>();
        List<MessageDto> messageDtoList = new ArrayList<>();

        when(service.getAllMessages()).thenReturn(messageList);
        when(mapper.mapToMessageDto(messageList)).thenReturn(messageDtoList);

        //When & Then
        mockMvc.perform(get("/api/messages").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetMessages() throws Exception {
        //Given
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber"));
        List<MessageDto> messageDtoList = new ArrayList<>();
        messageDtoList.add(new MessageDto(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber"));

        when(service.getAllMessages()).thenReturn(messageList);
        when(mapper.mapToMessageDto(messageList)).thenReturn(messageDtoList);

        //When & Then
        mockMvc.perform(get("/api/messages")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is("TestEmail")))
                .andExpect(jsonPath("$[0].title", is("TestTitle")))
                .andExpect(jsonPath("$[0].content", is("TestContent")))
                .andExpect(jsonPath("$[0].magicNumber", is("TestMagicNumber")));
    }

    @Test
    public void testGetMessageById() throws Exception {
        //Given
        MessageDto messageDto = new MessageDto(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");
        Message message = new Message(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");

        when(service.getMessageById(any())).thenReturn(message);
        when(mapper.mapToMessageDto(message)).thenReturn(messageDto);

        //When & Then
        mockMvc.perform(get("/api/message/{messageId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("messageId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("email", is("TestEmail")))
                .andExpect(jsonPath("title", is("TestTitle")))
                .andExpect(jsonPath("content", is("TestContent")))
                .andExpect(jsonPath("magicNumber", is("TestMagicNumber")));
    }

    @Test
    public void testGetMessagesByEmail() throws Exception {
        //Given
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber"));
        List<MessageDto> messageDtoList = new ArrayList<>();
        messageDtoList.add(new MessageDto(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber"));

        when(mapper.mapToMessageDto(messageList)).thenReturn(messageDtoList);
        when(service.getMessagesByEmail(any())).thenReturn(messageList);

        //When & Then
        mockMvc.perform(get("/api/messages/{emailValue}", "TestEmail")
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
    public void testCreateMessage() throws Exception {
        //Given
        MessageDto messageDto = new MessageDto(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");
        Message message = new Message(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");

        when(mapper.mapToMessageDto(message)).thenReturn(messageDto);
        when(mapper.mapToMessage(messageDto)).thenReturn(message);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(messageDto);

        //When & Then
        mockMvc.perform(post("/api/message")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMessage() throws Exception {
        //Given
        MessageDto messageDto = new MessageDto(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");
        Message message = new Message(1L, "TestEmail", "TestTitle", "TestContent", "TestMagicNumber");

        when(mapper.mapToMessageDto(message)).thenReturn(messageDto);
        when(mapper.mapToMessage(messageDto)).thenReturn(message);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(messageDto);

        //When & Then
        mockMvc.perform(put("/api/message")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMessage() throws Exception {
        mockMvc.perform(delete("/api/message/{messageId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("messageId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSendMessages() throws Exception {
        mockMvc.perform(post("/api/send/{magicNumber}", "test@test.pl")
                .contentType(MediaType.APPLICATION_JSON)
                .param("magicNumber", "magicNumber"))
                .andExpect(status().isOk());
    }
}

