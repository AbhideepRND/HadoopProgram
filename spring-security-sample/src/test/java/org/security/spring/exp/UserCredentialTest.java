package org.security.spring.exp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.security.spring.exp.bo.UserCredential;
import org.security.spring.exp.boot.ApplicationContext;
import org.security.spring.exp.dao.impl.GenericDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ApplicationContext.class)
public class UserCredentialTest {

	@Autowired 
	private GenericDaoImpl<UserCredential> serach;
	
	@Test
	public void searchUserTest(){
		final UserCredential userCredential = new UserCredential();
		userCredential.setUsername("Abhideep");
		final List<UserCredential> search = serach.search(userCredential);
		
		
		
	}
}
