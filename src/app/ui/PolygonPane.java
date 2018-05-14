package app.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class PolygonPane extends GridPane {

    public PolygonPane() {
        setPadding(new Insets(20, 20, 20, 20));
        setHgap(5);
        setVgap(5);

        Label titleLabel = new Label("Polygon");
        titleLabel.setFont(new Font(15));

        Label fillLabel = new Label("Füllfarbe");
        SmallTextField fillText = new SmallTextField("#000000");

        Label fillOpacityLabel = new Label("Deckkraft der Farbe");
        SmallTextField fillOpacityText = new SmallTextField("1.0");

        Label strokeLabel = new Label("Strichfarbe");
        SmallTextField strokeText = new SmallTextField("#000000");

        Label strokeWidthLabel = new Label("Dicke des Striches");
        SmallTextField strokeWidthText = new SmallTextField("1.0");

        Label strokeDashLabel = new Label("Strich-Muster");
        SmallTextField strokeDashText = new SmallTextField("5 2");

        add(titleLabel, 0, 0);
        add(fillLabel, 1, 1);
        add(fillText, 2, 1);
        add(fillOpacityLabel, 3, 1);
        add(fillOpacityText, 4, 1);
        add(strokeLabel, 1, 2);
        add(strokeText, 2, 2);
        add(strokeWidthLabel, 3, 2);
        add(strokeWidthText, 4, 2);
        add(strokeDashLabel, 5, 2);
        add(strokeDashText, 6, 2);
    }
}
