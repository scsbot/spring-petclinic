package com.example.legacy;

import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

class LegacyRabbitSnippets {

	void configure(com.rabbitmq.client.Channel channel, java.util.Map<String, Object> headers,
			java.util.Map<String, Object> args) throws java.io.IOException {
		channel.basicQos(10, true);
		QueueBuilder.nonDurable("legacy-queue").build();
		Object count = headers.get("x-delivery-count");
		args.put("x-queue-mode", "lazy");
		args.put("x-queue-version", "1");
		Jackson2JsonMessageConverter conv = new Jackson2JsonMessageConverter();
	}

}
