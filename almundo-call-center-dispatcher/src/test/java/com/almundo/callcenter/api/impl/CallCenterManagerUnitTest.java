package com.almundo.callcenter.api.impl;

import java.util.concurrent.PriorityBlockingQueue;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

import com.almunfo.callcenter.model.CallAgent;
import com.almunfo.callcenter.model.Director;
import com.almunfo.callcenter.model.Operator;
import com.almunfo.callcenter.model.Supervisor;
import com.almunfo.callcenter.model.enums.RoleType;

/**
 * Unit test for {@link CallCenterManager}
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 *
 */
public class CallCenterManagerUnitTest {

	/**Supervisors to test */
	final static int QUANTITY_SUPERVISORS = 2;
	
	/** Operators to test */
	final static int QUANTITY_OPERATORS = 5;
	
	/** Directors to test */
	final static int QUANTITY_DIRECTORS = 1;
	
	
	/**
	 * Validates call agents creation
	 */
	@Test
	public void validatesCallAgentsCreation(){
		
		final CallCenterManager callCenterManager = new CallCenterManager(QUANTITY_SUPERVISORS, QUANTITY_OPERATORS, QUANTITY_DIRECTORS);		
		final PriorityBlockingQueue<CallAgent> callAgents = callCenterManager.getCallAgentsAvailable();
		
		Assert.assertNotNull(callAgents);
		Assert.assertTrue(callAgents.size() == 8);
		
	}
	
	
	/**
	 * Validates call agent priority level in the Queue
	 */
	@Test
	public void validatesCallAgentPriorityLevel(){
		
		final CallCenterManager callCenterManager = new CallCenterManager(QUANTITY_SUPERVISORS, QUANTITY_OPERATORS, QUANTITY_DIRECTORS);
		
		final PriorityBlockingQueue<CallAgent> callAgents = callCenterManager.getCallAgentsAvailable();
		Assert.assertNotNull(callAgents);
		
		for(int i = 0; i < QUANTITY_OPERATORS; i++){
			validateRol(callAgents.poll(), RoleType.OPERATOR);
		}
		
		for(int i = 0; i < QUANTITY_SUPERVISORS; i++){
			validateRol(callAgents.poll(), RoleType.SUPERVISOR);
		}
		
		validateRol(callAgents.poll(), RoleType.DIRECTOR);
		
	}
	
	/**
	 * Validates add call agent to queue
	 */
	@Test
	public void validatesAddCallAgentToQueue(){
		
		final CallCenterManager callCenterManager = new CallCenterManager(NumberUtils.INTEGER_ZERO, NumberUtils.INTEGER_ZERO, NumberUtils.INTEGER_ZERO);
		callCenterManager.addCallAgent(new Supervisor());
		callCenterManager.addCallAgent(new Director());
		callCenterManager.addCallAgent(new Operator());
		
		validateRol(callCenterManager.getNextCallAgentAvailable().get(), RoleType.OPERATOR);
		validateRol(callCenterManager.getNextCallAgentAvailable().get(), RoleType.SUPERVISOR);
		validateRol(callCenterManager.getNextCallAgentAvailable().get(), RoleType.DIRECTOR);
		
	}


	/**
	 * Method to validate the rol type allowed
	 * 
	 * @param callAgent
	 * @param roleTypeExpected
	 */
	private void validateRol(final CallAgent callAgent, final RoleType roleTypeExpected) {
		
		Assert.assertNotNull(callAgent);
		
		final RoleType roleType = callAgent.getRoleType();
		Assert.assertNotNull(roleType);
		Assert.assertEquals(roleTypeExpected,callAgent.getRoleType());
	}
	
}
