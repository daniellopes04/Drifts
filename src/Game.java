import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {

	private World myWorld = new World(500);
	private int x, y;
	private Ball chief; 
	private Ball[] greenList;
	private int quantGreens = 5;
	
	public Game() {
		chief = new Ball(myWorld.getSize() / 2 - 20, myWorld.getSize() / 2 - 20);
		greenList = new Ball[quantGreens];
		
		for(int i = 0; i < quantGreens; i++) {
			Random random = new Random();
			x = random.nextInt(myWorld.getSize() - 40);
			y = random.nextInt(myWorld.getSize() - 40);
			greenList[i] = new Ball(x, y);
		}
	}
	
	public void go() {
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    GamePanel drawPanel = new GamePanel();
	    frame.getContentPane().add(drawPanel);
	    frame.setSize(myWorld.getSize(), myWorld.getSize());
	    frame.setVisible(true);
	    
	    for(int i = 0; i < 3000; i++) {
	        for(int j = 0; j < quantGreens; j++) {
	        	greenList[j].move(myWorld);
	        	
	        	if(chief.hasCollided(greenList[j])) {
	        		try {
	    	        	Thread.sleep(100);
	    	        }
	    	        catch(Exception ex) { 
	    	        	System.out.println("Deu ruim...");
	    	        }
	        	}
	        	
	        	drawPanel.repaint();
	        }
	        
	        try {
	        	Thread.sleep(5);
	        }
	        catch(Exception ex) { 
	        	System.out.println("Deu ruim...");
	        }
	    }
	}
	
	public class GamePanel extends JPanel {
		
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(Color.red);
			g.fillOval(chief.getX(), chief.getY(), chief.getRadius()*2, chief.getRadius()*2);
			
			for(int i = 0; i < quantGreens; i++) {
				g.setColor(Color.green);
				g.fillOval(greenList[i].getX(), greenList[i].getY(), greenList[i].getRadius()*2, greenList[i].getRadius()*2);
			}
	    }
		
	}
	
}
