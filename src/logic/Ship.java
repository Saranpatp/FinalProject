package logic;

import drawing.GameUI;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.Bullet;
import sharedObject.RenderableHolder;

public class Ship extends MovingEntity {
	Image ship = new Image("shipSkin.gif");
	private static int DEFAULT_XSPEED = 10;
	private static int DEFAULT_YSPEED = 10;
	private static int DEFAULT_HEALTH = 3;// 3 life
	private boolean flashing = false;
	private int flashCounter;
	private int flashDurationCounter;
	public boolean isShooting = false;
	public boolean isShieldON = false;
	private int shieldCounter; // will have ui for this
	private final int MAX_SHIELD_DURATION=600;//max 10 sec of shield
	private final int MIN_SHIELD_DURATION=300;
	private Shield shield;
	protected int health;

	/*
	 * /life counter
	 * 
	 */
	public Ship(int x, int y) {// keyboard contorl
		super(x, y, DEFAULT_XSPEED, DEFAULT_YSPEED);
		health = DEFAULT_HEALTH;
		z=1000;
	}

	@Override
	public void draw(GraphicsContext gc) {
		//Health UI
		gc.setLineWidth(1);
		gc.setStroke(Color.WHITE);
		gc.strokeRect(50, 30, DEFAULT_HEALTH*100, 10);
				
		gc.setFill(Color.RED);
		gc.fillRect(50, 30, health*100, 10);
		//ship
		gc.drawImage(ship, x, y);
		if (isShieldON) {
			shield = new Shield(x, y);
			shield.draw(gc);
		}else gc.setLineWidth(1);
		
		//shield UI
		gc.setStroke(Color.RED);
		gc.strokeRect(50, 50, MIN_SHIELD_DURATION*0.5, 10); //minlimit
		
		gc.setStroke(Color.WHITE);
		gc.strokeRect(50, 50, MAX_SHIELD_DURATION*0.5, 10);//max limit
		
		gc.setFill(Color.CHARTREUSE);
		gc.fillRect(50, 50, shieldCounter*0.5, 10);//shield bar
	}

	/*
	 * public boolean Collided(Enemy enemy) { if(this.collideWith(enemy)) {
	 * decreaseHealth(1);//ª¹ Åº1 health return } }
	 */
	public void decreaseHealth(int damage) {
		if (!isShieldON) {
			if (!flashing) {
				RenderableHolder.damageSound.play();
				flashCounter = 10;
				flashDurationCounter = 10;
				flashing = true;
				if (health - damage <= 0) {
					health = 0;
					destroyed = true;
					RenderableHolder.deathSound.play();
					System.out.println("your die! noobS");
				} else
					health -= damage;
			}
		}else {
			RenderableHolder.shieldSound.play();
		}
		

	}

	private void moveFB() {// change to getter and setter later
		// if(y<680&&y>-50)
		this.y += ySpeed;
		if (y < 0)
			y = 0;
		if (y > GameUI.DEFAULT_GAME_HEIGHT - 100)
			y = GameUI.DEFAULT_GAME_HEIGHT - 100;
		// else this.y+=ySpeed;
	}

	private void moveLR() {
		// if(x>800&& x<-50)
		x += xSpeed;
		if (x < 0)
			x = 0;
		if (x > GameUI.DEFAULT_GAME_WIDTH - 70)
			x = GameUI.DEFAULT_GAME_WIDTH - 70;
		// this.x+=xSpeed;
		// System.out.println("x="+x);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		if (isShieldON)
			return shield.getBounds();
		Rectangle shipHitbox = new Rectangle(x, y, 50, 50);
		shipHitbox.setFill(Color.BLUE);
		return shipHitbox;
	}

	public void update() {
		//if(isShieldON) {shield = new Shield(x, y);}
		if(isShieldON) {
			shieldCounter--;
			if(shieldCounter<=0) isShieldON=false;
		}else if(shieldCounter<600) {
			shieldCounter++;
		}System.out.println("Shieldcounter ="+shieldCounter);
		
		if (flashing) {
			if (flashCounter == 0) {
				this.visible = true;
				flashing = false;
			} else {
				if (flashDurationCounter > 0) {
					this.visible = flashCounter <= 5;
					flashDurationCounter--;
				} else {
					this.visible = true;
					flashDurationCounter = 10;
					flashCounter--;
				}
			}

		} else {
			this.visible = !InputUtility.getKeyPressed(KeyCode.SHIFT);
		}
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			ySpeed = -DEFAULT_YSPEED;
			moveFB();
		}
		if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
			// shoot the bullet
			isShooting = true;
			// System.out.println("PEW PEW PEW");
		}
		if (InputUtility.getKeyPressed(KeyCode.S)) {
			ySpeed = DEFAULT_YSPEED;
			moveFB();
		}
		if (InputUtility.getKeyPressed(KeyCode.F)) {
			if (!isShieldON&&shieldCounter>=300)
				isShieldON = true; // can only turn on shield shield will drain the power and then off
			// else isShieldON=true;
		}
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			xSpeed = -DEFAULT_XSPEED;
			moveLR();

		} else if (InputUtility.getKeyPressed(KeyCode.D)) {
			xSpeed = DEFAULT_XSPEED;
			moveLR();
		}
	}
}
