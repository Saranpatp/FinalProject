package logic;



public abstract class MovingEntity extends Entity implements Moveable  {
	protected int xSpeed;
	protected int ySpeed;
	
	public MovingEntity(int x,int y ,int xSpeed,int ySpeed) {
		super(x, y);
		this.xSpeed=xSpeed;
		this.ySpeed=ySpeed;
	}
	
	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	@Override
	public void move() {
		this.x+=xSpeed;
		this.y+=ySpeed;
	}

	
}
