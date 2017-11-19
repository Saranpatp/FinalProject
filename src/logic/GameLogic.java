package logic;

import java.util.ArrayList;
import java.util.List;

import drawing.GameUI;
import sharedObject.RenderableHolder;

public class GameLogic {
	//private List<Entity> gameObjectContainer;
	public List<Entity> gameObjectContainer; //test only
	private Ship ship,ship2;
	private Field field;
	private Enemy enemy;
	//private Bullet bullet;
	public GameLogic() {
		this.gameObjectContainer=new ArrayList<Entity>();
		//Field field = new Field();
		//RenderableHolder.getInstance().add(field);
		//field = new Field();
		ship = new Ship(160,300);
		enemy = new Enemy(100, 50, 1, 0, 1, 50, 50);
		//ship2 = new Ship(400,200);
		//addNewObject(field)
		addNewObject(ship);
		addNewObject(enemy);
		//bullet = new Bullet(ship.getX(), ship.getY(),0, 10, 10);
		//addNewObject(bullet);
		//addNewObject(bullet);
		//addNewObject(ship2);
		
	}
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){

		ship.update();
		enemy.update();
		if(ship.isShooting) {
			shoot();
		}
		ship.isShooting=false;
		RenderableHolder.getInstance().update();
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			if (gameObjectContainer.get(i) instanceof Bullet) {
				((Bullet) gameObjectContainer.get(i)).update();
				//((Bullet) gameObjectContainer.get(i)).isCollide(enemy);
				if(((Bullet) gameObjectContainer.get(i)).isCollide(enemy)) {
					gameObjectContainer.remove(i);
					System.out.println("COLLIDED TRUE");
				}
			}
		}
		RenderableHolder.getInstance().update();
		
		//System.out.println("is bullet collided with alien"+ bullet.collideWith(enemy));
		//ship2.update();
		/*if(!mine.isDestroyed() && tank.collideWith(mine)){
			mine.onCollision(tank);
		}*/
	}
	private void shoot() {
		Bullet bullet = new Bullet(ship.x,ship.y,0,10,10);
		addNewObject(bullet);
		
	}
		
}
