package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;


public class Field implements IRenderable {
	private static int[][] field = { { -1, -1, -1, 0, -1, 0, 0, 0, -1, 0 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ -1, -1, -1, 0, 0, 0, 0, 0, 0, 0 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

			{ 0, 0, 0, 0, 0, 0, -2, 0, 0, 0 }, { 0, -2, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	public int getTerrain(int x, int y) {
		if (x < 0 || x >= field[0].length || y < 0 || y >= field.length)
			return -3;
		return field[y][x];
	}

	private int getTileIndex(int x, int y) {
		int terrain = getTerrain(x, y);
		if (terrain <= 0 && terrain >= -2)
			return -terrain;
		else
			return 0;
	}

	@Override
	public int getZ() {
		return -9999;//ตัวหลังสุด ปลใวาดก่อน
	}

	@Override
	public void draw(GraphicsContext gc) {
		for (int x = 0; x <= field[0].length; x++) {
			for (int y = 0; y <= field.length; y++) {
				WritableImage croppedImage = new WritableImage(new Image(ClassLoader.getSystemResource("Map.png")
						.toString()).getPixelReader(),getTileIndex(x, y) * 64, 0, 64, 64);
				gc.drawImage(croppedImage, x * 64, y * 64);
			}
		}
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
}

