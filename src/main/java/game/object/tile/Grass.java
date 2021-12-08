package game.object.tile;

import game.graphics.Sprite;
import game.object.StaticObject;

public class Grass extends StaticObject {
  public Grass(int x, int y) {
    super(x, y, Sprite.grass.getFxImage());
  }
}
