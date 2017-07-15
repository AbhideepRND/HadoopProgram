package org.security.spring.exp;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.security.spring.exp.dto.LoginResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class TestRestService {

	public static void main(String[] args) {
		listAllUsers();	
	}
	
	private static HttpHeaders getHeaders(){
	        String plainCredentials="Abhideep:bakshi";
	        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
	         
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "Basic " + base64Credentials);
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        return headers;
	    }
	     
	    /*
	     * Send a GET request to get list of all users.
	     */
	    @SuppressWarnings("unchecked")
	    private static void listAllUsers(){
	        System.out.println("\nTesting listAllUsers API-----------");
	        RestTemplate restTemplate = new RestTemplate(); 
	         
	        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
	         ResponseEntity<LoginResponse> response = restTemplate.exchange("http://localhost:8080/erp/login.json", HttpMethod.GET, request, LoginResponse.class);
	        
	    }
}
