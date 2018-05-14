package app.pane;

import app.ui.PolygonPane;
import app.ui.RulePane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;


public class EditorPane extends ScrollPane {

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

        Button showPreviewButton = new Button("Preview anzeigen");
        showPreviewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPreview();
            }
        });


        pane.add(nameLabel, 0, 0);
        pane.add(nameTextField, 1, 0);
        pane.add(addRuleButton, 2, 0);
        pane.add(showPreviewButton, 3, 0);

        return pane;
    }

    private void showPreview() {
        previewPane.setSldPreviewText("Test");
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
}
