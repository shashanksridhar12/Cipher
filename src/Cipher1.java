import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
 
public class Cipher1 {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:" + '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of movement.
	 * @param plainText the plain text to be encrypted.
	 * @param shiftAmount the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static void main(String[] args){
		
	}
	public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
		String a="";
		
		for(int i=0; i< plainText.length(); i++){
			int index = ALPHABET.indexOf(plainText.substring(i, i+1));
			a+= ALPHABET.substring((index+shiftAmount)%ALPHABET.length(),((index+shiftAmount)%ALPHABET.length()) +1 );
			
		}
		return a;
	}
	
	
 
	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the rotation cipher.
	 * @param cipherText the encrypted text.
	 * @param shiftAmount the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipherText, int shiftAmount) {
			int shifta = shiftAmount%ALPHABET.length();
			return rotationCipherEncrypt(cipherText,ALPHABET.length()-shifta );
	}
 
	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the String code
	 * @param plainText the plain text to be encrypted.
	 * @param code the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plainText, String code) {
		String a = "";
		for(int i=0; i< plainText.length(); i++){
			String codeLetter = code.substring(i%code.length(),(i%code.length())+1);
			String plainTextLetter = plainText.substring(i, i+1);
			a+=rotationCipherEncrypt(plainTextLetter, ALPHABET.indexOf(codeLetter));
		}
		return a;
	}
 
	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * @param cipherText the encrypted text.
	 * @param code the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipherText, String code) {
		String a = "";
		for(int i=0; i< cipherText.length(); i++){
			String codeLetter = code.substring(i%code.length(),(i%code.length())+1);
			String cipherTextLetter = cipherText.substring(i, i+1);
			a+=rotationCipherEncrypt(cipherTextLetter, ALPHABET.length() - ALPHABET.indexOf(codeLetter));
		}
		return a;     
 
	}
 
	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *          The plaintext string you wish to remove illegal characters from
	 * @param alphabet
	 *          A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i <  plaintext.length(); i++) { 			// loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) 	// get index of char
				b.append(plaintext.charAt(i)); 									// if it exists, keep it
			else
																												// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
																																				// a
																																				// message
						+ "\"");
		}
		return b.toString();
	}
 
	/**
	 * returns true if plaintext is valid English.
	 * 
	 * @param plaintext
	 *          the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	private static boolean isEnglish(String plaintext) {
		// YOU WRITE THIS METHOD!
		return false;
	}
 
	/**
	 * Returns a single String of a text file's content.
	 * 
	 * @param filepath
	 *          A String containing the path to the file you wish to read
	 * @return String returns the contents of the file as a String
	 */
	public static String getFileAsString(String filepath) {
		Scanner s;
		StringBuilder sb = new StringBuilder(500000);
		try {
			s = new Scanner(new FileReader(filepath));
			s.useDelimiter("");
 
			while (s.hasNext()) {
				String n = s.next();
				sb.append(n);
			}
			
			s.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		
		return sb.toString();
	}
 
	/**
	 * Writes the contents of a String to a new text file.
	 * 
	 * @param filepath
	 *          A String containing the path to the file you wish to create
	 * @param text
	 *          The String you wish to write to the file.
	 */
	public static void writeStringToFile(String filepath, String text) {
		try {
			PrintWriter out = new PrintWriter(filepath);
			for (int i = 0; i <  text.length(); i++) {
				char n = text.charAt(i);
				out.print(n);
			}
			out.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
 


public static int bruteForceCrackRotationCipher(String ciphertext) {
		for(int i=0; i< 84; i++){
			String plaintext = Cipher1.rotationCipherDecrypt(ciphertext, i);
			if(Cipher1.isEnglish(plaintext)){
				return i;
			}
		}
		
		return -1;
	}
}
 
//I am not able to figure out the solution to problem 2. I can come during FLEX to finish the problem.
