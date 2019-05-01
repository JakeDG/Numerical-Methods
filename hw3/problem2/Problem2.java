package hw3.problem2;

/* 
 * Developed by Jacob Gidley
 * This program uses Simpson’s method to evaluate an integral when n subdivisions are used
 * and calculates the absolute and relative error between the real solution and Simpson's method's result
 */

public class Problem2 
{
	private static final double exact = 2.0 / 7.0; // The exact value of the integral (globally available)
	
	public static void main(String[] args) 
	{
		int[] n = {2, 4, 8, 16, 32, 64, 128, 256};	// Sub-division n values
		double 	a = 0.0, b = 1.0,	// Interval values
				h,				// h value
				sum,			// Sn(f) summation value 
				snf,			// Sn(f) estimation value
				prevError = 0, 	// Previous error value
				j;				// Used to progress through the interval's sub-divisions and be used in f(x)
		
		System.out.println("n\t\tSn(f)\t\t\tAbsolute Error\t\t\tRelative Error\t\t\tError Ratio");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		
		// Calculate Sn(f) estimation
		for(int i = 0; i < n.length; i++)
		{
			j = a;	// Set starting n iteration value
			h = (b-a)/n[i];	// Get h value
			sum = 0;	
			
			int k = 0; // Counter to iterate through n-iterations of the interval
			while(k <= n[i])
			{
				if (k == 0 || k == n[i]) // if 1st or last iteration , then f(j)
					sum += f(j);
				else if (k % 2 != 0) // k is odd, then 4f(j)
					sum += 4 * f(j);
				else				// k is even, then 2f(j)
					sum += 2 * f(j);
				
				j += h;	// Increment j to next value in interval
				k++;
			}
			
			snf = (h/3.0) * sum; // Get the estimation
			
			// Print values
			if (prevError == 0)
			{
				System.out.printf("%d\t%.17f\t%23.15f\t\t%23.15f\t\t\t%7s\n", n[i], snf, absoluteError(snf), relativeError(snf), "N/A");
			}
			else
			{
				System.out.printf("%d\t%.17f\t%23.15f\t\t%23.15f\t%30.15f\n", n[i], snf, absoluteError(snf), relativeError(snf), prevError / snf);
			}
			
			prevError = absoluteError(snf);
		}
	}
	
	// Calculates the value of f(x) given x
	public static double f(double x)
	{
		return Math.pow(x, 5.0/2.0);
	}
	
	// Calculates the absolute error
	public static double absoluteError(double x)
	{
		return Math.abs(exact - x);
	}
	
	// Calculates the relative error
	public static double relativeError(double x)
	{
		return Math.abs((exact - x) / exact);
	}
}
