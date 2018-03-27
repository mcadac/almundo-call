package com.almundo.service.model;

/**
 * Response message to return status of a request
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 *
 */
public class ResponseMessage {
	
	/** Response's id */
	private String id;
	
	/** Response's message */
	private String message;
	
	/**
	 * Constructor 
	 * 
	 * @param id - {@link String}
	 * @param message - {@link String}
	 */
	public ResponseMessage(final String id, final String message) {
		
		this.id = id;
		this.message = message;
		
	}

	/**
	 * Gets response's id
	 * 
	 * @return {@link String} Response's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets response's id
	 * @param id {@link String}
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * Gets response's message
	 * 
	 * @return {@link String} - Response's message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets response's message
	 * 
	 * @param message - {@link String}
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
