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
		z=0;
	}
	
	

	@Override
	public void draw(GraphicsContext gc) {//this shield should be around the ship
		// TODO Auto-generated method stub
		gc.setFill(Color.AQUA);
		System.out.println("Shield here****************************************");
		System.out.println("shield x ="+x);
		System.out.println("shield y ="+y);
		//gc.setOp
		gc.fillOval(getX(), getY(), radius, radius);
		
		
	}
	public void update() {
		
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		Rectangle shieldHitbox = new Rectangle(getX(),getY(),90,10);
		return shieldHitbox;
	}
	
	

}
