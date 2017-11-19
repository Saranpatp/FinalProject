package logic;

import java.io.IOException;

import drawing.GameUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends MovingEntity{
	
	protected final int DEFAULT_ALIEN_HEALTH = 3; //have to hit it 10 time
	protected int health;
	private int enemytype,width,height;
	//public boolean deleted;
	

	Image alien1 = new Image("alien1Skin.gif");
	Image alien2 = new Image("alien2Skin.gif");
	Image alien3 = new Image("alien3Skin.gif");
		public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public void decreaseHealth(int damage) {// for more level gun
		if(health-damage<0) {
			health=0;
			destroyed=true;
		}
		else health-=damage;
	}
	
	
	public Enemy(int x, int y, int xSpeed, int ySpeed,int enemytype,int width,int height) {
		super(x, y, xSpeed, ySpeed);
		this.enemytype=enemytype;
		this.width=width;
		this.height=height;
		this.z=400;
		if(enemytype<4) {
			health = DEFAULT_ALIEN_HEALTH;// boss will add in later in FULL GAME RELEASE
		}
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(this.enemytype==1) {
			gc.drawImage(alien1, x, y);
			// test to find the middle of the alien to find the right point to let them shoot later
			/*gc.setStroke(Color.WHITE);
			gc.strokeLine(getX()+20, getY(), getX()+20, getY()+50);// x=x+20 and y=y+20 is the most cloestes we could find
			*/
		}
		if(this.enemytype==2) {
			gc.drawImage(alien3, x, y);
		}
		if(this.enemytype==3) {
			gc.drawImage(alien3, x, y);
		}
		
	}
	
	public void update() {
		if(x<0 || x>GameUI.DEFAULT_GAME_WIDTH-40) {
			setxSpeed(-getxSpeed()); // to make it turn back
			//ใส่ +yspeed ตรงนี้
			setY(getY()+40);
		}
		setX(getX()+getxSpeed());//move normaly
		//**************************yspeed add in later ใส่ ที่หลังให้เคลื่อน ตอนหมดจอ
		if(y>GameUI.DEFAULT_GAME_HEIGHT||y<0) {
			destroyed=true;//fixed memory leak
		}
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		Rectangle enemyHitbox = new Rectangle(x,y,width,height-3); // reduce hit box to eleminate square feeling when bullet hitting
		//enemyHitbox.setFill(Color.AQUA);
		return enemyHitbox;
	}
	
}
