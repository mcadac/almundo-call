package com.almundo.service.api;

import com.almundo.service.model.RequestMessage;

/**
 * Queue sender to interact with queue component
 * e.g put message
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public interface IQueueSender {
	
	/**
	 * Method to put a call request in the queue component
	 * 
	 * @param callRequest
	 * @return boolean - true if the call request put in the queue component was successful
	 */
	boolean putCallRequest(RequestMessage callRequest);

}
