package edu.pet.tasktrackeremailsender.rabbitmq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pet.tasktrackeremailsender.dto.EmailDto;
import edu.pet.tasktrackeremailsender.mail.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MessageProcessorService {
    private final EmailSenderService emailSenderService;
    private final ObjectMapper objectMapper;

    public void process(Message message) throws IOException {
        EmailDto emailDto = objectMapper.readValue(message.getBody(), EmailDto.class);
        emailSenderService.sendMessage(emailDto);
    }


}
