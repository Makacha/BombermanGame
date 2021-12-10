package game;

import game.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
  protected double x;
  protected double y;
  protected Image image;
  protected Game game;

  public GameObject(Game game, int xUnit, int yUnit, Image image) {
    this.x = xUnit * Sprite.SCALED_SIZE;
    this.y = yUnit * Sprite.SCALED_SIZE;
    this.image = image;
    this.game = game;
  }

  abstract public void update(long now);

  public void render(GraphicsContext graphicsContext, double left, double top) {
    graphicsContext.drawImage(image, x - left, y - top);
  }

  public boolean checkCollision(GameObject other) {
    double left = x;
    double top = y;
    double right = x + Sprite.SCALED_SIZE - 1;
    double bottom = y + Sprite.SCALED_SIZE - 1;
    double otherLeft = other.getX();
    double otherTop = other.getY();
    double otherRight = other.getX() + Sprite.SCALED_SIZE - 1;
    double otherBottom = other.getY() + Sprite.SCALED_SIZE - 1;
    return !(right < otherLeft || bottom < otherTop || left > otherRight || top > otherBottom);
  }

  public boolean checkCollision(int x, int y) {
    double left = this.x;
    double top = this.y;
    double right = this.x + Sprite.SCALED_SIZE - 1;
    double bottom = this.y + Sprite.SCALED_SIZE - 1;
    double otherLeft = x * Sprite.SCALED_SIZE;
    double otherTop = y * Sprite.SCALED_SIZE;
    double otherRight = otherLeft + Sprite.SCALED_SIZE - 1;
    double otherBottom = otherTop + Sprite.SCALED_SIZE - 1;
    return !(right < otherLeft || bottom < otherTop || left > otherRight || top > otherBottom);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public int xUnit() {
    return (int) x / Sprite.SCALED_SIZE;
  }

  public int yUnit() {
    return (int) y / Sprite.SCALED_SIZE;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }
}
