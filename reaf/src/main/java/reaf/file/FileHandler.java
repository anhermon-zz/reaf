package reaf.file;

import java.io.File;
import java.util.ArrayList;

/**
 * file handler inteface
 * @author Angel
 *
 */
public interface FileHandler {

	public ArrayList<String[]> read(File file) throws Exception;
}
