package piandv.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
//import org.springframework.security.authentication.encoding.PasswordEncoder;

public class AccountTest {

	@Test
	public void test() {
		Account acc=new Account(217863876,"Bras","AM");
		assertEquals("", "Bras", acc.getFname()); 
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	    String hashedPass = encoder.encodePassword("abc123", null);
	    System.out.println(hashedPass);
	    hashedPass = encoder.encodePassword("npt0424", null);
	    System.out.println(hashedPass);
		//fail("Not yet implemented");
	}

}
