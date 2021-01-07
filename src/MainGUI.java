//imports
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;


public class MainGUI extends JFrame implements ActionListener, MouseListener {
	//gui stuff
	JPanel screen = new JPanel();
	int screenX=960;
	int screenY=540;
	
	//boids
	ArrayList<Boids> boids = new ArrayList<Boids>();

	// constructor method
	public MainGUI() {
		frameSetup();
		panelDesign();
	}

	//set up the frame
	private void frameSetup() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(screenX, screenY);
		setTitle("Boids");
		setLayout(null);
		add(screen);
		setVisible(true);
		
//		setResizable(false);
		repaint();
		
	}

	//set up the panel
	private void panelDesign() {
		//add features for the screen
		screen.setBorder(null);
		screen.setBounds(0, 0, screenX, screenY);
		screen.setLayout(null);
		screen.setBackground(new java.awt.Color(47, 47, 47));
		screen.addMouseListener(this);
		

	}
	
	
	private void addBoid(int x, int y) {
		boids.add(new Boids(new Vector(x,y),new Vector(0,0)));
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