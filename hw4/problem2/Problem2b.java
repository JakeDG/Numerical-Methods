package hw4.problem2;

/* Developed by Jacob Gidley
 * This program constructs, given a set of 10 pairs points, the least squares approximation of degree 1 and computes the error
 * I tried to code this as close to how you do it on paper with the exact formulas. The code is a little strange, 
 * but that's why it looks the way it does and why I coded this solution the way I did. 
 */

import Jama.Matrix; // A Java numerical package

public class Problem2b 
{
	final static double m = 10.0;	// Global iterations count
	
	public static void main(String[] args)
	{
		double x[] = {4, 4.2, 4.5, 4.7, 5.1, 5.5, 5.9, 6.3, 6.8, 7.1};
		double y[] = {102.56, 113.18, 130.11, 142.05, 167.53, 195.14, 224.87, 256.73, 299.50, 326.72};
		double a[][] = new double[10][3];
		double b[][] = new double[10][1];
		double error = 0;
		
		// Fill in arrays with values
		for(int i = 0; i < m; i++)
		{
			a[i][0] = Math.pow(x[i], 2);
			a[i][1] = x[i];
			a[i][2] = 1.0;
			b[i][0] = y[i];
		}
		
		// Create and initialize Matrices
		Matrix A = new Matrix(a);
		Matrix B = new Matrix(b);
		Matrix X = new Matrix(3,1);
		
		// Get least squares matrix
		X = A.solve(B);
		
		// Compute error
		for(int i = 0; i < m; i++)
		{
			error += Math.pow((y[i] - (X.get(0, 0) * Math.pow(x[i], 2) + X.get(1, 0) * x[i] + X.get(2, 0))), 2);
		}
		
		// Output results
		System.out.printf("a = %.10f, b = %.10f, c = %.10f\n", X.get(0,0), X.get(1,0), X.get(2, 0));
		System.out.printf("P_2(x) = %.10f(x^2) + %.10f(x) + %.10f\n", X.get(0,0), X.get(1,0), X.get(2, 0));
		System.out.printf("Error S = %.10f\n", error);
	}
}
