package game.object.selfdestruct;

import game.Game;
import game.graphics.Animation;

public class FlameHorizontal extends Flame {
  public FlameHorizontal(Game game, int x, int y) {
    super(game, x, y, Animation.explosion_horizontal.getFxImages());
  }

}
