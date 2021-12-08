package game.object;

import game.graphics.Sprite;

public class Player extends MoveableObject {

  public Player(int x, int y) {
    super(x, y, Sprite.player_right.getFxImage());
  }
}
