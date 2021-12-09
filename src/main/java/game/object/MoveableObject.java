package game.object;

import game.Game;
import game.graphics.Sprite;
import game.util.Direction;
import javafx.scene.image.Image;

public abstract class MoveableObject extends DynamicObject {
  public static final double DEFAULT_SPEED = 2.25;
  protected Image[] animationDefault;
  protected Image[] animationLeft;
  protected Image[] animationRight;
  protected Image[] animationUp;
  protected Image[] animationDown;
  protected Direction direction = Direction.NONE;
  protected Direction[] preDirection = new Direction[2];
  protected double speed = DEFAULT_SPEED;
  protected double jumpDistance;
  protected double speedX;
  protected double speedY;
  protected boolean alive = true;

  public MoveableObject(Game game, int x, int y, int durationImage, Image... images) {
    super(game, x, y, durationImage, images);
    animationDefault = images;
    preDirection[0] = Direction.NONE;
    preDirection[1] = Direction.NONE;
    setAnimation();
  }

  public abstract void setAnimation();

  public void dead() {
    alive = false;
  }

  @Override
  public void updateActivity(long now) {
    updateProperties(now);
    if (direction != Direction.NONE) {
      move();
    } else {
      preDirection[1] = preDirection[0];
      preDirection[0] = Direction.NONE;
      direction = Direction.NONE;
    }
    switch (direction) {
      case LEFT:
        animation = animationLeft;
        break;
      case RIGHT:
        animation = animationRight;
        break;
      case UP:
        animation = animationUp;
        break;
      case DOWN:
        animation = animationDown;
        break;
      default:
        animation = animationDefault;
    }
  }

  public abstract void updateProperties(long now);

  public void move() {
    jumpDistance = speed * 2.0 - 0.000001;
    double newX = x;
    double newY = y;
    double[] newPos = new double[0];
    if (preDirection[0] != preDirection[1] || preDirection[0] != direction) {
      switch (direction) {
        case LEFT:
          newPos = moveLeft();
          break;
        case RIGHT:
          newPos = moveRight();
          break;
        case UP:
          newPos = moveUp();
          break;
        case DOWN:
          newPos = moveDown();
          break;
        default:
      }
    }
    if (newPos.length == 0) {
      switch (direction) {
        case LEFT:
        case RIGHT:
          if (speedY < 0) {
            newPos = moveUp();
          } else if (speedY > 0) {
            newPos = moveDown();
          }
          break;
        case UP:
        case DOWN:
          if (speedX < 0) {
            newPos = moveLeft();
          } else if (speedX > 0) {
            newPos = moveRight();
          }
          break;
        default:
      }
    }
    if (newPos.length == 0) {
      switch (direction) {
        case LEFT:
          newPos = moveLeft();
          break;
        case RIGHT:
          newPos = moveRight();
          break;
        case UP:
          newPos = moveUp();
          break;
        case DOWN:
          newPos = moveDown();
          break;
        default:
      }
    }
    if (newPos.length > 0) {
      newX = newPos[0];
      newY = newPos[1];
    } else {
      preDirection[1] = preDirection[0];
      preDirection[0] = Direction.NONE;
    }
    preDirection[1] = preDirection[0];
    if (x < newX) {
      direction = Direction.RIGHT;
      preDirection[0] = Direction.RIGHT;
    } else if (x > newX) {
      direction = Direction.LEFT;
      preDirection[0] = Direction.LEFT;
    } else if (y < newY) {
      direction = Direction.DOWN;
      preDirection[0] = Direction.DOWN;
    } else if (y > newY) {
      direction = Direction.UP;
      preDirection[0] = Direction.UP;
    } 
    x = newX;
    y = newY;
  }

  public double[] moveLeft() {
    double newLeft = x + speedX;
    double newTop = y + speedY;
    double newRight = newLeft + Sprite.SCALED_SIZE - 1;
    double newBottom = newTop + Sprite.SCALED_SIZE - 1;
    double fixedTop = game.collisionUp(newLeft, newTop);
    fixedTop = Math.max(fixedTop, game.collisionUp(newRight, newTop));
    double fixedBottom = game.collisionDown(newLeft, newBottom);
    fixedBottom = Math.min(fixedBottom, game.collisionDown(newRight, newBottom));
    double fixedLeft;
    if (fixedTop <= fixedBottom && Math.abs(fixedTop - newTop) <= jumpDistance
        && Math.abs(fixedBottom - newBottom) <= jumpDistance) {
      if (fixedBottom != newBottom) {
        newTop = fixedBottom - Sprite.SCALED_SIZE + 1;
        newBottom = fixedBottom;
      } else {
        newTop = fixedTop;
        newBottom = fixedTop + Sprite.SCALED_SIZE - 1;
      }
      fixedLeft = game.collisionLeft(newLeft, newTop);
      newLeft = Math.max(fixedLeft, game.collisionLeft(newLeft, newBottom));
    } else {
      fixedLeft = game.collisionLeft(newLeft, y);
      newLeft = Math.max(fixedLeft, game.collisionLeft(newLeft, y + Sprite.SCALED_SIZE - 1));
      newTop = y;
    }
    double[] result = new double[0];
    if (newLeft != x) {
      result = new double[2];
      result[0] = newLeft;
      result[1] = newTop;
    }
    return result;
  }

  public double[] moveRight() {
    double newLeft = x + speedX;
    double newTop = y + speedY;
    double newRight = newLeft + Sprite.SCALED_SIZE - 1;
    double newBottom = newTop + Sprite.SCALED_SIZE - 1;
    double fixedTop = game.collisionUp(newLeft, newTop);
    fixedTop = Math.max(fixedTop, game.collisionUp(newRight, newTop));
    double fixedBottom = game.collisionDown(newLeft, newBottom);
    fixedBottom = Math.min(fixedBottom, game.collisionDown(newRight, newBottom));
    double fixedRight;
    if (fixedTop <= fixedBottom && Math.abs(fixedTop - newTop) <= jumpDistance
        && Math.abs(fixedBottom - newBottom) <= jumpDistance) {
      if (fixedBottom != newBottom) {
        newTop = fixedBottom - Sprite.SCALED_SIZE + 1;
        newBottom = fixedBottom;
      } else {
        newTop = fixedTop;
        newBottom = fixedTop + Sprite.SCALED_SIZE - 1;
      }
      fixedRight = game.collisionRight(newRight, newTop);
      newRight = Math.min(fixedRight, game.collisionRight(newRight, newBottom));
      newLeft = newRight - Sprite.SCALED_SIZE + 1;
    } else {
      fixedRight = game.collisionRight(newRight, y);
      newRight = Math.min(fixedRight, game.collisionRight(newRight, y + Sprite.SCALED_SIZE - 1));
      newLeft = newRight - Sprite.SCALED_SIZE + 1;
      newTop = y;
    }
    double[] result = new double[0];
    if (newLeft != x) {
      result = new double[2];
      result[0] = newLeft;
      result[1] = newTop;
    }
    return result;
  }

  public double[] moveUp() {
    double newLeft = x + speedX;
    double newTop = y + speedY;
    double newRight = newLeft + Sprite.SCALED_SIZE - 1;
    double newBottom = newTop + Sprite.SCALED_SIZE - 1;
    double fixedLeft = game.collisionLeft(newLeft, newTop);
    fixedLeft = Math.max(fixedLeft, game.collisionLeft(newLeft, newBottom));
    double fixedRight = game.collisionRight(newRight, newTop);
    fixedRight = Math.min(fixedRight, game.collisionRight(newRight, newBottom));
    double fixedTop;
    if (fixedLeft <= fixedRight && Math.abs(fixedLeft - newLeft) <= jumpDistance
        && Math.abs(fixedRight - newRight) <= jumpDistance) {
      if (fixedRight != newRight) {
        newLeft = fixedRight - Sprite.SCALED_SIZE + 1;
        newRight = fixedRight;
      } else {
        newLeft = fixedLeft;
        newRight = fixedLeft + Sprite.SCALED_SIZE - 1;
      }
      fixedTop = game.collisionUp(newLeft, newTop);
      newTop = Math.max(fixedTop, game.collisionUp(newRight, newTop));
    } else {
      fixedTop = game.collisionUp(x, newTop);
      newTop = Math.max(fixedTop, game.collisionUp(x + Sprite.SCALED_SIZE - 1, newTop));
      newLeft = x;
    }
    double[] result = new double[0];
    if (newTop != y) {
      result = new double[2];
      result[0] = newLeft;
      result[1] = newTop;
    }
    return result;
  }

  public double[] moveDown() {
    double newLeft = x + speedX;
    double newTop = y + speedY;
    double newRight = newLeft + Sprite.SCALED_SIZE - 1;
    double newBottom = newTop + Sprite.SCALED_SIZE - 1;
    double fixedLeft = game.collisionLeft(newLeft, newTop);
    fixedLeft = Math.max(fixedLeft, game.collisionLeft(newLeft, newBottom));
    double fixedRight = game.collisionRight(newRight, newTop);
    fixedRight = Math.min(fixedRight, game.collisionRight(newRight, newBottom));
    double fixedBottom;
    if (fixedLeft <= fixedRight && Math.abs(fixedLeft - newLeft) <= jumpDistance
        && Math.abs(fixedRight - newRight) <= jumpDistance) {
      if (fixedRight != newRight) {
        newLeft = fixedRight - Sprite.SCALED_SIZE + 1;
        newRight = fixedRight;
      } else {
        newLeft = fixedLeft;
        newRight = fixedLeft + Sprite.SCALED_SIZE - 1;
      }
      fixedBottom = game.collisionDown(newLeft, newBottom);
      newBottom = Math.min(fixedBottom, game.collisionDown(newRight, newBottom));
      newTop = newBottom - Sprite.SCALED_SIZE + 1;
    } else {
      fixedBottom = game.collisionDown(x, newBottom);
      newBottom = Math.min(fixedBottom, game.collisionDown(x + Sprite.SCALED_SIZE - 1, newBottom));
      newTop = newBottom - Sprite.SCALED_SIZE + 1;
      newLeft = x;
    }
    double[] result = new double[0];
    if (newTop != y) {
      result = new double[2];
      result[0] = newLeft;
      result[1] = newTop;
    }
    return result;
  }

  public boolean isAlive() {
    return alive;
  }
}
