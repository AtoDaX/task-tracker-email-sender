package edu.pet.tasktrackeremailsender.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pet.tasktrackeremailsender.dto.EmailDto;
import edu.pet.tasktrackeremailsender.mail.service.EmailSenderService;
import edu.pet.tasktrackeremailsender.rabbitmq.service.MessageProcessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Message;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageProcessorServiceTest {

    @InjectMocks
    private MessageProcessorService messageProcessorService;

    @Mock
    private EmailSenderService emailSenderService;

    @Mock
    private ObjectMapper objectMapper;

    private Message message;
    private EmailDto emailDto;

    @BeforeEach
    public void setUp() {
        message = new Message("test".getBytes());
        emailDto = new EmailDto("test@example.com", "Test subject", "Test body");
    }

    @Test
    public void process_validMessage_callsEmailSenderService() throws IOException {
        // Arrange
        when(objectMapper.readValue(message.getBody(), EmailDto.class)).thenReturn(emailDto);

        // Act
        messageProcessorService.process(message);

        // Assert
        verify(objectMapper).readValue(message.getBody(), EmailDto.class);
        verify(emailSenderService).sendMessage(emailDto);
    }
}