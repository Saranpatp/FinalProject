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

	protected int health;

	/*
	 * /life counter
	 * 
	 */
	public Ship(int x, int y) {// keyboard contorl
		super(x, y, DEFAULT_XSPEED, DEFAULT_YSPEED);
		health = DEFAULT_HEALTH;

	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(ship, x, y);

	}

	/*
	 * public boolean Collided(Enemy enemy) { if(this.collideWith(enemy)) {
	 * decreaseHealth(1);//ª¹ Åº1 health return } }
	 */
	public void decreaseHealth(int damage) {
		if (!flashing) {
			RenderableHolder.damageSound.play();
			flashCounter = 10;
			flashDurationCounter = 20;
			flashing = true;
			if (health - damage <= 0) {
				health = 0;
				destroyed = true;
				RenderableHolder.deathSound.play();
				System.out.println("your die! noobS");
			} else
				health -= damage;
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
		if (x > GameUI.DEFAULT_GAME_WIDTH - 50)
			x = GameUI.DEFAULT_GAME_WIDTH - 50;
		// this.x+=xSpeed;
		// System.out.println("x="+x);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		Rectangle shipHitbox = new Rectangle(x, y, 50, 50);
		shipHitbox.setFill(Color.BLUE);
		return shipHitbox;
	}

	public void update() {
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
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			xSpeed = -DEFAULT_XSPEED;
			moveLR();

		} else if (InputUtility.getKeyPressed(KeyCode.D)) {
			xSpeed = DEFAULT_XSPEED;
			moveLR();
		}
	}
}
