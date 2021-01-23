package boids;

import java.io.*;

import edu.wlu.cs.levy.CG.KeyDuplicateException;
import edu.wlu.cs.levy.CG.KeySizeException;


/**
 * 
 * @author Bryan Wang
 * 
 * GitHub: https://github.com/CanadianCrafter/Boids
 * 
 * 
 * Date: January 22, 2021
 * 
 * Course Code: ICS4U1-01 | Mr. Fernandes
 * 
 * Title: Boids
 * 
 * Description (See README):
 * 
 * Boids is an artificial life program, which simulates the flocking behavior of birds and fish. 
 * However, it doesn't do this with a complex algorithm, but a handful of basic rules that each of the boids follow. 
 * Thus, they are an example of emergent behavior. The rules they follow are:
 * 		> Separation: Steer to avoid crowding local flock mates.
 * 		> Alignment: Steer towards the average heading of local flock mates.
 * 		> Cohesion: Steer to move towards the average position (center of mass) of local flock mates.
 * 		> Obstacle avoidance: Steer away from obstacles.
 * You can learn more about it on Wikipedia: https://en.wikipedia.org/wiki/Boids.
 * 
 * This program aims to do recreate just that and will follow the pseudocode from http://www.kfish.org/boids/pseudocode.html. 
 * Additional features that make the program more like a simulation might get added.
 * 
 * Features:
 * 		> Displays emergent behavior
 * 		> Allows users to change the rules of the simulation
 * 		> Allows users to add Boids and Obstacles
 * 
 * Major Skills:
 * 		> GitHub
 * 		> Using External JARs
 * 		> Using a KD-Tree to optimize
 * 		> Object Oriented Programming
 * 		> Use of Graphics 2D
 * 
 * Areas of Concern:
 * 		> Because the KD-Tree was not used, the simulation isn't too efficient when the number of boids is too large.
 * 		> Flickering may occur due to performance issues. If this is a problem draw circles instead of a line (MainGUI
 * 
 * 		> Right Click to spawn a Boid
 * 		> Left Click to spawn an obstacle
 * 
 * 		> The project is missing features that I would have wished to add. What is here is of good quality though.
 * 		> The Boid movement vector calculations might work better with other mathematical models.
 * 
 */

public class BoidsTest {
	
	public static void main(String[] args) throws IOException, KeySizeException, KeyDuplicateException {
		new MainGUI();

	}
}
