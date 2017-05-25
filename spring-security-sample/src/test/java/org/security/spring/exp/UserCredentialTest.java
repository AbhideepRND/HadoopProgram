package org.security.spring.exp;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.security.spring.exp.bo.UserCredential;
import org.security.spring.exp.boot.SpringIntegrationBootConfig;
import org.security.spring.exp.dao.impl.GenericDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringIntegrationBootConfig.class)
public class UserCredentialTest {

	@Autowired 
	@Qualifier(value="UserCredentialDaoImpl")
	private GenericDaoImpl<UserCredential> serachDao;
	
	@Test
	public void searchUserTest(){
		final UserCredential userCredential = new UserCredential();
		userCredential.setUsername("Abhideep");
		final List<UserCredential> search = serachDao.search(userCredential);
		Assert.assertEquals(1, search.size());
		
		
	}
}