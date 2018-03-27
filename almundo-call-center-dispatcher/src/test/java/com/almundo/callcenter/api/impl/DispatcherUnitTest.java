package com.almundo.callcenter.api.impl;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.almundo.callcenter.api.ICallCenterService;
import com.almunfo.callcenter.model.CallRequest;

/**
 * Unit test for {@link Dispatcher}
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public class DispatcherUnitTest {

	/** Target class */
	private Dispatcher dispatcher;
	
	/** Call center service mock*/
	private ICallCenterService callCenterserviceMock;
	
	
	/** Init uni test*/
	@Before
	public void init(){
		
		callCenterserviceMock = EasyMock.createMock(ICallCenterService.class);
		dispatcher = new Dispatcher(2, callCenterserviceMock);
		
	}
	
	/**
	 * Validates methods called in  dispatCall method
	 */
	@Test
	public void validateDispatcherCall(){
		
		EasyMock.reset(callCenterserviceMock);
		callCenterserviceMock.processCall(EasyMock.anyObject(CallRequest.class));
		EasyMock.expectLastCall().times(1);
		EasyMock.replay(callCenterserviceMock);
		
		dispatcher.dispatchCall(new CallRequest());
		EasyMock.verify(callCenterserviceMock);
		
	}
	
}
