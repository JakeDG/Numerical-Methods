package hw4.problem3;

/* 	
 * Developed by Jacob Gidley
 * This program applies Jacobi’s iterative method to solve the system of linear equations.
 * The Jama mathematical package is used to perform matrix manipulations and calculations
 */

import Jama.Matrix; // A Java numerical package
 
public class Problem3 
{	
	private static final int N = 25; // Global # of iterations
	
	public static void main(String[]args)
	{	
		Matrix xM, x0; // Matrices to store current and previous values of the x-matrix 
		double[][] a = {{4, 1, 1, 0, 0},
						{-1, -3, 1, 1, 0},
						{2, 1, 5, -1, -1},
						{-1, -1, -1, 4, 0},
						{0, 2, -1, 1, 4}};
		double[] b = {6, 6, -6, 6, 6};
		double[] x = {0, 0, 0, 0, 0};	// Initial guess of zero for all x values
		double TOL = Math.pow(10, -3);	// Error tolerance
		int k = 0;
		
		System.out.println("Starting with inital guess of x = [0,0,0,0,0]^T\n");
		System.out.println("k |	    x_1   	|     	   x_2		|	   x_3		|	   x_4		|	    x_5		|   Error: ||x - X0||	|\n");

		while (k < N)
		{
			x0 = new Matrix(x, 5); 
			for(int i = 0; i < x.length; i++)
			{
				x[i] = (1.0 / a[i][i]) * summation(a, x, b, i);
			}
			
			xM = new Matrix(x, 5);	// Store x values array in a matrix
			
			// Check if error tolerance is met
			if(xM.minus(x0).normInf() > TOL)
			{
				System.out.printf("%d |%20.15f	|%20.15f	|%20.15f	|%20.15f	|%20.15f	|%20.15f	|\n",
									k, x[0], x[1], x[2], x[3], x[4], xM.minus(x0).normInf());
			}
			else
				break;
				
			k++;
		}
	}
	
	/* Calculates the summation part of the algorithm for Jacobi's method */ 
	public static double summation(double[][] a, double[] x, double[] b, int i)
	{
		double sum = 0;
		
		for(int j = 0; j < x.length; j++)
		{
			if (j != i)
			{
				sum += (-a[i][j] * x[j]);
			}
		}
		
		sum += b[i];
		
		return sum;
	}
}
