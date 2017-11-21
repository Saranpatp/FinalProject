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
		if(wavenumber<=3&&wavenumber>0) {//set their health to be low too
			for(int i =0;i<wave.length;i++) {
				for(int j = 0; j<wave[i].length;j++) {
					wave[i][j] = new Enemy(j*enemycolSpace, i*enemyrowSpace, 1+((wavenumber-1)*5), 0, wavenumber, 40, 40);// i must be y axis because it indicate the row
					addNewObject(wave[i][j]);
					wave[i][j].setHealth(1+(wavenumber*5));//tougher enemy
				}
				
			}
		}
	}
	public GameLogic() {
		this.gameObjectContainer=new ArrayList<Entity>();
		ship = new Ship(500,700);
		spawnEnemy(2);
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
					((Enemy) gameObjectContainer.get(i)).hit();
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
	private void shoot() {// shoot will take in when we have score and ship level and then we will level up these cannon
		if(bulletCounter==0||bulletCounter%20==0) {
		try {
			AudioClip shootsound = new AudioClip(ClassLoader.getSystemResource("bulletSound.wav").toString());
			shootsound.play();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cant load bullet sound");
		}
		Bullet bullet = new Bullet(ship.getX(),ship.getY()+3,0,15,10);
		Bullet oleftbullet = new Bullet(ship.getX()-24,ship.getY()+30,0,15,10);//outer left gun
		Bullet orightbullet = new Bullet(ship.getX()+24,ship.getY()+30,0,15,10);//outer right gun
		Bullet ileftbullet = new Bullet(ship.getX()-15,ship.getY()+27,0,15,10);//outer left gun
		Bullet irightbullet = new Bullet(ship.getX()+15,ship.getY()+27,0,15,10);//outer right gun
		addNewObject(bullet);
		addNewObject(oleftbullet);
		addNewObject(orightbullet);
		addNewObject(ileftbullet);
		addNewObject(irightbullet);
		bulletCounter=0;
		}
		// gotta add bullet reloaded UI
	}
		
}
