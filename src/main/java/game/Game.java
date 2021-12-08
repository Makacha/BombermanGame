package game;

import java.io.FileInputStream;
import java.util.Scanner;

import game.object.Object;
import game.object.Player;
import game.object.StaticObject;
import game.object.tile.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class Game {
  private int height;
  private int width;
  private int left;
  private int top;
  private int level;
  private Player player;
  private Object[][] objects;
  private Grass[][] background;

  public Game() {
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
      objects = new Object[height][width];
      background= new Grass[height][width];
      for (int y = 0; y < height; y++) {
        String line = scanner.nextLine();
        if (line.equals("")) {
          line = scanner.nextLine();
        }
        for (int x = 0; x < width; x++) {
          background[y][x] = new Grass(x, y);
          switch (line.charAt(x)) {
            case 'p':
              if (player == null) {
                player = new Player(x, y);
              } else {
                // TODO: reset player
              }
              break;
            case '#':
              objects[y][x] = new Wall(x, y);
              break;
            case '*':
              objects[y][x] = new Brick(x, y);
              break;
            default:
              objects[y][x] = new Grass(x, y);
          }
        }
      }
      scanner.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void update(long now) {
    // TODO:
  }

  public void render(GraphicsContext graphicsContext) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (objects[i][j] instanceof StaticObject) {
          objects[i][j].render(graphicsContext, left, top);
        } else {
          background[i][j].render(graphicsContext, left, top);
        }
      }
    }
  }

  public void setOnKeyPressed(KeyEvent keyEvent) {
    // TODO:
  }

  public void setOnKeyReleased(KeyEvent keyEvent) {
    // TODO:
  }
}
