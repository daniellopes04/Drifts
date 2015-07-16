import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
//import java.awt.Event;

public class Game implements MouseMotionListener {

	private World world;
	private Ball[] greenList;
	private Ball[] blueList;
	private Ball[] magentaList;
	private Ball chief;
	private int width = 2;
	private int height = 2;
	private int quantGreens = 20;
	private int quantBlues = 10;
	private int quantMagentas = 40;
	private Color background = StdDraw.GRAY;
	private ArrayList<Ball> collidedList;
	
	JFrame frame = new JFrame();
	JPanel telaJogo = new JPanel();
	
	public Game() {
		world = new World(width, height, background);
		greenList = new Ball[quantGreens];
		blueList = new Ball[quantBlues];
		magentaList = new Ball[quantMagentas];
		
		//telaJogo.addMouseListener(chief);
		telaJogo.addMouseMotionListener(this);
		frame.getContentPane().add(BorderLayout.CENTER, telaJogo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		collidedList = new ArrayList<Ball>();
		
		for (int i = 0; i < quantGreens; i++) {
			greenList[i] = new Ball(
								new Point(
									Math.random(), 
									0.95
								), 
								new Velocity(
									-0.01 * Math.random(), 
									-0.01 * Math.random()
								), 
								0.05,
								StdDraw.GREEN
							);
		}
		
		for (int i = 0; i < quantBlues; i++) {
			blueList[i] = new Ball(
								new Point(
									2 * Math.random() - 1, 
									Math.random()
								), 
								new Velocity(
									-0.01 * Math.random(), 
									0.01 * Math.random()
								), 
								0.05,
								StdDraw.BLUE
							);
		}
		
		for (int i = 0; i < quantMagentas; i++) {
			magentaList[i] = new Ball(
								new Point(
									2 * Math.random() - 1, 
									Math.random()
								), 
								new Velocity(
									-0.01 * Math.random(), 
									0.01 * Math.random()
								), 
								0.05,
								StdDraw.MAGENTA
							);
		}
		
		chief = new Ball(
				new Point(
					2 * Math.random() - 1, 
					Math.random()
				), 
				new Velocity(
					-0.01 * Math.random(), 
					0.01 * Math.random()
				), 
				0.05, 
				StdDraw.RED
			);
	}
	
	public void go() {
		StdDraw.setScale(-world.getWidth() / 2, +world.getWidth() / 2);
		boolean roda = true;
		
		while (roda == true) {
			StdDraw.clear(world.getBackground());
			
			for (int i = 0; i < quantGreens; i++) {
				greenList[i].move(world);
				greenList[i].draw();
			}
			
			for (int i = 0; i < quantBlues; i++) {
				blueList[i].move(world);
				blueList[i].draw();
			}
			
			for (int i = 0; i < quantMagentas; i++) {
				magentaList[i].move(world);
				magentaList[i].draw();
			}
			
			chief.move(world);
			chief.draw();
			
			for (int i = 0; i < quantGreens; i++) {
				if (chief.hasCollided(greenList[i])) {					
					collidedList.add(greenList[i]);
					greenList[i].setV(chief.getV());
				}
				else{
					
				}
			}

			for (int i = 0; i < quantBlues; i++) {
				if (chief.hasCollided(blueList[i])) {					
					collidedList.add(blueList[i]);
					blueList[i].setV(chief.getV());
				}
			}
			
			/*for (int i = 0; i < quantMagentas; i++) {
				if (chief.hasCollided(magentaList[i])) {					
					roda = false;
					System.out.println("Cabou");
				}
			} */
			
			StdDraw.show(10);
		}
	}
	
	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent me) {
		int newX = me.getX();
		int newY = me.getY();
		Point newCenter = new Point(newX, newY);
		
		if(newY > 2) {
			newCenter.setPy(2);
		}
		
		if(newX > 2) {
			newCenter.setPx(2);
		}
		
		chief.setCenter(newCenter);
	}
	
}
