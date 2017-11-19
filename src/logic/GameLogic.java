package logic;

import java.util.ArrayList;
import java.util.List;

import drawing.GameUI;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer; //test only
	private Ship ship;
	//private Enemy enemy;
	private Enemy[][] wave=new Enemy[5][10];
	private int enemycolSpace = 80; //40pixel space each
	private int enemyrowSpace = 80;// 40pixel apart previous row
	//add a wave of enemy by using array[][] to do so
	//private Bullet bullet;
	public void spawnEnemy(int wavenumber) {//still have only wave number 1 must update to 1-3 level and then boss
		if(wavenumber==1) {//set their health to be low too
			for(int i =0;i<wave.length;i++) {
				for(int j = 0; j<wave[i].length;j++) {
					/*int prev = i;
					if(prev!=i) {
						//add y space to indicate next row and reset x postion to be on the left
					}*/
					wave[i][j] = new Enemy(j*enemycolSpace, i*enemyrowSpace, 1, 0, 1, 40, 40);// i must be y axis because it indicate the row
					addNewObject(wave[i][j]);
				}
				
			}
		}
	}
	public GameLogic() {
		this.gameObjectContainer=new ArrayList<Entity>();
		ship = new Ship(500,700);
		//enemy = new Enemy(100, 50, 1, 0, 1, 50, 50);
		spawnEnemy(1);
		addNewObject(ship);
		//addNewObject(enemy);
		
	}
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){

		//enemy.update();
		ship.update();
		if(ship.isShooting) {
			shoot();
			ship.isShooting=false;
		}
		ship.isShooting=false;
		RenderableHolder.getInstance().update();
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			if(gameObjectContainer.get(i) instanceof Enemy){
				((Enemy) gameObjectContainer.get(i)).update();
				}
			if (gameObjectContainer.get(i) instanceof Bullet) {
				((Bullet) gameObjectContainer.get(i)).update();
				if(((Bullet) gameObjectContainer.get(i)).isCollide(wave)) {
					gameObjectContainer.remove(i);
					System.out.println("COLLIDED TRUE");
				}
			}
		}
		RenderableHolder.getInstance().update();
	}
	private void shoot() {
		Bullet bullet = new Bullet(ship.x,ship.y,0,10,10);
		addNewObject(bullet);
		
	}
		
}
