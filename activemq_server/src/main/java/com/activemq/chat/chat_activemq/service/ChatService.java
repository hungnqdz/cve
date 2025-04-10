package com.activemq.chat.chat_activemq.service;

import com.activemq.chat.chat_activemq.model.Message;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final JmsTemplate jmsTemplate;
    private final List<Message> messages = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate;


    public void sendMessage(Message message) {
        String json = new Gson().toJson(message);
        System.out.println(json);
        jmsTemplate.convertAndSend("chat.topic", json);
    }



    @JmsListener(destination = "chat.topic", subscription = "chat-subscriber", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(final javax.jms.Message jsonMessage) throws JMSException {
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            String messageData = textMessage.getText();
            System.out.println("Message raw data: " + messageData);
            Message message = new Gson().fromJson(messageData, Message.class);
            System.out.println(message);
            synchronized (messages) {
                messages.add(message);
                messagingTemplate.convertAndSend("/topic/messages", message);
            }
        }
    }


    public List<Message> getMessages() {
        synchronized (messages) {
            return new ArrayList<>(messages);
        }
    }
}
