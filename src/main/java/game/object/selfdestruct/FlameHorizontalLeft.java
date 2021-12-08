package game.object.selfdestruct;

import game.Game;
import game.graphics.Animation;

public class FlameHorizontalLeft extends Flame{
  public FlameHorizontalLeft(Game game, int x, int y) {
    super(game, x, y, Animation.explosion_horizontal_left.getFxImages());
  }
}
