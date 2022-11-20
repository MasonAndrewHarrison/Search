package Search1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		
		ArrayList<websiteObject> articleList = ReadFile.article("index.html", "<>"); 
		ArrayList<keyWord> keyWordList = ReadFile.keyWords("index.html");
		
		int[][] matrix = MatrixHit.hitMatrixInt(articleList, keyWordList);
	    try {
	      BufferedWriter writer = new BufferedWriter(new FileWriter("hitMatrix.bmp"));	
		//writes matrix to file
		for(int i = 0; i < matrix.length; i++) {
			writer.write("\n");
			for(int j = 0; j < matrix[0].length; j++) {	
				writer.write(matrix[i][j]);			
				}
			}
	    	writer.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	
	    
	    
	    
	    try {
	      BufferedReader reader = new BufferedReader(new FileReader("hitMatrix.bmp"));
	      String line;
	      while((line = reader.readLine()) != null) {
	        System.out.println(line);
	      }
	      reader.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	

}
