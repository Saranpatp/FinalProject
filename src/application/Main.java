package application;
	
import java.util.List;

import drawing.GameUI;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.Entity;
import logic.Field;
import logic.GameLogic;
import logic.Ship;
import sharedObject.RenderableHolder;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
			StackPane root = new StackPane();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			GameLogic logic = new GameLogic();
			GameUI gameui = new GameUI(640, 480);
			root.getChildren().add(gameui);
			//GraphicsContext gc=gameui.getGraphicsContext2D();
			//testLogic(gc, logic.gameObjectContainer);
			gameui.requestFocus();
			primaryStage.setTitle("My game");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			AnimationTimer animation = new AnimationTimer() {
				public void handle(long now) {
					gameui.paintComponent();
					logic.logicUpdate();
					RenderableHolder.getInstance().update();
					InputUtility.updateInputState();
				}
			};
			animation.start();
	}
	public void testLogic(GraphicsContext gc,List<Entity> GOC) {
		System.out.println(GOC.size());
		for(Entity e: GOC) {
			e.draw(gc);
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
