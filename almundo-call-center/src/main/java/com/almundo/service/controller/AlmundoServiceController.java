package com.almundo.service.controller;

import static com.almundo.service.model.ServiceConstant.API_VERSION;
import static com.almundo.service.model.ServiceConstant.PUT_REQUEST_MESSAGE_ERROR;
import static com.almundo.service.model.ServiceConstant.PUT_REQUEST_MESSAGE_SUCCESSFUL;
import static com.almundo.service.model.ServiceConstant.PUT_REQUEST_MESSAGE_UNSUCCESSFUL;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.service.api.IQueueSender;
import com.almundo.service.model.RequestMessage;
import com.almundo.service.model.ResponseMessage;
import com.almundo.service.model.ServiceConstant;

/**
 * Service controller to expose a rest service
 * <ul>Methods
 * <li>Send call request</li>
 * <li>Get service status</li>
 * </ul>
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 *
 */
@RestController
@RequestMapping(value = API_VERSION)
public class AlmundoServiceController {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(AlmundoServiceController.class);
	
	/** Bean to put call request in queue component */
	private IQueueSender queueSender;
	
	/**
	 * Constructor
	 */
	@Autowired
	public AlmundoServiceController(final IQueueSender queueSender) {
		
		this.queueSender = queueSender;
	}
	
	
	/**
	 * Gets service status
	 * 
	 * @return {@link ResponseMessage} - Information about service status
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/status")
	public ResponseMessage getServiceStatus(){
		
		return ServiceConstant.RESPONSE_MESSAGE_STATUS;
	}
	
	
	/**
	 * Send call request to queue component
	 * 
	 * @param message - Message to send as call request
	 * @return {@link ResponseMessage} - Information if call request was delivery to queue componente
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public ResponseMessage sendCallRequest(@RequestBody RequestMessage message){

		LOGGER.info("Call request to process : {}", message);
		
		try{
			
			prepareRequestMessage(message);
			return doCallRequest(message);
			
		} catch (final Exception exception){
			
			LOGGER.error("Exception processing call request :", exception);
			return createResponseMessage( PUT_REQUEST_MESSAGE_ERROR);
		}
		
		
		
		
	}

	/**
	 * Prepare request message to send the call request 
	 * <p>This method adds an ID and first attempt</p>
	 * 
	 * @param message - {@link RequestMessage}
	 */
	private void prepareRequestMessage(final RequestMessage message){
		
		Validate.notNull(message, "Request message cannot be null");
		Validate.notNull(message.getContent(), "Content message cannot be null");
		
		if (StringUtils.isBlank(message.getId())){
			message.setId(UUID.randomUUID().toString());
		}
		
		if (message.getAttempt() != NumberUtils.INTEGER_ONE){
			message.setAttempt(NumberUtils.INTEGER_ONE);
		}
		
	}
	

	/**
	 * Put call request in the queue component
	 * 
	 * @param message - {@link RequestMessage}
	 * @return {@link ResponseMessage}
	 */
	private ResponseMessage doCallRequest(RequestMessage message) {
		
		if (queueSender.putCallRequest(message)){

			return createResponseMessage(PUT_REQUEST_MESSAGE_SUCCESSFUL);

		} else{
			
			return createResponseMessage(PUT_REQUEST_MESSAGE_UNSUCCESSFUL);
		}
	}
	

	
	/**
	 * Create a {@link ResponseMessage} to return a client response
	 * 
	 * @param responseMessage - {@link String}
	 * @return {@link ResponseMessage} object to return 
	 */
	private ResponseMessage createResponseMessage(final String responseMessage){
		
		return new ResponseMessage(UUID.randomUUID().toString(), responseMessage);
	}

}
