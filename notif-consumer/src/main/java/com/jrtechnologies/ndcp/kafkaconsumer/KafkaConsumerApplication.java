package com.jrtechnologies.ndcp.kafkaconsumer;

import com.jrtechnologies.ndcp.kafkaconsumer.services.KafkaNotifConsumer;

/**
 * Main application, used to start the application and run the consumer
 */
public class KafkaConsumerApplication {

	public static void main(String... args) {
		KafkaNotifConsumer kafkaNotifConsumer = new KafkaNotifConsumer(args);
		kafkaNotifConsumer.runConsumer();
	}
}
