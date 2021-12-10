package game.object.item;

import game.Game;
import game.object.StaticObject;
import javafx.scene.image.Image;

public abstract class Item extends StaticObject{
  public Item(Game game, int x, int y, Image image) {
    super(game, x, y, image);
  }

  @Override
  public void update(long now) {
    if (this.checkCollision(game.getPlayer())) {
      itemCollect();
      game.removeObject(this);
    }
  }

  public abstract void itemCollect();
}
