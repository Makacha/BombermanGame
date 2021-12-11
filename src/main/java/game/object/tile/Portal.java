package game.object.tile;

import game.Game;
import game.graphics.Sprite;
import game.object.Player;
import game.object.StaticObject;

public class Portal extends StaticObject {
  public Portal(Game game, int x, int y) {
    super(game, x, y, Sprite.portal.getFxImage());
  }

  @Override
  public void update(long now) {
    if (game.getEnemies().size() > 0) {
      return;
    }
    Player player = (Player) game.getPlayer();
    if (Math.abs(x - player.getX()) <= player.getJumpDistance()
        && Math.abs(y - player.getY()) <= player.getJumpDistance()) {
          game.nextLevel();
          System.out.println("Level passed!");
    }
  }
}
