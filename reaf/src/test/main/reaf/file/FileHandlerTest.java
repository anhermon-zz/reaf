package reaf.file;

import java.io.File;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import reaf.text.StringUtil;

/**
 * file handler tester
 * @author Angel
 *
 */
public class FileHandlerTest {
	
	private static File file;
	@Before
	public void setup(){
		file = new File("src/test/resources/test.tsv");
	}
	@Test
	public void ReadTest() throws Exception {
		FileHandler fh = FileHandlerImp.init();
		ArrayList<String[]> out = fh.read(file);
		for (String[] row : out)
				System.out.println(StringUtil.arrayAsString(row));
		Assert.assertNotNull(out);
	}


}
