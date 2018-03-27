package com.almundo.callcenter.api.impl;

import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.almundo.callcenter.queue.QueueService;
import com.almunfo.callcenter.model.CallAgent;
import com.almunfo.callcenter.model.CallRequest;
import com.almunfo.callcenter.model.Operator;

/**
 * Unit test for {@link CallCenterService}
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public class CallCenterServiceUnitTest {

	/** Target class */
	private CallCenterService callCenterService;
	
	/** Call center manager mock */
	private CallCenterManager callCenterManagerMock;
	
	/** Queue service manager mock */
	private QueueService queueServiceMock;
	
	/**
	 * Init unit test
	 */
	@Before
	public void init(){
		
		callCenterManagerMock = EasyMock.createMock(CallCenterManager.class);
		queueServiceMock = EasyMock.createMock(QueueService.class);
		callCenterService = new CallCenterService(callCenterManagerMock, queueServiceMock);
	}
	
	/**
	 * Process call successful
	 */
	@Test
	public void processCallSuccessful(){
		
		EasyMock.reset(callCenterManagerMock, queueServiceMock);
		EasyMock.expect(callCenterManagerMock.getNextCallAgentAvailable())
			.andReturn(Optional.of(new Operator()))
			.times(1);
		
		EasyMock.expect(callCenterManagerMock.addCallAgent(EasyMock.anyObject(CallAgent.class)))
			.andReturn(Boolean.TRUE)
			.times(1);
		
		EasyMock.replay(callCenterManagerMock, queueServiceMock);
		callCenterService.processCall(new CallRequest());
		EasyMock.verify(callCenterManagerMock, queueServiceMock);
	}
	
	
	/**
	 * Validates to put a new call attempt when not there any call agent to answer the call 
	 */
	@Test
	public void putNewCallAtemmpt(){
		
		final CallRequest callRequest = new CallRequest();
		
		EasyMock.reset(callCenterManagerMock, queueServiceMock);
		EasyMock.expect(callCenterManagerMock.getNextCallAgentAvailable())
			.andReturn(Optional.empty())
			.times(1);
		
		queueServiceMock.putNewCallAttempt(callRequest);
		EasyMock.expectLastCall().times(1);
		
		EasyMock.replay(callCenterManagerMock, queueServiceMock);
		
		callCenterService.processCall(callRequest);
		EasyMock.verify(callCenterManagerMock, queueServiceMock);
	}
	
}
