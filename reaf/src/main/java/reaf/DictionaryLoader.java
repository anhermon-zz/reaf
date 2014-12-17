package reaf;

import java.io.File;
import java.util.ArrayList;

import reaf.file.FileHandler;
import reaf.file.FileHandlerImp;

/**
 * load dictionary to database
 * @author Angel
 *
 */
public class DictionaryLoader {
	private final FileHandler fh = FileHandlerImp.init();

	public static void main(String args[]) throws Exception {
		DictionaryLoader dl = DictionaryLoader.init();
		dl.load(new File("src/main/resources/cellphones.tsv"));
	}
	/**
	 * initiate instance of {@link DictionaryLoader}
	 * @return {@link DictionaryLoader}
	 */
	public static DictionaryLoader init() {
		return new DictionaryLoader();
	}
	private DictionaryLoader() {}
	/**
	 * Receives {@link File} containing attributes and values, and loads into database
	 * @param in
	 * @throws Exception 
	 */
	public void load(File in) throws Exception{
		ArrayList<String[]> data = fh.read(in);
	}
	/**
	 * TODO:Choose an orm, and use one to push dictionary to database
	 */
}
