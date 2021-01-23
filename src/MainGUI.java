package boids;

//imports
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import edu.wlu.cs.levy.CG.*;

public class MainGUI extends JFrame implements ActionListener, MouseListener {

	// Panels
	public static JPanel screen = new JPanel();
	public static JPanel board = new JPanel();
	public static JPanel sliders = new JPanel();
	static int screenX = 800;
	static int screenY = 600;

	// Sliders
	private static JSlider localRadiusSlider = new JSlider(0, 100, 50);
	private static JSlider seperationRadiusSlider = new JSlider(0, 100, 50);
	private static JSlider alignmentRadiusSlider = new JSlider(0, 100, 50);
	private static JSlider cohesionRadiusSlider = new JSlider(0, 100, 50);
	private static JSlider avoidObstacleRadiusSlider = new JSlider(0, 100, 50);

	private static JSlider seperationStrengthSlider = new JSlider(0, 100, 50);
	private static JSlider alignmentStrengthSlider = new JSlider(0, 100, 50);
	private static JSlider cohesionStrengthSlider = new JSlider(0, 100, 50);
	private static JSlider randomStrengthSlider = new JSlider(0, 100, 50);
	private static JSlider wallStrengthSlider = new JSlider(0, 100, 50);
	private static JSlider avoidObstacleStrengthSlider = new JSlider(0, 100, 50);

	// constants for sliders (real values will be a fraction of this)
	private final double LOCAL_RADIUS = 24;
	private final double SEPERATION_RADIUS = 16;
	private final double ALIGNMENT_RADIUS = 20;
	private final double COHESION_RADIUS = 20;
	private final double AVOID_OBSTACLE_RADIUS = 20;

	private final double SEPERATION_STRENGTH = 3;
	private final double ALIGNMENT_STRENGTH = 0.8;
	private final double COHESION_STRENGTH = 12;
	private final double RANDOM_STRENGTH = 1;
	private final double WALL_STRENGTH = 10;
	private final double AVOID_OBSTACLE_STRENGTH = 10;

	// JTextAreas labeling the Sliders
	private static JButton localRadiusButton = new JButton("Boid Vision");
	private static JButton seperationRadiusButton = new JButton("Seperation Radius");
	private static JButton alignmentRadiusButton = new JButton("Alignment Radius");
	private static JButton cohesionRadiusButton = new JButton("Cohesion Radius");
	private static JButton avoidObstacleRadiusButton = new JButton("Obstacle Avoidance Radius");

	private static JButton seperationStrengthButton = new JButton("Seperation Strength");
	private static JButton alignmentStrengthButton = new JButton("Alignment Strength");
	private static JButton cohesionStrengthButton = new JButton("Cohesion Strength");
	private static JButton randomStrengthButton = new JButton("Random Strength");
	private static JButton wallStrengthButton = new JButton("Wall Strength");
	private static JButton avoidObstacleStrengthButton = new JButton("Obstacle Avoidance Strength");

	Timer animationTimer = new Timer(40, this);

	// boids
	public static Group group = new Group();

//	KDTree kd;

	// constructor method
	public MainGUI() throws KeySizeException, KeyDuplicateException {
		frameSetup();
		panelDesign();
		popUpInfo();
//		kd = new KDTree(2); //Create a new 2-D tree
		startingBoids(100);
		animationTimer.start();

	}

	// set up the frame
	public void frameSetup() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(screenX, screenY);
		setTitle("Boids");
		setLayout(null);
		add(screen);
		setVisible(true);
		repaint();

	}

	/**
	 * set up the panels
	 */
	public void panelDesign() {

		// the entire screen
		screen.setLayout(new BorderLayout());
		screen.addMouseListener(this);

		// format all the textareas
		formatButton(localRadiusButton, seperationRadiusButton, alignmentRadiusButton, cohesionRadiusButton,
				avoidObstacleRadiusButton, seperationStrengthButton, alignmentStrengthButton, cohesionStrengthButton,
				randomStrengthButton, wallStrengthButton, avoidObstacleStrengthButton);

		// format all the sliders
		formatSlider(localRadiusSlider, seperationRadiusSlider, alignmentRadiusSlider, cohesionRadiusSlider,
				avoidObstacleRadiusSlider, seperationStrengthSlider, alignmentStrengthSlider, cohesionStrengthSlider,
				randomStrengthSlider, wallStrengthSlider, avoidObstacleStrengthSlider);

		// the board where the boids are drawn on
		board.setPreferredSize(new Dimension(screenX, screenY));
		board.setBackground(new java.awt.Color(122, 117, 116));

		// where the sliders are contained
		sliders.setLayout(new GridLayout(0, 2));
		sliders.setBackground(new java.awt.Color(47, 47, 47));

		// add textareas and sliders into jframe
		sliders.add(localRadiusButton);
		sliders.add(localRadiusSlider);
		sliders.add(seperationRadiusButton);
		sliders.add(seperationRadiusSlider);
		sliders.add(alignmentRadiusButton);
		sliders.add(alignmentRadiusSlider);
		sliders.add(cohesionRadiusButton);
		sliders.add(cohesionRadiusSlider);
		sliders.add(avoidObstacleRadiusButton);
		sliders.add(avoidObstacleRadiusSlider);

		sliders.add(seperationStrengthButton);
		sliders.add(seperationStrengthSlider);
		sliders.add(alignmentStrengthButton);
		sliders.add(alignmentStrengthSlider);
		sliders.add(cohesionStrengthButton);
		sliders.add(cohesionStrengthSlider);
		sliders.add(randomStrengthButton);
		sliders.add(randomStrengthSlider);
		sliders.add(wallStrengthButton);
		sliders.add(wallStrengthSlider);
		sliders.add(avoidObstacleStrengthButton);
		sliders.add(avoidObstacleStrengthSlider);

		// adds the various JPanels into the screen
		screen.add(sliders, "West");
		screen.add(board, "Center");
		setContentPane(screen);
		pack();

	}

	/**
	 * Displays a pop up with the control scheme
	 */
	private void popUpInfo() {
		JOptionPane.showConfirmDialog(null, new ImageIcon("Images/info.png"), "Info", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Adds a specified number of boids at the start of the program
	 * 
	 * @param amount of Boids spawned at the start
	 * @throws KeySizeException
	 * @throws KeyDuplicateException
	 */
	public void startingBoids(int amount) throws KeySizeException, KeyDuplicateException {
		for (int i = 0; i < amount; i++) {
			group.addBoid(
					new Boids(new Vector(Math.random() * screenX + 400, Math.random() * screenY), new Vector(0, 0)));

//			kd.insert(boids.get(i).position.data, boids.get(i)); //insert boid into the KD-Tree

		}

	}

	/**
	 * Moves all the Boids
	 * 
	 * @throws KeySizeException
	 * @throws IllegalArgumentException
	 * @throws KeyMissingException
	 * @throws KeyDuplicateException
	 */
	public void move() throws KeySizeException, IllegalArgumentException, KeyMissingException, KeyDuplicateException {

		for (int i = 0; i < group.boids.size(); i++) {
			group.boids.get(i).updateVelocity();
			group.boids.get(i).updatePosition();

			// KD-Tree implementation
//			double coords[] = boids.get(i).position.data;
//			Boids newBoids[] = new Boids[distance];
//			kd.nearest(coords,distance).toArray(newBoids);
////			kd.delete(coords);
//			boids.get(i).updateVelocity();
//			boids.get(i).updatePosition();
////			kd.insert(boids.get(i).position.data, boids.get(i));

		}

//		kd = new KDTree(2);
//		for(int i =0;i<boids.size();i++) {
//			kd.insert(boids.get(i).position.data, boids.get(i));
//		}

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.WHITE);
		g2d.setStroke(new BasicStroke(4));
		draw(g2d);

	}

	public void draw(Graphics g) {

		// draw the boids
		for (int i = 0; i < group.boids.size(); i++) {
			double x = group.boids.get(i).position.data[0];
			double y = group.boids.get(i).position.data[1];

			// comment out the next three lines, and uncomment the 4th if facing performance
			// issues
			double x2 = x + group.boids.get(i).velocity.setMagnitude(6).data[0];
			double y2 = y + group.boids.get(i).velocity.setMagnitude(6).data[1];
			g.drawLine((int) x, (int) y, (int) x2, (int) y2);
//			g.fillOval((int) Math.round(x) - 5, (int) Math.round(y) - 5, 10, 10);
		}

		// draw the obstacles
		for (int i = 0; i < group.obstacles.size(); i++) {
			double x = group.obstacles.get(i).position.data[0];
			double y = group.obstacles.get(i).position.data[1];
			g.fillRect((int) Math.round(x) - 5, (int) Math.round(y) - 5, 10, 10);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		paintComponent(g);
	}

	/**
	 * formats all the buttons (The buttons are just for formatting so they don't do
	 * anything)
	 * 
	 * @param button
	 */
	private void formatButton(JButton... button) {
		JButton buttons[] = button;
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setPreferredSize(new Dimension(200, 20));
			buttons[i].setBorderPainted(false);
			buttons[i].setBackground(new java.awt.Color(47, 47, 47));
			buttons[i].setForeground(Color.WHITE);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setOpaque(false);
		}

	}

	/**
	 * formats all the sliders
	 * 
	 * @param slider
	 */
	private void formatSlider(JSlider... slider) {
		JSlider sliders[] = slider;
		for (int i = 0; i < sliders.length; i++) {
			sliders[i].setPreferredSize(new Dimension(200, 20));
			sliders[i].setBackground(new java.awt.Color(47, 47, 47));
			sliders[i].setForeground(Color.WHITE);
			sliders[i].setOpaque(false);
		}

	}

	/**
	 * carries out the actions
	 */
	public void actionPerformed(ActionEvent event) {
		repaint();

		// move Boids
		if (event.getSource() == animationTimer) {
			try {
				move();
			} catch (KeySizeException | IllegalArgumentException | KeyMissingException | KeyDuplicateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// get frame sizes to change walls
		Dimension frameDimensions = getSize();
		screenX = (int) frameDimensions.getWidth();
		screenY = (int) frameDimensions.getHeight();

		// change movement variables
		group.setLocalRadius(LOCAL_RADIUS * localRadiusSlider.getValue() / 100);
		group.setSeperationRadius(SEPERATION_RADIUS * seperationRadiusSlider.getValue() / 100);
		group.setAlignmentRadius(ALIGNMENT_RADIUS * alignmentRadiusSlider.getValue() / 100);
		group.setCohesionRadius(COHESION_RADIUS * cohesionRadiusSlider.getValue() / 100);
		group.setAvoidObstacleRadius(AVOID_OBSTACLE_RADIUS * avoidObstacleRadiusSlider.getValue() / 100);

		group.setSeperationStrength(SEPERATION_STRENGTH * seperationStrengthSlider.getValue() / 100);
		group.setAlignmentStrength(ALIGNMENT_STRENGTH * alignmentStrengthSlider.getValue() / 100);
		group.setCohesionStrength(COHESION_STRENGTH * cohesionStrengthSlider.getValue() / 100);
		group.setRandomStrength(RANDOM_STRENGTH * randomStrengthSlider.getValue() / 100);
		group.setWallStrength(WALL_STRENGTH * wallStrengthSlider.getValue() / 100);
		group.setAvoidObstacleStrength(AVOID_OBSTACLE_STRENGTH * avoidObstacleStrengthSlider.getValue() / 100);
//		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// add Boid at the mouse click
		if (event.getButton() == MouseEvent.BUTTON1)
			group.boids.add(new Boids(new Vector(event.getX(), event.getY() + 20), new Vector(0, 0)));

		// add Obstacle at the mouse click
		else if (event.getButton() == MouseEvent.BUTTON3)
			group.obstacles.add(new Obstacles(new Vector(event.getX(), event.getY() + 20)));
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub

	}

}