package game.object.tile;

import game.Game;
import game.GameObject;
import game.graphics.Sprite;
import game.object.StaticObject;

public class Brick extends StaticObject {
  private GameObject behindObject;

  public Brick(Game game, int x, int y) {
    super(game, x, y, Sprite.brick.getFxImage());
  }

  public Brick(Game game, int x, int y, GameObject behindObject) {
    super(game, x, y, Sprite.brick.getFxImage());
    this.behindObject = behindObject;
  }

  public void setBehindObject(GameObject behindObject) {
    this.behindObject = behindObject;
  }

  public GameObject getBehindObject() {
    return behindObject;
  }
}
