package com.marcin.mail_application.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface SentMessageRepository extends CrudRepository<SentMessage, Long> {
    @Override
    List<SentMessage> findAll();

    @Override
    Optional<SentMessage> findById(Long id);

    List<SentMessage> findByEmail(String email);

    @Override
    SentMessage save(SentMessage sentMessage);

    @Override
    void deleteById(Long id);
}
