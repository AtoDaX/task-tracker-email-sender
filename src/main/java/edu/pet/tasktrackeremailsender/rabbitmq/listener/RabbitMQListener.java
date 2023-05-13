package edu.pet.tasktrackeremailsender.rabbitmq.listener;


import edu.pet.tasktrackeremailsender.mail.mailsender.EmailSender;
import edu.pet.tasktrackeremailsender.rabbitmq.service.MessageProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class RabbitMQListener implements MessageListener {
    private final MessageProcessorService messageProcessorService;


    public void onMessage(Message message) {
        try {
            messageProcessorService.process(message);
        } catch (IOException e) {
            //todo custom ex + handling
            throw new RuntimeException(e);
        }
    }

}
