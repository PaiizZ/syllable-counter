import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
	public static void main(String[] args) {
		final String DICT_URL = "http://se.cpe.ku.ac.th/dictionary.txt";
		SimpleSyllableCounter counter = new SimpleSyllableCounter();
		int actual = 0;
		try {
			URL url = url = new URL ( DICT_URL );
			InputStream input = input = url.openStream();
			BufferedReader reader = new BufferedReader( new InputStreamReader (input) );
			while( true ){
				String word = reader.readLine();
				if(word == null) break;
				actual += counter.countSyllables(word);
			}
			System.out.print(actual);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
