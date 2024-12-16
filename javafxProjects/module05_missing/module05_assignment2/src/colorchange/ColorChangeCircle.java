package colorchange;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ColorChangeCircle extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a Circle
        Circle circle = new Circle(100); // Radius of 100
        circle.setFill(Color.WHITE); // Default color
        circle.setStroke(Color.BLACK); // Border color

        // Add mouse event handlers
        circle.setOnMousePressed((MouseEvent e) -> {
            circle.setFill(Color.BLACK); // Change to black on mouse press
        });

        circle.setOnMouseReleased((MouseEvent e) -> {
            circle.setFill(Color.WHITE); // Change back to white on mouse release
        });

        // Add the circle to a StackPane
        StackPane root = new StackPane();
        root.getChildren().add(circle);

        // Create the Scene
        Scene scene = new Scene(root, 400, 400); // Width and Height
        primaryStage.setTitle("Color Change Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
