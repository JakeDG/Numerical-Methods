package hw3.problem3;

/* 
 * Developed by Jacob Gidley
 * This program finds the solution of a nonlinear system by using Newton's method
 */

public class Problem3 {

	public static void main(String[] args) 
	{
		// Inital guesses
		double x0 = 2.98;
		double y0 = 0.15;
	
		double x = 0, y = 0; // Next guesses
		boolean firstItr = true; // 1st iteration flag
		double jMatrix[], fMatrix[]; // Matrices 
		
		// Counter
		int k = 0;
		
		System.out.print("k\t\txk\t\t\tyk\t\t\txk+1\t\t\tyk+1\t\t    xk+1 - xk\n");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		
		do 
		{
			if (firstItr == false) // Check if 1st iteration
			{
				x0 = x;
				y0 = y;
			}
			else
			{
				firstItr = false;
			}
			
			// Get the Jacobian matrix
			jMatrix = getJacobianMatrix(x0,y0);
			
			// Get function matrix
			fMatrix = getFuncMatrix(x0,y0);
		
			gaussElimination(jMatrix, fMatrix); // Perform Gaussian elimination
			jMatrix = inverseMatrix(jMatrix);	// Invert the Jacobian matrix
			fMatrix = multiplyMatrices(jMatrix, fMatrix); // Multiply the inverse of the Jacobian by the function matrix
			
			x = fMatrix[0] + x0; // Get x value
			y = fMatrix[1] + y0; // Get y value
			
			System.out.printf("%d\t%10.15f\t%10.15f\t%10.15f\t%10.15f\t%10.15f\n", k++, x0, y0, x, y, Math.abs(x - x0));

		} while (Math.abs(x - x0) >= Math.pow(10, -12)); // Keep iterating until the absolute error of x <= 10E-12
		
		// Print final result of x and y
		System.out.printf("\nFinal value of x: %.15f\n", x0);
		System.out.printf("Final value of y: %.15f\n", y0);
	}

	public static double[] getJacobianMatrix(double x, double y)
	{
		double[] j = new double[4];
		
		j[0] = (2 * x) + Math.pow(y,3); // pf/px
		j[1] = 3 * x * Math.pow(y,2);	// pf/py
		j[2] = 6 * x * y;				// pg/px
		j[3] = (3 * Math.pow(x,2)) - (3 * Math.pow(y,2));	// pg/py
		
		return j;
	}
	
	public static double[] getFuncMatrix(double x, double y)
	{
		double[] f = new double[2];
		
		f[0] = -1 * (Math.pow(x, 2) + (x * Math.pow(y, 3)) - 9); // f(x,y)
		f[1] = -1 * ((3 * Math.pow(x, 2) * y) - Math.pow(y, 3) - 4); // g(x,y)
		
		return f;
	}
	
	/* Performs gaussian elimination on a 2x2 matrix and a 1x2 */
	public static void gaussElimination(double[] j, double[] f)
	{
		double opNum;
		
		// Start Gaussian elimination
		if (j[2] < 0) 
			opNum = j[2] / j[0];
		else
			opNum = -j[2] / j[0];
		
		// Perform row operation: opNum * R1 -> R2
		j[2] = (opNum * j[0]) + j[2];
		j[3] = (opNum * j[1]) + j[3];
		f[1] = (opNum * f[0]) + f[1];
	}
	
	/* Calculates the inverse of a 2x2 matrix */
	public static double[] inverseMatrix(double[] m)
	{
		double det;
		double[] inverse = new double[4];
		
		// Inverse of M = 1/|M| * adj(M)
		det = 1 / ((m[0] * m[3]) - (m[1] * m[2]));
		
		// Constructing adj(M)
		inverse[0] = m[3] * det;
		inverse[1] = -m[2] * det; 
		inverse[2] = -m[1] * det;
		inverse[3] = m[0] * det;
		
		return inverse;
	}
	
	/* Multiplies two 2x2 matrices together */
	public static double[] multiplyMatrices(double[] j, double[] f)
	{
		double[] m = new double[2];
		
		m[0] = (j[0] * f[0]) + (j[1] * f[1]);
		m[1] = (j[2] * f[0]) + (j[3] * f[1]);
			
		return m;
	}
}