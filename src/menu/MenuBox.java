package menu;

import application.Main;
import menu.MenuItem;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuBox extends StackPane {
	private int recwidth = (int) (Main.DEFAULT_WIDTH * 0.35);
	public FadeTransition ft; // to sync
	
	
	public MenuBox(String title, MenuItem... items) { // Menuitems... เเปลว่าจะรับข้อมูลเเนว menuitem ได้หลายตัว
		Rectangle bg = new Rectangle(recwidth, Main.DEFAUlT_HEIGHT);
		bg.setOpacity(0.5); // set opacity ที่ 20%

		DropShadow shadow = new DropShadow(7, 5, 0, Color.BLACK);
		shadow.setSpread(0.8);// เงา spread มากขึ้น

		bg.setEffect(shadow);
		
		//background blink
		/*FadeTransition ft = new FadeTransition(Duration.millis(1800),bg);
		
		ft.setFromValue(0.5);
	    ft.setToValue(0.3);
	    ft.setCycleCount(-1); // infinite loop
	    ft.setAutoReverse(true);
	    ft.play();*/
	    

		Text text = new Text(title + " ");
	
		text.setFont(Main.font);
		text.setFill(Color.WHITE);
		ft = new FadeTransition(Duration.millis(750),text);
		ft.setFromValue(1.0);
	    ft.setToValue(0);
	    ft.setCycleCount(100);
	    ft.setAutoReverse(true);
	    ft.play();

	    Rectangle boarder = new Rectangle(0,0,250,300);
	    boarder.setFill(Color.GRAY);
	    
	    /*Line hSep = new Line(); // เส้นใต้ title
	   // hSep.setStartX(recwidth*0.20);
	    hSep.setEndX(recwidth); // เส้นราบเเนวเเกน x เเนวนิน
	    hSep.setStrokeWidth(3);
		hSep.setStroke(Color.LIGHTGRAY);*/
		
		//hSep.setOpacity(1);// ********************************change*/

		Line vSep = new Line();// เส้นเเนตั้ง
		vSep.setStartX(recwidth);
		vSep.setEndX(recwidth);
		vSep.setEndY(Main.DEFAULT_WIDTH);
		vSep.setStroke(Color.DARKMAGENTA);
		vSep.setOpacity(0.4);

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_RIGHT);
		vbox.setPadding(new Insets(55, 0, 0, 0));
		vbox.getChildren().addAll(text);// ใส่พวก menu item ทั้งหมดลง ใน vbox
		vbox.getChildren().addAll(items);

		setAlignment(Pos.TOP_RIGHT); // top right of stack pane
		getChildren().addAll(bg, vSep, vbox);

	}
}

