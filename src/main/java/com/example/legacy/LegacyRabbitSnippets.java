package com.example.legacy;

import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

class LegacyRabbitSnippets {

	void configure(com.rabbitmq.client.Channel channel, java.util.Map<String, Object> headers,
			java.util.Map<String, Object> args) throws java.io.IOException {
		// TODO [RMQ4.3.4]: global_qos is silently downgraded to per-consumer prefetch (N x M messages may be in flight, not N) - verify that's acceptable, or restructure to per-consumer channels.
		channel.basicQos(10, true);
		// TODO [RMQ4.3.4]: a transient non-exclusive queue raises a connection-level 541 and loops on reconnect - add .exclusive() or make it durable
		QueueBuilder.nonDurable("legacy-queue").build();
		// TODO [RMQ4.3.4]: acquired-count and delivery-count are now separate quorum-queue counters - acquired-count increments on every requeue, delivery-count only on a failed delivery attempt, so code reading x-delivery-count expecting the old combined count now sees a smaller number - add x-acquired-count if you need the original total.
		Object count = headers.get("x-delivery-count");
		// TODO [RMQ4.3.4-queuemode]: enforcement is contested - release notes and rabbit_classic_queue.erl disagree on whether declarations fail, test before relying on either reading
		args.put("x-queue-mode", "lazy");
		// TODO [RMQ4.3.4-queueversion]: CQv1 removed - check_queue_version accepts only 2, the queue must be recreated, not re-argued
		args.put("x-queue-version", "1");
		// TODO [spring-amqp]: 3.2.11+ silently narrows the default trusted packages from all (*) to {java.util, java.lang} - this constructor call looks unchanged, but an untrusted inbound type now throws IllegalArgumentException at runtime. Pass your real packages explicitly, e.g. new Jackson2JsonMessageConverter("com.acme.events")
		Jackson2JsonMessageConverter conv = new Jackson2JsonMessageConverter();
	}

}
