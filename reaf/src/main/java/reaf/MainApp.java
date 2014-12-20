package reaf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import reaf.file.FileHandler;
import reaf.file.FileHandlerImp;
import reaf.text.StringUtil;

public class MainApp {
	private static final FileHandler fh = FileHandlerImp.init();
	private static final StandardAnalyzer analyzer = new StandardAnalyzer(); 
	private static final Directory index = new RAMDirectory();
	private static final IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
	
	public static void main(String[] args) throws Exception{
		execute("LG Optimus G");
	}

	public static void execute(String q) throws Exception, ParseException, IOException {
		ArrayList<String[]> out = loadFile("src/main/resources/cellphones.tsv");
		index(out);
		executeQuery(q,1000);
	}
	
	private static void executeQuery(String querystr,int hitsPerPage) throws ParseException,IOException {
		Query query = buildQuery(querystr);
		try(IndexReader reader = DirectoryReader.open(index)){
		    IndexSearcher searcher = new IndexSearcher(reader);
			TopDocs results = searcher.search(query, hitsPerPage);
			int count = results.scoreDocs.length;
			System.out.println("count:" + count);
			for(ScoreDoc doc : results.scoreDocs){
				Document d = searcher.doc(doc.doc);
				System.out.println("Attribute:" + d.get("attribute") + " Value:" + d.get("value") + " Score:" + doc.score);
			}
	  }
	}
	
	/**
	 * Build an instance of {@link Query} from a given string.
	 * Input string is converted to lower case, trimmed, and expended with nGrams.
	 * @param querystr input String
	 * @return {@link Query}
	 * @throws ParseException
	 */
	private static Query buildQuery(String querystr) throws ParseException {
		querystr = StringUtil.nGramString(querystr.toLowerCase().trim(),3);
		QueryParser queryParser = new QueryParser("value_norm", analyzer);
		queryParser.setDefaultOperator(QueryParser.Operator.OR);
		Query query = queryParser.parse(QueryParser.escape(querystr));
		System.out.println("query:" + query);
		return query;
	}
	private static ArrayList<String[]> loadFile(String path) throws Exception {
		return fh.read(new File(path));
	}
	private static void index(ArrayList<String[]> input) throws Exception {
	  try(IndexWriter w = new IndexWriter(index, config)){
		  for(String[] row : input){
		    addDoc(w, row[0], row[1]);
		  }
	  }
	}
	
	private static void addDoc(IndexWriter w, String attribute, String value) throws IOException {
	  Document doc = new Document();
	  doc.add(new StringField("attribute", attribute, Field.Store.YES));
	  doc.add(new TextField("value", value, Field.Store.YES));
	  doc.add(new TextField("value_norm", StringUtil.normalize(value).replaceAll("\\s+", ""), Field.Store.NO));
	  w.addDocument(doc);
	}
	
}
