package boids;

import java.util.*;

/**
 * A Boid
 * 
 * @author Bryan Wang
 *
 */
public class Boids {

	Vector position; // Vector that stores the position of the boid
	Vector velocity; // Vector that stores the velocity of the boid
	Group group = new Group(); // information on the boid
	ArrayList<Boids> localFlock; // the boids this boid can "see"

//	int colour[];

	// constructor
	public Boids(Vector position, Vector velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	/**
	 * Updates the velocity of the boid
	 */
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
		velocity = velocity.add(seperation().multiply(group.getSeperationStrength()));
		velocity = velocity.add(alignment().multiply(group.getAlignmentStrength()));
		velocity = velocity.add(cohesion().multiply(group.getCohesionStrength()));
		velocity = velocity.add(avoidObstacles().multiply(group.getAvoidObstacleStrength()));
		velocity = velocity.add(random().multiply(group.getRandomStrength()));
		velocity = velocity.add(bounds());
		limitVelocity();
	}

	/**
	 * Updates the position of the boid
	 */
	public void updatePosition() {
		position = position.add(velocity);
	}

	/**
	 * Generates the local flock
	 * 
	 * @return New ArrayList of Boids within the local radius of this boid
	 */
	private ArrayList<Boids> generateLocalFlock() {
		localFlock = new ArrayList<Boids>();
		for (int i = 0; i < group.boids.size(); i++) {
			if (position.distanceTo(group.boids.get(i).position) < group.getLocalRadius())
				localFlock.add(group.boids.get(i));
		}
		return localFlock;
	}

	/**
	 * Calculates the separation vector for this boid
	 * 
	 * @return New Vector of seperation's effect on this boid
	 */
	public Vector seperation() {
		Vector seperation = new Vector(0, 0);
		for (int i = 0; i < localFlock.size(); i++) {
			double distance = position.distanceTo(localFlock.get(i).position);
			if (distance != 0 && distance < group.getSeperationRadius()) {
				seperation = seperation
						.add(position.subtract(localFlock.get(i).position).setMagnitude(1).divide(distance));
//				seperation = seperation.subtract(localFlock.get(i).position).add(position);
			}
		}

		return seperation;

	}

	/**
	 * Calculates the alignment vector for this boid
	 * 
	 * @return New Vector of elignment's effect on this boid
	 */
	public Vector alignment() {
		Vector alignment = new Vector(0, 0);
		int cnt = 0;
		for (int i = 0; i < localFlock.size(); i++) {
			double distance = position.distanceTo(localFlock.get(i).position);
			if (distance != 0 && distance < group.getAlignmentRadius()) {
				alignment = alignment.add(localFlock.get(i).velocity.setMagnitude(1).divide(distance));
				cnt++;
			}

		}
//		alignment = alignment.divide(size).subtract(velocity).divide(group.getAlignmentRadius());
		if (cnt != 0)
			alignment.divide(cnt);

		return alignment;

	}

	/**
	 * Calculates the cohesion vector for this boid
	 * 
	 * @return New Vector of cohesion's effect on this boid
	 */
	public Vector cohesion() {
		Vector cohesion = new Vector(0, 0);
		int cnt = 0;
		for (int i = 0; i < localFlock.size(); i++) {
			double distance = position.distanceTo(localFlock.get(i).position);
			if (distance != 0 && distance < group.getCohesionRadius()) {
				cohesion = cohesion.add(localFlock.get(i).position);
				cnt++;
			}

		}
//		cohesion = cohesion.divide(localFlock.size()).subtract(position);
		if (cnt == 0)
			return new Vector(0, 0);
		else
			cohesion = cohesion.add(position).divide(cnt).subtract(position).setMagnitude(0.05);
		return cohesion;

	}

	/**
	 * Calculates the random vector for this boid
	 * 
	 * @return New Vetor that randomly affects this boid
	 */
	private Vector random() {
		return new Vector(Math.random() * 2 - 1, Math.random() * 2 - 1);
	}

	/**
	 * Calculates the obstacle avoidance vector for this boid
	 * 
	 * @return New Vector of obstacle avoidance's effect on this boid
	 */
	private Vector avoidObstacles() {
		Vector avoidObstacles = new Vector(0, 0);
		for (int i = 0; i < MainGUI.group.getObstacles().size(); i++) {
			double distance = position.distanceTo(MainGUI.group.getObstacles().get(i).position);
			if (distance < MainGUI.group.getAvoidObstacleRadius()) {
				avoidObstacles = avoidObstacles.add(position.subtract(MainGUI.group.getObstacles().get(i).position)
						.setMagnitude(1).divide(distance));
			}
		}

		return avoidObstacles;
	}

	/**
	 * Calculates the wall avoidance vector for this boid
	 * 
	 * @return New Vector of wall avoidance's effect on this boid
	 */
	public Vector bounds() {
		double x = 0;
		double y = 0;
		if (this.position.data[0] < 50 + 400)
			x = group.getWallStrength();
		else if (position.data[0] > MainGUI.screenX - 50)
			x = -1 * group.getWallStrength();
		if (this.position.data[1] < 50)
			y = group.getWallStrength();
		else if (position.data[1] > MainGUI.screenY - 50)
			y = -1 * group.getWallStrength();
		return new Vector(x, y);

	}

	/**
	 * limits the velocity of the boid so it doesn't go too fast
	 */
	private void limitVelocity() {
		int vlim = 4;
//		if(velocity.magnitude()>vlim) {
//			velocity=velocity.divide(velocity.magnitude());
//			velocity=velocity.multiply(vlim);
//		}
		for (int i = 0; i < velocity.data.length; i++) {
			if (velocity.data[i] > vlim)
				velocity.data[i] = vlim;
			else if (velocity.data[i] < -1 * vlim)
				velocity.data[i] = -1 * vlim;

		}

	}

	@Override
	public String toString() {
		return "Boids [position=" + position + ", velocity=" + velocity + "]";
	}

}
