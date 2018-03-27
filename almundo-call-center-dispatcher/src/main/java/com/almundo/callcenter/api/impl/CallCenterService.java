package com.almundo.callcenter.api.impl;

import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.api.ICallCenterManager;
import com.almundo.callcenter.api.ICallCenterService;
import com.almundo.callcenter.api.IQueueService;
import com.almunfo.callcenter.model.CallAgent;
import com.almunfo.callcenter.model.CallRequest;

/**
 * Call center service to process call request and its life cycle
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
@Service
public class CallCenterService implements ICallCenterService {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CallCenterService.class);
	
	/** Call center manager */
	private ICallCenterManager<CallAgent> callCenterManager;
	
	/** Queue service to interact with queue component*/
	private IQueueService queueService;
	
	
	/**
	 * Constructor 
	 * 
	 * @param callCenterManager - {@link ICallCenterManager}
	 */
	@Autowired
	public CallCenterService(final ICallCenterManager<CallAgent> callCenterManager, final IQueueService queueService) {
		
		
		this.callCenterManager = callCenterManager;
		this.queueService =  queueService;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.almundo.callcenter.api.ICallCenterService#processCall(com.almunfo.callcenter.model.CallRequest)
	 */
	@Override
	public void processCall(final CallRequest callRequest) {

		final Optional<CallAgent> callAgentAvailable = callCenterManager.getNextCallAgentAvailable();

		if (callAgentAvailable.isPresent()) {
			
			answerCall(callRequest, callAgentAvailable.get());
			
		} else {
			
			LOGGER.warn("Call agent not available at moment. Creating a new attempt for call request: {}", callRequest.getId());
			callRequest.addAttempt();
			queueService.putNewCallAttempt(callRequest);
		}
	}
	
	/**
	 * Answer call request with call agent available
	 * 
	 * @param callRequest - {@link CallRequest}
	 * @param callAgentAvailable - {@link CallAgent}
	 */
	private void answerCall(final CallRequest callRequest, final CallAgent callAgentAvailable) {
		
		try{
			
			LOGGER.info("Call request: {}  answering by call agent: {} with role {}",
					callRequest.getId(),
					callAgentAvailable.getId(), 
					callAgentAvailable.getRoleType().name());
			
			callAgentAvailable.answerCall();
			
		} catch (final InterruptedException exception){
			
			LOGGER.error("Exception answering call {} : ", callRequest.getId(), exception);
		
		} finally {
			finishCall(callRequest, callAgentAvailable);
		}
	}
	
	
	
	/**
	 * Finish call 
	 * <p>This finishing the call and adds the call center to process a next call request</p>
	 * 
	 * @param callRequest
	 * @param callAgent - {@link CallAgent}
	 */
	private void finishCall(final CallRequest callRequest, final CallAgent callAgent){
		
		LOGGER.info("Call request {} ended", callRequest.getId());
		callCenterManager.addCallAgent(callAgent);
		
	}


}
