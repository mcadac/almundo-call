package com.almundo.service.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.almundo.service.api.IQueueSender;
import com.almundo.service.model.RequestMessage;

/**
 * Class to interact with RabbitMQ (queue component)
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
@Component
public class CallQueueSender implements IQueueSender {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CallQueueSender.class);

	/** Queue request object - RabbitMQ*/
	public Queue queueRequest;
	
	/** Rabbit template to interact with RabbitMQ*/
	public RabbitTemplate rabbitTemplate;
	
	/**
	 * Constructor
	 */
	@Autowired
	public CallQueueSender(final Queue queueRequest, final RabbitTemplate rabbitTemplate) {
		
		this.queueRequest = queueRequest;
		this.rabbitTemplate = rabbitTemplate;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.almundo.service.api.IQueueSender#putCallRequest(com.almundo.service.model.RequestMessage)
	 */
	@Override
	public boolean putCallRequest(final RequestMessage callRequest) {
		
		try{
		
			LOGGER.info("Sending message: {}", callRequest);
			rabbitTemplate.convertAndSend(queueRequest.getName(), callRequest);
			return true;
		
		} catch (final Exception exception){
			
			LOGGER.error("Exception sending message {}", callRequest, exception);
			return false;
		}
	
	}

}
