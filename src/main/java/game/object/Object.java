package game.object;

import game.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Object {
  protected int x;
  protected int y;
  protected Image image;

  public Object(int xUnit, int yUnit, Image image) {
    this.x = xUnit * Sprite.SCALED_SIZE;
    this.y = yUnit * Sprite.SCALED_SIZE;
    this.image = image;
  }

  public void render(GraphicsContext graphicsContext, int left, int top) {
    graphicsContext.drawImage(image, x - left, y - top);
  }
}
