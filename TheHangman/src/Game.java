
public class Game {
	
	private String word;
	private String visibleWord;
	
	public void main(String[] args) {
		
		
	}
	
	public Game(String word) {
		this.word = word;
		this.visibleWord = "";
		for(int i = 0; i < word.length(); i++) {
			this.visibleWord += "_";
		}
	}
	
	public void newWord(String word) {
		this.word = word;
		this.visibleWord = "";
		for(int i = 0; i < word.length(); i++) {
			this.visibleWord += "_";
		}
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int tryLetter(Character letter) throws Exception {
		int matches = 0;
		String tmpWord = "";
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == letter && visibleWord.charAt(i) == '_') {
				matches++;
				tmpWord += String.valueOf(word.charAt(i));
			}
			else {
				tmpWord += String.valueOf(visibleWord.charAt(i));
			}
		}
		this.visibleWord = tmpWord;
		return matches;
	}
	
	public String getVisibleWord() {
		return visibleWord;
	}
	
	
}
