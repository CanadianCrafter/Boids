import java.util.*;

public class Boids {
	Vector position;
	Vector velocity;
//	int colour[];
	
	public Boids(Vector position, Vector velocity) {
		super();
		this.position = position;
		this.velocity = velocity;
	}
	
	public void updateVelocity(ArrayList<Boids> localFlock, double seperationCoefficient, double alignmentCoefficient, double cohesionCoefficient) {
		velocity.add(seperation(localFlock, seperationCoefficient));
		velocity.add(alignment(localFlock, alignmentCoefficient));
		velocity.add(cohesion(localFlock, cohesionCoefficient));
	}
	
	
	public Vector seperation(ArrayList<Boids> localFlock, double seperationCoefficient) {
		int size = localFlock.size();
		Vector seperation = new Vector(0,0);
		for(int i =0;i<size;i++) {
			if(position.subtract(localFlock.get(i).position).magnitude()<seperationCoefficient)
				seperation=seperation.subtract(position.subtract(localFlock.get(i).position));
		}
		
		return seperation;
		
	}
	
	public Vector alignment(ArrayList<Boids> localFlock, double alignmentCoefficient) {
		int size = localFlock.size();
		Vector alignment = new Vector(0,0);
		for(int i =0;i<size;i++) {
			alignment.add(localFlock.get(i).velocity);
		}
		alignment = alignment.divide(size).subtract(velocity).divide(alignmentCoefficient);
		
		return alignment;
		
	}
	
	public Vector cohesion(ArrayList<Boids> localFlock, double cohesionCoefficient) {
		int size = localFlock.size();
		Vector cohesion = new Vector(0,0);
		for(int i =0;i<size;i++) {
			cohesion.add(localFlock.get(i).position);
		}
		cohesion = cohesion.divide(size).subtract(position).divide(cohesionCoefficient);
		
		return cohesion;
		
	}
	
}
