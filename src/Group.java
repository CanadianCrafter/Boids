package boids;

import java.util.ArrayList;

/**
 * Class that holds information for a group of boids/obstacles
 * 
 * @author Bryan Wang
 *
 */
public class Group {

	public ArrayList<Boids> boids = new ArrayList<Boids>(); //list of all the boids
	public ArrayList<Obstacles> obstacles = new ArrayList<Obstacles>(); //list of all the obstacles

	// Radius settings for effects on boids
	public double localRadius = 12;
	public double seperationRadius = 8;
	public double alignmentRadius = 10;
	public double cohesionRadius = 10;
	public double avoidObstacleRadius = 10;

	// Strength setting for effects on boids
	public double seperationStrength = 1.5;
	public double alignmentStrength = 0.4;
	public double cohesionStrength = 6;
	public double randomStrength = 0.5;
	public double wallStrength = 5;
	public double avoidObstacleStrength = 5;

	// constructor
	public Group() {

	}

	// setters and getters
	public ArrayList<Boids> getBoids() {
		return boids;
	}

	public void setBoids(ArrayList<Boids> boids) {
		this.boids = boids;
	}

	public void addBoid(Boids boid) {
		boid.group = this;
		boids.add(boid);
	}

	public ArrayList<Obstacles> getObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<Obstacles> obstacles) {
		this.obstacles = obstacles;
	}

	public void addObstacles(Obstacles obstacle) {
		obstacles.add(obstacle);
	}

	public double getLocalRadius() {
		return localRadius;
	}

	public void setLocalRadius(double localRadius) {
		this.localRadius = localRadius;
	}

	public double getSeperationRadius() {
		return seperationRadius;
	}

	public void setSeperationRadius(double seperationRadius) {
		this.seperationRadius = seperationRadius;
	}

	public double getAlignmentRadius() {
		return alignmentRadius;
	}

	public void setAlignmentRadius(double alignmentRadius) {
		this.alignmentRadius = alignmentRadius;
	}

	public double getCohesionRadius() {
		return cohesionRadius;
	}

	public void setCohesionRadius(double cohesionRadius) {
		this.cohesionRadius = cohesionRadius;
	}

	public double getAvoidObstacleRadius() {
		return avoidObstacleRadius;
	}

	public void setAvoidObstacleRadius(double avoidObstacleRadius) {
		this.avoidObstacleRadius = avoidObstacleRadius;
	}

	public double getSeperationStrength() {
		return seperationStrength;
	}

	public void setSeperationStrength(double seperationStrength) {
		this.seperationStrength = seperationStrength;
	}

	public double getAlignmentStrength() {
		return alignmentStrength;
	}

	public void setAlignmentStrength(double alignmentStrength) {
		this.alignmentStrength = alignmentStrength;
	}

	public double getCohesionStrength() {
		return cohesionStrength;
	}

	public void setCohesionStrength(double cohesionStrength) {
		this.cohesionStrength = cohesionStrength;
	}

	public double getRandomStrength() {
		return randomStrength;
	}

	public void setRandomStrength(double randomStrength) {
		this.randomStrength = randomStrength;
	}

	public double getWallStrength() {
		return wallStrength;
	}

	public void setWallStrength(double wallStrength) {
		this.wallStrength = wallStrength;
	}

	public double getAvoidObstacleStrength() {
		return avoidObstacleStrength;
	}

	public void setAvoidObstacleStrength(double avoidObstacleStrength) {
		this.avoidObstacleStrength = avoidObstacleStrength;
	}

}
