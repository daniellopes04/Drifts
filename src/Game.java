import java.awt.Color;
import java.util.ArrayList;
//import java.awt.Event;

public class Game {

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
	
	public Game() {
		world = new World(width, height, background);
		greenList = new Ball[quantGreens];
		blueList = new Ball[quantBlues];
		magentaList = new Ball[quantMagentas];
		
		collidedList = new ArrayList<Ball>();
		
		for (int i = 0; i < quantGreens; i++) {
			greenList[i] = new Ball(
								new Point(
									2 * Math.random() - 1, 
									Math.random()
								), 
								new Velocity(
									-0.02 * Math.random(), 
									0.02 * Math.random()
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
									-0.02 * Math.random(), 
									0.02 * Math.random()
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
									-0.02 * Math.random(), 
									0.02 * Math.random()
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
					-0.02 * Math.random(), 
					0.02 * Math.random()
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
			
			StdDraw.show(10);
		}
	}
	
}
