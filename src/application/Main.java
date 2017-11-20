package application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import drawing.GameUI;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;


import menu.MenuItem;
import menu.MenuBox;

public class Main extends Application {
	//Scene mainmenu,game;
	public static Font font;
	private MenuBox menu;
	public static final int DEFAULT_WIDTH = 1280;// 16:9 Ratio
	public static final int DEFAUlT_HEIGHT = 800;
	

	@Override
	public void start(Stage primaryStage) {
		try {//playsong
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("Ost2.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		
		Pane root = new Pane();
		Scene scene = new Scene(root);
		root.setPrefSize(DEFAULT_WIDTH, DEFAUlT_HEIGHT);
		
		try (InputStream is = Files.newInputStream(Paths.get("res/images/CyberPunk2.jpg"));
				InputStream fontStream = Files.newInputStream(Paths.get("res/font/BLADRMF_.TTF"))) {// load resorce font
																									// and image
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(DEFAULT_WIDTH);
			
			img.setFitHeight(DEFAUlT_HEIGHT);

			root.getChildren().add(img);

			font = Font.loadFont(fontStream, 56);

		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error with load image or font");
		}

		MenuItem itemQuit = new MenuItem("QUIT");
		MenuItem itemStart = new MenuItem("START GAME");
		itemQuit.setOnMouseClicked(event -> System.exit(0)); // ใส่ function ให้ ปุ่ม quit
		itemStart.setOnMouseClicked(event ->{
			clip.stop();// stop the music before game start
			primaryStage.setScene(startgame());
			
		});

		menu = new MenuBox("cy3erpunk", itemStart, new MenuItem("HELP"), new MenuItem("CREDITS"),
				itemQuit); // set iteamQuit เสดก็ใส่ตรวนี้

		root.getChildren().add(menu);
		// set fix height and width
		primaryStage.setMaxHeight(DEFAUlT_HEIGHT);
		primaryStage.setMaxWidth(DEFAULT_WIDTH);
		primaryStage.setMinHeight(DEFAUlT_HEIGHT);
		primaryStage.setMinWidth(DEFAULT_WIDTH);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cy3erPunk");
		primaryStage.show();
		} catch (Exception ex) {
			System.out.println("Cant load wav");
		}
	}

	public Scene startgame() {
		StackPane root = new StackPane();
		Scene scene = new Scene(root);
		GameLogic logic = new GameLogic();
		GameUI gameui = new GameUI();
		
		root.getChildren().add(gameui);
		gameui.requestFocus(); // without this UI didnt response
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameui.paintComponent();
				logic.logicUpdate();
				RenderableHolder.getInstance().update();
				InputUtility.updateInputState();
			}
		};
		animation.start();
		return scene;
		

	}



	public static void main(String[] args) {
		launch(args);
	}
}
