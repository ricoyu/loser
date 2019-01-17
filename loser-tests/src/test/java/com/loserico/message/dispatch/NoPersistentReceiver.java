package com.loserico.message.dispatch;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoPersistentReceiver {

	private static String url = "failover:(tcp://192.168.1.3:61616,tcp://192.168.1.4:61616)";
	private static final Logger logger = LoggerFactory.getLogger(NoPersistentReceiver.class);

	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(true, AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic("dispatch.policy.topic1");

		for (int i = 0; i < 2; i++) {
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener((message) -> {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println(consumer + "收到消息: " + textMessage.getText());
					session.commit();
				} catch (JMSException e) {
					logger.error("msg", e);
				}
			});
		}
	}
}
