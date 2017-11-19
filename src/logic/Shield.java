package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Shield extends Entity {
	
	protected int width;
	protected int height;
	
	public Shield(int x,int y, int width,int height) {
		super(x, y);
		this.z=100;
		this.width = width;
		this.height= height;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void draw(GraphicsContext gc) {//this shield should be around the ship
		// TODO Auto-generated method stub
		gc.setFill(Color.AQUA);
		//gc.setOp
		gc.fillRect(getX(), getY(), 90, 10);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		Rectangle shieldHitbox = new Rectangle(getX(),getY(),90,10);
		return null;
	}
	
	

}
