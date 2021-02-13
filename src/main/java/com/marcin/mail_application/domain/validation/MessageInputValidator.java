package com.marcin.mail_application.domain.validation;

import com.marcin.mail_application.domain.Message;
import com.marcin.mail_application.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class MessageInputValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public boolean validate(Message message) {
        Set<ConstraintViolation<Message>> violations = validator.validate(message);

        for (ConstraintViolation<Message> violation : violations) {
            LOGGER.error(violation.getMessage());
        }

        if (violations.size() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
