package com.almundo.service.controller;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.almundo.service.api.IQueueSender;
import com.almundo.service.model.RequestMessage;
import com.almundo.service.model.ResponseMessage;
import com.almundo.service.model.ServiceConstant;

/**
 * Unit test for {@link AlmundoServiceController}
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public class AlmundoServiceControllerUnitTest {
	
	/** Target class */
	private AlmundoServiceController almundoServiceController;
	
	/** Mock */
	private IQueueSender queueSenderMock;

	/**
	 * Init unit test
	 */
	@Before
	public void init(){
		
		queueSenderMock = EasyMock.createMock(IQueueSender.class);
		almundoServiceController = new AlmundoServiceController(queueSenderMock);
		
	}
	
	
	/**
	 * Validates status method - Response message
	 */
	@Test
	public void validateStatusResponseMessage(){
		
		final ResponseMessage responseMessage = almundoServiceController.getServiceStatus();
		
		Assert.assertNotNull(responseMessage);
		Assert.assertNotNull(responseMessage.getId());
		Assert.assertNotNull(responseMessage.getMessage());
		
	}
	
	/**
	 * Validates response message when is a request null
	 * @throws Exception 
	 */
	@Test
	public void validatesResponseMessageWhenIsRequestNull() throws Exception{
		
		final ResponseMessage responseMessage = almundoServiceController.sendCallRequest(null);
		
		Assert.assertNotNull(responseMessage);
		Assert.assertNotNull(responseMessage.getId());
		Assert.assertNotNull(responseMessage.getMessage());
		Assert.assertNotNull(ServiceConstant.PUT_REQUEST_MESSAGE_ERROR.equals(responseMessage.getMessage()));
	}
	
	/**
	 * Validates response message when is a content null
	 */
	@Test
	public void validatesResponseMessageWhenIsContentNull(){
		
		final ResponseMessage responseMessage = almundoServiceController.sendCallRequest(new RequestMessage());
		
		Assert.assertNotNull(responseMessage);
		Assert.assertNotNull(responseMessage.getId());
		Assert.assertNotNull(responseMessage.getMessage());
		Assert.assertNotNull(ServiceConstant.PUT_REQUEST_MESSAGE_ERROR.equals(responseMessage.getMessage()));
	}
	
}

