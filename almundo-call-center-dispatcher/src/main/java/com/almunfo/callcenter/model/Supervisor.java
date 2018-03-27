package com.almunfo.callcenter.model;

import com.almunfo.callcenter.model.enums.RoleType;

/**
 * Call supervisor in Almundo call center
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 * 
 */
public class Supervisor extends CallAgent {

	/** Priority level */
	private static final Integer SUPERVISOR_PRIORITY = Integer.valueOf(2);
	
	/**
	 * Default supervisor
	 */
	public Supervisor() {
		setRoleType(RoleType.SUPERVISOR);
	}

	/*
	 * (non-Javadoc)
	 * @see com.almunfo.callcenter.model.CallAgent#getPriority()
	 */
	@Override
	public Integer getPriority() {
		return SUPERVISOR_PRIORITY;
	}
}
