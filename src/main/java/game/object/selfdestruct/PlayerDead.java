package game.object.selfdestruct;

import game.Game;
import game.graphics.Animation;

public class PlayerDead extends Selfdestruct {
  public static final int PLAYER_DEAD_DURATION_IMAGE = 12;

  public PlayerDead(Game game, double x, double y) {
    super(game, 0, 0, PLAYER_DEAD_DURATION_IMAGE, Animation.player_dead.getFxImages());
    this.x = x;
    this.y = y;
  }
}
