package game.object.enemy;

import game.Game;
import game.object.MoveableObject;
import javafx.scene.image.Image;

public abstract class Enemy extends MoveableObject {
  public Enemy(Game game, int x, int y, int durationImage,Image... images) {
    super(game, x, y, durationImage, images);
  }
  
}
