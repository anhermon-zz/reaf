package reaf.text;

import java.util.LinkedHashSet;
import java.util.Set;

import org.tartarus.martin.Stemmer;

/**
 * General String utilities
 * 
 * @author Angel
 *
 */
public class StringUtil {
	/**
	 * {@link StringUtil} is meant to group static methods, and is not mean to
	 * be instantiated.
	 */
	private StringUtil() {
	}

	/**
	 * Removes non-character from text.
	 * 
	 * @param input
	 * @return
	 */
	public static String removeNonCharacter(String input) {
		return input.replaceAll("[^\\p{L}\\p{Nd}\\s]+", "");
	}

	public static String reduceSpaces(String input) {
		return input.replaceAll("\\s+", " ");
	}

	/**
	 * Converts input String to lower case, trims and reduces spaces, remove
	 * special characters.
	 * 
	 * @param in
	 * @return
	 */
	public static String normalize(String in) {
		in = in.toLowerCase();
		in = removeNonCharacter(in);
		in = reduceSpaces(in).trim();
		return in;
	}

	public static String stemm(String in) {
		Stemmer s = new Stemmer();
		s.add(in.toCharArray(), in.length());
		s.stem();
		return s.toString();
	}

	/**
	 * Generates n-grams from input string and input n.
	 * @param sentence input string
	 * @param n
	 * @return n-grams
	 */
	public static Set<String> nGrams (String sentence,int n) {
		String[] words = sentence.split("\\W+");
		StringBuilder sb = new StringBuilder();
		int total  = words.length;
		Set<String> out = new LinkedHashSet<>();
		for(int i=0;i<=total;i++) {
        	sb.delete(0, sb.length());
        	for (int j = i; j < i+n && j < total; j++) {
        	  if(sb.length() > 0) sb.append(" ");
        		sb.append(words[j]);
        		String word = sb.toString().trim().intern();
        		out.add(word);
        	}
        }
        return out;
    }
	
	public static String nGramString(String sentence,int n) {
		StringBuilder sb = new StringBuilder();
		Set<String> ng = nGrams (sentence,n);
		for(String str : ng){
			if (sb.length() > 0)
				sb.append(" ");
			sb.append(str.replaceAll("\\s+", ""));
		}
		return sb.toString();
	}
	
	/**
	 * Converts String[] to tab (\t) delimited {@link String}
	 * @param in String[]
	 * @return tab delimited String
	 */
	public static final String arrayAsString(String[] in){
		StringBuilder sb = new StringBuilder();
		for(String field : in){
			if(sb.length()>0)
				sb.append("\t");
			sb.append(field);
		}
		return sb.toString();
	}
}
