//imports
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import edu.wlu.cs.levy.CG.*;
public class MainGUI extends JFrame implements ActionListener, MouseListener {
	//gui stuff
	JPanel screen = new JPanel();
	int screenX=960;
	int screenY=540;
	
	//boids
	ArrayList<Boids> boids = new ArrayList<Boids>();
	KDTree kd;
	
	//settings
	int distance;
	double seperationCoefficient;
	double alignmentCoefficient;
	double cohesionCoefficient;

	// constructor method
	public MainGUI() throws KeySizeException, KeyDuplicateException {
		frameSetup();
		panelDesign();
		kd = new KDTree(2);
		startingBoids(20);//temporary
	}

	//set up the frame
	public void frameSetup() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(screenX, screenY);
		setTitle("Boids");
		setLayout(null);
		add(screen);
		setVisible(true);
		
		setResizable(false); //remove later
		repaint();
		
	}

	//set up the panel
	public void panelDesign() {
		//add features for the screen
		screen.setBorder(null);
		screen.setBounds(0, 0, screenX, screenY);
		screen.setLayout(null);
		screen.setBackground(new java.awt.Color(47, 47, 47));
		screen.addMouseListener(this);
		

	}
	

	public void startingBoids(int amount) throws KeySizeException, KeyDuplicateException {
		for(int i =0;i<amount;i++) {
			addBoid(Math.random()*screenX,Math.random()*screenY);
			kd.insert(getDoubleArray(boids.get(i).position.data), boids.get(i));
			
		}
	}
	
	public void move() throws KeySizeException, IllegalArgumentException, KeyMissingException, KeyDuplicateException {
		for(int i =0;i<boids.size();i++) {
			double coords[] = getDoubleArray(boids.get(i).position.data);
			ArrayList<Boids> newBoids = (ArrayList<Boids>) kd.nearest(coords,distance);
			kd.delete(coords);
			boids.get(i).updateVelocity(newBoids, seperationCoefficient, alignmentCoefficient, cohesionCoefficient);
			boids.get(i).updatePosition();
			kd.insert(getDoubleArray(boids.get(i).position.data), boids.get(i));
		}
		
		kd = new KDTree(2);
		for(int i =0;i<boids.size();i++) {
			kd.insert(getDoubleArray(boids.get(i).position.data), boids.get(i));
		}
		
	}
	
	
	
	
	public void addBoid(double x, double y) {
		boids.add(new Boids(new Vector(x,y),new Vector(0,0)));
	}
	
	public void draw(Graphics g) {
		for(int i =0;i<boids.size();i++) {
			double x = boids.get(i).position.data.get(0);
			double y = boids.get(i).position.data.get(1);
			g.drawLine((int)x,(int)y,(int)x,(int)y);
		}
	}
	
	public double[] getDoubleArray(ArrayList<Double> arraylist) {
		double array[] = new double[arraylist.size()];
		for(int i =0;i<arraylist.size();i++) {
			array[i]=arraylist.get(i);
		}
		return array;
	}

	
	
	
	//carries out the actions for each of the buttons
	public void actionPerformed(ActionEvent event) {

	}

	@Override
	public void mouseClicked(MouseEvent event) {
		addBoid(event.getX(),event.getY());
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