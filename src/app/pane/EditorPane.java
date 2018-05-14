package app.pane;

import app.ui.PolygonPane;
import app.ui.RulePane;
import app.ui.SmallTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;


public class EditorPane extends ScrollPane {

    private static final int COUNT_OF_STANDARD_ITEMS_PER_ROW = 2;

    static final ObservableList fontWeightList = FXCollections.observableArrayList(
            "Normal", "Bold");


    private List<RulePane> rulePanes = new ArrayList<>();
    private List<PolygonPane> polygonPanes = new ArrayList<>();

    private PreviewPane previewPane;
    private GridPane contentPane;

    public EditorPane(PreviewPane previewPane) {
        super();
        this.previewPane = previewPane;

        contentPane = new GridPane();

        contentPane.add(createGlobalControls(), 0, 0);

        // Set content for ScrollPane
        setContent(contentPane);
        // Always show vertical scroll bar
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        // Horizontal scroll bar is only displayed when needed
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setPadding(new Insets(10, 10, 10, 10));
    }


    private GridPane createGlobalControls() {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        // create name ui
        Label nameLabel = new Label("Name");
        TextField nameTextField = new TextField();

        Button addRuleButton = new Button("Regel hinzufügen");
        addRuleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addRulePane();
            }
        });


        pane.add(nameLabel, 0, 0);
        pane.add(nameTextField, 1, 0);
        pane.add(addRuleButton, 2, 0);

        return pane;
    }

    private void addRulePane() {
        RulePane rulePane = new RulePane();
        rulePanes.add(rulePane);

        Button removeRuleButton = new Button("Regel löschen");
        removeRuleButton.setAlignment(Pos.CENTER);
        removeRuleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rulePanes.remove(rulePane);
                contentPane.getChildren().remove(rulePane);
                contentPane.getChildren().remove(removeRuleButton);
            }
        });
        contentPane.add(rulePane, 0, rulePanes.size(), 1, 1);
        contentPane.add(removeRuleButton, 1, rulePanes.size(), 1, 1);

    }

    private void addLineItemsTo(GridPane pane) {
        Label strokeLabel = new Label("Strichfarbe");
        SmallTextField strokeText = new SmallTextField("#000000");

        Label strokeWidthLabel = new Label("Dicke des Striches");
        SmallTextField strokeWidthText = new SmallTextField("1.0");

        Label strokeDashLabel = new Label("Strich-Muster");
        SmallTextField strokeDashText = new SmallTextField("5 2");

        Label offsetLabel = new Label("Senkrechte Verschiebung");
        SmallTextField offsetText = new SmallTextField("5 2");

        pane.add(strokeLabel, 2, 1);
        pane.add(strokeText, 3, 1);
        pane.add(strokeWidthLabel, 4, 1);
        pane.add(strokeWidthText, 5, 1);
        pane.add(strokeDashLabel, 2, 2);
        pane.add(strokeDashText, 3, 2);
        pane.add(offsetLabel, 4, 2);
        pane.add(offsetText, 5, 2);
    }

    // removes items from symbolizer row
    private void removeAdditionalItemsFrom(GridPane pane) {
        pane.getChildren().remove(COUNT_OF_STANDARD_ITEMS_PER_ROW, pane.getChildren().size());

    }


    private void addTextItemsTo(GridPane pane) {
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

        pane.add(labelLabel, 2, 1);
        pane.add(labelText, 3, 1);
        pane.add(fontSizeLabel, 4, 1);
        pane.add(fontSizeText, 5, 1);
        pane.add(fontWeightLabel, 2, 2);
        pane.add(fontWeightBox, 3, 2);
        pane.add(fillLabel, 4, 2);
        pane.add(fillText, 5, 2);
        pane.add(anchorXLabel, 2, 3);
        pane.add(anchorXText, 3, 3);
        pane.add(anchorYLabel, 4, 3);
        pane.add(anchorYText, 5, 3);
        pane.add(displacementXLabel, 2, 4);
        pane.add(displacementXText, 3, 4);
        pane.add(displacementYLabel, 4, 4);
        pane.add(displacementYText, 5, 4);

    }

}
