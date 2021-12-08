package game.object.tile;

import game.Game;
import game.graphics.Sprite;
import game.object.StaticObject;

public class Grass extends StaticObject {
  public Grass(Game game, int x, int y) {
    super(game, x, y, Sprite.grass.getFxImage());
  }
}
