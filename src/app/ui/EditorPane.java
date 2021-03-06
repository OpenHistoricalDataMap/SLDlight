package app.ui;

import app.model.NamedLayer;
import app.model.Rule;
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

    private List<RulePane> rulePanes;

    private PreviewPane previewPane;
    private GridPane contentPane;

    private TextField namedLayerName;

    public EditorPane(PreviewPane previewPane) {
        super();
        this.previewPane = previewPane;

        init();
    }

    public void init() {
        contentPane = new GridPane();

        contentPane.add(createGlobalControls(), 0, 0);

        rulePanes = new ArrayList<>();

        setContent(contentPane);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setPadding(new Insets(10, 10, 10, 10));

        showPreview();
    }


    private GridPane createGlobalControls() {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        Label nameLabel = new Label("Name");
        namedLayerName = new TextField();

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
        pane.add(namedLayerName, 1, 0);
        pane.add(addRuleButton, 2, 0);
        pane.add(showPreviewButton, 3, 0);

        return pane;
    }


    public void generateControlsFromNamedLayer(NamedLayer layer) {
        rulePanes.clear();
        namedLayerName.setText(layer.getName());
        for (Rule rule : layer.getRules()) {
            addRulePane(new RulePane(rule));
        }

    }

    public void showPreview() {
        NamedLayer namedLayer = new NamedLayer(namedLayerName.getText());
        for (RulePane rulePane : rulePanes) {
            namedLayer.addRule(rulePane.getRule(namedLayerName.getText()));
        }

        previewPane.showSLDLightPreviewText(getSLDLightContent());
        previewPane.showSLDPreviewText(getSLDContent());
    }

    private void addRulePane(RulePane rulePane) {
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

    private void addRulePane() {
        RulePane rulePane = new RulePane(rulePanes.size());
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

    public String getSLDContent() {
        NamedLayer namedLayer = new NamedLayer(namedLayerName.getText());
        for (RulePane rulePane : rulePanes) {
            namedLayer.addRule(rulePane.getRule(namedLayerName.getText()));
        }

        return namedLayer.toSLD();
    }

    public String getSLDLightContent() {
        NamedLayer namedLayer = new NamedLayer(namedLayerName.getText());
        for (RulePane rulePane : rulePanes) {
            namedLayer.addRule(rulePane.getRule(namedLayerName.getText()));
        }

        return namedLayer.toSLDLight();
    }

    public String getNamedLayerName() {
        return namedLayerName.getText();
    }
}
