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

  public static final int WIDTH = 20;
  public static final int HEIGHT = 13;

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
    game = new Game();
    // set key
    scene.setOnKeyPressed(game::setOnKeyPressed);
    scene.setOnKeyReleased(game::setOnKeyReleased);
    // Create timer to control frame per second
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        update(now);
        render();
      }
    };
    timer.start();
  }

  public void update(long now) {
    game.update(now);
  }

  public void render() {
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    game.render(graphicsContext);
  }

}