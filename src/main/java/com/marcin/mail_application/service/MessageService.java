package com.marcin.mail_application.service;

import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.domain.MessageRepository;
import com.marcin.mail_application.domain.exceptions.ValidationInputException;
import com.marcin.mail_application.domain.exceptions.WrongMagicNumberException;
import com.marcin.mail_application.domain.validation.MessageInputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository repository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SentMessageService sentMessageService;
    @Autowired
    private MessageInputValidator validator;


    public List<Message> getAllMessages() {
        return repository.findAll();
    }

    public Message getMessageById(final Long id) {

        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        } else throw new IllegalArgumentException("Message not found");
    }

    public List<Message> getMessagesByEmail(final String email) {
        return repository.findByEmail(email);
    }

    public void createMessage(final Message message) throws Exception {
        if (validator.validate(message)) {
            repository.save(message);
            LOGGER.info("Message created");
        } else {
            throw new ValidationInputException("Message can't be created, validation failed");
        }
    }

    public Message updateMessage(Message message) throws Exception {
        Optional<Message> dbRecord = repository.findById(message.getId());

        if (dbRecord.isPresent()) {
            Message update = dbRecord.get();

            update.setEmail(message.getEmail());
            update.setTitle(message.getTitle());
            update.setContent(message.getContent());
            update.setMagicNumber(message.getMagicNumber());

            if (validator.validate(update)) {
                repository.save(update);
                LOGGER.info("Message updated");
            } else {
                throw new ValidationInputException("Message can't be updated, validation failed");
            }

            return update;
        } else throw new IllegalArgumentException("Message not found");
    }

    public void deleteMessage(Long id) {
        repository.deleteById(id);

        LOGGER.info("Message deleted");
    }

    public void sendMessages(String magicNumber) throws Exception {
        List<Message> messagesToSend = repository.findByMagicNumber(magicNumber);

        int size = messagesToSend.size();

        if (size > 0) {

            System.out.println("Messages with Magic Number: " + magicNumber + " that has been send:");

            for (int i = 0; i < messagesToSend.size(); i++) {
                Message message = messagesToSend.get(i);
                System.out.println(i+1 + ") " + message.getEmail() + " with title \"" + message.getTitle()
                        + "\", email text \"" + message.getContent() + "\";");

                emailService.send(message);

                sentMessageService.createSentMessage(message);

                deleteMessage(message.getId());
            }
        } else throw new WrongMagicNumberException("No messages with this Magic Number to send");
    }
}
