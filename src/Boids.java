package boids;

import java.util.*;

public class Boids {
	Vector position;
	Vector velocity;
	Group group = new Group();
	ArrayList<Boids> localFlock;
	
//	int colour[];
	
	public Boids(Vector position, Vector velocity) {
		this.position = position;
		this.velocity = velocity;
	}
	
	public void updateVelocity() {
		localFlock = generateLocalFlock();
		
//		System.out.printf("Seperation: %s\n", seperation().multiply(group.getSeperationStrength()).toString());
//		System.out.printf("Alignment: %s\n", alignment().multiply(group.getAlignmentStrength()).toString());
//		System.out.printf("Cohesion: %s\n", cohesion().multiply(group.getCohesionStrength()).toString());
//		System.out.printf("Avoid Obstacles: %s\n", avoidObstacles().multiply(group.getAvoidObstacleStrength()).toString());
//		System.out.printf("Random: %s\n", random().multiply(group.getRandomStrength()).toString());
//		System.out.printf("Bounds: %s\n", bounds().toString());
//		System.out.println("=======");
//		for(int i =0;i<localFlock.size();i++) {
//			System.out.println(position.distanceTo(localFlock.get(i).position));
//		}
		velocity=velocity.add(seperation().multiply(group.getSeperationStrength()));
		velocity=velocity.add(alignment().multiply(group.getAlignmentStrength()));
		velocity=velocity.add(cohesion().multiply(group.getCohesionStrength()));
		velocity=velocity.add(avoidObstacles().multiply(group.getAvoidObstacleStrength()));
		velocity=velocity.add(random().multiply(group.getRandomStrength()));
		velocity=velocity.add(bounds());
		limitVelocity();
	}
	

	private ArrayList<Boids> generateLocalFlock() {
		localFlock = new ArrayList<Boids>();
		for(int i =0;i<group.boids.size();i++) {
			if(position.distanceTo(group.boids.get(i).position)<group.getLocalRadius())
				localFlock.add(group.boids.get(i));
		}
		return localFlock;
	}

	public void updatePosition() {
		position=position.add(velocity);
	}
	
	
	public Vector seperation() {
		Vector seperation = new Vector(0,0);
		for(int i =0;i<localFlock.size();i++) {
			double distance = position.distanceTo(localFlock.get(i).position);
			if(distance!=0&&distance<group.getSeperationRadius()) {
				seperation = seperation.add(position.subtract(localFlock.get(i).position).setMagnitude(1).divide(distance));
//				seperation = seperation.subtract(localFlock.get(i).position).add(position);
			}
		}
		
		return seperation;
		
	}
	
	public Vector alignment() {
		Vector alignment = new Vector(0,0);
		int cnt =0;
		for(int i =0;i<localFlock.size();i++) {
			double distance = position.distanceTo(localFlock.get(i).position);
			if(distance!=0&&distance<group.getAlignmentRadius()) {
				alignment = alignment.add(localFlock.get(i).velocity.setMagnitude(1).divide(distance));
				cnt++;
			}
				
		}
//		alignment = alignment.divide(size).subtract(velocity).divide(group.getAlignmentRadius());
		if(cnt!=0)alignment.divide(cnt);
		
		return alignment;
		
	}
	
	public Vector cohesion() {
		Vector cohesion = new Vector(0,0);
		int cnt = 0;
		for(int i =0;i<localFlock.size();i++) {
			double distance = position.distanceTo(localFlock.get(i).position);
			if(distance!=0&&distance<group.getCohesionRadius()) {
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
	
	private Vector avoidObstacles() {
		Vector avoidObstacles = new Vector(0,0);
		for(int i =0;i<MainGUI.group.getObstacles().size();i++) {
			double distance = position.distanceTo(MainGUI.group.getObstacles().get(i).position);
			if(distance<MainGUI.group.getAvoidObstacleRadius()) {
				avoidObstacles = avoidObstacles.add(position.subtract(MainGUI.group.getObstacles().get(i).position).setMagnitude(1).divide(distance));
			}
		}
		
		return avoidObstacles;
	}
	
	
	public Vector bounds() {
		double x =0;
		double y =0;
		if(this.position.data[0]<50)x=group.getWallStrength();
		else if(position.data[0]>MainGUI.screenX-50)x=-1*group.getWallStrength();
		if(this.position.data[1]<50)y=group.getWallStrength();
		else if(position.data[1]>MainGUI.screenY-50)y=-1*group.getWallStrength();
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
