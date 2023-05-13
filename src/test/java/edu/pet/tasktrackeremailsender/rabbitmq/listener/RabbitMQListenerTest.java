package edu.pet.tasktrackeremailsender.rabbitmq.listener;

import edu.pet.tasktrackeremailsender.rabbitmq.service.MessageProcessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Message;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RabbitMQListenerTest {
    @InjectMocks
    private RabbitMQListener listener;

    @Mock
    private MessageProcessorService MessageProcessorService;

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new Message("test".getBytes());

    }

    @Test
    public void onMessage_ValidMessage_CallsMessageProcessorService() throws IOException {
        // Act
        listener.onMessage(message);

        // Assert
        verify(MessageProcessorService, times(1)).process(message);
    }

}
