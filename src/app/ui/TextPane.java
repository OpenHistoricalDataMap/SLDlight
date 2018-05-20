package app.ui;

import app.model.symbolizer.Symbolizer;
import app.model.symbolizer.TextSymbolizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class TextPane extends GridPane {


    static final ObservableList fontWeightList = FXCollections.observableArrayList(
            "Normal", "Bold");

    private SmallTextField labelText, fontSizeText, fillText, anchorXText, anchorYText, displacementXText, displacementYText;
    private ChoiceBox fontWeightBox;

    public TextPane() {
        setPadding(new Insets(20, 20, 20, 20));
        setHgap(5);
        setVgap(5);

        Label titleLabel = new Label("Text");
        titleLabel.setFont(new Font(15));

        Label labelLabel = new Label("Label");
        labelText = new SmallTextField("Label");

        Label fontSizeLabel = new Label("Schriftgröße");
        fontSizeText = new SmallTextField("12");

        Label fontWeightLabel = new Label("Schriftstil");
        fontWeightBox = new ChoiceBox(fontWeightList);
        fontWeightBox.getSelectionModel().select(0);

        Label fillLabel = new Label("Farbe");
        fillText = new SmallTextField("#000000");

        Label anchorXLabel = new Label("Ankerpunkt X");
        anchorXText = new SmallTextField("0");

        Label anchorYLabel = new Label("Ankerpunkt Y");
        anchorYText = new SmallTextField("0");

        Label displacementXLabel = new Label("Verschiebung X");
        displacementXText = new SmallTextField("0");

        Label displacementYLabel = new Label("Verschiebung Y");
        displacementYText = new SmallTextField("0");

        add(titleLabel, 0, 0);
        add(labelLabel, 1, 1);
        add(labelText, 2, 1);
        add(fontSizeLabel, 3, 1);
        add(fontSizeText, 4, 1);
        add(fontWeightLabel, 1, 2);
        add(fontWeightBox, 2, 2);
        add(fillLabel, 3, 2);
        add(fillText, 4, 2);
        add(anchorXLabel, 1, 3);
        add(anchorXText, 2, 3);
        add(anchorYLabel, 3, 3);
        add(anchorYText, 4, 3);
        add(displacementXLabel, 1, 4);
        add(displacementXText, 2, 4);
        add(displacementYLabel, 3, 4);
        add(displacementYText, 4, 4);
    }

    public TextPane(TextSymbolizer symbolizer) {
        this();
    }

    public Symbolizer toSymbolizer() {
        TextSymbolizer textSymbolizer = new TextSymbolizer();
        textSymbolizer.setLabel(labelText.getText());
        textSymbolizer.setFontSize(fontSizeText.getText());
        textSymbolizer.setFontWeight((String) fontWeightBox.getSelectionModel().getSelectedItem());
        textSymbolizer.setFill(fillText.getText());
        textSymbolizer.setAnchorPointX(anchorXText.getText());
        textSymbolizer.setAnchorPointY(anchorYText.getText());
        textSymbolizer.setDisplacementX(displacementXText.getText());
        textSymbolizer.setDisplacementY(displacementYText.getText());
        return textSymbolizer;
    }
}
