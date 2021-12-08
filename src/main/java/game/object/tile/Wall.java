package game.object.tile;

import game.graphics.Sprite;
import game.object.StaticObject;

public class Wall extends StaticObject {
  public Wall(int x, int y) {
    super(x, y, Sprite.wall.getFxImage());
  }
}
