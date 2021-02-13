package com.marcin.mail_application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageDto {

    private Long id;
    private String email;
    private String title;
    private String content;
    private String magicNumber;

}
