package logic;



public abstract class MovingEntity extends Entity  {
	protected int xSpeed;
	protected int ySpeed;
	
	public MovingEntity(int x,int y ,int xSpeed,int ySpeed) {
		super(x, y);
		this.xSpeed=xSpeed;
		this.ySpeed=ySpeed;
	}
	
	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public void move() {
		this.x+=xSpeed;
		this.y+=ySpeed;
	}

	
}
