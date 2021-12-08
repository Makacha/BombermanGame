package game;

import game.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
  protected int x;
  protected int y;
  protected int xUnit;
  protected int yUnit;
  protected Image image;
  protected Game game;

  public GameObject(Game game, int xUnit, int yUnit, Image image) {
    this.x = xUnit * Sprite.SCALED_SIZE;
    this.y = yUnit * Sprite.SCALED_SIZE;
    this.xUnit = xUnit;
    this.yUnit = yUnit;
    this.image = image;
    this.game = game;
  }

  abstract public void update(long now);

  public void render(GraphicsContext graphicsContext, int left, int top) {
    graphicsContext.drawImage(image, x - left, y - top);
  }
}
