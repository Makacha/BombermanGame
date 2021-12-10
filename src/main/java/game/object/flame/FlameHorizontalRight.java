package game.object.flame;

import game.Game;
import game.graphics.Animation;

public class FlameHorizontalRight extends Flame {
  public FlameHorizontalRight(Game game, int x, int y) {
    super(game, x, y, Animation.explosion_horizontal_right.getFxImages());
  }
}
