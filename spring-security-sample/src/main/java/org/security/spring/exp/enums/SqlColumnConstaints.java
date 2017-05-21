package org.security.spring.exp.enums;

public enum SqlColumnConstaints {

	Username("username"),
	Password("pasword"),
	Role("roles");
	private String columnName; 
	
	private SqlColumnConstaints(String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
}
