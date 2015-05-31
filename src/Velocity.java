
public class Velocity {

	private double vx, vy;

	public Velocity(double vx, double vy) {
		super();
		this.vx = vx;
		this.vy = vy;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	@Override
	public String toString() {
		return "Velocity [vx=" + vx + ", vy=" + vy + "]";
	}
	
}
