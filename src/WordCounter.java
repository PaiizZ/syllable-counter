import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * WordCounter is find a vowel of word that you input and has each other State class inside.
 * @author Wanchanapon Thanwaranurak
 * @version 31/3/2017
 */
public class WordCounter {
	private State state ; //state of word counter
	private final State START = new StartState(); // start state
	private final State SINGLE_VOWEL = new SingleVowelState(); // single vowel state
	private final State CONSONANT = new ConsonantState(); // consonant state
	private final State NONWORD = new NonWordState(); // non word state
	private final State MULTIVOWEL = new MultiVowelState(); // multi vowel state
	private final State HYPHEN = new HyphenState(); // hyphen state
	private final State CHARACTER_E = new CharacterEState(); // character e state
	private int syllableCount = 0 ; //syllables of URL
	private Class<? extends State> stateNow;
	private String stateName;
	private String nameClass;
	/**
	 * Start state check of a state for first character of word.
	 */
	class StartState extends State{
		@Override
		public void handleChar(char c) {
			if ( "Ee".indexOf(c) >= 0) { setState(CHARACTER_E) ;}
			else if(isVowelOrY(c)) {setState(SINGLE_VOWEL); }
			else if(isLetter(c)) setState(CONSONANT);
			else setState(NONWORD);

		}
		public void enterState() {
			syllableCount++;
		}

	}
	/**
	 * Single vowel state is a state for first vowel of word.
	 */
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
	/**
	 * Consonant state is a state for letter of word.
	 */
	class ConsonantState extends State{
		@Override
		public void handleChar(char c) {
			if ( "Ee".indexOf(c) >= 0) { setState(CHARACTER_E) ;}
			else if(isVowelOrY(c)) {setState(SINGLE_VOWEL); }
			else if(isLetter(c)) { }
			else if(c == '-') {setState(HYPHEN); }
			else setState(NONWORD); 
		}
		public void enterState() {
			syllableCount++;
		}
	}
	/**
	 * Character E state is a state for E character of word.
	 */
	class CharacterEState extends State{
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
	/**
	 * Multi vowel state is a state for vowel more than one of word.
	 */
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
	/**
	 * Hyphen state is a state for '-' of word.
	 */
	class HyphenState extends State{
		@Override
		public void handleChar(char c) {
			if ( "Ee".indexOf(c) >= 0) { setState(CHARACTER_E) ;}
			else if(isVowel(c)) { setState(SINGLE_VOWEL);}
			else if(isLetter(c)) { setState(CONSONANT); }
			else setState(NONWORD); 
		}
		public void enterState() {
			syllableCount++;
		}
	}
	/**
	 * Non word state is a state for not letter and vowel of word.
	 */
	class NonWordState extends State{
		@Override
		public void handleChar(char c) {

		}
	}

	/**
	 * Change the state that want to move
	 * @param newstate is state that you will change
	 */
	public void setState (State newstate){
		if(newstate != state ) 
			this.state = newstate ;
	}

	/**
	 * Check the character
	 * @param c is character of this word
	 * @return true if c is a character
	 */
	public boolean isLetter (char c){
		if(Character.isLetter(c)) return true;
		return false ;
	}
	/**
	 * Check a vowel and y character
	 * @param c is character of this word
	 * @return true if c is a vowel and y
	 */
	public boolean isVowelOrY (char c){
		return "AEIOUYaeiouy".indexOf(c)>=0 ;
	}
	/**
	 * Check a vowel
	 * @param c is character of this word
	 * @return true if c is a vowel
	 */
	public boolean isVowel (char c){
		return "AEIOUaeiou".indexOf(c)>=0 ;
	}
	/**
	 * Check vowel of this word
	 * @param word that you want to check vowel
	 * @return counter a vowel
	 */
	public int countSyllables (String word){
		syllableCount = 0 ;
		state = START ;
		for( int k=0; k<word.length(); k++ ) {
			state.handleChar(word.charAt(k));
		}
		stateNow = state.getClass();
		stateName = stateNow.getName();
		nameClass = stateName.substring(12,stateName.length());
		if(nameClass.equals("CharacterEState") && syllableCount == 0 || nameClass.equals("SingleVowelState") || nameClass.equals("MultiVowelState") ){
			syllableCount++;
		}
		return syllableCount ;
	}
	/**
	 * State is abstract class that has a method handleChar and enterState
	 */
	abstract class State {
		/**
		 * Check condition for move to another state.
		 * @param c is character of this word
		 */
		public abstract void handleChar(char c);
		/**
		 * updater vowel 
		 */
		public void enterState(){ }
	}
}
