import java.awt.Color;
import java.util.ArrayList;

public class Game {

	private World world;
	private Ball[] targetList;
	private Ball chief;
	private int width = 2;
	private int height = 2;
	private int quantTargets = 40;
	private Color background = StdDraw.GRAY;
	private ArrayList<Ball> collidedList;
	
	public Game() {
		world = new World(width, height, background);
		targetList = new Ball[quantTargets];
		collidedList = new ArrayList<Ball>();
		
		for (int i = 0; i < quantTargets; i++) {
			targetList[i] = new Ball(
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
					StdDraw.YELLOW
				);
	}
	
	public void go() {
		StdDraw.setScale(-world.getWidth() / 2, +world.getWidth() / 2);
		boolean roda = true;
		
		while (roda == true) {
			StdDraw.clear(world.getBackground());
			
			for (int i = 0; i < quantTargets; i++) {
				targetList[i].move(world);
				targetList[i].draw();
			}
			
			chief.move(world);
			chief.draw();
			
			for (int i = 0; i < quantTargets; i++) {
				if (chief.hasCollided(targetList[i])) {					
					collidedList.add(targetList[i]);
					targetList[i].setV(chief.getV());
				}
				else{
					
				}
			}
			
			StdDraw.show(10);
		}
	}
	
}
