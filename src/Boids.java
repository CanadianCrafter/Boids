import java.util.*;

public class Boids {
	Vector position;
	Vector velocity;
//	int colour[];
	
	public Boids(Vector position, Vector velocity) {
		this.position = position;
		this.velocity = velocity;
	}
	
	public void updateVelocity(ArrayList<Boids> group, double localRadius, double seperationCoefficient, double alignmentCoefficient, double cohesionCoefficient, double seperationStrength, double cohesionStrength, double randomStrength, int screenX, int screenY) {
		ArrayList<Boids> localFlock = generateLocalFlock(group, localRadius);
		
		System.out.printf("Seperation: %s\n", seperation(localFlock, seperationCoefficient).multiply(seperationStrength).toString());
		System.out.printf("Alignment: %s\n", alignment(localFlock, alignmentCoefficient).multiply(.75).toString());
		System.out.printf("Cohesion: %s\n", cohesion(localFlock, cohesionCoefficient).multiply(cohesionStrength).toString());
		System.out.printf("Random: %s\n", random().multiply(randomStrength).toString());
		System.out.printf("Bounds: %s\n", bounds(screenX,screenY).toString());
		System.out.println("=======");
//		for(int i =0;i<localFlock.size();i++) {
//			System.out.println(position.distanceTo(localFlock.get(i).position));
//		}
		velocity=velocity.add(seperation(localFlock, seperationCoefficient).multiply(seperationStrength));
		velocity=velocity.add(alignment(localFlock, alignmentCoefficient).multiply(.75));
		velocity=velocity.add(cohesion(localFlock, cohesionCoefficient).multiply(cohesionStrength));
		velocity=velocity.add(random().multiply(randomStrength));
		velocity=velocity.add(bounds(screenX,screenY));
		limitVelocity();
	}
	

	private ArrayList<Boids> generateLocalFlock(ArrayList<Boids> group, double localRadius) {
		int size = group.size();
		ArrayList<Boids> localFlock = new ArrayList<Boids>();
		for(int i =0;i<size;i++) {
			if(position.distanceTo(group.get(i).position)<localRadius)
				localFlock.add(group.get(i));
		}
		return localFlock;
	}

	public void updatePosition() {
		position=position.add(velocity);
	}
	
	
	public Vector seperation(ArrayList<Boids> localFlock, double seperationRadius) {
		Vector seperation = new Vector(0,0);
		for(int i =0;i<localFlock.size();i++) {
			double distance = position.distanceTo(localFlock.get(i).position);
			if(distance!=0&&distance<seperationRadius) {
				seperation = seperation.add(position.subtract(localFlock.get(i).position).setMagnitude(1).divide(distance));
//				seperation = seperation.subtract(localFlock.get(i).position).add(position);
			}
		}
		
		return seperation;
		
	}
	
	public Vector alignment(ArrayList<Boids> localFlock, double alignmentRadius) {
		Vector alignment = new Vector(0,0);
		int cnt =0;
		for(int i =0;i<localFlock.size();i++) {
			double distance = position.distanceTo(localFlock.get(i).position);
			if(distance!=0&&distance<alignmentRadius) {
				alignment = alignment.add(localFlock.get(i).velocity.setMagnitude(1).divide(distance));
				cnt++;
			}
				
		}
//		alignment = alignment.divide(size).subtract(velocity).divide(alignmentCoefficient);
//		if(cnt!=0)alignment.divide(cnt);
		
		return alignment;
		
	}
	
	public Vector cohesion(ArrayList<Boids> localFlock, double cohesionRadius) {
		Vector cohesion = new Vector(0,0);
		int cnt = 0;
		for(int i =0;i<localFlock.size();i++) {
			double distance = position.distanceTo(localFlock.get(i).position);
			if(distance!=0&&distance<cohesionRadius) {
				cohesion = cohesion.add(localFlock.get(i).position);
				cnt++;
			}
				
		}
//		cohesion = cohesion.divide(size).subtract(position).divide(cohesionCoefficient);
		if(cnt==0) return new Vector(0,0);
		else cohesion = cohesion.add(position).divide(cnt).subtract(position).setMagnitude(0.05);
		return cohesion;
		
	}
	
	private Vector random() {
		return new Vector(Math.random()*2-1,Math.random()*2-1);
	}
	
	public Vector bounds(int screenX, int screenY) {
		int x =0;
		int y =0;
		if(this.position.data[0]<50)x=2;
		else if(position.data[0]>screenX-50)x=-2;
		if(this.position.data[1]<50)y=2;
		else if(position.data[1]>screenY-50)y=-2;
		return new Vector(x,y);
		
	}
	
	private void limitVelocity() {
		int vlim = 5;
//		if(velocity.magnitude()>vlim) {
//			velocity=velocity.divide(velocity.magnitude());
//			velocity=velocity.multiply(vlim);
//		}
		for(int i = 0;i<velocity.data.length;i++) {
			if(velocity.data[i]>vlim) velocity.data[i]=vlim;
			else if (velocity.data[i]<-1*vlim) velocity.data[i] = -1*vlim;
			
		}
			
	}
	

	@Override
	public String toString() {
		return "Boids [position=" + position + ", velocity=" + velocity + "]";
	}
	
	
	
}
