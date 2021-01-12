//imports
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import edu.wlu.cs.levy.CG.*;
public class MainGUI extends JFrame implements ActionListener, MouseListener {
	//gui stuff
	JPanel screen = new JPanel();
	int screenX=960;
	int screenY=540;
	Timer animationTimer = new Timer(30, this);
	
	//boids
	ArrayList<Boids> boids = new ArrayList<Boids>();
	KDTree kd;
	
	//settings
	int distance= 6;
	double seperationCoefficient= 10;
	double alignmentCoefficient = 10;
	double cohesionCoefficient =100;

	// constructor method
	public MainGUI() throws KeySizeException, KeyDuplicateException {
		frameSetup();
		panelDesign();
		kd = new KDTree(2);
		startingBoids(30);//temporary
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
		
		setResizable(false); //remove later
		repaint();
		
	}

	//set up the panel
	public void panelDesign() {
		//add features for the screen
		screen.setBorder(null);
		screen.setBounds(0, 0, screenX, screenY);
		screen.setLayout(null);
//		screen.setBackground(new java.awt.Color(47, 47, 47));
		screen.addMouseListener(this);
		

	}

	public void startingBoids(int amount) throws KeySizeException, KeyDuplicateException {
		for(int i =0;i<amount;i++) {
			boids.add(new Boids(new Vector(Math.random()*screenX,Math.random()*screenY),new Vector(0,0)));
			kd.insert(boids.get(i).position.data, boids.get(i));
			
		}
		
	}
	
	public void move() throws KeySizeException, IllegalArgumentException, KeyMissingException, KeyDuplicateException {
		
		for(int i =0;i<boids.size();i++) {
			System.out.println(boids.toString());
			double coords[] = boids.get(i).position.data;
			Boids newBoids[] = new Boids[distance];
			kd.nearest(coords,distance).toArray(newBoids);
//			kd.delete(coords);
//			System.out.println(boids.get(i).toString());
//			System.out.println(Arrays.toString(newBoids));
			boids.get(i).updateVelocity(newBoids, seperationCoefficient, alignmentCoefficient, cohesionCoefficient, screenX, screenY);
			boids.get(i).updatePosition();
//			kd.insert(boids.get(i).position.data, boids.get(i));
		}
		
		kd = new KDTree(2);
		for(int i =0;i<boids.size();i++) {
			kd.insert(boids.get(i).position.data, boids.get(i));
		}
		
	}
	
	
	
	
	

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(5));
		draw(g2d);
		
	}
	
	public void draw(Graphics g) {
		for(int i =0;i<boids.size();i++) {
			double x = boids.get(i).position.data[0];
			double y = boids.get(i).position.data[1];
			g.drawLine((int)x,(int)y,(int)x,(int)y);
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
		if(event.getSource()==animationTimer) {
			try {
				move();
				repaint();
			} catch (KeySizeException | IllegalArgumentException | KeyMissingException | KeyDuplicateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
//		addBoid(event.getX(),event.getY());
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