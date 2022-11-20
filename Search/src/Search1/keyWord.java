package Search1;

/**
 * Contains information about every word used in all the articles
 * @author Mason Harrison
 */

public class keyWord {
	
	String word;
	int repeat;
	double weight; 
	
	public keyWord(String word, int repeat) {
		this.word = word;
		this.repeat = repeat;
		this.weight = 1/(double)repeat;
		
		
	} 

	

	

	

}
