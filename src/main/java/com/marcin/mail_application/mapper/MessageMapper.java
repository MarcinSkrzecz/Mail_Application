package com.marcin.mail_application.mapper;

import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.domain.MessageDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    public Message mapToMessage(final MessageDto messageDto) {
        if (messageDto.getId() != null) {
            return new Message(
                    messageDto.getId(),
                    messageDto.getEmail(),
                    messageDto.getTitle(),
                    messageDto.getContent(),
                    messageDto.getMagicNumber()
            );
        } else {
            return new Message(
                    messageDto.getEmail(),
                    messageDto.getTitle(),
                    messageDto.getContent(),
                    messageDto.getMagicNumber()
            );
        }
    }

    public MessageDto mapToMessageDto(final Message message) {
        if (message.getId() == null) {
            throw new IllegalArgumentException("Message not exist");
        } else {
            return new MessageDto(
                    message.getId(),
                    message.getEmail(),
                    message.getTitle(),
                    message.getContent(),
                    message.getMagicNumber()
            );
        }
    }

    public List<MessageDto> mapToMessageDto(final List<Message> messageList) {
        return messageList.stream()
                .map(this::mapToMessageDto)
                .collect(Collectors.toList());
    }
}
