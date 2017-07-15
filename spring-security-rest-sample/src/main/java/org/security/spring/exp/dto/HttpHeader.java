package org.security.spring.exp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HttpHeader {

	private String userReference;
	private String contextType;
	
	public String getUserReference() {
		return userReference;
	}

	@JsonProperty("Refer")
	public void setUserReference(String userReference) {
		this.userReference = userReference;
	}

	
	public String getContextType() {
		return contextType;
	}

	@JsonProperty("Context-Type")
	public void setContextType(String contextType) {
		this.contextType = contextType;
	}
	
	
	
}
