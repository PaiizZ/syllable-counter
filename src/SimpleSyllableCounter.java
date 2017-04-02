/**
 * Enum_State is a value of state
 * @author Wanchanapon Thanwaranurak
 * @version 31/3/2017
 */
enum Enum_State {
	START,
	SINGLE_VOWEL,
	CONSONANT,
	NONWORD,
	MULTIVOWEL,
	HYPHEN,
	CHARECTER_E
}
/**
 * SimpleSyllableCounter is find a vowel of word that you input.
 */
public class SimpleSyllableCounter {
	/**
	 * Check vowel of this word
	 * @param word that you want to check vowel
	 * @return counter a vowel
	 */
	public int countSyllables( String word ){
		int syllables = 0;
		char c = ' ';
		Enum_State state = Enum_State.START;
		for( int k=0 ; k < word.length() ; k++){
			c = word.charAt(k);
			if( c == '\'') continue;
			switch(state) {
			case START :
				if ( "Ee".indexOf(c) >= 0) { state = state.CHARECTER_E ;}
				if(isVowelOrY(c)) { state = state.SINGLE_VOWEL ; }
				else if(isLetter(c)) { state = state.CONSONANT ; }
				else state = state.NONWORD;
				break;
			case SINGLE_VOWEL : 
				if ( isVowel(c)) { state = state.MULTIVOWEL ; }
				else if(isLetter(c)) { state = state.CONSONANT ; syllables++; }
				else if(c == '-') { state = state.HYPHEN ; syllables++;}
				else state = state.NONWORD; 
				break;
			case CONSONANT : 
				if ( "Ee".indexOf(c) >= 0) { state = state.CHARECTER_E ; }
				else if ( isVowelOrY(c)) { state = state.SINGLE_VOWEL ; }
				else if(isLetter(c)) { }
				else if(c == '-') { state = state.HYPHEN ; }
				else state = state.NONWORD;
				break;

			case CHARECTER_E : 
				if ( isVowel(c) ) { state = state.MULTIVOWEL ; }
				else if(isLetter(c)) { state = state.CONSONANT ; syllables++; }
				else if(c == '-') { state = state.HYPHEN ; syllables++;}
				else state = state.NONWORD;
				break;
			case MULTIVOWEL : 
				if ( isVowel(c)) {  }
				else if(isLetter(c)) { state = state.CONSONANT ; syllables++; }
				else if(c == '-') { state = state.HYPHEN ; syllables++; }
				else state = state.NONWORD; 
				break;
			case HYPHEN :
				if ( "Ee".indexOf(c) >= 0) { state = state.CHARECTER_E ; }
				else if ( isVowel(c) ) { state = state.SINGLE_VOWEL ; }
				else if(isLetter(c)) { state = state.CONSONANT ;  }
				else state = state.NONWORD;
				break;
			default :
			}
		}
		if(state.name().equals("CHARECTER_E") && syllables == 0 || state.name().equals("SINGLE_VOWEL") || state.name().equals("MULTIVOWEL")){
			syllables++;
		}
		return syllables;
	}
	/**
	 * Check the character
	 * @param c is character of this word
	 * @return true if c is a character
	 */
	public boolean isLetter (char c){
		return Character.isLetter(c) ;
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


}
