package game;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import game.graphics.Sprite;
import game.object.Player;
import game.object.StaticObject;
import game.object.selfdestruct.Bomb;
import game.object.tile.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class Game {
  private final double SCREEN_HEIGHT;
  private final double SCREEN_WIDTH;
  private int height;
  private int width;
  private double left;
  private double top;
  private int level;
  private Player player;
  private List<GameObject> enemies;
  private List<GameObject> otherObjects;
  private GameObject[][] objects;
  private Grass[][] background;
  private boolean running = true;
  private List<KeyEvent> keyList = new ArrayList<>();

  public Game(double SCREEN_WIDTH, double SCREEN_HEIGHT) {
    this.SCREEN_HEIGHT = SCREEN_HEIGHT;
    this.SCREEN_WIDTH = SCREEN_WIDTH;
    left = 0;
    top = 0;
    level = 1;
    createMap();
  }

  public void createMap() {
    try {
      String sourcePath = "/map/level" + level + ".txt";
      FileInputStream source = new FileInputStream(Game.class.getResource(sourcePath).getPath());
      Scanner scanner = new Scanner(source);
      height = scanner.nextInt();
      width = scanner.nextInt();
      objects = new GameObject[height][width];
      background = new Grass[height][width];
      enemies = new ArrayList<>();
      otherObjects = new ArrayList<>();
      for (int y = 0; y < height; y++) {
        String line = scanner.nextLine();
        if (line.equals("")) {
          line = scanner.nextLine();
        }
        for (int x = 0; x < width; x++) {
          background[y][x] = new Grass(this, x, y);
          switch (line.charAt(x)) {
            case 'p':
              if (player == null) {
                player = new Player(this, x, y);
              } else {
                player.reset(x, y);
              }
              break;
            case '#':
              objects[y][x] = new Wall(this, x, y);
              break;
            case '*':
              objects[y][x] = new Brick(this, x, y);
              break;
            default:
          }
        }
      }
      scanner.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public boolean valid(int x, int y) {
    return y >= 0 && y < height && x >= 0 && x < width;
  }

  public GameObject get(int x, int y) {
    if (valid(x, y)) {
      return objects[y][x];
    }
    return null;
  }

  public void add(int x, int y, GameObject object) {
    if (valid(x, y) && objects[y][x] == null) {
      objects[y][x] = object;
    }
  }

  public void remove(int x, int y) {
    if (valid(x, y)) {
      objects[y][x] = null;
    }
  }

  public void addObject(GameObject object) {
    if (valid(object.xUnit(), object.yUnit())) {
      otherObjects.add(object);
    }
  }

  public void removeObject(GameObject object) {
    otherObjects.remove(object);
  }

  public boolean canPlaceBomb(int x, int y) {
    if (valid(x, y)) {
      for (GameObject object : otherObjects) {
        if (object instanceof Bomb && object.xUnit() == x && object.yUnit() == y) {
          return false;
        }
      }
      if (objects[y][x] != null) {
        return false;
      }
    }
    return true;
  }

  public double collisionLeft(double x, double y) {
    int xUnit = (int) x / Sprite.SCALED_SIZE;
    int yUnit = (int) y / Sprite.SCALED_SIZE;
    if (valid(xUnit, yUnit) && objects[yUnit][xUnit] != null) {
      return (xUnit + 1) * Sprite.SCALED_SIZE;
    }
    return x;
  }

  public double collisionRight(double x, double y) {
    int xUnit = (int) x / Sprite.SCALED_SIZE;
    int yUnit = (int) y / Sprite.SCALED_SIZE;
    if (valid(xUnit, yUnit) && objects[yUnit][xUnit] != null) {
      return xUnit * Sprite.SCALED_SIZE - 1;
    }
    return x;
  }

  public double collisionUp(double x, double y) {
    int xUnit = (int) x / Sprite.SCALED_SIZE;
    int yUnit = (int) y / Sprite.SCALED_SIZE;
    if (valid(xUnit, yUnit) && objects[yUnit][xUnit] != null) {
      return (yUnit + 1) * Sprite.SCALED_SIZE;
    }
    return y;
  }

  public double collisionDown(double x, double y) {
    int xUnit = (int) x / Sprite.SCALED_SIZE;
    int yUnit = (int) y / Sprite.SCALED_SIZE;
    if (valid(xUnit, yUnit) && objects[yUnit][xUnit] != null) {
      return yUnit * Sprite.SCALED_SIZE - 1;
    }
    return y;
  }

  public void update(long now) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (objects[i][j] != null) {
          objects[i][j].update(now);
        }
      }
    }
    player.update(now);
    for (int i = 0; i < enemies.size(); i++) {
      enemies.get(i).update(now);
    }
    for (int i = 0; i < otherObjects.size(); i++) {
      otherObjects.get(i).update(now);
    }
    if (player.getX() >= SCREEN_WIDTH / 2
        && player.getX() <= width * Sprite.SCALED_SIZE - SCREEN_WIDTH / 2.0) {
      left = player.getX() - SCREEN_WIDTH / 2;
    }
    if (player.getY() >= SCREEN_HEIGHT / 2.0
        && player.getY() <= height * Sprite.SCALED_SIZE - SCREEN_HEIGHT / 2.0) {
      top = player.getY() - SCREEN_HEIGHT / 2;
    }
  }

  public void render(GraphicsContext graphicsContext) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (objects[i][j] instanceof StaticObject) {
          objects[i][j].render(graphicsContext, left, top);
        } else {
          background[i][j].render(graphicsContext, left, top);
          if (objects[i][j] != null) {
            objects[i][j].render(graphicsContext, left, top);
          }
        }
      }
    }
    player.render(graphicsContext, left, top);
    for (int i = 0; i < enemies.size(); i++) {
      enemies.get(i).render(graphicsContext, left, top);
      ;
    }
    for (int i = 0; i < otherObjects.size(); i++) {
      otherObjects.get(i).render(graphicsContext, left, top);
      ;
    }
  }

  public void onKeyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getCode()) {
      case LEFT:
      case RIGHT:
      case UP:
      case DOWN:
      case SPACE:
        break;
      case P:
        if (running) {
          running = false;
        } else {
          running = true;
        }
        break;
      default:
        return;
    }
    keyList.add(keyEvent);
    player.onKeyPressed(keyEvent.getCode());
  }

  public void onKeyReleased(KeyEvent keyEvent) {
    player.onKeyReleased(keyEvent.getCode());
    keyList.removeIf(e -> e.getCode() == keyEvent.getCode());
    if (keyList.size() > 0) {
      player.onKeyPressed(keyList.get(keyList.size() - 1).getCode());
    }
  }

  public boolean isRunning() {
    return running;
  }

  public List<GameObject> getEnemies() {
    return enemies;
  }
}
