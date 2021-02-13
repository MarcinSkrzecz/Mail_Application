package com.marcin.mail_application.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Messages")
public class Message {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @Column(name = "MESSAGE_ID")
    private Long id;

    @NotNull(message = "Email can't be empty")
    @Email(message = "Email should be valid")
    @Column(name = "EMAIL")
    private String email;

    @NotNull(message = "Title can't be empty")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    @Column(name = "TITLE")
    private String title;

    @NotNull(message = "Content can't be empty")
    @Size(min = 5, max = 5000, message = "Content must be between 5 and 5000 characters")
    @Column(name = "CONTENT")
    private String content;

    @NotNull(message = "Magic Number can't be empty")
    @DecimalMin(value = "0", message = "Minimal Magic Number must be equal or greater than 0 and must be number not text")
    @DecimalMax(value = "999999", message = "Maximal Magic Number must be lower than 1.000.000 and must be number not text")
    @Column(name = "MAGIC_NUMBER")
    private String magicNumber;

    public Message(String email, String title, String content, String magicNumber) {
        this.email = email;
        this.title = title;
        this.content = content;
        this.magicNumber = magicNumber;
    }
}