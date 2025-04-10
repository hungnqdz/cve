package com.activemq.chat.chat_activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ChatActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatActivemqApplication.class, args);
	}

}
