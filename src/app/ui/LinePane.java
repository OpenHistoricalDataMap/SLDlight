package app.ui;

import app.model.symbolizer.LineSymbolizer;
import app.model.symbolizer.Symbolizer;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LinePane extends GridPane {

    private SmallTextField strokeWidthText, strokeDashText, offsetText;
    private ColorPicker strokeColorPicker;


    public LinePane() {
        setPadding(new Insets(20, 20, 20, 20));
        setHgap(5);
        setVgap(5);

        Label titleLabel = new Label("Linie");
        titleLabel.setFont(new Font(15));

        Label strokeLabel = new Label("Strichfarbe");
        strokeColorPicker = new ColorPicker(Color.BLACK);

        Label strokeWidthLabel = new Label("Dicke des Striches");
        strokeWidthText = new SmallTextField("1.0");

        Label strokeDashLabel = new Label("Strich-Muster");
        strokeDashText = new SmallTextField("5 2");

        Label offsetLabel = new Label("Senkrechte Verschiebung");
        offsetText = new SmallTextField("0");

        add(titleLabel, 0, 0);
        add(strokeLabel, 1, 1);
        add(strokeColorPicker, 2, 1);
        add(strokeWidthLabel, 3, 1);
        add(strokeWidthText, 4, 1);
        add(strokeDashLabel, 1, 2);
        add(strokeDashText, 2, 2);
        add(offsetLabel, 3, 2);
        add(offsetText, 4, 2);
    }

    public LinePane(LineSymbolizer symbolizer) {
        this();
        strokeWidthText.setText(symbolizer.getStrokeWidth());
        strokeDashText.setText(symbolizer.getStrokeDashArray());
        offsetText.setText(symbolizer.getPerpendicularOffset());
        strokeColorPicker.setValue(Color.web(symbolizer.getStroke()));
    }

    public Symbolizer toSymbolizer() {
        LineSymbolizer lineSymbolizer = new LineSymbolizer();
        lineSymbolizer.setStroke(getFormattedColorFromHexColor(strokeColorPicker.getValue()));
        lineSymbolizer.setStrokeWidth(strokeWidthText.getText());
        lineSymbolizer.setStrokeDashArray(strokeDashText.getText());
        lineSymbolizer.setPerpendicularOffset(offsetText.getText());
        return lineSymbolizer;
    }

    private String getFormattedColorFromHexColor(Color color) {
        return "#" + color.toString().substring(2, color.toString().length() - 2);
    }
}
