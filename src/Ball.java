
public class Ball {

	private Point center;
	private Velocity v;
	private int radius = 20;
	
	public Ball(int x, int y) {
		center = new Point(x, y);
		if(x % 2 == 0) {
			v = new Velocity(1, 1);
		}
		else {
			v = new Velocity(-1, 1);
		}
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	
	public int getX() {
		return this.center.getX();
	}
	
	public void setX(int x) {
		this.center.setX(x);
	}
	
	public int getY() {
		return this.center.getY();
	}
	
	public void setY(int y) {
		this.center.setY(y);
	}
	
	public int getVx() {
		return this.v.getVx();
	}
	
	public void setVx(int vx) {
		this.v.setVx(vx);
	}
	
	public int getVy() {
		return this.v.getVy();
	}
	
	public void setVy(int vy) {
		this.v.setVy(vy);
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void move(World world) {
		if((this.getX() > world.getSize() - this.getRadius() * 2) || (this.getX() < 0)) {
			this.setVx(-this.getVx());
		}
		
		if((this.getY() > world.getSize() - this.getRadius() * 2) || (this.getY() < 0)) {
			this.setVy(-this.getVy());
		}
		
		this.setX(this.getX() + this.getVx());
		this.setY(this.getY() + this.getVy());
	}
	
	public boolean hasCollided(Ball ball) {		
		int dx = this.getX() - ball.getX();
		int dy = this.getY() - ball.getY();
		
		if (Math.pow(dx, 2) + Math.pow(dy, 2) <= Math.pow(radius*2, 2)) {
			return true;
		}
		else return false;
	}
}
