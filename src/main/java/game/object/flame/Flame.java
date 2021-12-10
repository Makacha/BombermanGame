package game.object.flame;

import game.Game;
import game.GameObject;
import game.object.Player;
import game.object.enemy.Enemy;
import game.object.item.Item;
import game.object.selfdestruct.Bomb;
import game.object.selfdestruct.Selfdestruct;
import javafx.scene.image.Image;

public abstract class Flame extends Selfdestruct {
  public static final int FLAME_DURATION_IMAGE = 5;

  public Flame(Game game, int x, int y, Image... images) {
    super(game, x, y, FLAME_DURATION_IMAGE, images);
  }

  @Override
  public void update(long now) {
    if (this.checkCollision(game.getPlayer())) {
      Player player = (Player) game.getPlayer();
      player.setAlive(false);
    }
    for (GameObject object : game.getEnemies()) {
      Enemy enemy = (Enemy) object;
      if (this.checkCollision(enemy)) {
        enemy.setAlive(false);
      }
    }
    if (index > 1) {
      Bomb bomb = (Bomb) game.get(xUnit(), yUnit());
      if (bomb != null) {
        bomb.selfDestruct();
      }
      GameObject object = game.getObject(xUnit(), yUnit());
      if (object != null && object instanceof Bomb) {
        bomb.selfDestruct();
      }
    }
    GameObject item = game.getObject(xUnit(), yUnit());
    if (item != null && item instanceof Item) {
      game.removeObject(item);
    }
    super.update(now);
  }

}
