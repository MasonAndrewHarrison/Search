package Search1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Read data from the HTML file and stored them
 * in array list.
 * @author Mason Harrison
 */

public class ReadFile {
	
	/**
	 * This takes a file and splits it into an array list.
	 * <p> 
	 * When ever it reads the string in the second parameter 
	 * it end this element in the array and starts a new one.
	 * </p>
	 * @param file name of the file
	 * @param spliter the string that splits the file into the array list
	 * @return the file converted into an array list
	 */
	
	public static ArrayList<websiteObject> article(String file, String spliter){
		String web = "";
	    try {	
		BufferedReader reader = new BufferedReader(new FileReader(file));
	      String line;
	      while((line = reader.readLine()) != null) {
	    	web = web + line;
	      }
	      reader.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    AllowedCharLists allowedCharLists = new AllowedCharLists();
		char[] allowed = allowedCharLists.allowedLarger;
	    
	    String[] array = web.split(spliter);
	    ArrayList<websiteObject> websiteList = new ArrayList<>();
	    for(int i = 0; i < array.length; i++) {
	    	String altered = allowChar(array[i], allowed);
	    	String[] dummy = altered.split(" ");
	    	int wordCount = dummy.length;
			websiteObject temp = new websiteObject(altered, wordCount);
			websiteList.add(temp);
	    }
	    return websiteList;
	}
	
	/**
	 * This takes a file and splits it into an array list.
	 * <p> 
	 * This adds to an array list already made.
	 * </p>
	 * @param file name of the file
	 * @param spliter the string that splits the file into the array list
	 * @param websiteList is the array list being added to
	 * @return the file converted into an array list
	 */
	
	public static ArrayList<websiteObject> article(String file, String spliter,
			ArrayList<websiteObject> websiteList){
		
		String web = ""; 
	    try {	
		BufferedReader reader = new BufferedReader(new FileReader(file));
	      String line;
	      while((line = reader.readLine()) != null) {
	    	web = web + line;
	      }
	      reader.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    AllowedCharLists allowedCharLists = new AllowedCharLists();
		char[] allowed = allowedCharLists.allowedLarger;
	    
	    String[] array = web.split(spliter);
	    for(int i = 0; i < array.length; i++) {
	    	String altered = allowChar(array[i], allowed);
	    	String[] dummy = altered.split(" ");
	    	int wordCount = dummy.length;
			websiteObject temp = new websiteObject(altered, wordCount);
			websiteList.add(temp);
	    }
	    return websiteList;
	}
	
	/**
	 * Takes an HTML file and turns it into an
	 * array list.
	 * @param file 
	 * @return key word array list
	 */
	
	public static ArrayList<keyWord> keyWords(String file){
		
		//take the file and turns it into a string
		String web = "";
	    try {	
		BufferedReader reader = new BufferedReader(new FileReader(file));
	      String line;
	      while((line = reader.readLine()) != null) {
	    	web = web + line;
	      }
	      reader.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    //splits the string into an array
	    String[] array = web.split(" ");
	    
	    //list of allowed characters
	    AllowedCharLists allowedCharLists = new AllowedCharLists();
		char[] allowed = allowedCharLists.allowedSmaller;
	    
	    //puts the array into and array list and alters the words
	    ArrayList<String> stringList = new ArrayList<>();
	    for(int i = 0; i < array.length; i++) {
	    	String str = (array[i]);
	    	String lowerCase = str.toLowerCase();
	    	String altered = allowChar(lowerCase, allowed);
	    	stringList.add(altered);
	    }
	    //removes all words that are just spaces
	    for(int i = 0; i < stringList.size(); i++) {
	    	if(stringList.get(i).equals(" ")) {
	    		stringList.remove(i);
			} 
	    	else if(stringList.get(i).equals("")) {
	    		stringList.remove(i);
	    	}
		}
	    //sets up the keyWords array list
		ArrayList<keyWord> keyWords = new ArrayList<>();
		
		//defines keyWords array list
		for(int i = 0; i < stringList.size(); i++) {
			  String word = stringList.get(i);
			  int wordCount = 0;
			  for(int j = 0; j < stringList.size(); j++) {
				if(word.equals(stringList.get(j))) {  
				wordCount++;
				if(wordCount > 1) {
				  	stringList.remove(j);
			 	  }  
				} 
			  }
			  keyWord temp = new keyWord(word, wordCount);
			  keyWords.add(temp);
		}
		
		//sorts object array list
		boolean unsorted = true;
		while(unsorted) {
			unsorted = false;
			  
			//loops through n
			for(int i = 0; i < keyWords.size() -1; i++) {
				  
				//sets up variables
				int currentNum = keyWords.get(i).repeat;
				int nextNum = keyWords.get(i + 1).repeat;
				keyWord currentObject = keyWords.get(i);
				keyWord nextObject = keyWords.get(i + 1);
				  
				//checks if n > n+1
				if(currentNum > nextNum) {
					  
					//swaps n with n+1
					unsorted = true;
					keyWord temp = currentObject;
					keyWords.set(i, nextObject);
					keyWords.set(i + 1, temp);
				}
			}
		}
	    return keyWords;
	}
	
	/**
	 * Allows certain letters.
	 * @param word is being modified
	 * @param allowed is the white list of charators
	 * @return the allowed letters
	 */
	
	private static String allowChar(String word, char[] allowed) {
		String allowedWord = "";
	    char[] list = word.toCharArray();
		for(int i = 0; i < list.length; i++) {
			for(int j = 0; j < allowed.length; j++)
				if(allowed[j] == list[i]) {
					allowedWord = allowedWord + list[i];
				}
		}
		return allowedWord;
	}
}
