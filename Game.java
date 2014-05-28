/**
 * Created by Sean Roper on 5/27/14.
 */
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;


public class Game extends Application {
    public static Pane canvas;

    @Override
    public void start(final Stage primaryStage) {
        canvas = new Pane();
        final Scene scene = new Scene(canvas, 600, 800);

        primaryStage.setTitle("War");
        primaryStage.setScene(scene);
        primaryStage.show();

        final List<Card> player = ();
        canvas.getChildren().addAll(player);


    }

}
