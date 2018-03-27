package com.almunfo.callcenter.model;

import java.io.Serializable;

/**
 * The Class CallRequest to receive a call request
 *
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public class CallRequest implements Serializable {
	
	/** Serial UID */
	private static final long serialVersionUID = -9223355986551521641L;

	/**  Message's id. */
	private String id;
	
	/**  Message attempt. */
	private int attempt;
	
	/**  Message content. */
	private String content;
	
	/**
	 * Default constructor
	 */
	public CallRequest() {
		//Empty
	}
	
	/**
	 * Constructor to set Id
	 * 
	 * @param id - {@link String}
	 */
	public CallRequest(final String id){
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the attempt.
	 *
	 * @return the attempt
	 */
	public int getAttempt() {
		return attempt;
	}

	/**
	 * Sets the attempt.
	 *
	 * @param attempt the new attempt
	 */
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Add an attempt
	 */
	public void addAttempt(){
		attempt = Integer.sum(attempt, 1);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return this.content;
	}
	
}
