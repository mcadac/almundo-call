package com.almundo.callcenter.api.impl;

import java.util.Optional;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.almundo.callcenter.api.ICallCenterManager;
import com.almunfo.callcenter.model.CallAgent;
import com.almunfo.callcenter.model.Director;
import com.almunfo.callcenter.model.Operator;
import com.almunfo.callcenter.model.Supervisor;

/**
 * Implementation of {@link ICallCenterManager} to process call request
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 *
 */
@Component
public class CallCenterManager implements ICallCenterManager<CallAgent> {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CallCenterManager.class);
	
	/** Person list registered in Almundo call center to process call request*/
	private final PriorityBlockingQueue<CallAgent> callAgents = new PriorityBlockingQueue<>();
	
	
	/**
	 * Constructor to set call agents  
	 */
	public CallCenterManager(@Value("${quantity.supervisors:2}") int quantitySupervisors, 
			@Value("${quantity.operators:2}") int quantityOperators, 
			@Value("${quantity.directors:2}") int quantityDirectors) {
		
		createOperators(quantityOperators);
		createSupervisors(quantitySupervisors);
		createDirectors(quantityDirectors);
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.almundo.callcenter.api.ICallCenterManager#getNextPersonAvailable()
	 */
	@Override
	public Optional<CallAgent> getNextCallAgentAvailable() {
		
		return Optional.ofNullable(callAgents.poll());
	}

	/*
	 * (non-Javadoc)
	 * @see com.almundo.callcenter.api.ICallCenterManager#getPersonsAvailable()
	 */
	@Override
	public PriorityBlockingQueue<CallAgent> getCallAgentsAvailable() {
	
		return callAgents;
	}

	/*
	 * (non-Javadoc)
	 * @see com.almundo.callcenter.api.ICallCenterManager#addPerson(java.lang.Object)
	 */
	@Override
	public boolean addCallAgent(final CallAgent callAgent) {
		
		return callAgents.add(callAgent);
	
	}

	
	
	/**
	 * Creates the initial operators
	 * <p>Adds operators object to a collection</p>
	 */
	private void createOperators(final int quantityOperators) {
		
		LOGGER.info("Creating {} operators", quantityOperators);
		
		callAgents.addAll( Stream.generate(Operator::new)
				.limit(quantityOperators)
				.collect(Collectors.toList()));
	}
	
	
	/**
	 * Creates the initial Supervisors
	 * <p>Adds supervisors object to a collection</p>
	 */
	private void createSupervisors(final int quantitySupervisors){
		
		LOGGER.info("Creating {} supervisors", quantitySupervisors);
		
		callAgents.addAll( Stream.generate(Supervisor::new)
				.limit(quantitySupervisors)
				.collect(Collectors.toList()));
		
	}
	
	
	/**
	 * Create the initial directors
	 * <p>Adds directors object to a collection</p>
	 */
	private void createDirectors(final int quantityDirectors){
		
		LOGGER.info("Creating {} directors", quantityDirectors);
		
		callAgents.addAll( Stream.generate(Director::new)
				.limit(quantityDirectors)
				.collect(Collectors.toList()));
	}

}
