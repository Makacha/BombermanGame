package game.object.enemy;

import game.Game;
import game.object.MoveableObject;
import game.object.Player;
import javafx.scene.image.Image;

public abstract class Enemy extends MoveableObject {
  public Enemy(Game game, int x, int y, int durationImage,Image... images) {
    super(game, x, y, durationImage, images);
  }

  @Override
  public void update(long now) {
    if (this.checkCollision(game.getPlayer())) {
      Player player = (Player) game.getPlayer();
      player.setAlive(false);
    }
    super.update(now);
  }
}
