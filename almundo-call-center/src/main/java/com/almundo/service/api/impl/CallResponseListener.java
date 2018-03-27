package com.almundo.service.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.almundo.service.api.IQueueListener;

/**
 * Listener to receive call response from Rabbitmq (queue component)
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 *
 */
@Component
public class CallResponseListener implements IQueueListener {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CallResponseListener.class);
	
	/*
	 * (non-Javadoc)
	 * @see com.almundo.service.api.IQueueListener#handleMessgae(java.lang.String)
	 */
	@Override
	@RabbitListener(queues = "${queue.name.call.response}")
	public void handleMessgae(final String message) {
		
		LOGGER.info("Message received: {}", message);
	}

}
