import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Game {

	private boolean gameStatus = true;
	private World myWorld = new World(500);
	private int x, y;
	private Ball chief; 
	private ArrayList<Ball> greenList;
	private int quantGreen;
	private ArrayList<Ball> blueList;
	private int quantBlue;
	private ArrayList<Ball> magentaList;
	private int quantMagenta;
	private ArrayList<Ball> collidedGreenList;
	private ArrayList<Ball> collidedBlueList;
	private ArrayList<Ball> ballsOutList;
	private int points = 0;
	Random random = new Random();
	
	public Game() {
		chief = new Ball(myWorld.getSize() / 2 - 20, myWorld.getSize() / 2 - 20);
		greenList = new ArrayList<Ball>();
		blueList = new ArrayList<Ball>();
		magentaList = new ArrayList<Ball>();
		collidedGreenList = new ArrayList<Ball>();
		collidedBlueList = new ArrayList<Ball>();
		ballsOutList = new ArrayList<Ball>();
		
//		for(int i = 0; i < quantGreen; i++) {
//			Random random = new Random();
//			x = random.nextInt(myWorld.getSize() - 40);
//			y = random.nextInt(myWorld.getSize() - 40);
//			Ball ball = new Ball(x, y);
//			greenList.add(ball);
//		}
//		
//		for(int i = 0; i < quantBlue; i++) {
//			Random random = new Random();
//			x = random.nextInt(myWorld.getSize() - 40);
//			y = random.nextInt(myWorld.getSize() - 40);
//			Ball ball = new Ball(x, y);
//			blueList.add(ball);
//		}		
//		
//		for(int i = 0; i < quantMagenta; i++)  {
//			Random random = new Random();
//			x = random.nextInt(myWorld.getSize() - 40);
//			y = random.nextInt(myWorld.getSize() - 40);
//			Ball ball = new Ball(x, y);
//			magentaList.add(ball);
//		}
	}
	
	public void go() {
		JFrame frame = new JFrame();
		JPanel pointsPanel = new JPanel();
		JLabel pointsIndicator = new JLabel(String.valueOf(points));
		Font pointsFont = new Font("serif", Font.BOLD, 30);
		GamePanel drawPanel = new GamePanel();
		pointsIndicator.setFont(pointsFont);
		pointsPanel.add(pointsIndicator);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
	    frame.getContentPane().add(BorderLayout.NORTH, pointsPanel);
	    frame.setSize(myWorld.getSize(), myWorld.getSize());
	    frame.setVisible(true);
	    
	    while(gameStatus == true) {
	    		
	    	quantGreen = random.nextInt(2);
	    	quantBlue = random.nextInt(2);
	    	quantMagenta = random.nextInt(2);
	    	
	    	if(greenList.size() < 5 && blueList.size() < 5 && magentaList.size() < 5) {
		    	for(int i = 0; i < quantGreen; i++) {
					x = random.nextInt(myWorld.getSize() - 40);
					y = 0;
					Ball ball = new Ball(x, y);
					greenList.add(ball);
				}
				
				for(int i = 0; i < quantBlue; i++) {
					x = random.nextInt(myWorld.getSize() - 40);
					y = 0;
					Ball ball = new Ball(x, y);
					blueList.add(ball);
				}		
				
				for(int i = 0; i < quantMagenta; i++)  {
					x = random.nextInt(myWorld.getSize() - 40);
					y = 0;
					Ball ball = new Ball(x, y);
					magentaList.add(ball);
				}
	    	}
	    	
	    	for(Ball ball:greenList) {
	        	ball.move(myWorld);
	        	
	        	if(chief.hasCollided(ball) && ball.getVx() != 0) {
	        		ball.setVx(0);
	        		ball.setVy(0);
	        		collidedGreenList.add(ball);
	        	}
	        }
	    	
	    	for(Ball ball:blueList) {
	        	ball.move(myWorld); 
	        	
	        	for(Ball collidedGreenBall:collidedGreenList) {
	        		if(ball.hasCollided(collidedGreenBall)) {
	        			int size = collidedGreenList.size();

	        			if (size == 3) {
	        				points += 1;
	        			}else if (size == 4) {
	        				points += 5;
	        			} else if (size == 5) {
	        				points += 11;
	        			} else if (size == 6) {
	        				points += 17;
	        			} else if (size == 7) {
	        				points += 25;
	        			} else if (size == 8) {
	        				points += 33;
	        			} else if (size == 9) {
	        				points += 41;
	        			} else if (size == 10) {
	        				points += 51;
	        			} else if (size > 10){
	        				points += 51;
	        			}
	        			
	        			pointsIndicator.setText(String.valueOf(points));
	        			collidedBlueList.add(ball);
	        			break;
	        		}
	        	}
	        }
	    	
	    	for(Ball collidedBlueBall:collidedBlueList) {
        		if(blueList.contains(collidedBlueBall)) {
        			blueList.remove(collidedBlueBall);
        			
        			for(Ball collidedGreenBall:collidedGreenList) {
                		if(greenList.contains(collidedGreenBall)) {
                			greenList.remove(collidedGreenBall);
                		}
                	}
        			
        			collidedGreenList.clear();
        		}
        	}
	    	collidedBlueList.clear();
	    	
	    	for(Ball ball:magentaList) {
	        	ball.move(myWorld);
	        	
	        	if(chief.hasCollided(ball)) {
	        		try {
//	    	        	Thread.sleep(50);
//	        			gameStatus = false;
	    	        }
	    	        catch(Exception ex) { 
	    	        	System.out.println("Algo de errado não está certo...");
	    	        }
	        	}
	        }
	    	
	    	for(Ball ball:greenList) {
	    		if(ball.getX() > myWorld.getSize() || ball.getY() > myWorld.getSize()
	    				|| ball.getX() < -40 || ball.getY() < -40) {
	    			ballsOutList.add(ball);
	    		}
	    	}
	    	
	    	for(Ball ball:blueList) {
	    		if(ball.getX() > myWorld.getSize() || ball.getY() > myWorld.getSize()
	    				|| ball.getX() < -40 || ball.getY() < -40) {
	    			ballsOutList.add(ball);
	    		}
	    	}
	    	
	    	for(Ball ball:magentaList) {
	    		if(ball.getX() > myWorld.getSize() || ball.getY() > myWorld.getSize()
	    				|| ball.getX() < -40 || ball.getY() < -40) {
	    			ballsOutList.add(ball);
	    		}
	    	}
	    	
	    	for(Ball ball:ballsOutList) {
	    		if(greenList.contains(ball)) {
	    			greenList.remove(ball);
	    		}
	    		
	    		if(blueList.contains(ball)) {
	    			blueList.remove(ball);
	    		}
	    		
	    		if(magentaList.contains(ball)) {
	    			magentaList.remove(ball);
	    		}
	    	}
	        ballsOutList.clear();
	    	
	        try {
	        	Thread.sleep(50);
	        	drawPanel.repaint();
	        }
	        catch
	        (Exception ex) { 
	        	System.out.println("Algo de errado não está certo...");
	        }
	    }
	    
	}
	
	public class GamePanel extends JPanel {
		
		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(Color.RED);
			g.fillOval(chief.getX(), chief.getY(), chief.getRadius()*2, chief.getRadius()*2);

			try {
				for(Ball ball:greenList) {
					g.setColor(Color.GREEN);
					g.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
				}
			}
			catch(Exception ex) {

			}
			
			try {
				for(Ball ball:blueList) {
					g.setColor(Color.BLUE);
					g.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
				}
			}
			catch(Exception ex) {

			}
			
			try {
				for(Ball ball:magentaList) {
					g.setColor(Color.MAGENTA);
					g.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
				}
			}	
			catch(Exception ex) {

			}
			
	    }
		
	}
	
}
