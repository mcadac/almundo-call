package com.almunfo.callcenter.model;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.Validate;

import com.almunfo.callcenter.model.enums.RoleType;

/**
 * Abstract class to define the information required for each person 
 * registered as able to process a call request 
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public abstract class CallAgent implements Comparable<CallAgent> {

	/** Call agent's id registered in Almundo call center */
	private String id;
	
	/** Person's role in Almundo call center*/
	private RoleType roleType;

	/**
	 * Parent constructor
	 */
	public CallAgent() {
		this.id = UUID.randomUUID().toString();
	}


	/**
	 * Gets priority level to take a call request
	 * 
	 * @return {@link Integer} - priority level
	 */
	public abstract Integer getPriority();
	
	/**
	 * Gets person's id
	 * 
	 * @return {@link String}
	 */
	public String getId() {
		return id;
	}


	/**
	 * Gets person's role
	 * 
	 * @return {@link RoleType}
	 */
	public RoleType getRoleType() {
		return roleType;
	}


	/**
	 * Sets person's role
	 * 
	 * @param roleType
	 */
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	
	/**
	 * Sets person¡s id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	/**
	 * 
	 * @param callAgent
	 * @return
	 */
	@Override
	public int compareTo(final CallAgent callAgent) {
		
		Validate.notNull(callAgent, "Call agent cannot be null");
		Validate.notNull(callAgent.getPriority(), "Call agent priority level cannot be null");
		
		return callAgent.getPriority().compareTo(this.getPriority());
	}
	
	
	/**
	 * Answer call, it has a duration between 5 and 10 seconds.
	 *  
	 * @throws InterruptedException 
	 */
	public void answerCall() throws InterruptedException {

		TimeUnit.SECONDS.sleep( new Random()
				.ints(5, 10)
				.findAny()
				.getAsInt());

	}
	
}
