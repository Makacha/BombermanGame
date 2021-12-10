package game.object.enemy;

import game.Game;
import game.graphics.Animation;
import game.object.selfdestruct.OnealDead;
import game.util.Direction;

public class Oneal extends Enemy {
  public static final int ONEAL_DURATION_IMAGE = 5;
  public static final double ONEAL_PEED = 3.2;

  public Oneal(Game game, int x, int y) {
    super(game, x, y, ONEAL_DURATION_IMAGE, Animation.oneal_left.getFxImages());
    speed = ONEAL_PEED;
  }

  @Override
  public void setAnimation() {
    animationLeft = Animation.oneal_left.getFxImages();
    animationRight = Animation.oneal_right.getFxImages();
    animationUp = Animation.oneal_left.getFxImages();
    animationDown = Animation.oneal_right.getFxImages();
  }

  @Override
  public void update(long now) {
    if (!alive) {
      game.addObject(new OnealDead(game, x, y));
      game.removeEnemy(this);
      return;
    }
    int state = (int) (Math.random() * 50);
    if (direction == Direction.NONE || state == 0) {
      state = (int) (Math.random() * 20);
      switch (state) {
        case 0:
          direction = Direction.LEFT;
          speedX = -speed;
          break;
        case 1:
          direction = Direction.RIGHT;
          speedX = speed;
          break;
        case 2:
          direction = Direction.UP;
          speedY = -speed;
          break;
        case 3:
          direction = Direction.DOWN;
          speedY = speed;
          break;
        default:
          speedX = 0;
          speedY = 0;
          direction = Direction.NONE;
      }
    }
    super.update(now);
  }
}
