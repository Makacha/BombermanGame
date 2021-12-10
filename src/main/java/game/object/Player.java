package game.object;

import game.Game;
import game.graphics.Animation;
import game.graphics.Sprite;
import game.object.selfdestruct.Bomb;
import game.object.selfdestruct.PlayerDead;
import game.util.Direction;
import javafx.scene.input.KeyCode;

public class Player extends MoveableObject {
  public static final int PLAYER_DURATION_FRAME = 3;
  public static final double PLAYER_MAX_SPEED = 4.5;
  private boolean newBomb = false;
  private int numberBombs = 1;
  private int countBombs = 0;
  private int bombPower = 1;
  private int lives = 3;

  public Player(Game game, int x, int y) {
    super(game, x, y, PLAYER_DURATION_FRAME, Animation.player_default.getFxImages());
  }

  @Override
  public void setAnimation() {
    animationLeft = Animation.player_left.getFxImages();
    animationRight = Animation.player_right.getFxImages();
    animationUp = Animation.player_up.getFxImages();
    animationDown = Animation.player_down.getFxImages();
  }

  @Override
  public void update(long now) {
    if (!alive) {
      lives--;
      stunned = true;
      System.out.println("Player is dead! Remain lives: " + lives);
      if (lives > 0) {
        System.out.println("Game restart!");
        game.setRestart(true);
      } else {
        game.setOver(true);
      }
      PlayerDead playerDead = new PlayerDead(game, x, y);
      game.addObject(playerDead);
      return;
    }
    if (newBomb) {
      newBomb = false;
      placeBomb(now);
    }
    super.update(now);
  }

  public void placeBomb(long now) {
    int xUnit = ((int) x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    int yUnit = ((int) y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    if (game.canPlaceBomb(xUnit, yUnit) && countBombs < numberBombs) {
      countBombs++;
      game.addObject(new Bomb(game, xUnit, yUnit, this, now, bombPower));
    }
  }

  public void increaseBomb(int value) {
    numberBombs += value;
  }

  public void increaseBombPower(int value) {
    bombPower += value;
  }

  public void inscreaseSpeed(double value) {
    speed += value;
  }

  public void bombExploded() {
    countBombs--;
  }

  public void reset(int xUnit, int yUnit) {
    this.x = xUnit * Sprite.SCALED_SIZE;
    this.y = yUnit * Sprite.SCALED_SIZE;
    direction = Direction.NONE;
    alive = true;
    stunned = false;
    countBombs = 0;
  }

  public void restart(int xUnit, int yUnit) {
    // TODO: get log
    this.x = xUnit * Sprite.SCALED_SIZE;
    this.y = yUnit * Sprite.SCALED_SIZE;
    direction = Direction.NONE;
    alive = true;
    stunned = false;
    speed = DEFAULT_SPEED;
    numberBombs = 1;
    bombPower = 1;
    countBombs = 0;
  }

  public void onKeyPressed(KeyCode keyCode) {
    switch (keyCode) {
      case LEFT:
        direction = Direction.LEFT;
        speedX = -speed;
        break;
      case RIGHT:
        direction = Direction.RIGHT;
        speedX = speed;
        break;
      case UP:
        direction = Direction.UP;
        speedY = -speed;
        break;
      case DOWN:
        direction = Direction.DOWN;
        speedY = speed;
        break;
      case SPACE:
        newBomb = true;
        break;
      default:
        direction = Direction.NONE;
    }
  }

  public void onKeyReleased(KeyCode keyCode) {
    switch (keyCode) {
      case LEFT:
      case RIGHT:
        direction = Direction.NONE;
        speedX = 0;
        break;
      case UP:
      case DOWN:
        direction = Direction.NONE;
        speedY = 0;
        break;
      case SPACE:
        newBomb = false;
        break;
      default:
        direction = Direction.NONE;
    }
  }

  public int getBombPower() {
    return bombPower;
  }
}
