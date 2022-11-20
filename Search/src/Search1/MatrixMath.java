package Search1;

import java.util.Random;

/**
 * This class does opperation to one or more matricies.
 * <p>
 * 	The purpose of this class it to handle all the math
 * 	behind the sinces.
 * </p>
 * @author Mason Harrison
 */

public class MatrixMath{

	/**
	 * Prints a matrix. 
	 * <p>
	 * 	Align each number to a column and row in the console.
	 * 	This makes it easier to read the matrix.
	 * </p>
	 * @param matrix is the matrix that is printed
	 * @return 
	 */

	public static void print(double[][] matrix) {
		String[][] matrixString = new String[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrixString[i][j] = String.valueOf(matrix[i][j]);
			}
		}
		print(matrixString);
	}
	
	/**
	 * Prints a matrix. 
	 * <p>
	 * 	Align each number to a column and row in the console.
	 * 	This makes it easier to read the matrix.
	 * </p>
	 * @param matrix is the matrix that is printed
	 * @return 
	 */

	public static void print(int[][] matrix) {
		String[][] matrixString = new String[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrixString[i][j] = String.valueOf(matrix[i][j]);
			}
		}
		print(matrixString);
	}
	
	/**
	 * Prints a matrix. 
	 * <p>
	 * 	Align each number to a column and row in the console.
	 * 	This makes it easier to read the matrix.
	 * </p>
	 * @param matrix is the matrix that is printed
	 * @return 
	 */
	
	public static void print(String[][] matrix) {
		
		//check if the matrix is null
		if(matrix == null) {
			System.out.println("ERROR: ");
			System.out.println("Matrix is null");
		}
		else {	
		
			//sets up max length array
			int[] maxLength = new int[matrix[0].length];
		
			//makes array of the max character length in each column
			for(int j = 0; j < matrix[0].length; j++) {
				for(int i = 0; i < matrix.length; i++) {
					int numLength = matrix[i][j].length();		//finds the length of that string
					if(numLength > maxLength[j]) {				//compares current max length to each charactor length
						maxLength[j] = numLength;				//updates max length to a b
				}
			}
		}	
		//loops through matrix
		for(int i = 0; i < matrix.length; i++) {
			System.out.print("| ");
			for(int j = 0; j < matrix[0].length; j++) {
				int numLength = matrix[i][j].length();						//finds the length of that string
				String space = "";									//sets up space with a place holder
				for(int k = numLength - 2; k < maxLength[j]; k++) {	//loops from num length -2 ... max length in j
					space = space + " ";							//adds a space
				}
					System.out.print(matrix[i][j] + space);			//print each number space out evenly
				}
				System.out.println("|");
			}
		}
	}
	
	/**
	 * Adds two matricies.
	 * <p>
	 * 	Will add matrices together assuming that they are the 
	 * 	same length and width.
	 * </p>
	 * @param matrixA is the first matrix
	 * @param matrixB is the second matrix
	 * @return matrixA + matrixB
	 */
	
	public static double[][] add(double[][] matrixA, double[][] matrixB) {

		//sets up the sumMatrix
		double[][] sumMatrix;
		
		//defines matrixA and matrixB
		int rowA = matrixA.length;
		int rowB = matrixB.length;
		
		//defines matrixA's and matrixB's columns
		int columnA = matrixA[0].length;
		int columnB = matrixB[0].length;
		
		//defines the size of the sumMatrix
		sumMatrix = new double[rowA][columnA];
		
		//checks if matrixA and matrixB are the same size
		if (rowA != rowB || columnA != columnB) {
			System.out.println("ERROR: ");
			System.out.println("Matrix A and B are not the same size");
			return null;
		}
		//loops through the sumMatrix 
		for(int i = 0; i < rowA ; i++) {
			for(int j = 0; j < columnA ; j++) {	
				//adds matrixA and matrixB to the sumMatrix
				sumMatrix[i][j] = matrixA[i][j] + matrixB[i][j];
			}
		}
		return sumMatrix;
	}
	
	/**
	 * Multiplies two matricies
	 * <p>
	 * 	Will multiply two matricies together if matrixA's columns 
	 * 	equal matrixB's rows.
	 * </p>
	 * @param matrixA is the first matrix
	 * @param matrixB is the second matrix
	 * @return MatrixA * MatrixB
	 */
	 
	public static double[][] mult(double[][] matrixA, double[][] matrixB){
		
		//sets up the productMatrix
		double[][] productMatrix;
		
		//finds rows length
		int rowA = matrixA.length;
		int rowB = matrixB.length;
		
		//finds columns length
		int columnA = matrixA[0].length;
		int columnB = matrixB[0].length;
		
		//sets defines the productMatrix's rows and columns
		productMatrix = new double[rowA][columnB];
		
		//checks if matrixA's column are equal to matrixB's rows
		if(columnA != rowB) {
			System.out.println("ERROR: ");
			System.out.println("Matrix A's column are not equal to Matrix B's rows");
			return null;
		}
		//checks if all rows are the same in matrixA
		for(int i = 0; i < rowA; i++) {
			if(columnA != matrixA[i].length) {
				System.out.println("ERROR: ");
				System.out.println("Not all rows are the same in Matrix A");
				return null;
			} 
		}
		//checks if all rows are the same in matrixA
		for(int i = 0; i < rowB; i++) {
			if(columnB != matrixB[i].length) {
				System.out.println("ERROR: ");
				System.out.println("Not all rows are the same in Matrix B");
				return null;
			}
		}
		//multiply matrixA and matrixB
			for(int i = 0; i < columnB; i++) {
				for(int j = 0; j < rowA; j++) {
					for(int k = 0; k < rowB; k++) {
						productMatrix[j][i] += matrixA[j][k] * matrixB[k][i];
					}
				}
			}		
		return productMatrix;
	}
	
	/**
	 * Generates a matrix of random numbers
	 * @param max largest possible number in the matrix
	 * @param columns 
	 * @param rows
	 * @return random matrix
	 */
	
	public static int[][] randomInt(int max, int columns, int rows){
		
		// create instance of Random class
	    Random rand = new Random();
		
		//sets up randomMatrix
		int[][] randomMatrix;
		randomMatrix = new int[rows][columns];
		
		//loops through randomMatrix
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				randomMatrix[i][j] = rand.nextInt(max);
			}
		}
		return randomMatrix;
	}
	
	/**
	 * Generates a matrix of random numbers
	 * @param max largest possible number in the matrix
	 * @param columns 
	 * @param rows
	 * @return random matrix
	 */
	
	public static double[][] randomDouble(int max, int columns, int rows){
		
		// create instance of Random class
	    Random rand = new Random();
		
		//sets up randomMatrix
		double[][] randomMatrix;
		randomMatrix = new double[rows][columns];
		
		//loops through randomMatrix
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				randomMatrix[i][j] = (double) rand.nextDouble(max);
			}
		}
		
		return randomMatrix;
	}

}
