package game.object.selfdestruct;

import game.Game;
import game.graphics.Animation;

public class FlameVerticalBottom extends Flame{
  public FlameVerticalBottom(Game game, int x, int y) {
    super(game, x, y, Animation.explosion_vertical_down.getFxImages());
  }
  
}
