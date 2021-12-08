package game.object.tile;

import game.graphics.Sprite;
import game.object.StaticObject;

public class Brick extends StaticObject {
  public Brick(int x, int y) {
    super(x, y, Sprite.brick.getFxImage());
  }
}
