package com.marcin.mail_application.controller;

import com.marcin.mail_application.domain.SentMessageDto;
import com.marcin.mail_application.mapper.SentMessageMapper;
import com.marcin.mail_application.service.SentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SentMessageController {

    @Autowired
    private SentMessageMapper mapper;
    @Autowired
    private SentMessageService service;

    @RequestMapping(method = RequestMethod.GET, value = "/sent_messages")
    public List<SentMessageDto> getAllMessages() {
        return mapper.mapToSentMessageDto(service.getAllSentMessages());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sent_messages/{emailValue}")
    public List<SentMessageDto> getMessagesByEmail(@RequestParam String email) {
        return mapper.mapToSentMessageDto(service.getSentMessagesByEmail(email));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/sent_message/{sentMessageId}")
    public void deleteSentMessage(@RequestParam Long sentMessageId) {
        service.deleteSentMessage(sentMessageId);
    }
}
