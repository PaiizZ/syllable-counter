
public class SimpleSyllableCounter {
	public int countSyllables( String word ){
		int syllables = 0;
		boolean vowel = false ;
		char c = ' ';
		State state = State.START;
		for( int k=0 ; k < word.length() ; k++){
			c = word.charAt(k);
			if( c == '\'') continue;
			switch(state) {
			case START :
				if(isVowelOrY(c)) { state = state.SINGLE_VOWEL ; syllables++; vowel = true ; }
				else if(isLetter(c)) { state = state.CONSONANT ; }
				else state = State.NONWORD;
				break;
			case CONSONANT : 
				if ( isVowelWithOutE(c)) { state = state.SINGLE_VOWEL ; syllables++; vowel = true ; }
				else if ( "Ee".indexOf(c) >= 0) { state = state.CHARECTER_E ; syllables++; }
				else if(isLetter(c)) { }
				else if(c == '-') { state = state.HYPHEN ; }
				else state = State.NONWORD;
				break;
			case SINGLE_VOWEL : 
				if ( isVowel(c)) { state = state.MULTIVOWEL ; }
				else if(isLetter(c)) { state = state.CONSONANT ; }
				else if(c == '-') { state = state.HYPHEN ; }
				else state = State.NONWORD;
				break;
			case CHARECTER_E : 
				if ( isVowelWithOutE(c) ) { state = state.MULTIVOWEL ; }
				else if(isLetter(c)) { state = state.CONSONANT ; }
				else if(c == '-') { state = state.HYPHEN ; }
				else state = State.NONWORD;
				break;
			case MULTIVOWEL : 
				if ( isVowel(c)) {  }
				else if(isLetter(c)) { state = state.CONSONANT ; }
				else if(c == '-') { state = state.HYPHEN ; }
				break;
			case HYPHEN :
				if ( isVowelOrY(c)) { state = state.SINGLE_VOWEL ; syllables++;  vowel = true ; }
				else if(isLetter(c)) { state = state.CONSONANT ;  }
				else if(c == '-') { state = State.NONWORD ; }
				else state = State.NONWORD;
				break;
			default :
			}
		}
		if(state.name().equals("CHARECTER_E")) {
				syllables--;
				if(!vowel) syllables++;
		};
		return syllables;
	}
	public boolean isLetter (char c){
		return Character.isLetter(c) ;
	}
	public boolean isVowelWithOutE (char c){
		return "AIOUYaiouy".indexOf(c)>=0 ;
	}
	public boolean isVowelOrY (char c){
		return "AEIOUYaeiouy".indexOf(c)>=0 ;
	}
	public boolean isVowel (char c){
		return "AEIOUaeiou".indexOf(c)>=0 ;
	}

}
