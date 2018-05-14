package app.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class PointPane extends GridPane {


    static final ObservableList wellKnownNameList = FXCollections.observableArrayList(
            "circle", "square", "triangle", "star", "cross", "x");

    public PointPane() {
        setPadding(new Insets(20, 20, 20, 20));
        setHgap(5);
        setVgap(5);

        Label titleLabel = new Label("Punkt");
        titleLabel.setFont(new Font(15));

        Label graphicNameLabel = new Label("Form");
        ChoiceBox graphicNameBox = new ChoiceBox(wellKnownNameList);

        Label fillLabel = new Label("Füllfarbe");
        SmallTextField fillText = new SmallTextField("#000000");

        Label sizeLabel = new Label("Größe");
        SmallTextField sizeText = new SmallTextField("3");

        add(titleLabel, 0, 0);
        add(graphicNameLabel, 1, 1);
        add(graphicNameBox, 2, 1);
        add(fillLabel, 3, 1);
        add(fillText, 4, 1);
        add(sizeLabel, 5, 1);
        add(sizeText, 6, 1);
    }
}
