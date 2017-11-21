package logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import drawing.GameUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sharedObject.RenderableHolder;

public class Enemy extends MovingEntity{
	
	protected final int DEFAULT_ALIEN_HEALTH = 1; //have to hit it 1 time
	protected int health;
	private int enemytype,width,height;
	//public boolean deleted;
	

	public  Image alien1;
	public  Image alien2;
	public  Image alien3;
	
	
		public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public void decreaseHealth(int damage) {// for more level gun
		if(health-damage<=0) {
			health=0;
			destroyed=true;
			GameLogic.enemybodycount++;
		}
		else health-=damage;
	}
	
	public void loadResource() {
		try {
			alien1 = new Image("alien1Skin.gif");
			alien2 = new Image("alien2Skin.gif");
			alien3 = new Image("alien3Skin.gif");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cant load alien skin");
		}
		
	}
	
	public Enemy(int x, int y, int xSpeed, int ySpeed,int enemytype,int width,int height) {
		super(x, y, xSpeed, ySpeed);
		this.enemytype=enemytype;
		this.width=width;
		this.height=height;
		this.z=400;
		loadResource();
		if(enemytype<4) {
			health = DEFAULT_ALIEN_HEALTH;// boss will add in later in FULL GAME RELEASE
		}
		
	}
	public void hit() { //when ship hit the enemy
		decreaseHealth(2);//more damage than shoot
		RenderableHolder.damageSound.play();
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
