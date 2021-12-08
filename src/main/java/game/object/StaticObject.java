package game.object;

import game.Game;
import game.GameObject;
import javafx.scene.image.Image;

public abstract class StaticObject extends GameObject {
  public StaticObject(Game game, int x, int y, Image image) {
    super(game, x, y, image);
  }

  @Override
  public void update(long now) {

  }
}
