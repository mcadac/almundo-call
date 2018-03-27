package com.almundo.service.model;

import java.util.UUID;

/**
 * Objects constant used in service - this help the memory consumption
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public final class ServiceConstant {
	
	/** Response message used when is put a call request in queue component */
	public static final String PUT_REQUEST_MESSAGE_SUCCESSFUL = "Call request received";
	
	/** Response message used when was some exception putting a call request */
	public static final String PUT_REQUEST_MESSAGE_UNSUCCESSFUL = "We cannot process your request, please try again later";
	
	/** Response message when request message has an error or server had an exception*/
	public static final String PUT_REQUEST_MESSAGE_ERROR = "Please verify request message or try again later";
	
	
	/** Object used as response message of service status */
	public static final ResponseMessage RESPONSE_MESSAGE_STATUS = new ResponseMessage(UUID.randomUUID().toString(), "Service running");
	
	/** Current API version*/
	public static final String API_VERSION = "/v1";
	
}
