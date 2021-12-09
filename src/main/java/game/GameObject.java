package game;

import game.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
  protected double x;
  protected double y;
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

  public void render(GraphicsContext graphicsContext, double left, double top) {
    graphicsContext.drawImage(image, x - left, y - top);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public int getxUnit() {
    return xUnit;
  }

  public int getyUnit() {
    return yUnit;
  }
}
