import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private Ball collidedBlue;
	private ArrayList<Ball> ballsOutList;
	private int points = 0;
	private int x1, y1, x2, y2, x3, y3;
	private Ellipse2D myChief;
	Random random = new Random();
	
	public Game() {
		chief = new Ball(myWorld.getSize() / 2 - 20, myWorld.getSize() / 2 - 20);
		greenList = new ArrayList<Ball>();
		blueList = new ArrayList<Ball>();
		magentaList = new ArrayList<Ball>();
		collidedGreenList = new ArrayList<Ball>();
		ballsOutList = new ArrayList<Ball>();
		x1 = chief.getX();
		y1 = chief.getY();
	}
	
	public void go() {
		JFrame frame = new JFrame();
		JPanel pointsPanel = new JPanel();
		JLabel pointsIndicator = new JLabel();
		JPanel instructionsPanel = new JPanel();
		JLabel instructions = new JLabel("Clique com o mouse na bola vermelha e arraste para jogar.");
		Font pointsFont = new Font("serif", Font.BOLD, 30);
		GamePanel drawPanel = new GamePanel();
		pointsIndicator.setFont(pointsFont);
		pointsPanel.add(pointsIndicator);
		instructionsPanel.add(instructions);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
	    frame.getContentPane().add(BorderLayout.NORTH, pointsPanel);
	    frame.getContentPane().add(BorderLayout.SOUTH, instructionsPanel);
	    frame.setSize(myWorld.getSize(), myWorld.getSize());
	    frame.setVisible(true);
	    
	    try {
			pointsIndicator.setText("O jogo começará em..");
			Thread.sleep(1000);
			pointsIndicator.setText("3");
			Thread.sleep(1000);
			pointsIndicator.setText("2");
			Thread.sleep(1000);
			pointsIndicator.setText("1");
			Thread.sleep(1000);
			pointsIndicator.setText("Comece!");
			Thread.sleep(1000);
			pointsIndicator.setText("Pontos: " + String.valueOf(points));
			
        }
        catch(Exception ex) { 
        	System.out.println("Algo de errado não está certo...");
        }
	    
	    while(gameStatus == true) {
	    	quantGreen = random.nextInt(2);
	    	quantBlue = random.nextInt(2);
	    	quantMagenta = random.nextInt(2);
	    	
	    	if(greenList.size() < 5 && blueList.size() < 3 && magentaList.size() < 7) {
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
	        	
	        	if(ball.hasCollided(chief) && ball.getVx() != 0) {
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
	        			
	        			pointsIndicator.setText("Pontos: " + String.valueOf(points));
	        			collidedBlue = ball;
	        			break;
	        		}
	        	}
	        }
	    	
	    	if(collidedBlue != null) {
	    		for(Ball collidedGreenBall:collidedGreenList) {
	        		if(greenList.contains(collidedGreenBall)) {
	        			greenList.remove(collidedGreenBall);
	        		}
	        	}
	    		collidedGreenList.clear();
		    	blueList.remove(collidedBlue);
		    	collidedBlue = null;
	    	}
	    	
	    	
	    	for(Ball ball:magentaList) {
	        	ball.move(myWorld);
	        	
	        	if(ball.hasCollided(chief)) {
	        		try {
	        			gameStatus = false;
	        			pointsIndicator.setText("Você perdeu... =(");
	    	        }
	    	        catch(Exception ex) { 
	    	        	System.out.println("Algo de errado não está certo...");
	    	        }
	        	}
	        }
	    	
	    	for(Ball ball:greenList) {
	    		if((ball.getX() > myWorld.getSize() || ball.getY() > myWorld.getSize() 
	    				|| ball.getX() < -40 || ball.getY() < -40) 
	    				&& collidedGreenList.contains(ball) == false) {
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

		public GamePanel(){
			addMouseListener(new myMouseListener());
			addMouseMotionListener(new myMouseMotionListener());
		}
		
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			myChief = new Ellipse2D.Float(x1, y1, chief.getRadius()*2, chief.getRadius()*2);
			
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g2d.setColor(Color.RED);
			g2d.fill(myChief);
			
			try {
				for(Ball ball:greenList) {
					g2d.setColor(Color.GREEN);
					g2d.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
				}
			}
			catch(Exception ex) {

			}
			
			try {
				for(Ball ball:blueList) {
					g2d.setColor(Color.BLUE);
					g2d.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
				}
			}
			catch(Exception ex) {

			}
			
			try {
				for(Ball ball:magentaList) {
					g2d.setColor(Color.MAGENTA);
					g2d.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
				}
			}	
			catch(Exception ex) {

			}
	    }
		
		public class myMouseListener extends MouseAdapter {
			public void mousePressed(MouseEvent e) {
				x2 = e.getX();
				y2 = e.getY();
			}
		}
		
		public class myMouseMotionListener extends MouseMotionAdapter {
			public void mouseDragged(MouseEvent e) {
				if(gameStatus) {
					if(myChief.getBounds2D().contains(x2, y2)) {
						x3 = e.getX();
						y3 = e.getY();
						
						x1 += x3 - x2;
						y1 += y3 - y2;
						
						chief.setX(x1);
						chief.setY(y1);
						
						for(Ball ball:greenList) {
				        	if(chief.hasCollided(ball) && ball.getVx() != 0) {
				        		ball.setVx(0);
				        		ball.setVy(0);
				        		collidedGreenList.add(ball);
				        	}
				        }
						
						for(Ball ball:collidedGreenList) {
							ball.setX(ball.getX() + x3 - x2);
							ball.setY(ball.getY() + y3 - y2);
						}
						
						x2 = x3;
						y2 = y3;
					}

					repaint();
				}
			}
		}
	}
}
