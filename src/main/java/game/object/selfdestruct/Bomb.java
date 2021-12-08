package game.object.selfdestruct;

import game.Game;
import game.GameObject;
import game.graphics.Animation;
import game.object.DynamicObject;
import game.object.Player;
import game.object.tile.Brick;
import game.object.tile.Wall;

public class Bomb extends DynamicObject {
  public static final int BOMB_DURATION_IMAGE = 7;
  private long setTime;
  private Player owner;

  public Bomb(Game game, int x, int y, Player owner, long setTime) {
    super(game, x, y, BOMB_DURATION_IMAGE, Animation.bomb.getFxImages());
    this.owner = owner;
    this.setTime = setTime;
  }

  @Override
  public void updateActivity(long now) {
    if (now - setTime >= 2_000_000_000) {
      selfDestruct();
    } else {
      // TODO:
    }
  }

  public void selfDestruct() {
    game.remove(xUnit, yUnit);
    game.add(xUnit, yUnit, new FlameCenter(game, xUnit, yUnit));
    game.add(xUnit - 1, yUnit, new FlameHorizontalLeft(game, xUnit - 1, yUnit));
    game.add(xUnit + 1, yUnit, new FlameHorizontalRight(game, xUnit + 1, yUnit));
    game.add(xUnit, yUnit - 1, new FlameVerticalTop(game, xUnit, yUnit - 1));
    game.add(xUnit, yUnit + 1, new FlameVerticalBottom(game, xUnit, yUnit + 1));
    // left
    for (int i = 1; i < owner.getBombPower(); i++) {
      GameObject object = game.get(xUnit - i, yUnit);
      if (object instanceof Wall) {
        break;
      } else if (object instanceof Brick) {
        game.remove(xUnit - i, yUnit);
        game.add(xUnit - i, yUnit, new BrickExplode(game, xUnit - i, yUnit));
        break;
      }
      game.add(xUnit - i, yUnit, new FlameHorizontal(game, xUnit - i, yUnit));
    }
    // right
    for (int i = 1; i < owner.getBombPower(); i++) {
      GameObject object = game.get(xUnit + i, yUnit);
      if (object instanceof Wall) {
        break;
      } else if (object instanceof Brick) {
        game.remove(xUnit + i, yUnit);
        game.add(xUnit + i, yUnit, new BrickExplode(game, xUnit + i, yUnit));
        break;
      }
      game.add(xUnit + i, yUnit, new FlameHorizontal(game, xUnit + i, yUnit));
    }
    // up
    for (int i = 1; i < owner.getBombPower(); i++) {
      GameObject object = game.get(xUnit, yUnit - i);
      if (object instanceof Wall) {
        break;
      } else if (object instanceof Brick) {
        game.remove(xUnit, yUnit - i);
        game.add(xUnit, yUnit - i, new BrickExplode(game, xUnit, yUnit - i));
        break;
      }
      game.add(xUnit, yUnit - i, new FlameVertical(game, xUnit, yUnit - i));
    }
    // down
    for (int i = 1; i < owner.getBombPower(); i++) {
      GameObject object = game.get(xUnit, yUnit + i);
      if (object instanceof Wall) {
        break;
      } else if (object instanceof Brick) {
        game.remove(xUnit, yUnit + i);
        game.add(xUnit, yUnit + i, new BrickExplode(game, xUnit, yUnit + i));
        break;
      }
      game.add(xUnit, yUnit + i, new FlameVertical(game, xUnit, yUnit + i));
    }
  }

}
