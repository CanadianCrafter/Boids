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
	//gui stuff
	public static JPanel screen = new JPanel();
	public static JPanel board = new JPanel();
	public static JPanel sliders = new JPanel();
	static int screenX=1000;
	static int screenY=600;
	
	//Sliders
	private static JSlider localRadiusSlider = new JSlider(0,100,50);
	private static JSlider seperationRadiusSlider= new JSlider(0,100,50);
	private static JSlider alignmentRadiusSlider = new JSlider(0,100,50);
	private static JSlider cohesionRadiusSlider = new JSlider(0,100,50);
	private static JSlider avoidObstacleRadiusSlider = new JSlider(0,100,50);
	
	private static JSlider boidSpeedSlider = new JSlider(0,100,50);
	
	private static JSlider seperationStrengthSlider = new JSlider(0,100,50);
	private static JSlider alignmentStrengthSlider = new JSlider(0,100,50);
	private static JSlider cohesionStrengthSlider = new JSlider(0,100,50);
	private static JSlider randomStrengthSlider = new JSlider(0,100,50);
	private static JSlider wallStrengthSlider = new JSlider(0,100,50);
	private static JSlider avoidObstacleStrengthSlider = new JSlider(0,100,50);
	
	//constants for sliders (real values will be a fraction of this)
	private final double LOCAL_RADIUS=24;
	private final double SEPERATION_RADIUS = 16;
	private final double ALIGNMENT_RADIUS = 20;
	private final double COHESION_RADIUS = 20;
	private final double AVOID_OBSTACLE_RADIUS = 20;
	
	private final double BOID_SPEED = 2;
	
	private final double SEPERATION_STRENGTH = 3;
	private final double ALIGNMENT_STRENGTH = 0.8;
	private final double COHESION_STRENGTH = 12;
	private final double RANDOM_STRENGTH = 1;
	private final double WALL_STRENGTH = 10;
	private final double AVOID_OBSTACLE_STRENGTH =10;
	
	
	
	
	Timer animationTimer = new Timer(20, this);
	
	//boids
	public static Group group = new Group();
//	KDTree kd;
	

	// constructor method
	public MainGUI() throws KeySizeException, KeyDuplicateException {
		frameSetup();
		panelDesign();
//		kd = new KDTree(2);
		startingBoids(100);//temporary
		animationTimer.start();
		
	}

	
	//set up the frame
	public void frameSetup() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(screenX, screenY);
		setTitle("Boids");
		setLayout(null);
		add(screen);
		setVisible(true);
//		setResizable(false); //remove later
		repaint();
		
	}

	//set up the panel
	public void panelDesign() {
		//add features for the screen
//		screen.setBorder(null);
//		screen.setBounds(0, 0, screenX, screenY);
		screen.setLayout(new BorderLayout());
//		screen.setBackground(new java.awt.Color(47, 47, 47));
		screen.addMouseListener(this);
	
		
		board.setPreferredSize(new Dimension(screenX,screenY));
		
		sliders.setLayout(new BoxLayout(sliders, BoxLayout.Y_AXIS));
		sliders.add(localRadiusSlider);
		sliders.add(seperationRadiusSlider);
		sliders.add(alignmentRadiusSlider);
		sliders.add(cohesionRadiusSlider);
		sliders.add(avoidObstacleRadiusSlider);
		
		sliders.add(boidSpeedSlider);
		
		sliders.add(seperationStrengthSlider);
		sliders.add(alignmentStrengthSlider);
		sliders.add(cohesionStrengthSlider);
		sliders.add(randomStrengthSlider);
		sliders.add(wallStrengthSlider);
		sliders.add(avoidObstacleStrengthSlider);
		
		localRadiusSlider.setPreferredSize(new Dimension(120, 20));
		seperationRadiusSlider.setPreferredSize(new Dimension(120, 20));
		alignmentRadiusSlider.setPreferredSize(new Dimension(120, 20));
		cohesionRadiusSlider.setPreferredSize(new Dimension(120, 20));
		avoidObstacleRadiusSlider.setPreferredSize(new Dimension(120, 20));
		
		boidSpeedSlider.setPreferredSize(new Dimension(120, 20));
		
		seperationStrengthSlider.setPreferredSize(new Dimension(120, 20));
		alignmentStrengthSlider.setPreferredSize(new Dimension(120, 20));
		cohesionStrengthSlider.setPreferredSize(new Dimension(120, 20));
		randomStrengthSlider.setPreferredSize(new Dimension(120, 20));
		wallStrengthSlider.setPreferredSize(new Dimension(120, 20));
		avoidObstacleStrengthSlider.setPreferredSize(new Dimension(120, 20));
		
		
		
		screen.add(sliders,"West");
		screen.add(board, "Center");
		setContentPane(screen);
		pack();

	}

	public void startingBoids(int amount) throws KeySizeException, KeyDuplicateException {
		for(int i =0;i<amount;i++) {
			group.addBoid(new Boids(new Vector(Math.random()*screenX,Math.random()*screenY),new Vector(0,0)));
//			kd.insert(boids.get(i).position.data, boids.get(i));
			
		}
		
	}
	
	public void move() throws KeySizeException, IllegalArgumentException, KeyMissingException, KeyDuplicateException {
		
		for(int i =0;i<group.boids.size();i++) {
//			System.out.println(group.boids.toString());
//			double coords[] = boids.get(i).position.data;
//			Boids newBoids[] = new Boids[distance];
//			kd.nearest(coords,distance).toArray(newBoids);
////			kd.delete(coords);
////			System.out.println(boids.get(i).toString());
////			System.out.println(Arrays.toString(newBoids));
//			boids.get(i).updateVelocity(newBoids, seperationCoefficient, alignmentCoefficient, cohesionCoefficient, screenX, screenY);
//			boids.get(i).updatePosition();
////			kd.insert(boids.get(i).position.data, boids.get(i));
			group.boids.get(i).updateVelocity(); //switch to get set
			group.boids.get(i).updatePosition();
		} 
		
//		kd = new KDTree(2);
//		for(int i =0;i<boids.size();i++) {
//			kd.insert(boids.get(i).position.data, boids.get(i));
//		}
		
	}
	

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(4));
		draw(g2d);
		
	}
	
	public void draw(Graphics g) {
		for(int i =0;i<group.boids.size();i++) {
			double x = group.boids.get(i).position.data[0];
			double y = group.boids.get(i).position.data[1];
//			double x2 = x+group.boids.get(i).velocity.setMagnitude(6).data[0];
//			double y2 = y+group.boids.get(i).velocity.setMagnitude(6).data[1];
//			g.drawLine((int)x,(int)y,(int)x2,(int)y2);
			g.fillOval((int) Math.round(x) - 5, (int) Math.round(y) - 5, 10, 10);
		}
		
		for(int i =0;i<group.obstacles.size();i++) {
			double x = group.obstacles.get(i).position.data[0];
			double y = group.obstacles.get(i).position.data[1];
			g.fillRect((int) Math.round(x) - 5, (int) Math.round(y) - 5, 10, 10);
		}
	}
	
	public void paint(Graphics g) {
	    super.paint(g);
	    paintComponent(g);
	}
	
	public double[] getDoubleArray(ArrayList<Double> arraylist) {
		double array[] = new double[arraylist.size()];
		for(int i =0;i<arraylist.size();i++) {
			array[i]=arraylist.get(i);
		}
		return array;
	}
	
	public ArrayList<Boids> getBoidArrayList(Boids[] array) {
		ArrayList<Boids> arraylist = new ArrayList<Boids>();
		for(int i =0;i<array.length;i++) {
			arraylist.add(array[i]);
		}
		return arraylist;
	}

	
	
	
	//carries out the actions for each of the buttons
	public void actionPerformed(ActionEvent event) {
		repaint();
		if(event.getSource()==animationTimer) {
			try {
				move();
			} catch (KeySizeException | IllegalArgumentException | KeyMissingException | KeyDuplicateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Dimension frameDimensions = getSize();
		screenX = (int) frameDimensions.getWidth();
		screenY = (int) frameDimensions.getHeight();
		
		
		group.setLocalRadius(LOCAL_RADIUS*localRadiusSlider.getValue()/100);
		group.setSeperationRadius(SEPERATION_RADIUS*seperationRadiusSlider.getValue()/100);
		group.setAlignmentRadius(ALIGNMENT_RADIUS*alignmentRadiusSlider.getValue()/100);
		group.setCohesionRadius(COHESION_RADIUS*cohesionRadiusSlider.getValue()/100);
		group.setAvoidObstacleRadius(AVOID_OBSTACLE_RADIUS*avoidObstacleRadiusSlider.getValue()/100);
		
		group.setBoidSpeed(BOID_SPEED*boidSpeedSlider.getValue()/100);
		
		group.setSeperationStrength(SEPERATION_STRENGTH*seperationStrengthSlider.getValue()/100);
		group.setAlignmentStrength(ALIGNMENT_STRENGTH*alignmentStrengthSlider.getValue()/100);
		group.setCohesionStrength(COHESION_STRENGTH*cohesionStrengthSlider.getValue()/100);
		group.setRandomStrength(RANDOM_STRENGTH*randomStrengthSlider.getValue()/100);
		group.setWallStrength(WALL_STRENGTH*wallStrengthSlider.getValue()/100);
		group.setAvoidObstacleStrength(AVOID_OBSTACLE_STRENGTH*avoidObstacleStrengthSlider.getValue()/100);
//		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getButton()==MouseEvent.BUTTON1)
			group.boids.add(new Boids(new Vector(event.getX(),event.getY()+20), new Vector(0,0)));
		else if (event.getButton()==MouseEvent.BUTTON3)
			group.obstacles.add(new Obstacles(new Vector(event.getX(),event.getY()+20)));
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