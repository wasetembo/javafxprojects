package colorselector;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorSelector extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create sliders for Red, Green, Blue, and Opacity
        Slider redSlider = createColorSlider();
        Slider greenSlider = createColorSlider();
        Slider blueSlider = createColorSlider();
        Slider opacitySlider = createOpacitySlider();

        // Create a text node
        Text text = new Text("Dynamic Color Selector");
        text.setFont(Font.font(24));

        // Bind text fill to slider values
        text.fillProperty().bind(Bindings.createObjectBinding(() ->
                        Color.color(
                                redSlider.getValue() / 100,
                                greenSlider.getValue() / 100,
                                blueSlider.getValue() / 100,
                                opacitySlider.getValue() / 100),
                redSlider.valueProperty(),
                greenSlider.valueProperty(),
                blueSlider.valueProperty(),
                opacitySlider.valueProperty()
        ));

        // Create a GridPane to hold sliders and labels
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        // Add sliders and labels to the pane
        pane.add(new Label("Red:"), 0, 0);
        pane.add(redSlider, 1, 0);

        pane.add(new Label("Green:"), 0, 1);
        pane.add(greenSlider, 1, 1);

        pane.add(new Label("Blue:"), 0, 2);
        pane.add(blueSlider, 1, 2);

        pane.add(new Label("Opacity:"), 0, 3);
        pane.add(opacitySlider, 1, 3);

        // Add text to the pane
        pane.add(text, 0, 4, 2, 1);

        // Set up the scene
        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Color Selector");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create a color slider (0 to 100 range)
    private Slider createColorSlider() {
        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        return slider;
    }

    // Method to create an opacity slider (0 to 100 range)
    private Slider createOpacitySlider() {
        Slider slider = new Slider(0, 100, 100);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        return slider;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
