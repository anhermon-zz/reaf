package reaf.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reaf.text.StringUtil;
/**
 * file handler implementation
 * @author Angel
 *
 */
public class FileHandlerImp implements FileHandler {
	private FileHandlerImp(){};
	public static FileHandler init(){
		return new FileHandlerImp();
	}
	private static final Logger logger = LoggerFactory.getLogger(FileHandlerImp.class);
	private static final String DEFAULT_DELIMITER = "\t";
	private String delimiter = DEFAULT_DELIMITER;
	@Override
	public ArrayList<String[]> read(File file) throws Exception{
		ArrayList<String[]> out = new ArrayList<>();
		logger.debug("reading:" + file.getAbsolutePath());
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
			while((line = br.readLine())!=null)
				out.add(line.split(delimiter));
		}
		return out;
	}
	@Override
	public String asString(File file) throws Exception {
		StringBuilder out = new StringBuilder();
		for(String[] row : read(file)){
			out.append(out.length()>0?"\n":"");
			out.append(StringUtil.arrayAsString(row));
		}
		return out.toString().trim().toLowerCase().replaceAll("[\\t\\n]+", " ");
	}
	public String getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

}
