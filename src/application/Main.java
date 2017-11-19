package application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import drawing.GameUI;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import logic.Entity;
import logic.Field;
import logic.GameLogic;
import logic.Ship;
import sharedObject.RenderableHolder;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	Scene mainmenu,game;
	private static Font font;
	private MenuBox menu;
	private static final int DEFAULT_WIDTH = 1280;// 16:9 Ratio
	private static final int DEFAUlT_HEIGHT = 800;
	

	@Override
	public void start(Stage primaryStage) {
		try {//playsong
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("Ost.wav"));
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
		itemQuit.setOnMouseClicked(event -> System.exit(0)); // ��� function ��� ���� quit
		itemStart.setOnMouseClicked(event ->{
			clip.stop();
			primaryStage.setScene(startgame());
			
		});

		menu = new MenuBox("cy3erpunk", itemStart, new MenuItem("HELP"), new MenuItem("CREDITS"),
				itemQuit); // set iteamQuit �ʴ������ǹ��

		root.getChildren().add(menu);
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
		//primaryStage.setScene(scene);

	}

	/*public void testLogic(GraphicsContext gc, List<Entity> GOC) {
		System.out.println(GOC.size());
		for (Entity e : GOC) {
			e.draw(gc);
		}
	}*/

	

	public void playTheme() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("Ost.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Cant load wav");
		}
	}

	private static class MenuBox extends StackPane {
		private int recwidth = (int) (DEFAULT_WIDTH * 0.35);

		public MenuBox(String title, MenuItem... items) { // Menuitems... �����Ҩ��Ѻ��������� menuitem �����µ��
			Rectangle bg = new Rectangle(recwidth, DEFAUlT_HEIGHT);
			bg.setOpacity(0.5); // set opacity ��� 20%

			DropShadow shadow = new DropShadow(7, 5, 0, Color.BLACK);
			shadow.setSpread(0.8);// �� spread �ҡ���

			bg.setEffect(shadow);

			Text text = new Text(title + " ");

			text.setFont(font);
			text.setFill(Color.WHITE);

			Line hSep = new Line(); // ����� title
			hSep.setEndX(recwidth); // ����Һ����ࡹ x ��ǹԹ
			hSep.setStroke(Color.PLUM);
			hSep.setOpacity(0.4);// ********************************change

			Line vSep = new Line();// ����๵��
			vSep.setStartX(recwidth);
			vSep.setEndX(recwidth);
			vSep.setEndY(DEFAULT_WIDTH);
			vSep.setStroke(Color.DARKMAGENTA);
			vSep.setOpacity(0.4);

			VBox vbox = new VBox();
			vbox.setAlignment(Pos.TOP_RIGHT);
			vbox.setPadding(new Insets(55, 0, 0, 0));
			vbox.getChildren().addAll(text, hSep);// ���ǡ menu item ������ŧ � vbox
			vbox.getChildren().addAll(items);

			setAlignment(Pos.TOP_RIGHT); // top right of stack pane
			getChildren().addAll(bg, vSep, vbox);

		}
	}

	private static class MenuItem extends StackPane {// ������ٵ�ҧ�
		private int recwidth = (int) (DEFAULT_WIDTH * 0.35);

		public MenuItem(String name) {
			Rectangle bg = new Rectangle(recwidth, 35);// background �ͧ menu ������ѹ

			LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
					new Stop[] { new Stop(0, Color.WHITE), new Stop(0.2, Color.VIOLET) });

			bg.setFill(gradient);
			bg.setVisible(false);
			bg.setEffect(new DropShadow(5, 0, 5, Color.DARKMAGENTA));// was Black***************** //radius,drop at
																		// x,drop at y,colour

			Text text = new Text(name + "      ");
			text.setFill(Color.LIGHTGREY);
			text.setFont(Font.font(30));

			setAlignment(Pos.CENTER_RIGHT);
			getChildren().addAll(bg, text);

			setOnMouseEntered(event -> { // lambda function
				bg.setVisible(true);
				text.setFill(Color.WHITE);
			});

			setOnMouseExited(event -> {
				bg.setVisible(false);
				text.setFill(Color.LIGHTGRAY);
			});

			setOnMousePressed(event -> {
				bg.setFill(Color.WHITE);
				text.setFill(Color.VIOLET);
			});

			setOnMouseReleased(event -> {
				bg.setFill(gradient);
				text.setFill(Color.WHITE);
			});

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
