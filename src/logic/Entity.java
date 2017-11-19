package logic;



import javafx.scene.shape.Rectangle;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
	protected int x,y;
	
	protected int z;
	protected boolean visible,destroyed;
	
	public Entity() {};
	
	public abstract Rectangle getBounds();//hit box use getBound in pareent and <shape>.intersects(<shape>)
	
	public Entity(int x,int y){
		this.x=x;
		this.y=y;
		visible = true;
		destroyed = false;
	}
	public boolean collideWith(Entity other){
		return this.getBounds().getBoundsInParent().intersects(other.getBounds().getBoundsInParent());
	}
	@Override
	public boolean isDestroyed(){
		return destroyed;
	}
	@Override
	public boolean isVisible(){
		return visible;
	}
	@Override
	public int getZ(){
		return z;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
