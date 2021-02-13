package com.marcin.mail_application.controller;

import com.marcin.mail_application.domain.MessageDto;
import com.marcin.mail_application.domain.exceptions.ValidationInputException;
import com.marcin.mail_application.domain.exceptions.WrongMagicNumberException;
import com.marcin.mail_application.mapper.MessageMapper;
import com.marcin.mail_application.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageMapper mapper;
    @Autowired
    private MessageService service;

    @RequestMapping(method = RequestMethod.GET, value = "/messages")
    public List<MessageDto> getAllMessages() {
        return mapper.mapToMessageDto(service.getAllMessages());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/message/{messageId}")
    public MessageDto getMessageById(@RequestParam Long messageId) {
        return mapper.mapToMessageDto(service.getMessageById(messageId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages/{emailValue}")
    public List<MessageDto> getMessagesByEmail(@RequestParam String email) {
        return mapper.mapToMessageDto(service.getMessagesByEmail(email));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/message", consumes = APPLICATION_JSON_VALUE)
    public void createMessage(@RequestBody MessageDto messageDto) throws Exception {
        try {
            service.createMessage(mapper.mapToMessage(messageDto));
        } catch (Exception e) {
            throw new ValidationInputException("Observed issue: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/message", consumes = APPLICATION_JSON_VALUE)
    public MessageDto updateMessage(@RequestBody MessageDto messageDto) throws Exception {
        try {
            return mapper.mapToMessageDto(service.updateMessage(mapper.mapToMessage(messageDto)));
        } catch (Exception e) {
            throw new ValidationInputException("Observed issue: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/send/{magicNumber}")
    public void sendMessages(@RequestParam String magicNumber) throws Exception {
        try {
            service.sendMessages(magicNumber);
        } catch (Exception e) {
            throw new WrongMagicNumberException("Observed issue: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/message/{messageId}")
    public void deleteMessage(@RequestParam Long messageId) {
        service.deleteMessage(messageId);
    }
}
