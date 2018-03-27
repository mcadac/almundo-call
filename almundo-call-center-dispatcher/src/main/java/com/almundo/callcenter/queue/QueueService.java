package com.almundo.callcenter.queue;

import org.apache.commons.lang3.Validate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.api.IQueueService;
import com.almunfo.callcenter.model.CallRequest;

/**
 * Queue response service to send response message to a queue component
 *  
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
@Service
public class QueueService implements IQueueService {

	/** Rabbit template to interact with RabbitMQ */
	private RabbitTemplate rabbitTemplate;
	
	/** Queue response object */
	private Queue queueResponse;
	
	/** Queue request object */
	private Queue queueRequest;
	
	
	/**
	 * Constructor with instance variables required
	 */
	@Autowired
	public QueueService(final RabbitTemplate rabbitTemplate, final Queue queueResponse, final Queue queueRequest) {

		this.rabbitTemplate = rabbitTemplate;
		this.queueResponse = queueResponse;
		this.queueRequest = queueRequest;
		
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.almundo.callcenter.api.IQueueService#putCallResponse(java.lang.String)
	 */
	@Override
	public void putCallResponse(final String callResponse) {
		
		Validate.notNull(callResponse, "Call response cannot be null");
		rabbitTemplate.convertAndSend(queueResponse.getName(), callResponse);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.almundo.callcenter.api.IQueueService#putNewCallAttempt(java.lang.String)
	 */
	@Override
	public void putNewCallAttempt(final CallRequest callRequest) {
		
		Validate.notNull(callRequest, "The new call attempt cannot be null");
		rabbitTemplate.convertAndSend(queueRequest.getName(), callRequest);
	}
	
	
}
