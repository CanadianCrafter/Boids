package boids;

import java.util.*;

/**
 * Vector --- Class to represent n-degree Vector (some methods are for 2-d Vectors only)
 * @author Bryan Wang
 */

public class Vector {
	
	double data[]; //the vector
	int size; //the degree of the vector
	
	//constructor for a n-degree vector given vector components
	public Vector(double ...data) {
		size=data.length;
		this.data = new double[size];
		for(int i =0;i<size;i++)
			this.data[i]=data[i];
	}
	
	//constructor for an empty vector with a n-degree
	public Vector(int size,boolean flag) {
		this.size = size;
		data = new double[size];
	}
	
	
	/**
	 * Returns the result of vector addition
	 * @param vector to add to initial vector
	 * @return New vector representing vector1 + vector2
	 */
	public Vector add(Vector vector) {
		Vector sum = new Vector(size,false);
		for(int i =0;i<size;i++)
			sum.data[i] = data[i]+vector.data[i];
		return sum;
	}
	
	/**
	 * Returns the result of vector subtraction
	 * @param vector to subtract from the initial vector
	 * @return New vector representing vector1 - vector2
	 */
	public Vector subtract(Vector vector) {
		Vector difference = new Vector(size,false);
		for(int i =0;i<size;i++)
			difference.data[i] = data[i]-vector.data[i];
		return difference;
	}
	
	/**
	 * Returns the result of scalar vector multiplication
	 * @param factor the initial vector is multiplied by
	 * @return New vector representing vector1 * K
	 */
	public Vector multiply(double factor) {
		Vector product = new Vector(size,false);
		for(int i =0;i<size;i++)
			product.data[i] = data[i]*factor;
		return product;
	}
	
	/**
	 * Returns the result of scalar vector division
	 * @param factor the initial vector is divided by
	 * @return New vector representing vector1 / K
	 */
	public Vector divide(double factor) {
		Vector quotient = new Vector(size,false);
		for(int i =0;i<size;i++)
			quotient.data[i] = data[i]/factor;
		return quotient;
	}
	
	/**
	 * Returns the vector set to a specific magnitude
	 * @param factor the desired magnitude 
	 * @return New vector parallel to initial vector with the specified magnitude
	 */
	public Vector setMagnitude(double factor) {
		if(factor==0||this.magnitude()==0) return new Vector(size,false);
		return this.divide(this.magnitude()/factor);
	}
	
	/**
	 * Returns the magnitude of the vector
	 * @return double magnitude of the vector
	 */
	public double magnitude() {
		return Math.sqrt(this.dotProduct(this));
	}
	
	/**
	 * Returns the distance between two points on a Cartesian plane
	 * @param vector the second coordinate (Not actually a vector)
	 * @return double distance from point1 to point2.
	 */
	public double distanceTo(Vector vector) {
		return Math.sqrt(Math.abs(vector.data[0]-data[0])+Math.abs(vector.data[1]-data[1]));
	}
	
	/**
	 * Returns the dot product between two vectors
	 * @param vector to calculate the dot product with
	 * @return double dot product between vector1 and vector2
	 */
	public double dotProduct(Vector vector) {
		double dotProduct=0;
		for(int i =0;i<size;i++)
			dotProduct+=data[i]*vector.data[i];
		return dotProduct;
	}
	
	
	public String toString() {
		return "Vector [data=" + Arrays.toString(data) + ", size=" + size + "]";
	}

	
}
