import java.awt.Color;

public class Ball {

	private Point center;
	private Velocity v;
	private double radius;
	private Color color;
	
	public Ball(Point center, Velocity v, double radius, Color color) {
		this.center = center;
		this.v = v;
		this.radius = radius;
		this.color = color;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public Velocity getV() {
		return v;
	}

	public void setV(Velocity v) {
		this.v = v;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Ball [center=" + center + ", v=" + v + ", radius=" + radius
				+ ", color=" + color + "]";
	}
	
	public void move(World world) {
		
		if(Math.abs(center.getPx() + v.getVx()) > (world.getWidth() / 2) - radius) {
			v.setVx(-v.getVx());
		}
		
		if(Math.abs(center.getPy() + v.getVy()) > (world.getWidth() / 2) - radius) {
			v.setVy(-v.getVy());
		}
		
		center.setPx(center.getPx() + v.getVx());
		center.setPy(center.getPy() + v.getVy());
		
	}
	
	public void draw() {
		
		StdDraw.setPenColor(getColor());
		StdDraw.filledCircle(getCenter().getPx(), getCenter().getPy(), getRadius());
		
	}
	
	public boolean hasCollided(Ball b) {
		
		double dx = this.getCenter().getPx() - b.getCenter().getPx();
		double dy = this.getCenter().getPy() - b.getCenter().getPy();
		double distance = this.radius + b.radius;
		
		if (Math.pow(dx, 2) + Math.pow(dy, 2) <= Math.pow(distance, 2)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
