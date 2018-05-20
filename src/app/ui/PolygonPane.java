package app.ui;

import app.model.symbolizer.PolygonSymbolizer;
import app.model.symbolizer.Symbolizer;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PolygonPane extends GridPane {

    private SmallTextField fillOpacityText, strokeWidthText, strokeDashText;

    private ColorPicker fillColorPicker, strokeColorPicker;

    public PolygonPane() {
        setPadding(new Insets(20, 20, 20, 20));
        setHgap(5);
        setVgap(5);

        Label titleLabel = new Label("Polygon");
        titleLabel.setFont(new Font(15));

        Label fillLabel = new Label("Füllfarbe");
        fillColorPicker = new ColorPicker(Color.BLACK);

        Label fillOpacityLabel = new Label("Deckkraft der Farbe");
        fillOpacityText = new SmallTextField("1.0");

        Label strokeLabel = new Label("Strichfarbe");
        strokeColorPicker = new ColorPicker(Color.BLACK);

        Label strokeWidthLabel = new Label("Dicke des Striches");
        strokeWidthText = new SmallTextField("1.0");

        Label strokeDashLabel = new Label("Strich-Muster");
        strokeDashText = new SmallTextField("5 2");

        add(titleLabel, 0, 0);
        add(fillLabel, 1, 1);
        add(fillColorPicker, 2, 1);
        add(fillOpacityLabel, 3, 1);
        add(fillOpacityText, 4, 1);
        add(strokeLabel, 1, 2);
        add(strokeColorPicker, 2, 2);
        add(strokeWidthLabel, 3, 2);
        add(strokeWidthText, 4, 2);
        add(strokeDashLabel, 5, 2);
        add(strokeDashText, 6, 2);
    }

    public PolygonPane(PolygonSymbolizer symbolizer) {
        this();
        fillColorPicker.setValue(Color.web(symbolizer.getFill()));
        fillOpacityText.setText(symbolizer.getFillOpacity());
        strokeColorPicker.setValue(Color.web(symbolizer.getStroke()));
        strokeWidthText.setText(symbolizer.getStrokeWidth());
        strokeDashText.setText(symbolizer.getStrokeDashArray());

    }

    public Symbolizer toSymbolizer() {
        PolygonSymbolizer polygonSymbolizer = new PolygonSymbolizer();
        polygonSymbolizer.setFill(getFormattedColorFromHexColor(fillColorPicker.getValue()));
        polygonSymbolizer.setFillOpacity(fillOpacityText.getText());
        polygonSymbolizer.setStroke(getFormattedColorFromHexColor(strokeColorPicker.getValue()));
        polygonSymbolizer.setStrokeWidth(strokeWidthText.getText());
        polygonSymbolizer.setStrokeDashArray(strokeDashText.getText());
        return polygonSymbolizer;
    }

    private String getFormattedColorFromHexColor(Color color) {
        return "#" + color.toString().substring(2, color.toString().length() - 2);
    }
}
