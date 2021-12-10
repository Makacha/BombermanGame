package game.object.selfdestruct;

import game.Game;
import game.GameObject;
import game.graphics.Animation;
import game.object.DynamicObject;
import game.object.Player;
import game.object.flame.FlameCenter;
import game.object.flame.FlameHorizontal;
import game.object.flame.FlameHorizontalLeft;
import game.object.flame.FlameHorizontalRight;
import game.object.flame.FlameVertical;
import game.object.flame.FlameVerticalBottom;
import game.object.flame.FlameVerticalTop;
import game.object.tile.Brick;

public class Bomb extends DynamicObject {
  public static final int BOMB_DURATION_IMAGE = 7;
  public static final long BOMB_DURATION_TIME = 2_000_000_000L;
  private long setTime;
  private Player owner;
  private int size;

  public Bomb(Game game, int x, int y, Player owner, long setTime, int size) {
    super(game, x, y, BOMB_DURATION_IMAGE, Animation.bomb.getFxImages());
    this.owner = owner;
    this.setTime = setTime;
    this.size = size;
  }

  @Override
  public void update(long now) {
    if (now - setTime >= BOMB_DURATION_TIME) {
      selfDestruct();
    } else {
      if (!checkCollision(owner)) {
        if (game.get(xUnit(), yUnit()) == null) {
          game.removeObject(this);
          game.add(xUnit(), yUnit(), this);
        }
      }
    }
    super.update(now);
  }

  public void selfDestruct() {
    game.addObject(new FlameCenter(game, xUnit(), yUnit()));
    // left
    for (int i = 1; i <= size; i++) {
      if (checkObject(xUnit() - i, yUnit())) {
        break;
      }
      if (i < size) {
        game.addObject(new FlameHorizontal(game, xUnit() - i, yUnit()));
      } else {
        game.addObject(new FlameHorizontalLeft(game, xUnit() - size, yUnit()));
      }
    }
    // right
    for (int i = 1; i <= size; i++) {
      if (checkObject(xUnit() + i, yUnit())) {
        break;
      }
      if (i < size) {
        game.addObject(new FlameHorizontal(game, xUnit() + i, yUnit()));
      } else {
        game.addObject(new FlameHorizontalRight(game, xUnit() + size, yUnit()));
      }
    }
    // up
    for (int i = 1; i <= size; i++) {
      if (checkObject(xUnit(), yUnit() - i)) {
        break;
      }
      if (i < size) {
        game.addObject(new FlameVertical(game, xUnit(), yUnit() - i));
      } else {
        game.addObject(new FlameVerticalTop(game, xUnit(), yUnit() - size));
      }
    }
    // down
    for (int i = 1; i <= size; i++) {
      if (checkObject(xUnit(), yUnit() + i)) {
        break;
      }
      if (i < size) {
        game.addObject(new FlameVertical(game, xUnit(), yUnit() + i));
      } else {
        game.addObject(new FlameVerticalBottom(game, xUnit(), yUnit() + size));
      }
    }
    owner.bombExploded();
    if (game.get(xUnit(), yUnit()) == null) {
      game.removeObject(this);
    } else {
      game.remove(xUnit(), yUnit());
    }
  }

  public boolean checkObject(int x, int y) {
    GameObject object = game.get(x, y);
    if (object != null) {
      if (object instanceof Bomb) {
        return false;
      }
      if (object instanceof Brick) {
        game.remove(x, y);
      }
      return true;
    }
    return false;
  }
}
