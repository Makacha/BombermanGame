package game.object.enemy;

import game.Game;
import game.graphics.Animation;
import game.object.selfdestruct.BalloomDead;
import game.util.Direction;

public class Balloom extends Enemy {
  public static final int BALLOOM_DURATION_IMAGE = 5;
  public static final double BALLOOM_SPEED = 1.3;

  public Balloom(Game game, int x, int y) {
    super(game, x, y, BALLOOM_DURATION_IMAGE, Animation.balloom_left.getFxImages());
    speed = BALLOOM_SPEED;
  }

  @Override
  public void setAnimation() {
    animationLeft = Animation.balloom_left.getFxImages();
    animationRight = Animation.balloom_right.getFxImages();
    animationUp = Animation.balloom_right.getFxImages();
    animationDown = Animation.balloom_left.getFxImages();
  }

  @Override
  public void update(long now) {
    if (!alive) {
      game.addObject(new BalloomDead(game, x, y));
      game.removeEnemy(this);
      return;
    }
    int state = (int) (Math.random() * 10);
    if (state == 0) {
      state = (int) (Math.random() * 5);
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
          direction = Direction.NONE;
      }
    }
    super.update(now);
  }
}
