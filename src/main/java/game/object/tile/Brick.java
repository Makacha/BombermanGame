package game.object.tile;

import game.Game;
import game.graphics.Sprite;
import game.object.StaticObject;

public class Brick extends StaticObject {
  public Brick(Game game, int x, int y) {
    super(game, x, y, Sprite.brick.getFxImage());
  }
}
