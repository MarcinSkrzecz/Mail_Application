package com.marcin.mail_application.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "SentMessages")
public class SentMessage {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @NotNull
    @Column(name = "MESSAGE_ID")
    private Long id;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Column(name = "CONTENT")
    private String content;

    @NotNull
    @Column(name = "MAGIC_NUMBER")
    private String magicNumber;

    @NotNull
    @Column(name = "WHEN_WAS_SENT")
    private LocalDate whenWasSent = LocalDate.now();

    public SentMessage(String email, String title, String content, String magicNumber) {
        this.email = email;
        this.title = title;
        this.content = content;
        this.magicNumber = magicNumber;
    }
}
