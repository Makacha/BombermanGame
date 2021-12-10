package game.object.selfdestruct;

import game.Game;
import game.graphics.Animation;

public class OnealDead extends Selfdestruct {
  public static final int BALLOOM_DEAD_DURATION_IMAGE = 10;

  public OnealDead(Game game, double x, double y) {
    super(game, 0, 0, BALLOOM_DEAD_DURATION_IMAGE, Animation.oneal_dead.getFxImages());
    this.x = x;
    this.y = y;
  }
}
