package boids;

/**
 * Class for moving entities (Currently unused since there are no other moving
 * entities other than boids)
 * 
 * @author Bryan Wang
 *
 */

public class Entity {

	Vector position; // Vector position of the entity
	Vector velocity; // Vector velocity of the entity
	Group group; //information on the entity

	public Entity(Vector position, Vector velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
