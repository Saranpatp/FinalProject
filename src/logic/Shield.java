package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Shield extends Entity {
	
	protected int radius;
	protected int height;
	
	public Shield(int x,int y) {
		super(x,y);
		radius = 75;
		z=1000;
	}
	
	

	@Override
	public void draw(GraphicsContext gc) {//this shield should be around the ship
		// TODO Auto-generated method stub
		//gc.setFill(Color.AQUA);
		/*System.out.println("Shield here****************************************");
		System.out.println("shield x ="+x);
		System.out.println("shield y ="+y);*/
		gc.setLineWidth(5);
		gc.setStroke(Color.CORNFLOWERBLUE);
		gc.strokeOval(getX()-13, getY()-10, radius, radius);
		
		
		
	}
	public void update() {
		
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		Rectangle shieldHitbox = new Rectangle(getX()-13,getY()-10,radius,radius);
		return shieldHitbox;
	}
	
	

}
