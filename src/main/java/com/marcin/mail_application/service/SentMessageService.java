package com.marcin.mail_application.service;

import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.domain.SentMessage;
import com.marcin.mail_application.domain.SentMessageRepository;
import com.marcin.mail_application.mapper.SentMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SentMessageService.class);

    @Autowired
    private SentMessageMapper mapper;
    @Autowired
    private SentMessageRepository repository;

    public List<SentMessage> getAllSentMessages() {
        return repository.findAll();
    }

    public List<SentMessage> getSentMessagesByEmail(final String email) {
        return repository.findByEmail(email);
    }

    public void createSentMessage(final Message message) {
        repository.save(mapper.mapToSentMessage(message));
        LOGGER.info("Message moved into Sent Messages");
    }

    public void deleteSentMessage(Long id) {
        repository.deleteById(id);
        LOGGER.info("Sent Message deleted");
    }
}
