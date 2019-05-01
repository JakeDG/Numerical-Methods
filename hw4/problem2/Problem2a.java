package hw4.problem2;

/* 
 * Developed by Jacob Gidley
 * This program constructs, given a set of 10 pairs points, the least squares approximation of degree 2 and computes the error
 * I tried to code this as close to how you do it on paper with the exact formulas. The code is a little strange, 
 * but that's why it looks the way it does and why I coded this solution the way I did. 
 */

public class Problem2a 
{
	final static double m = 10.0;	// Global iterations count
	
	public static void main(String[] args)
	{
		double x[] = {4, 4.2, 4.5, 4.7, 5.1, 5.5, 5.9, 6.3, 6.8, 7.1};
		double y[] = {102.56, 113.18, 130.11, 142.05, 167.53, 195.14, 224.87, 256.73, 299.50, 326.72};
		double a = 0; 
		double b = 0;
		double xSum = 0;
		double ySum = 0;
		double xSquaredSum = 0;
		double xySum = 0;
		double error = 0;
		
		// Get summation of x values squared
		for(int i = 0; i < m; i++)
		{
			xSum += x[i];
			ySum += y[i];
			xSquaredSum += Math.pow(x[i], 2);
			xySum += x[i] * y[i];
		}
	
		a = (m * xySum - xSum * ySum) / (m * xSquaredSum - Math.pow(xSum, 2));
		b = (xSquaredSum * ySum - xySum * xSum) / (m * xSquaredSum - Math.pow(xSum, 2));
		
		// Calculate error
		for(int i = 0; i < m; i++)
		{
			error += Math.pow((y[i] - (a * x[i] + b)), 2);
		}
		
		System.out.printf("a = %.10f, b = %.10f\n", a, b);
		System.out.printf("P(x) = %.10f(x) + %.10f\n", a, b);
		System.out.printf("Error S = %.10f\n", error);
	}
}
