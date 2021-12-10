package game.object;

import game.Game;
import game.GameObject;
import javafx.scene.image.Image;

public abstract class DynamicObject extends GameObject {
  protected Image[] animation;
  protected final int durationImage;
  protected int countFrame = 0;
  protected int index = 0;

  public DynamicObject(Game game, int x, int y, int durationImage, Image... images) {
    super(game, x, y, images[0]);
    this.durationImage = durationImage;
    animation = images;
  }

  @Override
  public void update(long now) {
    updateAnimation();
  }


  public void updateAnimation() {
    countFrame++;
    if (countFrame == durationImage) {
      index++;
      countFrame = 0;
    } 
    index = index % animation.length;
    image = animation[index];
  }
}
