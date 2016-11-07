import java.util.Arrays;

public class CipherRotation {

	public static void main(String[] args) {
		System.out.println(rotationCipherEncrypt("abc ",5));
		System.out.println(rotationCipherDecrypt("fghb",5));
		System.out.println(vigenereCipher("abcd","bbb"));
		int [] randomArray = randomPermutation(30);
		System.out.println(Arrays.toString(getWords("Hello my")));
		//System.out.println(Arrays.toString(randomArray));
		System.out.println(substitutionCipher("abcd", randomArray));
		
		
	}
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz .,!";
	
	
	/***
	 * This method rotates every letter in a string using its index and a shift amount
	 * @param the original string
	 * @param the amount each letter shifts
	 * @return the string with shifted letters
	 */
	public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
		String newText = "";
		for (int index =0; index < plainText.length(); index++) {
			String character = plainText.substring(index, index +1);
			int newIndex = ALPHABET.indexOf(character);
			newIndex += shiftAmount;
			if (newIndex < 0) {
				newIndex += ALPHABET.length();
			}
			if (newIndex > ALPHABET.length()) {
				newIndex = newIndex - ALPHABET.length();
			}
			String newCharacter =  ALPHABET.substring(newIndex, newIndex +1);
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
	public static String rotationCipherDecrypt(String newText, int shiftAmount) {
		String plainText = "";
		for (int index =0; index < newText.length(); index++) {
			String character = newText.substring(index, index +1);
			int newIndex = ALPHABET.indexOf(character);
			newIndex -= shiftAmount;
			if (newIndex < 0) {
				newIndex += ALPHABET.length();
			}
			if (newIndex > ALPHABET.length()) {
				newIndex = newIndex - ALPHABET.length();
			}
			String newCharacter =  ALPHABET.substring(newIndex, newIndex +1);
			plainText += newCharacter;
			
			
		}
		return plainText;

}
	public static String bruteForceRotationCipher(String ciphertext) {
		for(int shiftamount = Integer.MIN_VALUE; shiftamount < Integer.MAX_VALUE; shiftamount++) {
			if (isEnglish(rotationCipherDecrypt(ciphertext,shiftamount))) {
				return rotationCipherDecrypt(ciphertext,shiftamount);
			}
		}
		return ciphertext;
	}
	private static boolean isEnglish(String plaintext) {
		String[] arr = getWords(plaintext);
		
		int numberWordsEnglish = 0;
		for (int i = 0; i < arr.length; i++) {
			if (dictionary.isWord(arr[i])) {
				numberWordsEnglish++;
			}
		}
		if (((numberWordsEnglish) / (arr.length)) == 0.8) {
			return true;
		}
		
		return false;
	}

	private static String[] getWords(String plaintext) {
		int currentSpaceIndex = plaintext.indexOf(" ");
		int prevSpace = 0;
		int nextFree = 0;
		String output[] = new String[10];
		while (currentSpaceIndex != -1) {
			String word = plaintext.substring(prevSpace+1, currentSpaceIndex);
			
			if (!word.equals(" ")) {
				
				output[nextFree] = word;
				nextFree++;
			}
		}
		return null;
		
	}
	/***
	 * This method shifts each letter of a string differently
	 * @param the original string
	 * @param an array of the shifts
	 * @return the shifted string
	 */
	public static String substitutionCipher(String plaintext, int[] permutation) {
			String newString ="";
		for (int index =0; index < plaintext.length(); index++) {
			String character = plaintext.substring(index, index +1);
			int newIndex = ALPHABET.indexOf(character);
			newIndex = permutation[newIndex];
			String newCharacter = ALPHABET.substring(newIndex, newIndex +1);
			newString += newCharacter;

	}
		return newString;

	
	}
	/***
	 * This method checks if an array of permutations is valid
	 * @param an array of permutations
	 * @return boolean of if it is valid
	 */
	public static boolean isValid(int[] permutation) {
		for (int i = 0; i < permutation.length; i++) {
			for (int j = i+1; j < permutation.length; j++) {
				if (i == j) {
					return false;
				}
			}

		}
		return true;
	}
	/***
	 * This method prints an array of an inputted length with random values
	 * @param the length of the array
	 * @return the array with values
	 */
	public static int[] randomPermutation(int length) {
		int [] randomPermutations = new int[length];
		
		do {
			
			for (int i = 0; i < length; i ++) {
			randomPermutations[i] = (int)(1+ Math.random() *length);
		}
		} while (!isValid(randomPermutations)); 
			
		
		return randomPermutations;
	}
	
		
		
		
		public static String vigenereCipher(String plainText, String codeWord) {
			int [] indexes = indexes(codeWord,plainText);
			String string = "";
			for (int i = 0; i < plainText.length(); i++) {
				String character = plainText.substring(i, i+1);
				int index= ALPHABET.indexOf(character);
				index += indexes[i];
				index %= ALPHABET.length();
				string += ALPHABET.substring(index, index+1);
				
			}
			return string;
		}
		public static String vigenereDecipher(String string, String codeWord) {
			int [] indexes = indexes(codeWord,string);
			String plainText = "";
			for (int i = 0; i < string.length(); i++) {
				String character = string.substring(i, i+1);
				int index= ALPHABET.indexOf(character);
				index -= indexes[i];
				while (index < 0) {
					index += ALPHABET.length();
				}
				plainText += ALPHABET.substring(index, index+1);
				
			}
			return plainText;
		}
		
		
		public static int[] indexes(String codeWord, String plaintext) {
			int [] indexes = new int [codeWord.length()];
			int [] finalIndexArray = new int [plaintext.length()];
			int finalIndex = 0;
			
			for (int index = 0; index < codeWord.length(); index++) {
				String character = codeWord.substring(index,index+1);
				indexes[index]= ALPHABET.indexOf(character);
			}

			for (int i = 0; i < finalIndexArray.length; i++) {
				finalIndex = i%indexes.length;
				
				finalIndexArray[i] = indexes[finalIndex];			
				
			} 
			return finalIndexArray;
		}

	}


