package logic;

public abstract class ControlEntity extends Entity {
	public ControlEntity(int x,int y) {
		super(x, y);
	}
	public abstract void update();
}
