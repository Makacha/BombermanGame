package game.object.item;

import game.graphics.Sprite;
import game.object.Player;
import game.Game;

public class SpeedItem extends Item {
  public SpeedItem(Game game, int x, int y) {
    super(game, x, y, Sprite.powerup_speed.getFxImage());
  }
  @Override
  public void itemCollect() {
    Player player = (Player) game.getPlayer();
    player.inscreaseSpeed(0.5);
  }
}
