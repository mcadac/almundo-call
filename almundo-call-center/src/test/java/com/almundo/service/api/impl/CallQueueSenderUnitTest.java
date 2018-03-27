package com.almundo.service.api.impl;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.almundo.service.model.RequestMessage;
import com.almundo.service.model.ResponseMessage;

/**
 * Unit test fro {@link CallQueueSender}
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public class CallQueueSenderUnitTest {

	/** Target class */
	private CallQueueSender callQueueSender;
	
	/** Queue request Mock*/
	public Queue queueRequestMock;
	
	/** Rabbit template*/
	public RabbitTemplate rabbitTemplateMock;
	
	/**
	 * Init unit test
	 */
	@Before
	public void initi(){
		
		queueRequestMock = EasyMock.createMock(Queue.class);
		rabbitTemplateMock = EasyMock.createMock(RabbitTemplate.class);
		callQueueSender = new CallQueueSender(queueRequestMock, rabbitTemplateMock);
		
	}
	
	
	/**
	 * Validate put call request message
	 */
	@Test
	public void validatePutCallRequest(){
		
		final RequestMessage message = new RequestMessage();
		
		EasyMock.reset(rabbitTemplateMock);
		rabbitTemplateMock.convertAndSend(EasyMock.anyString(), EasyMock.anyObject(ResponseMessage.class));
		EasyMock.expectLastCall().times(1);
		EasyMock.replay(rabbitTemplateMock);
		
		final boolean result = callQueueSender.putCallRequest(message);
		EasyMock.verify(rabbitTemplateMock);
		Assert.assertTrue(result);
		
	}
	
}
