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
	
	
	public MenuBox(String title, MenuItem... items) { // Menuitems... �����Ҩ��Ѻ��������� menuitem �����µ��
		Rectangle bg = new Rectangle(recwidth, Main.DEFAUlT_HEIGHT);
		bg.setOpacity(0.5); // set opacity ��� 20%

		DropShadow shadow = new DropShadow(7, 5, 0, Color.BLACK);
		shadow.setSpread(0.8);// �� spread �ҡ���

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
		ft = new FadeTransition(Duration.millis(1000),text);
		ft.setFromValue(1.0);
	    ft.setToValue(0.3);
	    ft.setCycleCount(100);
	    ft.setAutoReverse(true);
	    ft.play();

		Line hSep = new Line(); // ����� title
		hSep.setEndX(recwidth); // ����Һ����ࡹ x ��ǹԹ
		hSep.setStroke(Color.PLUM);
		hSep.setOpacity(0.4);// ********************************change

		Line vSep = new Line();// ����๵��
		vSep.setStartX(recwidth);
		vSep.setEndX(recwidth);
		vSep.setEndY(Main.DEFAULT_WIDTH);
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
