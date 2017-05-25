package com.springframework.integration.explore.firstscenario;

import org.springframework.messaging.Message;

public class ConvertMessageToObject {

	public PersonTo convert(Message message){
		final PersonTo personTo = new PersonTo();
		return personTo;
	}
}
