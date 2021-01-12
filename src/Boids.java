import java.util.*;

public class Boids {
	Vector position;
	Vector velocity;
//	int colour[];
	
	public Boids(Vector position, Vector velocity) {
		this.position = position;
		this.velocity = velocity;
	}
	
	public void updateVelocity(Boids[] localFlock, double seperationCoefficient, double alignmentCoefficient, double cohesionCoefficient, int screenX, int screenY) {
		velocity=velocity.add(seperation(localFlock, seperationCoefficient));
		velocity=velocity.add(alignment(localFlock, alignmentCoefficient));
		velocity=velocity.add(cohesion(localFlock, cohesionCoefficient));
		velocity=velocity.add(bounds(screenX,screenY));
		limitVelocity();
	}
	
	

	public void updatePosition() {
		position=position.add(velocity);
	}
	
	
	public Vector seperation(Boids[] localFlock, double seperationCoefficient) {
		int size = localFlock.length;
		Vector seperation = new Vector(0,0);
		for(int i =0;i<size;i++) {
			if(position.subtract(localFlock[i].position).magnitude()<seperationCoefficient)
				seperation=seperation.subtract(position.subtract(localFlock[i].position));
		}
		
		return seperation;
		
	}
	
	public Vector alignment(Boids[] localFlock, double alignmentCoefficient) {
		int size = localFlock.length;
		Vector alignment = new Vector(0,0);
		for(int i =0;i<size;i++) {
			alignment.add(localFlock[i].velocity);
		}
		alignment = alignment.divide(size).subtract(velocity).divide(alignmentCoefficient);
		
		return alignment;
		
	}
	
	public Vector cohesion(Boids[] localFlock, double cohesionCoefficient) {
		int size = localFlock.length;
		Vector cohesion = new Vector(0,0);
		for(int i =0;i<size;i++) {
			cohesion.add(localFlock[i].position);
		}
		cohesion = cohesion.divide(size).subtract(position).divide(cohesionCoefficient);
		
		return cohesion;
		
	}
	
	public Vector bounds(int screenX, int screenY) {
		int x =0;
		int y =0;
		if(this.position.data[0]<50)x=20;
		else if(position.data[0]>screenX-50)x=-20;
		if(this.position.data[1]<50)y=20;
		else if(position.data[1]>screenY-50)y=-20;
		return new Vector(x,y);
		
	}
	
	private void limitVelocity() {
		int vlim = 50;
		if(velocity.magnitude()>vlim) {
			velocity=velocity.divide(velocity.magnitude());
			velocity=velocity.multiply(vlim);
		}
		
	}
	

	@Override
	public String toString() {
		return "Boids [position=" + position + ", velocity=" + velocity + "]";
	}
	
	
	
}
