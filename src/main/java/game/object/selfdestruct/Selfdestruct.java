package game.object.selfdestruct;

import game.Game;
import game.object.DynamicObject;
import javafx.scene.image.Image;

public abstract class Selfdestruct extends DynamicObject {
  public Selfdestruct(Game game, int x, int y, int durationImage,Image... images) {
    super(game, x, y, durationImage, images);
  }

  @Override
  public void update(long now) {
    if (countFrame ==  durationImage - 1 && index == animation.length - 1) {
      game.removeObject(this);
    } 
    super.update(now);
  }
}
