package boids;

/**
 * An Obstacle
 * @author Bryan Wang
 *
 */
public class Obstacles{
	
	Vector position; //Vector position of the obstacle
	
	//constructor
	public Obstacles(Vector position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Obstacles [position=" + position + "]";
	}
	
	
}
