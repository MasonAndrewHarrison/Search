package Search1;

import java.util.ArrayList;

/**
 * Is used to find different matrix hits.
 * @author Mason Harrison
 *
 */

public class MatrixHit {

	/**
	 * Finds how many times a word is used in
	 * an article
	 * @param keyWord is the word being looked for
	 * @param article is being search on
	 * @return hit rate
	 */
	
	private static int repeatValue(String keyWord, String article) {
		int hit = 0;
		  String[] strArray = article.split(" ");
		  for(int i = 0; i < strArray.length; i++) {
			  if(keyWord.equals(strArray[i])) {
				  hit++;
			  }
		  } 
		return hit;
	}
	
	/**
	 * Makes a matrix of all the hits.
	 * <p>
	 * 	Makes a matrix where the rows are are the key words
	 * 	and columns are articles. Matrix is a markov matrix.
	 * </p>
	 * <p>
	 * 	formula:
	 * 	hit = key words in article / all key words
	 * </p>
	 * @param articleList is an array list of all articles
	 * @param keyWordList is an array list of all key words
	 * @return hit matrix
	 */
	
	public static double[][] hitMatrix(ArrayList<websiteObject> articleList,
			ArrayList<keyWord> keyWordList){
		double[][] hitMatrix;
		  hitMatrix = new double[articleList.size()][keyWordList.size()];
		  
		  for(int i = 0; i < hitMatrix.length; i++) {
			  String article  = articleList.get(i).article.toLowerCase();
			  for(int j = 0; j < hitMatrix[1].length; j++) {
				  String keyWord = keyWordList.get(j).word;
				  int repeat = MatrixHit.repeatValue(keyWord, article);
				  //hitMatrix[i][j] = (double)repeat/(double)keyWordList.get(j).repeat;
				  hitMatrix[i][j] = repeat;
			  } 
		  }
		return hitMatrix;	
	}
	public static int[][] hitMatrixInt(ArrayList<websiteObject> articleList,
			ArrayList<keyWord> keyWordList){
		int[][] hitMatrix;
		  hitMatrix = new int[articleList.size()][keyWordList.size()];
		  
		  for(int i = 0; i < hitMatrix.length; i++) {
			  String article  = articleList.get(i).article.toLowerCase();
			  for(int j = 0; j < hitMatrix[1].length; j++) {
				  String keyWord = keyWordList.get(j).word;
				  int repeat = MatrixHit.repeatValue(keyWord, article);
				  //hitMatrix[i][j] = (double)repeat/(double)keyWordList.get(j).repeat;
				  hitMatrix[i][j] = repeat;
			  } 
		  }
		return hitMatrix;	
	}
	
	/**
	 * Makes a matrix that repesents the search
	 * @param input word being search
	 * @param keyWordList array list of all words
	 * @return hitKey
	 */
	
	public static double[][] searchMatrix(String input, ArrayList<keyWord> keyWordList){
		String[] inputArray = input.split(" ");
		  
		  double[][] hitKey = new double[keyWordList.size()][1];;
		  for(int i = 0; i < hitKey.length; i++) {
			  String keyWordStr = keyWordList.get(i).word;
			  for(int j = 0; j < inputArray.length; j++) {
				  if(keyWordStr.equals(inputArray[j])) {
					  hitKey[i][0] = 1;
				  }
			  } 
		  }	  
		  return hitKey;  
	}
	
	public static ArrayList<ArticleHit> articleHitList(double[][] articleHitArray, 
			ArrayList<websiteObject> articleList){
		ArrayList<ArticleHit> articleHitList = new ArrayList<>();
		for(int i = 0; i < articleHitArray.length; i++) {	
			double hit = articleHitArray[i][0];
			if(hit > 0.0) {
				ArticleHit temp = new ArticleHit(articleList.get(i).article, hit);
				articleHitList.add(temp);
			}
		}
		return articleHitList;
	}
	
	public static void printInfo( ArrayList<ArticleHit> articleHitList, double[][] hitKey) {
		
		int resultCount = 0;
		double totalHit = 0;
		
		for(int i = 0; i < articleHitList.size(); i++, resultCount++) {
			totalHit = totalHit + articleHitList.get(i).hit;
		}
		int inputLength = 0;
		for(int i = 0; i < hitKey.length; i++) {
			if(hitKey[i][0] == 1) {
				inputLength++;
			}
		}
		int error = (int)Math.abs(((inputLength - totalHit)/inputLength)*100);
		System.out.println();
		if(error >= 100) {
			error = 100;
			System.out.println("over 100% Percent Error");
			System.out.println(resultCount  + " results");
			System.out.println("complete");
		}
		else if(error <= 0){
			System.out.println("No Errors Detected");
			System.out.println(resultCount  + " results");
			System.out.println("successful 🙂");
		}
		else {
			System.out.println(error + "% Percent Error");
			System.out.println(resultCount  + " results");
			System.out.println("complete");
		}
		
	}
}
