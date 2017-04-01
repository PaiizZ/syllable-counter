import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import stopwatch.Stopwatch;
public class Main {
	public static void main(String[] args) {
		final String DICT_URL = "http://se.cpe.ku.ac.th/dictionary.txt";
		WordCounter counter = new WordCounter();
		Stopwatch stopwatch = new Stopwatch();
		int syllables = 0;
		int syllable = 0 ;
		int words = 0;
		String word = "";
		try {
			URL url = url = new URL ( DICT_URL );
			InputStream input = input = url.openStream();
			BufferedReader reader = new BufferedReader( new InputStreamReader (input) );
			stopwatch.start();
			while( (word = reader.readLine()) != null ){
				syllable = counter.countSyllables(word) ;
				if(syllable != 0 ){
					syllables += syllable;
					words++;
				}
			}
			stopwatch.stop();
			System.out.println("Reading words from "+DICT_URL);
			System.out.println(String.format("Counted %d syllables in %d words",syllables,words));
			System.out.println(String.format("Elapsed time: %.3f sec", stopwatch.getElapsed()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
