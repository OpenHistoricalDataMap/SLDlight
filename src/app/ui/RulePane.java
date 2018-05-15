package app.ui;

import app.model.Filter;
import app.model.Rule;
import app.model.Zoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RulePane extends GridPane {

    private static final ObservableList<Integer> zoomList = FXCollections.observableArrayList(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);


    static final ObservableList typeList = FXCollections.observableArrayList(
            "Polygon", "Linie", "Punkt", "Text");

    private PolygonPane polygonPane;
    private PointPane pointPane;
    private LinePane linePane;
    private TextPane textPane;
    // used for childpanes; starts with two because of rule controls
    private int rowIndex = 2;

    // used to give rule a name for sld
    private int nameIndex;


    private ChoiceBox minZoomBox, maxZoomBox;
    private SmallTextField filterText;

    public RulePane(int nameIndex) {
        this.nameIndex = nameIndex;

        setVgap(10);
        setHgap(10);
        setPadding(new Insets(20, 10, 20, 10));


        Label minZoomLabel = new Label("min Zoom");
        minZoomBox = new ChoiceBox(zoomList);
        minZoomBox.getSelectionModel().select(0);
        Label maxZoomLabel = new Label("max Zoom");
        maxZoomBox = new ChoiceBox(zoomList);
        maxZoomBox.getSelectionModel().select(0);

        Label filterLabel = new Label("Filter");
        filterText = new SmallTextField("");

        Label typeLabel = new Label("Typ");
        ChoiceBox typeBox = new ChoiceBox(typeList);
        typeBox.getSelectionModel().select(0);
        Button addSymbolizerButton = new Button("Objekt hinzufügen");
        addSymbolizerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String type = (String) typeBox.getSelectionModel().getSelectedItem();

                switch (type) {
                    case "Polygon":
                        if (polygonPane == null) {
                            polygonPane = new PolygonPane();
                            Button removePolygonPaneButton = new Button("Löschen");
                            removePolygonPaneButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    getChildren().remove(polygonPane);
                                    getChildren().remove(removePolygonPaneButton);
                                    polygonPane = null;
                                    rowIndex--;
                                }
                            });
                            add(removePolygonPaneButton, 0, rowIndex);
                            add(polygonPane, 1, rowIndex++, 8, 1);
                        }
                        break;
                    case "Linie":
                        if (linePane == null) {
                            linePane = new LinePane();
                            Button removeLinePaneButton = new Button("Löschen");
                            removeLinePaneButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    getChildren().remove(linePane);
                                    getChildren().remove(removeLinePaneButton);
                                    linePane = null;
                                    rowIndex--;
                                }
                            });
                            add(removeLinePaneButton, 0, rowIndex);
                            add(linePane, 1, rowIndex++, 8, 1);
                        }
                        break;
                    case "Punkt":
                        if (pointPane == null) {
                            pointPane = new PointPane();
                            Button removePointPaneButton = new Button("Löschen");
                            removePointPaneButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    getChildren().remove(pointPane);
                                    getChildren().remove(removePointPaneButton);
                                    pointPane = null;
                                    rowIndex--;
                                }
                            });
                            add(removePointPaneButton, 0, rowIndex);
                            add(pointPane, 1, rowIndex++, 8, 1);
                        }
                        break;
                    case "Text":
                        if (textPane == null) {
                            textPane = new TextPane();
                            Button removeTextPaneButton = new Button("Löschen");
                            removeTextPaneButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    getChildren().remove(textPane);
                                    getChildren().remove(removeTextPaneButton);
                                    textPane = null;
                                    rowIndex--;
                                }
                            });
                            add(removeTextPaneButton, 0, rowIndex);
                            add(textPane, 1, rowIndex++, 8, 1);
                        }
                        break;
                    default:
                        break;
                }
            }
        });


        add(minZoomLabel, 0, 0);
        add(minZoomBox, 1, 0);
        add(maxZoomLabel, 2, 0);
        add(maxZoomBox, 3, 0);
        add(filterLabel, 4, 0);
        add(filterText, 5, 0);
        add(typeBox, 0, 1);
        add(addSymbolizerButton, 1, 1);
    }


    public Rule getRule(String namedLayerName) {
        Rule rule = new Rule(namedLayerName + "_rule_" + nameIndex);
        Zoom zoom = new Zoom((Integer) minZoomBox.getSelectionModel().getSelectedItem(),
                (Integer) maxZoomBox.getSelectionModel().getSelectedItem());
        rule.setZoom(zoom);
        rule.setFilter(new Filter(filterText.getText()));

        if (polygonPane != null) {
            rule.addSymbolizer(polygonPane.toSymbolizer());
        }
        if (pointPane != null) {
            rule.addSymbolizer(pointPane.toSymbolizer());
        }
        if (linePane != null) {
            rule.addSymbolizer(linePane.toSymbolizer());
        }
        if (textPane != null){
            rule.addSymbolizer(textPane.toSymbolizer());
        }

        return rule;
    }
}