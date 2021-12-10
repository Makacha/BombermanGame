package game.object.item;

import game.Game;
import game.graphics.Sprite;
import game.object.Player;

public class BombItem extends Item {
  public BombItem(Game game, int x, int y) {
    super(game, x, y, Sprite.powerup_bombs.getFxImage());
  }
  @Override
  public void itemCollect() {
    Player player = (Player) game.getPlayer();
    player.increaseBomb(1);
  }
}
