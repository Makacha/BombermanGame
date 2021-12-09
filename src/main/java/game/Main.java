package game;

import game.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class Main extends Application {

  public static final int WIDTH = 21;
  public static final int HEIGHT = 13;
  public static final int FPS = 60;
  public static final int TPF = 1_000_000_000 / FPS;

  private Canvas canvas;
  private GraphicsContext graphicsContext;
  private Group root;
  private Scene scene;
  private Game game;

  public static void main(String[] args) {
    Application.launch(Main.class);
  }

  @Override
  public void start(Stage stage) {
    // Create Canvas
    canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    graphicsContext = canvas.getGraphicsContext2D();
    // Create root container
    root = new Group();
    root.getChildren().addAll(canvas);
    // Create scene
    scene = new Scene(root);
    // Add scene to stage
    stage.setScene(scene);
    stage.setTitle("Bomberman Game");
    stage.show();
    // Create new game
    game = new Game(canvas.getWidth(), canvas.getHeight());
    // set key
    scene.setOnKeyPressed(game::onKeyPressed);
    scene.setOnKeyReleased(game::onKeyReleased);
    // Create timer to control frame per second
    AnimationTimer timer = new AnimationTimer() {
      private long lastUpdate = 0;

      @Override
      public void handle(long now) {
        if (now - lastUpdate >= TPF) {
          update(now);
          render();
          lastUpdate = now;
        }
      }
    };
    timer.start();
  }

  public void update(long now) {
    if (game.isRunning()) {
      game.update(now);
    }
  }

  public void render() {
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    game.render(graphicsContext);
  }

}