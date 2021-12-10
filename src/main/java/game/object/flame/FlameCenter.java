package game.object.flame;

import game.Game;
import game.graphics.Animation;

public class FlameCenter extends Flame {
  public FlameCenter(Game game, int x, int y) {
    super(game, x, y, Animation.bomb_exploded.getFxImages());
  }
}
