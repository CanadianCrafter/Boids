import java.util.*;

public class Vector {
	ArrayList<Double> data = new ArrayList<Double>();
	int size;
	
	public Vector(double ...data) {
		size=data.length;
		for(int i =0;i<size;i++)
			this.data.add(data[i]);
	}
	
	public Vector add(Vector vector) {
		Vector sum = new Vector(0,0);
		for(int i =0;i<size;i++)
			sum.data.add(data.get(i)+vector.data.get(i));
		return sum;
	}
	
	public Vector subtract(Vector vector) {
		Vector difference = new Vector(0,0);
		for(int i =0;i<size;i++)
			difference.data.add(data.get(i)-vector.data.get(i));
		return difference;
	}
	
	public Vector multiply(double factor) {
		Vector product = new Vector(0,0);
		for(int i =0;i<size;i++)
			product.data.add(data.get(i)*factor);
		return product;
	}
	
	public Vector divide(double factor) {
		Vector quotient = new Vector(0,0);
		for(int i =0;i<size;i++)
			quotient.data.add(data.get(i)/factor);
		return quotient;
	}
	
	public double magnitude() {
		return Math.sqrt(this.dotProduct(this));
	}
	
	public double distanceTo(Vector vector) {
		return this.subtract(vector).magnitude();
	}
	
	
	public double dotProduct(Vector vector) {
		double dotProduct=0;
		for(int i =0;i<size;i++)
			dotProduct+=data.get(i)*vector.data.get(i);
		return dotProduct;
	}

	
	
	
	
}
