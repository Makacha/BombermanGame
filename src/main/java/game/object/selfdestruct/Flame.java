package game.object.selfdestruct;

import game.Game;
import javafx.scene.image.Image;

public abstract class Flame extends Selfdestruct {
  public static final int FLAME_DURATION_IMAGE = 7;

  public Flame(Game game, int x, int y, Image... images) {
    super(game, x, y, FLAME_DURATION_IMAGE, images);
  }

}
