package reaf.text;

import java.io.File;

import org.junit.Test;

import reaf.MainApp;
import reaf.file.FileHandler;
import reaf.file.FileHandlerImp;

public class ExtractionTest {
	private static final FileHandler fh = FileHandlerImp.init();
	private static final File description = new File("src/test/resources/description.tsv");
	@Test
	public void test() throws Exception {
		String desc = fh.asString(description);
		MainApp.execute(desc);
	}

}
