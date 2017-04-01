import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
public class WordCounter {
	private State state ; //state of word counter
	private final State START = new StartState();
	private final State SINGLE_VOWEL = new SingleVowelState();
	private final State CONSONANT = new ConsonantState();
	private final State NONWORD = new NonWordState();
	private final State MULTIVOWEL = new MultiVowelState();
	private final State HYPHEN = new HyphenState();
	private final State CHARECTER_E = new CharecterEState();	
	private int syllableCount = 0 ; //syllables of URL
	private Class<? extends State> stateNow;
	private String stateName;
	private String nameClass;

	class StartState extends State{
		@Override
		public void handleChar(char c) {
			if ( "Ee".indexOf(c) >= 0) { setState(CHARECTER_E) ;}
			else if(isVowelOrY(c)) {setState(SINGLE_VOWEL); }
			else if(isLetter(c)) setState(CONSONANT);
			else setState(NONWORD);

		}
		public void enterState() {
			syllableCount++;
		}
		
	}
	class SingleVowelState extends State{
		@Override
		public void handleChar(char c) {
			if(isVowel(c)) {setState(MULTIVOWEL); }
			else if(isLetter(c)) {setState(CONSONANT); enterState(); }
			else if(c == '-') {setState(HYPHEN); enterState(); }
			else setState(NONWORD); 
		}
		public void enterState() {
			syllableCount++;
		}
		
	}
	class ConsonantState extends State{
		@Override
		public void handleChar(char c) {
			if ( "Ee".indexOf(c) >= 0) { setState(CHARECTER_E) ;}
			else if(isVowelOrY(c)) {setState(SINGLE_VOWEL); }
			else if(isLetter(c)) { }
			else if(c == '-') {setState(HYPHEN); }
			else setState(NONWORD); 
		}
		public void enterState() {
			syllableCount++;
		}
	}
	class CharecterEState extends State{
		@Override
		public void handleChar(char c) {
			if(isVowel(c)) {setState(MULTIVOWEL); }
			else if(isLetter(c)) { setState(CONSONANT); enterState();}
			else if(c == '-') {setState(HYPHEN); enterState();}
			else setState(NONWORD); 
		}
		public void enterState() {
			syllableCount++;
		}
	}
	class MultiVowelState extends State{
		@Override
		public void handleChar(char c) {
			if(isVowel(c)) { }
			else if(isLetter(c)) { setState(CONSONANT); enterState();}
			else if(c == '-') {setState(HYPHEN); enterState();}
			else setState(NONWORD); 
		}
		public void enterState() {
			syllableCount++;
		}
	}
	class HyphenState extends State{
		@Override
		public void handleChar(char c) {
			if ( "Ee".indexOf(c) >= 0) { setState(CHARECTER_E) ;}
			else if(isVowel(c)) { setState(SINGLE_VOWEL);}
			else if(isLetter(c)) { setState(CONSONANT); }
			else setState(NONWORD); 
		}
		public void enterState() {
			syllableCount++;
		}
	}
	class NonWordState extends State{
		@Override
		public void handleChar(char c) {
			
		}
	}

	/**
	 * Change the state that you want
	 * @param newstate is state that you will change
	 */
	public void setState (State newstate){
		if(newstate != state ) 
			this.state = newstate ;
	}

	/**
	 * Check the character that you want
	 * @param c is character that you want to check
	 * @return true if c is a character
	 */
	public boolean isLetter (char c){
		if(Character.isLetter(c)) return true;
		return false ;
	}
	public boolean isVowelOrY (char c){
		return "AEIOUYaeiouy".indexOf(c)>=0 ;
	}
	public boolean isVowel (char c){
		return "AEIOUaeiou".indexOf(c)>=0 ;
	}
	public int countSyllables (String word){
		syllableCount = 0 ;
		state = START ;
		for( int k=0; k<word.length(); k++ ) {
			state.handleChar(word.charAt(k));
		}
		stateNow = state.getClass();
		stateName = stateNow.getName();
		nameClass = stateName.substring(12,stateName.length());
		if(nameClass.equals("CharecterEState") && syllableCount == 0 || nameClass.equals("SingleVowelState") || nameClass.equals("MultiVowelState") ){
			syllableCount++;
		}
		return syllableCount ;
	}
	abstract class State {
		public abstract void handleChar(char c);
		public void enterState(){ }
	}
}
