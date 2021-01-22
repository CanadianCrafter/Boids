package boids;

public class Obstacles{
	Vector position;
	
	public Obstacles(Vector position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Obstacles [position=" + position + "]";
	}
	
	
}
