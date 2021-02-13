package com.marcin.mail_application.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Override
    List<Message> findAll();

    @Override
    Optional<Message> findById(Long id);

    List<Message> findByEmail(String email);

    List<Message> findByMagicNumber(String magicNumber);

    @Override
    Message save(Message message);

    @Override
    void deleteById(Long id);
}
