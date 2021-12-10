package game.object.selfdestruct;

import game.Game;
import game.graphics.Animation;

public class BalloomDead extends Selfdestruct{
  public static final int BALLOOM_DEAD_DURATION_IMAGE = 10;

  public BalloomDead(Game game, double x, double y) {
    super(game, 0, 0, BALLOOM_DEAD_DURATION_IMAGE, Animation.balloom_dead.getFxImages());
    this.x = x;
    this.y = y;
  }
}
