package logic;

import java.util.ArrayList;
import java.util.List;

import drawing.GameUI;
import javafx.scene.media.AudioClip;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer; //test only
	private Ship ship;
	//private Enemy enemy;
	private Enemy[][] wave=new Enemy[5][10];
	private int enemycolSpace = 80; //40pixel space each
	private int enemyrowSpace = 80;// 40pixel apart previous row
	//add a wave of enemy by using array[][] to do so

	private int bulletCounter=0;//will reduce rate of fire
	private int enemyShootCounter=0;//enemy will able too shoot later on
	
	public void spawnEnemy(int wavenumber) {//still have only wave number 1 must update to 1-3 level and then boss
		if(wavenumber==1) {//set their health to be low too
			for(int i =0;i<wave.length;i++) {
				for(int j = 0; j<wave[i].length;j++) {
					wave[i][j] = new Enemy(j*enemycolSpace, i*enemyrowSpace, 1, 0, 1, 40, 40);// i must be y axis because it indicate the row
					addNewObject(wave[i][j]);
				}
				
			}
		}
	}
	public GameLogic() {
		this.gameObjectContainer=new ArrayList<Entity>();
		ship = new Ship(500,700);
		spawnEnemy(1);
		//Shield shield = new Shield(ship);
		addNewObject(ship);
		//addNewObject(shield);
		
		
	}
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		if(bulletCounter<20) bulletCounter+=1;
		//System.out.println("bullet COunter" + bulletCounter);//test bullet reload
		//enemy.update();
		ship.update();
		if(ship.isShooting) {
			shoot();
			ship.isShooting=false;
		}
		
		//bulletCounter=0;
		RenderableHolder.getInstance().update();
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			if(gameObjectContainer.get(i) instanceof Enemy){
				if(ship.collideWith((Enemy) gameObjectContainer.get(i))){
					ship.decreaseHealth(1);
				}
				((Enemy) gameObjectContainer.get(i)).update();
				}
			if (gameObjectContainer.get(i) instanceof Bullet) {
				((Bullet) gameObjectContainer.get(i)).update();
				if(((Bullet) gameObjectContainer.get(i)).isCollide(wave)) {
					//isCollide already decrease the enemy health
					gameObjectContainer.remove(i);
					//System.out.println("COLLIDED TRUE");
				}
			}
			if(((Entity) gameObjectContainer.get(i)).destroyed){
				gameObjectContainer.remove(i);
			}
		}
		RenderableHolder.getInstance().update();
	}
	private void shoot() {
		if(bulletCounter==0||bulletCounter%10==0) {
		try {
			AudioClip shootsound = new AudioClip(ClassLoader.getSystemResource("bulletSound.wav").toString());
			shootsound.play();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cant load bullet sound");
		}
		Bullet bullet = new Bullet(ship.getX(),ship.getY(),0,10,10);
		
		addNewObject(bullet);
		bulletCounter=0;
		}
		// gotta add bullet reloaded UI
	}
		
}
