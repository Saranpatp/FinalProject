package drawing;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.Ship;
import sharedObject.IRenderable;
//import sharedObject.RenderableHolder;
import sharedObject.RenderableHolder;

public class GameUI extends Canvas{
	public static final int DEFAULT_GAME_WIDTH = 1000;  
	public static final int DEFAULT_GAME_HEIGHT = 800;
	
	public GameUI() {
		super(DEFAULT_GAME_WIDTH, DEFAULT_GAME_HEIGHT);
		this.setVisible(true);
		addListerner();//add event handler to canvas
	}
	public void addListerner() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});
		// comment this when want to do only triggered || shoot one bullet at a time
		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});

		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftDown();
		});

		this.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftRelease();
		});

		this.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.mouseOnScreen = true;
		});

		this.setOnMouseExited((MouseEvent event) -> {
			InputUtility.mouseOnScreen = false;
		});

		this.setOnMouseMoved((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});

		this.setOnMouseDragged((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});
	}
	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);//draw black background on top the last one everytime
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {//draw the entity in the list //SPAWNER
			//System.out.println(entity.getZ());
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
			
		}

		

	}

}
