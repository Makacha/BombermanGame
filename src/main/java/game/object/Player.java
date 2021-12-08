package game.object;

import game.Game;
import game.graphics.Animation;
import game.graphics.Sprite;
import game.util.Direction;
import javafx.scene.input.KeyCode;

public class Player extends MoveableObject {
  public static final int PLAYER_DURATION_FRAME = 3;
  private int bombPower = 0;

  public Player(Game game, int x, int y) {
    super(game, x, y, PLAYER_DURATION_FRAME, Sprite.player_right.getFxImage());
  }

  @Override
  public void setAnimation() {
    animationLeft = Animation.player_left.getFxImages();
    animationRight = Animation.player_right.getFxImages();
    animationUp = Animation.player_up.getFxImages();
    animationDown = Animation.player_down.getFxImages();
  }

  public void onKeyPressed(KeyCode keyCode) {
    switch (keyCode) {
      case LEFT:
        direction = Direction.LEFT;
        speedX = -(int) speed;
        break;
      case RIGHT:
        direction = Direction.RIGHT;
        speedX = (int) speed;
        break;
      case UP:
        direction = Direction.UP;
        speedY = -(int) speed;
        break;
      case DOWN:
        direction = Direction.DOWN;
        speedY = (int) speed;
        break;
      default:
        direction = Direction.NONE;
    }
  }

  public void onKeyReleased(KeyCode keyCode) {
    switch (keyCode) {
      case LEFT:
        direction = Direction.NONE;
        speedX = 0;
        break;
      case RIGHT:
        direction = Direction.NONE;
        speedX = 0;
        break;
      case UP:
        direction = Direction.NONE;
        speedY = 0;
        break;
      case DOWN:
        direction = Direction.NONE;
        speedY = 0;
        break;
      default:
        direction = Direction.NONE;
    }
  }

  public void reset(int xUnit, int yUnit) {
    this.x = xUnit * Sprite.SCALED_SIZE;
    this.y = yUnit * Sprite.SCALED_SIZE;
    this.xUnit = xUnit;
    this.yUnit = yUnit;
  }

  public int getBombPower() {
    return bombPower;
  }
}
