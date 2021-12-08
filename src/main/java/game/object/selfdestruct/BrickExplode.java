package game.object.selfdestruct;

import game.Game;
import game.graphics.Animation;

public class BrickExplode extends Selfdestruct {
  public static final int FLAME_DURATION_IMAGE = 7;

  public BrickExplode(Game game, int x, int y) {
    super(game, x, y, FLAME_DURATION_IMAGE, Animation.brick_explode.getFxImages());
  }
  
}
