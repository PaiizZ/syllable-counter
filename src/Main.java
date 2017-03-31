import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
		public static void main(String[] args) {
			final String DICT_URL = "http://se.cpe.ku.ac.th/dictionary.txt";
			URL url = null;
			try {
				url = new URL ( DICT_URL );
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputStream input = null;
			try {
				input = url.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader reader = new BufferedReader( new InputStreamReader (input) );
		}
}
