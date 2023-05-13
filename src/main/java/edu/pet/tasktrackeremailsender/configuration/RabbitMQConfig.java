package edu.pet.tasktrackeremailsender.configuration;

import edu.pet.tasktrackeremailsender.rabbitmq.listener.RabbitMQListener;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitMQListener emailSendingListener;
    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(emailSenderQueue());
        simpleMessageListenerContainer.setMessageListener(emailSendingListener);
        return simpleMessageListenerContainer;

    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean
    public Queue emailSenderQueue(){
        return new Queue(QueueName.EMAIL_SENDER_TASKS.toString(), false);
    }


    /*@RabbitListener(queues = "EMAIL_SENDING_TASKS")
    public void listen(String in) {
        System.out.println("Message read from myQueue : " + in);
    }*/

}
