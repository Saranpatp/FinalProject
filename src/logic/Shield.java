package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Shield extends Entity {
	
	protected int radius;
	protected int height;
	
	public Shield(Ship ship) {
		x=ship.getX();
		y=ship.getY();
		radius = 50;
		z=100000;
	}
	
	

	@Override
	public void draw(GraphicsContext gc) {//this shield should be around the ship
		// TODO Auto-generated method stub
		gc.setFill(Color.AQUA);
		//gc.setOp
		gc.fillOval(getX(), getY(), radius, radius);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		Rectangle shieldHitbox = new Rectangle(getX(),getY(),90,10);
		return null;
	}
	
	

}
