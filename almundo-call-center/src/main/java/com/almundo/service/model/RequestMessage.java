package com.almundo.service.model;

import java.io.Serializable;

/**
 * The Class RequestMessage to create a call request
 *
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
public class RequestMessage implements Serializable {
	
	/** Serial UID */
	private static final long serialVersionUID = -4055359211709321264L;

	/**  Message's id. */
	private String id;
	
	/**  Message attempt. */
	private int attempt;
	
	/**  Message content. */
	private String content;

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
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return this.content;
	}
	
}
