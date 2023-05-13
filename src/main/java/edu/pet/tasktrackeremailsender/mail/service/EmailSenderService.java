package edu.pet.tasktrackeremailsender.mail.service;

import edu.pet.tasktrackeremailsender.dto.EmailDto;
import edu.pet.tasktrackeremailsender.mail.mailsender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final EmailSender emailSender;

    private final String senderEmail = "noreply@tasktracker.com";

    public void sendMessage(EmailDto emailDto){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(emailDto.getReceiverEmail());
        message.setSubject(emailDto.getSubject());
        message.setText(emailDto.getBody());
        emailSender.sendSimpleMessage(message);

    }
}
