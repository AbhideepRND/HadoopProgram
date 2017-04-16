package com.springframework.integration.explore.firstscenario;

public class ProcessRestRequest {
	
	public PersonTo process(PersonTo personto) {

		System.out.println(personto.getPersonName());
		return personto;
	}

}
