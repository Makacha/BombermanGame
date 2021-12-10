package game.object.item;

import game.Game;
import game.graphics.Sprite;
import game.object.Player;

public class FlameItem extends Item {
  public FlameItem(Game game, int x, int y) {
    super(game, x, y, Sprite.powerup_flames.getFxImage());
  }

  @Override
  public void itemCollect() {
    Player player = (Player) game.getPlayer();
    player.increaseBombPower(1);
  }
}
