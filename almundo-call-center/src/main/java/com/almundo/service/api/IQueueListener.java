package com.almundo.service.api;

/**
 * Queue listener to gets message from queue component
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public interface IQueueListener {

	/**
	 * Methos to handle message from queue component
	 * 
	 * @param message - Message form queue component
	 */
	void handleMessgae(final String message);
	
}
