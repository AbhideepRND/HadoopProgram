package org.security.spring.exp.enums;

public enum NativeSqlQueryName {

	SelectUserCredential("SelectUserCredential");
	
	private String propertyName;
	
	private NativeSqlQueryName(String propertyName) {
			this.propertyName = propertyName;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
}
