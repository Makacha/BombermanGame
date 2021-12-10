package game.object.flame;

import game.Game;
import game.graphics.Animation;

public class FlameVerticalTop extends Flame{
  public FlameVerticalTop(Game game, int x, int y) {
    super(game, x, y, Animation.explosion_vertical_top.getFxImages());
  }
}
