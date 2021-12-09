package game.object.selfdestruct;

import game.Game;
import game.GameObject;
import game.graphics.Animation;
import game.object.DynamicObject;
import game.object.Player;
import game.object.tile.Brick;

public class Bomb extends DynamicObject {
  public static final int BOMB_DURATION_IMAGE = 7;
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
  public void updateActivity(long now) {
    if (now - setTime >= 2_000_000_000) {
      selfDestruct();
    } else {
      if (!checkCollision(owner)) {
        if (game.get(xUnit(), yUnit()) == null) {
          game.removeObject(this);
          game.add(xUnit(), yUnit(), this);
        }
      }
    }
  }

  public void selfDestruct() {
    game.addObject(new FlameCenter(game, xUnit(), yUnit()));
    // left
    for (int i = 1; i <= size; i++) {
      GameObject object = game.get(xUnit() - i, yUnit());
      if (object != null) {
        if (object instanceof Brick) {
          game.remove(xUnit() - i, yUnit());
          game.addObject(new BrickExplode(game, xUnit() - i, yUnit()));
        }
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
      GameObject object = game.get(xUnit() + i, yUnit());
      if (object != null) {
        if (object instanceof Brick) {
          game.remove(xUnit() + i, yUnit());
          game.addObject(new BrickExplode(game, xUnit() + i, yUnit()));
        }
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
      GameObject object = game.get(xUnit(), yUnit() - i);
      if (object != null) {
        if (object instanceof Brick) {
          game.remove(xUnit(), yUnit() - i);
          game.addObject(new BrickExplode(game, xUnit(), yUnit() - i));
        }
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
      GameObject object = game.get(xUnit(), yUnit() + i);
      if (object != null) {
        if (object instanceof Brick) {
          game.remove(xUnit(), yUnit() + i);
          game.addObject(new BrickExplode(game, xUnit(), yUnit() + i));
        }
        break;
      }
      if (i < size) {
        game.addObject(new FlameVertical(game, xUnit(), yUnit() + i));
      } else {
        game.addObject(new FlameVerticalBottom(game, xUnit(), yUnit() + size));
      }
    }
    if (game.get(xUnit(), yUnit()) == null) {
      game.removeObject(this);
    } else {
      game.remove(xUnit(), yUnit());
    }
    owner.bombExploded();
  }
}
