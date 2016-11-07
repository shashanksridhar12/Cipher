
 public class Cipher {
 
	public static void main(String[] args) {
 		
 
 	}
 	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%_;?=:" + '\n' + '\r';
 	/***
 	 * This method rotates every letter in a string using its index and a shift amount
 	 * @param the original string
 	 * @param the amount each letter shifts
 	 * @return the string with shifted letters
 	 */
 	public static String rotationCipherEncrypt(String plain, int shift, String alphabet) {
 		String newText = "";
 		for (int index =0; index < plain.length(); index++) {
 			String character = plain.substring(index, index +1);
 			int newIndex = alphabet.indexOf(character);
 			newIndex += shift;
 			if (newIndex < 0) {
 				newIndex += alphabet.length();
 			}
 			if (newIndex > alphabet.length()) {
 				newIndex = newIndex -  alphabet.length();
 			}
 			String newCharacter =  alphabet.substring(newIndex, newIndex +1);
 			newText += newCharacter;
 			
 			
 		}
 		return newText;
 
 }
 	/***
 	 * This methos takes in a shifted string and a shift amount and returns the original String
 	 * @param The encoded string
 	 * @param The shift amount
 	 * @return the decoded string
 	 */
 	public static String rotationCipherDecrypt(String cipher, int shift, String alphabet) {
 		String plainText = "";
 		for (int index =0; index < cipher.length(); index++) {
 			String character = cipher.substring(index, index +1);
 			int newIndex = alphabet.indexOf(character);
 			newIndex = shift;
 			if (newIndex < 0) {
 				newIndex += alphabet.length();
 			}
 			if (newIndex > alphabet.length()) {
 				newIndex = newIndex -  alphabet.length();
 			}
 			String newCharacter =  alphabet.substring(newIndex, newIndex +1);
 			plainText += newCharacter;
 			
 			
 		}
 		return plainText;
 
 }
 	public static int rotationCipherCrack(String cipher, String alphabet) {
 		for(int i=0; i< 84; i++){
 			String plaintext = Cipher.rotationCipherDecrypt(cipher, i, alphabet);
 			if(Cipher.isEnglish(plaintext)){
 				return i;
 			}
 		}
 		
 		return 1;
 	}
 	/**
 	 * Returns plaintext encrypted by the vigenere cipher encoded with the String code
 	 * @param plainText the plain text to be encrypted.
 	 * @param code the code to use as the encryption key.
 	 * @return returns the encrypted plainText.
 	 */
 	public static String vigenereCipherEncrypt(String plain, String password, String alphabet) {
 		String a = "";
 		for(int i=0; i< plain.length(); i++){
 			String codeLetter = password.substring(i%password.length(),(i%password.length())+1);
 			String plainTextLetter = plain.substring(i, i+1);
 			a+=rotationCipherEncrypt(plainTextLetter, alphabet.indexOf(codeLetter), alphabet);
 		}
 		return a;
 	}
  
 	/**
 	 * Returns the result of decrypting cipherText with the key code.
 	 * @param cipherText the encrypted text.
 	 * @param code the decryption key
 	 * @return returns the decrypted cipherText.
 	 */
 	public static String vigenereCipherDecrypt(String cipher, String password, String alphabet) {
 		String a = "";
 		for(int i=0; i< cipher.length(); i++){
 			String codeLetter = password.substring(i%password.length(),(i%password.length())+1);
 			String cipherTextLetter = cipher.substring(i, i+1);
 			a+=rotationCipherEncrypt(cipherTextLetter, alphabet.length() -  ALPHABET.indexOf(codeLetter),alphabet);
 		}
 		return a;     
  
 	}
 	public static String getLettersOut(String encrypted, int index, int length) {
 		String CipherLetterGroup = "";
 		for(int i = index; i < encrypted.length(); i=i+length) {
 			CipherLetterGroup = CipherLetterGroup + encrypted.substring(index, i+1);
 			
 		}
 		return CipherLetterGroup;
 	}
 	public static String findcodeLetter(String temp, String alphabet) {
 		for(int shiftamount = 0; shiftamount < alphabet.length(); shiftamount++) {
 			String decoded = CipherRotation.rotationCipherDecrypt(temp, shiftamount);
 			LetterBag bag = new LetterBag();
 			for(int x = 0; x < decoded.length(); x++) {
 				bag.add(decoded.substring(x, x+1));
 			}
 			if (bag.getMostFrequent().equals(" ")){
 				return alphabet.substring(shiftamount, shiftamount+1);
 			}
 		}
 		return null;
 	}
 	public static String vigenereCipherCrack(String cipher, int passwordlength, String alphabet) {
 		String code = "";
 		for (int i =0; i<passwordlength; i++){
 			String lettergroup = getLettersOut(cipher,i, passwordlength);
 			code += findcodeLetter(lettergroup, alphabet);
 			
 		}
 		return code;
 		
 
 }
 	public static String vigenereCipherCrackThreeLetter(String cipher, String alphabet) {
 		String code = "";
 		for (int i =0; i<3; i++){
 			String lettergroup = getLettersOut(cipher,i, 3);
 			code += findcodeLetter(lettergroup, alphabet);
 			
 		}
 		return code;
 		
 
 }
 
 }
