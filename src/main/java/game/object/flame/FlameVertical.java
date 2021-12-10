package game.object.flame;

import game.Game;
import game.graphics.Animation;

public class FlameVertical extends Flame {
  public FlameVertical(Game game, int x, int y) {
    super(game, x, y, Animation.explosion_vertical.getFxImages());
  }
}
