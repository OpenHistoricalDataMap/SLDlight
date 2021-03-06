package app.ui;

import app.model.symbolizer.PointSymbolizer;
import app.model.symbolizer.Symbolizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PointPane extends GridPane {


    static final ObservableList wellKnownNameList = FXCollections.observableArrayList(
            "circle", "square", "triangle", "star", "cross", "x");

    private SmallTextField sizeText;
    private ChoiceBox graphicNameBox;
    private ColorPicker fillColorPicker;

    public PointPane() {
        setPadding(new Insets(20, 20, 20, 20));
        setHgap(5);
        setVgap(5);

        Label titleLabel = new Label("Punkt");
        titleLabel.setFont(new Font(15));

        Label graphicNameLabel = new Label("Form");
        graphicNameBox = new ChoiceBox(wellKnownNameList);
        graphicNameBox.getSelectionModel().select(0);

        Label fillLabel = new Label("Füllfarbe");
        fillColorPicker = new ColorPicker(Color.BLACK);

        Label sizeLabel = new Label("Größe");
        sizeText = new SmallTextField("3");

        add(titleLabel, 0, 0);
        add(graphicNameLabel, 1, 1);
        add(graphicNameBox, 2, 1);
        add(fillLabel, 3, 1);
        add(fillColorPicker, 4, 1);
        add(sizeLabel, 5, 1);
        add(sizeText, 6, 1);
    }

    public PointPane(PointSymbolizer symbolizer) {
        this();
        fillColorPicker.setValue(Color.web(symbolizer.getFill()));
        sizeText.setText(symbolizer.getSize());
        graphicNameBox.getSelectionModel().select(symbolizer.getWellKnownName());
    }

    public Symbolizer toSymbolizer() {
        PointSymbolizer pointSymbolizer = new PointSymbolizer();
        pointSymbolizer.setFill(getFormattedColorFromHexColor(fillColorPicker.getValue()));
        pointSymbolizer.setSize(sizeText.getText());
        pointSymbolizer.setWellKnownName((String) graphicNameBox.getSelectionModel().getSelectedItem());
        return pointSymbolizer;
    }

    private String getFormattedColorFromHexColor(Color color) {
        return "#" + color.toString().substring(2, color.toString().length() - 2);
    }
}
