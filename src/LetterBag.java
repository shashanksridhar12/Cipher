public class LetterBag {
	private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private static int[] letterFrequencies;
	
	public LetterBag() {
		letterFrequencies = new int[26];
		
	}
	
	/***
	 * adds letter to the bag
	 * @param letter you want added
	 */
	public void add(String letter) {
		String lower = letter.toLowerCase();
		int index = getIndexForLetter(lower);
		letterFrequencies[index]++;
	}

	/***
	 * finds index of a letter in the alphabet
	 * @param a letter
	 * @return the index
	 */
	private static int getIndexForLetter(String lower) {
		
		return alphabet.indexOf(lower);
	}
	/***
	 * tells number of letters in the bag
	 * @return number of letters
	 */
	public int getTotalWords(){
		int counter = 0;
		for (int i =0; i <26; i++) {
			counter = counter + letterFrequencies[i];
		}
		return counter;
		
	}
	/***
	 * returns number of letters that are unique in the bag
	 * @return number of unique letters
	 */
	public int getNumUniqueWords(){
		int counter = 0;
		for (int i =0; i <26; i++){
			if (letterFrequencies[i]!=0) {
			counter++;
			}
			
		}
		return counter;
	}
	/***
	 * finds number of times letter is in the bag
	 * @param letter to check for
	 * @return number of times it occurs
	 */
	public int getNumOccurances(String letter){
		int index = getIndexForLetter(letter);
		return letterFrequencies[index];
		
	}
	/***
	 * returns most frequent letter in the bag
	 * @return most frequent letter in bag
	 */
	public String getMostFrequent(){
		String mostFrequent = "a";
		for (int i = 0; i <letterFrequencies.length-1; i++) {
			if (letterFrequencies[getIndexForLetter(mostFrequent)] < letterFrequencies[i]) {
				
				mostFrequent = alphabet.substring(i, i+1);
			}
		}
		return mostFrequent;
	}
	
}




