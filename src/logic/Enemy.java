package logic;

import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends MovingEntity{
	
	Image alien1 = new Image("alien1Skin.gif");
	Image alien2 = new Image("alien2Skin.gif");
	Image alien3 = new Image("alien3Skin.gif");
	
	private int enemytype,width,height;
	/*static {
		loadResource();
	}*/
	public Enemy(int x, int y, int xSpeed, int ySpeed,int enemytype,int width,int height) {
		super(x, y, xSpeed, ySpeed);
		this.enemytype=enemytype;
		this.width=width;
		this.height=height;
		
		// TODO Auto-generated constructor stub
	}

	public static void loadResource() {
		try {
			Image alien1 = new Image("alien1Skin.gif");
			Image alien2 = new Image("res/images/alien2Skin.gif");
			Image alien3 = new Image("res/images/alien3Skin.gif");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("cant load alien image");
		}
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(this.enemytype==1) {
			gc.drawImage(alien1, x, y);
		}
		if(this.enemytype==2) {
			gc.drawImage(alien3, x, y);
		}
		if(this.enemytype==3) {
			gc.drawImage(alien3, x, y);
		}
		
	}
	
	public void update() {
		if(x<0 || x>480) xSpeed=-xSpeed;
		x+=xSpeed;
		System.out.println("Im here");
		//System.out.println("x="+x);
		//System.out.println(xSpeed);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		Rectangle enemyHitbox = new Rectangle(x,y,width,height);
		//enemyHitbox.setFill(Color.AQUA);
		return enemyHitbox;
	}
	
}
