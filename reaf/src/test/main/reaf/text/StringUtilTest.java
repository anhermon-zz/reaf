package reaf.text;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {
	

	@Test
	public void specialCharacterRemovalTest() {
		String input = "thi&&s i#&#s a t!!e?s!t";
		String expected = "this is a test";
		String out = StringUtil.removeNonCharacter(input);
		Assert.assertEquals(expected, out);
	}
	
	@Test
	public void normTest(){
		String input = " Thi$s                  is !!!!!!!!!!!!!!!! a ~~~+_!@ TeSt                   !! #";
		String expected = "this is a test";
		String out = StringUtil.normalize(input);
		Assert.assertEquals(expected, out);
	}

}
