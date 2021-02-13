package com.marcin.mail_application.mapper;

import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.domain.SentMessage;
import com.marcin.mail_application.domain.SentMessageDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SentMessageMapper {

    public SentMessage mapToSentMessage(final Message message) {
        return new SentMessage(
                message.getEmail(),
                message.getTitle(),
                message.getContent(),
                message.getMagicNumber()
        );
    }

    public SentMessageDto mapToSentMessageDto(final SentMessage sentMessage) {
        return new SentMessageDto(
                sentMessage.getId(),
                sentMessage.getEmail(),
                sentMessage.getTitle(),
                sentMessage.getContent(),
                sentMessage.getMagicNumber(),
                sentMessage.getWhenWasSent()
        );
    }

    public List<SentMessageDto> mapToSentMessageDto(final List<SentMessage> sentMessageList) {
        return sentMessageList.stream()
                .map(this::mapToSentMessageDto)
                .collect(Collectors.toList());
    }
}