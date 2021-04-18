/*
* The model of the project, encrypting and decrypting stuff
*/
class Cryptor{
	private static final String UPPER_ALPHABET = generateUppercaseAlphabet();
	private static final String LOWER_ALPHABET = generateLowercaseAlphabet();
	
	public static String[] crypt(ClientRequest request){
		if(request.op_code == OpCode.DECRYPT){
			return decrypt(request.message);
		}
		else if(request.op_code == OpCode.ENCRYPT){
			String[] temp = new String[1];
			temp[0] = encrypt(request.message, request.shift_value);
			return temp;
		}
		else if(request.op_code == OpCode.HELP){
			String[] temp = new String[5];
			temp[0] = "Request Formats";
			temp[1] = "1: ENCRYPT | SPACE | SHIFT-VALUE | SPACE | [STRING]";
			temp[2] = "2: DECRYPT | SPACE | [STRING]";
			temp[3] = "3: HELP";
			temp[4] = "4: CLOSE";
			return temp;
		}
		return null;
	}
	
	private static char shiftCharacter(char targetCharacter, int shiftAmount){//kthen karakterin pas zhvendosjes
		if(!Character.isLetter(targetCharacter))
			return targetCharacter;
			
		boolean shiftRight = shiftAmount >= 0;
		shiftAmount = Math.abs(shiftAmount);
		
		String alphabet = (Character.isLowerCase(targetCharacter))? LOWER_ALPHABET : UPPER_ALPHABET;
		int characterIndex = alphabet.indexOf(targetCharacter);
		return alphabet.charAt((shiftRight)? (characterIndex + shiftAmount) % alphabet.length() : 
					 ((characterIndex - shiftAmount) + alphabet.length()) % alphabet.length());
	}
	
	private static String encrypt(String message, int shiftAmount){//enkripton stringun
		char[] shiftedMessage = new char[message.length()];
			
		for(int i = 0; i < message.length(); i++){
			shiftedMessage[i] = shiftCharacter(message.charAt(i), shiftAmount);
		}
		return new String(shiftedMessage);
	}
	
	private static String[] decrypt(String message){
		String[] sentences;
		if(message.equals("")){
			sentences = new String[1];
			sentences[0] = "";
			return sentences;
		}
		
		sentences = new String[26];
		
		for(int i = 0; i < 26; i++){
			sentences[i] = encrypt(message, i);
		}
		return sentences;
	}
	
	private static String generateUppercaseAlphabet(){//gjeneron alfabetin
		char[] alphabet = new char[26];
		int j = 0;
		for(char i = 'A'; i <= 'Z'; i++){
			alphabet[j] = i;
			j++;
		}
		return new String(alphabet);
	}
	
	private static String generateLowercaseAlphabet(){//gjeneron alfabetin
		char[] alphabet = new char[26];
		int j = 0;
		for(char i = 'a'; i <= 'z'; i++){
			alphabet[j] = i;
			j++;
		}
		return new String(alphabet);
	}
}