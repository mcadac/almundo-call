package com.almundo.callcenter.api;

import com.almunfo.callcenter.model.CallRequest;

/**
 * Service to interact with a queue component
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 *
 */
public interface IQueueService {

	/**
	 * Puts call response to queue component
	 *  
	 * @param callRequest
	 */
	void putCallResponse(final String callRequest);
	
	/**
	 * Creates a new call attempt in queue component
	 * 
	 * @param callRequest
	 */
	void putNewCallAttempt(final CallRequest callRequest);
	
}
