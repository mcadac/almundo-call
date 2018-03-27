package com.almundo.callcenter.api.impl;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.almundo.callcenter.api.IDispatcher;
import com.almunfo.callcenter.model.CallRequest;

/**
 * Unit test for {@link CallRequestListener}
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public class CallRequestListenerUnitTest {

	/** Dispatcher mock test*/
	private IDispatcher dispatcherMock;
	
	/** Target class */
	private CallRequestListener callRequestListener;
	
	/**
	 * Constructor
	 */
	@Before
	public void init(){
		
		dispatcherMock = EasyMock.createMock(IDispatcher.class);
		callRequestListener = new CallRequestListener(dispatcherMock);
		
	}
	
	
	/**
	 * Validates the methods executed when arrive a message
	 */
	@Test
	public void validateDispatchCall(){
		
		EasyMock.reset(dispatcherMock);
		dispatcherMock.dispatchCall(EasyMock.anyObject(CallRequest.class));
		EasyMock.expectLastCall().times(1);
		EasyMock.replay(dispatcherMock);
		
		callRequestListener.handleCallRequest(new CallRequest());
		EasyMock.verify(dispatcherMock);
	}
	
}
