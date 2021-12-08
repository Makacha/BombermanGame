package game.object;

import javafx.scene.image.Image;

public abstract class MoveableObject extends DynamicObject {
  public MoveableObject(int x, int y, Image image) {
    super(x, y, image);
  }
}
