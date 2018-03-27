package com.almundo.callcenter.api;

import java.util.Collection;
import java.util.Optional;

/**
* Manager to process call requests and to get information about call center
* 
* @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
* @version 1.0
* @since 0.0.1
*/
public interface ICallCenterManager<T> {

	/**
	 * Gets the next call agent to take a new call request
	 * 
	 * @return T - a person available to take a call request
	 */
	Optional<T> getNextCallAgentAvailable();
	
	/**
	 * Gets call agents available to process a call request 
	 * 
	 * @return {@link Collection}
	 */
	Collection<T> getCallAgentsAvailable();
	
	/**
	 * Adds new call agent to process a call request 
	 * 
	 * @param callAgent
	 * @return
	 */
	boolean addCallAgent(T callAgent);
	
	
}
