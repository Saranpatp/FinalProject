package logic;



import drawing.GameUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sharedObject.RenderableHolder;

public class Bullet extends MovingEntity {
	int diameter; // how large the bullet will be
	private int damage; // damage of the bullet
	public static final int DEFAULT_BULLET_DAMAGE = 1;
	//public boolean deleted=false;//may have getter for this later
	public int getDiameter() {
		return diameter;
	}
	
	public boolean isCollide(Enemy[][] enemywave) {//only ship can shoot with this iscollied function 
		for(int i =0;i<enemywave.length;i++) {
			for(int j = 0; j<enemywave[i].length;j++) {
				if(this.collideWith(enemywave[i][j])&&!enemywave[i][j].isDestroyed()) {
					//decrease health
					
						//AudioClip damageSound = new AudioClip(ClassLoader.getSystemResource("damageSound.wav").toString());
					RenderableHolder.damageSound.play();
					
					enemywave[i][j].decreaseHealth(damage);//visible is not set
					destroyed=true;// this destroy the bullet
					return isDestroyed(); // this return this bullet is destroy
				}
			}
			
		}
		return isDestroyed();
	}
	int ySpeed; //bullet will shoot down or up only
	public Bullet(int x,int y,int xSpeed,int ySpeed,int diameter) {
		super(x, y, 0, 0);
		this.ySpeed=ySpeed; // Not sure why put ySpeed to the super isnt working
		this.diameter=diameter;
		this.damage = DEFAULT_BULLET_DAMAGE;
		this.z=500;
	}
			

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.GREENYELLOW);
        gc.fillRect(x+22, y, 5, diameter);
		
	}
	public void update() {
		// delete when go off the screen to save memory
		if(y<0||y>GameUI.DEFAULT_GAME_HEIGHT) destroyed=true;
		this.y-=ySpeed; // should go up
	}
	@Override  public Rectangle getBounds(
  ) {
		//System.out.println(diameter);
        Rectangle bulletHitbox = new Rectangle(x+21, y, 7, diameter);//use shape.getBound().getBoundInParent().intersects(shape.getBound().getBoundInParent());
        //System.out.println(bulletHitbox.getBoundsInParent().toString());
        return bulletHitbox;
        //�͹ update ��� Update ��ǹ����� ໹hitbox
        
    }

}
