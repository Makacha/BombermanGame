package game.object.selfdestruct;

import game.Game;
import game.graphics.Animation;

public class BrickExplode extends Selfdestruct {

  public static final int BRICK_EXPLODE_DURATION_IMAGE = 5;

  public BrickExplode(Game game, int x, int y) {
    super(game, x, y, BRICK_EXPLODE_DURATION_IMAGE, Animation.brick_explode.getFxImages());
  }

}
