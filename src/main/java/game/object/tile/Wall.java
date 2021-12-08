package game.object.tile;

import game.Game;
import game.graphics.Sprite;
import game.object.StaticObject;

public class Wall extends StaticObject {
  public Wall(Game game, int x, int y) {
    super(game, x, y, Sprite.wall.getFxImage());
  }
}
