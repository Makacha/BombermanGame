package game.object.selfdestruct;

import game.Game;
import game.object.DynamicObject;
import javafx.scene.image.Image;

public abstract class Selfdestruct extends DynamicObject {
  private boolean repeat = false;
  public Selfdestruct(Game game, int x, int y, int durationImage, Image... images) {
    super(game, x, y, durationImage, images);
  }

  @Override
  public void updateActivity(long now) {
    if (repeat && countFrame == 0 && index == 0) {
      game.removeObject(this);
    } else {
      repeat = true;
    }
  }
}
