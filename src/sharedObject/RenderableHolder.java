package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.Bullet;
import logic.Enemy;
import logic.Field;
import logic.Ship;
//import logic.;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static AudioClip damageSound;
	public static AudioClip deathSound;
	public static AudioClip shieldSound;
	
	static {
		loadResource();
	}

	public static void loadResource() {
		try {
			damageSound = new AudioClip(ClassLoader.getSystemResource("damageSound.wav").toString());
			deathSound = new AudioClip(ClassLoader.getSystemResource("deathSound.wav").toString());
			shieldSound = new AudioClip(ClassLoader.getSystemResource("shieldSound.wav").toString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cant load damage sound in RenderHolder");
		}
		
	}
	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		for(IRenderable x: entities){
			if(x instanceof Ship) System.out.println("Ship");
			if(x instanceof Enemy) System.out.println("Enemy");
			if(x instanceof Bullet) System.out.println("Bullet");
		}
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}

