package com.almundo.callcenter.api.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.api.ICallRequestListener;
import com.almundo.callcenter.api.IDispatcher;
import com.almunfo.callcenter.model.CallRequest;

/**
 * {@link ICallRequestListener} implementation to get call request from queue component (RabbitMQ)
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
@Service
public class CallRequestListener implements ICallRequestListener {

	/** Call request Dispatcher  */
	private IDispatcher dispatcher;
	

	/**
	 * Constructor with necessary instance objects
	 * 
	 * @param dispatcher - {@link IDispatcher}
	 */
	@Autowired
	public CallRequestListener(final IDispatcher dispatcher) {
		
		this.dispatcher = dispatcher;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.almundo.callcenter.api.ICallRequestListener#handleCallRequest(java.lang.String)
	 */
	@Override
	@RabbitListener(queues = "${queue.name.call.request}")
	public void handleCallRequest(final CallRequest callRequest) {
		
		dispatcher.dispatchCall(callRequest);
		
	}

}
