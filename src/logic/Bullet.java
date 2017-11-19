package logic;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends MovingEntity {
	int diameter;
	
	public int getDiameter() {
		return diameter;
	}
	
	public boolean isCollide(Entity entity) {
		
		if (this.collideWith(entity)&&!entity.isDestroyed()) {
				
				entity.destroyed = true; // not appear but still in GAME as a black fucking object
				destroyed = true;
				
				return isDestroyed();
		}
		
		return isDestroyed();
	}
	int ySpeed; //bullet will shoot down or up only
	public Bullet(int x,int y,int xSpeed,int ySpeed,int diameter) {
		super(x, y, 0, 0);
		this.ySpeed=ySpeed;
		this.diameter=diameter;
		//this.z=1000;
	}
			

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.WHEAT);
        gc.fillRect(x+21, y, 7, diameter);
		
	}
	public void update() {
		// TODO Auto-generated method stub
		//System.out.println("ySpeed="+ySpeed);
		this.y-=ySpeed; // should go up
	}
	@Override  public Rectangle getBounds(
  ) {
		System.out.println(diameter);
        Rectangle bulletHitbox = new Rectangle(x+21, y, 7, diameter);//use shape.getBound().getBoundInParent().intersects(shape.getBound().getBoundInParent());
        //System.out.println(bulletHitbox.getBoundsInParent().toString());
        return bulletHitbox;
        //ตอน update ให้ Update ตัวนี้ด้วย เปนhitbox
        
    }

}
