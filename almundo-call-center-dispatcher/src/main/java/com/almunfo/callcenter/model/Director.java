package com.almunfo.callcenter.model;

import com.almunfo.callcenter.model.enums.RoleType;

/**
 * Call director in Almundo call center
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 *
 */
public class Director extends CallAgent {

	/** Priority level */
	private static final int DIRECTOR_PRIORITY = Integer.valueOf(1);
	
	/**
	 * Default constructor
	 */
	public Director() {
		setRoleType(RoleType.DIRECTOR);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.almunfo.callcenter.model.Person#getPriority()
	 */
	@Override
	public Integer getPriority() {
		return DIRECTOR_PRIORITY;
	}
}
