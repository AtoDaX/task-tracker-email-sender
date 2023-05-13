package edu.pet.tasktrackeremailsender.mail.service;

import edu.pet.tasktrackeremailsender.dto.EmailDto;
import edu.pet.tasktrackeremailsender.mail.mailsender.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailSenderServiceTest {

    @InjectMocks
    private EmailSenderService emailSenderService;

    @Mock
    private EmailSender emailSender;

    private EmailDto emailDto;

    @BeforeEach
    public void setUp() {
        emailDto = new EmailDto("test@example.com", "Test subject", "Test body");
    }

    @Test
    public void sendMessage_validEmailDto_callsEmailSender() {
        // Act
        emailSenderService.sendMessage(emailDto);

        // Assert
        verify(emailSender).sendSimpleMessage(any(SimpleMailMessage.class));
    }
}