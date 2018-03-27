package com.almunfo.callcenter.model;

import com.almunfo.callcenter.model.enums.RoleType;

/**
 * Call operator in Almundo callcenter
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public class Operator extends CallAgent {

	/** Priority level */
	private static final Integer OPERATOR_PRIORITY = Integer.valueOf(3);
	
	/**
	 * Default constructor
	 */
	public Operator() {
		setRoleType(RoleType.OPERATOR);
	}


	/*
	 * (non-Javadoc)
	 * @see com.almunfo.callcenter.model.Person#getPriority()
	 */
	@Override
	public Integer getPriority() {
		return OPERATOR_PRIORITY;
	}
	
}
