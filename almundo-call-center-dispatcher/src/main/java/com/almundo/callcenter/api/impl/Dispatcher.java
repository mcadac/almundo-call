package com.almundo.callcenter.api.impl;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.almundo.callcenter.api.ICallCenterService;
import com.almundo.callcenter.api.IDispatcher;
import com.almunfo.callcenter.model.CallRequest;

/**
 * {@link IDispatcher} implementation to process and response every call request 
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
@Component
public class Dispatcher implements IDispatcher {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(Dispatcher.class); 
	
	/** Call center service to process every call request*/
	private ICallCenterService callCenterservice;
	
	/** Executor service in order to concurrent calls */
	private ExecutorService concurrentExecutorService;
	
	
	/**
	 * Default Constructor
	 * <p>
	 * This constructor creates an {@link ExecutorService} to process concurrent call request.
	 * If the instance variable is zero or minus then creates an Executor of cached type, else creates an 
	 * Executor of fixed type
	 * </p> 
	 * 
	 */
	@Autowired
	public Dispatcher(final @Value("${dispatcher.fixed.threads}") int fixedThreads, final ICallCenterService callCenterservice) {
		
	
		this.callCenterservice = callCenterservice;
		this.concurrentExecutorService = (fixedThreads > INTEGER_ZERO) ? 
				Executors.newFixedThreadPool(fixedThreads) : Executors.newCachedThreadPool();
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.almundo.callcenter.api.IDispatcher#dispatchCall(com.almunfo.callcenter.model.CallRequest)
	 */
	@Override
	public void dispatchCall(final CallRequest callRequest) {
		
		LOGGER.info("New call request : {}", callRequest.getId());
		CompletableFuture.runAsync(() -> callCenterservice.processCall(callRequest), concurrentExecutorService);
	}
	
	
}
