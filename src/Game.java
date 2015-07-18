import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {

	private boolean gameStatus = true;
	private World myWorld = new World(500);
	private int x, y;
	private Ball chief; 
	private ArrayList<Ball> greenList;
	private int quantGreen = 10;
	private ArrayList<Ball> blueList;
	private int quantBlue = 5;
	private ArrayList<Ball> magentaList;
	private int quantMagenta = 5;
	private ArrayList<Ball> collidedGreenList;
	private ArrayList<Ball> collidedBlueList;
	private int points = 0;
	
	public Game() {
		chief = new Ball(myWorld.getSize() / 2 - 20, myWorld.getSize() / 2 - 20);
		greenList = new ArrayList<Ball>();
		blueList = new ArrayList<Ball>();
		magentaList = new ArrayList<Ball>();
		collidedGreenList = new ArrayList<Ball>();
		collidedBlueList = new ArrayList<Ball>();
		
		for(int i = 0; i < quantGreen; i++) {
			Random random = new Random();
			x = random.nextInt(myWorld.getSize() - 40);
			y = random.nextInt(myWorld.getSize() - 40);
			Ball ball = new Ball(x, y);
			greenList.add(ball);
		}
		
		for(int i = 0; i < quantBlue; i++) {
			Random random = new Random();
			x = random.nextInt(myWorld.getSize() - 40);
			y = random.nextInt(myWorld.getSize() - 40);
			Ball ball = new Ball(x, y);
			blueList.add(ball);
		}		
		
		for(int i = 0; i < quantMagenta; i++)  {
			Random random = new Random();
			x = random.nextInt(myWorld.getSize() - 40);
			y = random.nextInt(myWorld.getSize() - 40);
			Ball ball = new Ball(x, y);
			magentaList.add(ball);
		}
	}
	
	public void go() {
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    GamePanel drawPanel = new GamePanel();
	    frame.getContentPane().add(drawPanel);
	    frame.setSize(myWorld.getSize(), myWorld.getSize());
	    frame.setVisible(true);
	    
	    while(gameStatus == true) {
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
	        			
//	        			points += collidedGreenList.size() * 10;
	        			System.out.println(collidedGreenList.size() + " " + points);
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
	        
	        try {
	        	Thread.sleep(20);
	        	drawPanel.repaint();
	        }
	        catch(Exception ex) { 
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
			
			for(Ball ball:greenList) {
				g.setColor(Color.GREEN);
				g.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
			}
			
			for(Ball ball:blueList) {
				g.setColor(Color.BLUE);
				g.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
			}
			
//			for(Ball ball:magentaList) {
//				g.setColor(Color.MAGENTA);
//				g.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
//			}
			
	    }
		
	}
	
}
