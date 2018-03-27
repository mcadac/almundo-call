package com.almundo.callcenter.api;

import com.almunfo.callcenter.model.CallRequest;

/**
 * Class to process call requests from input components
 * e.g queue component
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 *
 */
public interface IDispatcher {

	/**
	 * Method to handle call request
	 * 
	 * @param callRequest - Message from input component 
	 */
	void dispatchCall(CallRequest callRequest);
	
}
