package Search1;

import java.util.ArrayList;
import java.util.Scanner;

public class SourceFile {

	public static void mmain(String[] args) {
		
		ArrayList<websiteObject> articleList = ReadFile.article("index.html", "<>"); 
		ArrayList<keyWord> keyWordList = ReadFile.keyWords("index.html");
		
		double[][] hitMatrix = MatrixHit.hitMatrix(articleList, keyWordList);
		
		System.out.print("search: ");
		
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		String input = scn.nextLine();
		input = input.toLowerCase();

		System.out.println(input);
		System.out.println();
		
		double[][] searchMatrix = MatrixHit.searchMatrix(input, keyWordList);
		double[][] articleHitArray = MatrixMath.mult(hitMatrix, searchMatrix);
		
		ArrayList<ArticleHit> articleHitList = MatrixHit.articleHitList(articleHitArray, articleList);
		
		for(int i = 0; i < articleHitList.size(); i++) {
			System.out.println(articleHitList.get(i).article);
			System.out.println(articleHitList.get(i).hit);
			System.out.println();
		}
		
		MatrixHit.printInfo(articleHitList, searchMatrix);
		
	}
}