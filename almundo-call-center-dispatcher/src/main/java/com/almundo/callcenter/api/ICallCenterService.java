package com.almundo.callcenter.api;

import com.almunfo.callcenter.model.CallRequest;

/**
 * Interface to defines call center service
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public interface ICallCenterService {
	
	/**
	 * Process a call request
	 * 
	 * @param callRequest 
	 */
	void processCall(CallRequest callRequest);
}
