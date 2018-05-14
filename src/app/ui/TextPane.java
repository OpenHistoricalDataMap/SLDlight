package app.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class TextPane extends GridPane {


    static final ObservableList fontWeightList = FXCollections.observableArrayList(
            "Normal", "Bold");

    public TextPane() {
        setPadding(new Insets(20, 20, 20, 20));
        setHgap(5);
        setVgap(5);

        Label titleLabel = new Label("Text");
        titleLabel.setFont(new Font(15));

        Label labelLabel = new Label("Label");
        TextField labelText = new SmallTextField("");

        Label fontSizeLabel = new Label("Schriftgröße");
        TextField fontSizeText = new SmallTextField("12");

        Label fontWeightLabel = new Label("Schriftstil");
        ChoiceBox fontWeightBox = new ChoiceBox(fontWeightList);

        Label fillLabel = new Label("Farbe");
        TextField fillText = new SmallTextField("#000000");

        Label anchorXLabel = new Label("Ankerpunkt X");
        TextField anchorXText = new SmallTextField("0");

        Label anchorYLabel = new Label("Ankerpunkt Y");
        TextField anchorYText = new SmallTextField("0");

        Label displacementXLabel = new Label("Verschiebung X");
        TextField displacementXText = new SmallTextField("0");

        Label displacementYLabel = new Label("Verschiebung Y");
        TextField displacementYText = new SmallTextField("0");

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
}
