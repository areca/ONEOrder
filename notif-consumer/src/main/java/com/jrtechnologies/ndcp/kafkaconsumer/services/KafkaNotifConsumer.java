package com.jrtechnologies.ndcp.kafkaconsumer.services;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.runtime.Log;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Example of a kafka consumer which reads the messages of a kafka topic and print them on the screen.
 */
public class KafkaNotifConsumer {

	private final Consumer<String, String> consumer;
	private static boolean running = true;
	private static Object monitor = new Object();

	private static final Logger LOGGER = LogManager.getLogger(KafkaNotifConsumer.class);

	public KafkaNotifConsumer(String[] args) {
		if (args.length < 4) {
			throw new IllegalArgumentException("Arguments [server:port[,another.host:port]] username password group topic");
		}
		consumer = createConsumer(args[0], args[1], args[2], args[3], args[4]);
	}


	private Consumer<String, String> createConsumer(String bootstrapServers, String username, String password, String group, String topic) {
		final Properties props = new Properties();
		String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
		String jaasCfg = String.format(jaasTemplate, username, password);

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("security.protocol", "SASL_PLAINTEXT");
		props.put("sasl.mechanism", "PLAIN");
		props.put("sasl.jaas.config", jaasCfg);
		// Create the consumer using props.
		final Consumer<String, String> consumer = new KafkaConsumer<>(props);
		// Subscribe to the topic.
		consumer.subscribe(Collections.singletonList(topic));
		return consumer;
	}

	public void runConsumer() {
		//Application exit handling
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			running = false;
			System.out.println("SigTerm detected Closing application!");
			synchronized (monitor) {
				try {
					monitor.wait();
				} catch (InterruptedException e) {
				}
			}
			System.out.println("Application Terminated Gracefully");
		}));



		final int polling = 500;
		try {
			while (running) {
				final ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(polling));

				for (ConsumerRecord<String, String> record : consumerRecords) {
					LOGGER.info(record);
				}
				consumer.commitAsync();
			}
		} catch (WakeupException e) {
			// ignore for shutdown 2
		} finally {
			consumer.close();
			System.out.println("Kafka consumer closed");
			synchronized (monitor) {
				monitor.notifyAll();
			}
		}
	}


}
