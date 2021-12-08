package game.object;

import game.Game;
import game.graphics.Sprite;
import game.util.Direction;
import javafx.scene.image.Image;

public abstract class MoveableObject extends DynamicObject {
  protected Image[] animationDefault;
  protected Image[] animationLeft;
  protected Image[] animationRight;
  protected Image[] animationUp;
  protected Image[] animationDown;
  protected Direction direction = Direction.NONE;
  protected double speed = 2;
  protected int speedX;
  protected int speedY;
  protected boolean alive = true;

  public MoveableObject(Game game, int x, int y, int durationImage, Image... images) {
    super(game, x, y, durationImage, images);
    animationDefault = images;
    setAnimation();
  }

  public abstract void setAnimation();

  @Override
  public void updateActivity(long now) {
    if (direction != Direction.NONE) {
      move();
    } else {
      animation = animationDefault;
    }
  }

  public void move() {
    int newX = x;
    int newY = y;
    int newLeft;
    int newTop;
    int newRight;
    int newBottom;
    int fixedLeft;
    int fixedRight;
    int fixedTop;
    int fixedBottom;
    switch (direction) {
      case LEFT:
        newLeft = x + speedX;
        newTop = y + speedY;
        newRight = newLeft + Sprite.SCALED_SIZE - 1;
        newBottom = newTop + Sprite.SCALED_SIZE - 1;
        fixedTop = game.collisionUp(newLeft, newTop);
        fixedTop = Math.max(fixedTop, game.collisionUp(newRight, newTop));
        fixedBottom = game.collisionDown(newLeft, newBottom);
        fixedBottom = Math.min(fixedBottom, game.collisionDown(newRight, newBottom));
        if (fixedTop <= fixedBottom && Math.abs(fixedTop - newTop) <= speed
            && Math.abs(fixedBottom - newBottom) <= speed) {
          if (fixedBottom != newBottom) {
            newTop = fixedBottom - Sprite.SCALED_SIZE + 1;
            newBottom = fixedBottom;
          } else {
            newTop = fixedTop;
            newBottom = fixedTop + Sprite.SCALED_SIZE - 1;
          }
          fixedLeft = game.collisionLeft(newLeft, newTop);
          newLeft = Math.max(fixedLeft, game.collisionLeft(newLeft, newBottom));
          newX = newLeft;
          newY = newTop;
        } else {
          fixedLeft = game.collisionLeft(newLeft, newTop);
          newLeft = Math.max(fixedLeft, game.collisionLeft(newLeft, newBottom));
          fixedTop = game.collisionUp(newLeft, newTop);
          fixedBottom = game.collisionDown(newLeft, newBottom);
          if (fixedBottom != newBottom) {
            newTop = fixedBottom - Sprite.SCALED_SIZE + 1;
            newBottom = fixedBottom;
          } else {
            newTop = fixedTop;
            newBottom = fixedTop + Sprite.SCALED_SIZE - 1;
          }
          newX = newLeft;
          newY = newTop;
        }
        break;
      case RIGHT:
        newLeft = x + speedX;
        newTop = y + speedY;
        newRight = newLeft + Sprite.SCALED_SIZE - 1;
        newBottom = newTop + Sprite.SCALED_SIZE - 1;
        fixedTop = game.collisionUp(newLeft, newTop);
        fixedTop = Math.max(fixedTop, game.collisionUp(newRight, newTop));
        fixedBottom = game.collisionDown(newLeft, newBottom);
        fixedBottom = Math.min(fixedBottom, game.collisionDown(newRight, newBottom));
        if (fixedTop <= fixedBottom && Math.abs(fixedTop - newTop) <= speed
            && Math.abs(fixedBottom - newBottom) <= speed) {
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
          newX = newLeft;
          newY = newTop;
        } else {
          fixedRight = game.collisionRight(newRight, newTop);
          newRight = Math.min(fixedRight, game.collisionRight(newRight, newBottom));
          newLeft = newRight - Sprite.SCALED_SIZE + 1;
          fixedTop = game.collisionUp(newRight, newTop);
          fixedBottom = game.collisionDown(newRight, newBottom);
          if (fixedBottom != newBottom) {
            newTop = fixedBottom - Sprite.SCALED_SIZE + 1;
            newBottom = fixedBottom;
          } else {
            newTop = fixedTop;
            newBottom = fixedTop + Sprite.SCALED_SIZE - 1;
          }
          newX = newLeft;
          newY = newTop;
        }
        break;
      case UP:
        newLeft = x + speedX;
        newTop = y + speedY;
        newRight = newLeft + Sprite.SCALED_SIZE - 1;
        newBottom = newTop + Sprite.SCALED_SIZE - 1;
        fixedLeft = game.collisionLeft(newLeft, newTop);
        fixedLeft = Math.max(fixedLeft, game.collisionLeft(newLeft, newBottom));
        fixedRight = game.collisionRight(newRight, newTop);
        fixedRight = Math.min(fixedRight, game.collisionRight(newRight, newBottom));
        if (fixedLeft <= fixedRight && Math.abs(fixedLeft - newLeft) <= speed
            && Math.abs(fixedRight - newRight) <= speed) {
          if (fixedRight != newRight) {
            newLeft = fixedRight - Sprite.SCALED_SIZE + 1;
            newRight = fixedRight;
          } else {
            newLeft = fixedLeft;
            newRight = fixedLeft + Sprite.SCALED_SIZE - 1;
          }
          fixedTop = game.collisionUp(newLeft, newTop);
          newTop = Math.max(fixedTop, game.collisionUp(newRight, newTop));
          newX = newLeft;
          newY = newTop;
        } else {
          fixedTop = game.collisionUp(newLeft, newTop);
          newTop = Math.max(fixedTop, game.collisionUp(newRight, newTop));
          fixedLeft = game.collisionLeft(newLeft, newTop);
          fixedRight = game.collisionRight(newRight, newTop);
          if (fixedRight != newRight) {
            newLeft = fixedRight - Sprite.SCALED_SIZE + 1;
            newRight = fixedRight;
          } else {
            newLeft = fixedLeft;
            newRight = fixedLeft + Sprite.SCALED_SIZE - 1;
          }
          newX = newLeft;
          newY = newTop;
        }
        break;
      case DOWN:
        newLeft = x + speedX;
        newTop = y + speedY;
        newRight = newLeft + Sprite.SCALED_SIZE - 1;
        newBottom = newTop + Sprite.SCALED_SIZE - 1;
        fixedLeft = game.collisionLeft(newLeft, newTop);
        fixedLeft = Math.max(fixedLeft, game.collisionLeft(newLeft, newBottom));
        fixedRight = game.collisionRight(newRight, newTop);
        fixedRight = Math.min(fixedRight, game.collisionRight(newRight, newBottom));
        if (fixedLeft <= fixedRight && Math.abs(fixedLeft - newLeft) <= speed
            && Math.abs(fixedRight - newRight) <= speed) {
          if (fixedRight != newRight) {
            newLeft = fixedRight - Sprite.SCALED_SIZE + 1;
            newRight = fixedRight;
          } else {
            newLeft = fixedLeft;
            newRight = fixedLeft + Sprite.SCALED_SIZE - 1;
          }
          newBottom = game.collisionDown(newLeft, newBottom);
          newBottom = Math.min(newBottom, game.collisionDown(newRight, newBottom));
          newTop = newBottom - Sprite.SCALED_SIZE + 1;
          newX = newLeft;
          newY = newTop;
        } else {
          fixedBottom = game.collisionDown(newLeft, newBottom);
          newBottom = Math.min(fixedBottom, game.collisionDown(newRight, newBottom));
          newTop = newBottom - Sprite.SCALED_SIZE + 1;
          fixedLeft = game.collisionLeft(newLeft, newBottom);
          fixedRight = game.collisionRight(newRight, newBottom);
          if (fixedRight != newRight) {
            newLeft = fixedRight - Sprite.SCALED_SIZE + 1;
            newRight = fixedRight;
          } else {
            newLeft = fixedLeft;
            newRight = fixedLeft + Sprite.SCALED_SIZE - 1;
          }
          newX = newLeft;
          newY = newTop;
        }
        break;
      default:
    }
    if (x < newX) {
      animation = animationRight;
    } else if (x > newX) {
      animation = animationLeft;
    } else if (y < newY) {
      animation = animationDown;
    } else if (y > newY) {
      animation = animationUp;
    } else {
      animation = animationDefault;
    }
    x = newX;
    y = newY;
  }

  public boolean isAlive() {
    return alive;
  }
}
