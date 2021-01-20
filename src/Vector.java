import java.util.*;

public class Vector {
	double data[];
	int size;
	
	public Vector(double ...data) {
		size=data.length;
		this.data = new double[size];
		for(int i =0;i<size;i++)
			this.data[i]=data[i];
	}
	
	public Vector add(Vector vector) {
		Vector sum = new Vector(0,0);
		for(int i =0;i<size;i++)
			sum.data[i] = data[i]+vector.data[i];
		return sum;
	}
	
	public Vector subtract(Vector vector) {
		Vector difference = new Vector(0,0);
		for(int i =0;i<size;i++)
			difference.data[i] = data[i]-vector.data[i];
		return difference;
	}
	
	public Vector multiply(double factor) {
		Vector product = new Vector(0,0);
		for(int i =0;i<size;i++)
			product.data[i] = data[i]*factor;
		return product;
	}
	
	public Vector divide(double factor) {
		Vector quotient = new Vector(0,0);
		for(int i =0;i<size;i++)
			quotient.data[i] = data[i]/factor;
		return quotient;
	}
	
	public Vector setMagnitude(double factor) {
		if(factor==0||this.magnitude()==0) return new Vector(0,0);
		return this.divide(this.magnitude()/factor);
		
			
	}
	
	public double magnitude() {
		return Math.sqrt(this.dotProduct(this));
	}
	
	public double distanceTo(Vector vector) {
		return Math.sqrt(Math.abs(vector.data[0]-data[0])+Math.abs(vector.data[1]-data[1]));
	}
	
	public double dotProduct(Vector vector) {
		double dotProduct=0;
		for(int i =0;i<size;i++)
			dotProduct+=data[i]*vector.data[i];
		return dotProduct;
	}
	
	

	@Override
	public String toString() {
		return "Vector [data=" + Arrays.toString(data) + ", size=" + size + "]";
	}

	
	
	
	
}
