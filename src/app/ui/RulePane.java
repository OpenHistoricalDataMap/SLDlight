package app.ui;

import app.model.Filter;
import app.model.Rule;
import app.model.Zoom;
import app.model.symbolizer.*;
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

    public RulePane(Rule rule) {
        // nameindex is last value in rulename
        this(Integer.parseInt(rule.getName().split("_")[rule.getName().split("_").length - 1]));

        minZoomBox.getSelectionModel().select(Integer.parseInt(rule.getZoom().getMinScaleNumber()));
        maxZoomBox.getSelectionModel().select(Integer.parseInt(rule.getZoom().getMaxScaleNumber()));

        if (rule.getFilter() != null)
            filterText.setText(rule.getFilter().getPropertyEqualTo());

        for (Symbolizer symbolizer : rule.getSymbolizers()) {
            if (symbolizer instanceof PolygonSymbolizer) {
                polygonPane = new PolygonPane((PolygonSymbolizer) symbolizer);
                addPolygonPane(polygonPane);
            } else if (symbolizer instanceof PointSymbolizer) {
                pointPane = new PointPane((PointSymbolizer) symbolizer);
                addPointPane(pointPane);
            } else if (symbolizer instanceof LineSymbolizer) {
                linePane = new LinePane((LineSymbolizer) symbolizer);
                addLinePane(linePane);
            } else if (symbolizer instanceof TextSymbolizer) {
                textPane = new TextPane((TextSymbolizer) symbolizer);
                addTextPane(textPane);
            }
        }
    }

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
                            addPolygonPane(null);
                        }
                        break;
                    case "Linie":
                        if (linePane == null) {
                            addLinePane(null);
                        }
                        break;
                    case "Punkt":
                        if (pointPane == null) {
                            addPointPane(null);
                        }
                        break;
                    case "Text":
                        if (textPane == null) {
                            addTextPane(null);
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

    private void addPolygonPane(PolygonPane pane) {
        if (pane == null) {
            polygonPane = new PolygonPane();
        } else {
            polygonPane = pane;
        }
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

    private void addPointPane(PointPane pane) {
        if (pane == null) {
            pointPane = new PointPane();
        } else {
            pointPane = pane;
        }
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

    private void addLinePane(LinePane pane) {
        if (pane == null) {
            linePane = new LinePane();
        } else {
            linePane = pane;
        }
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

    private void addTextPane(TextPane pane) {
        if (pane == null) {
            textPane = new TextPane();
        } else {
            textPane = pane;
        }
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
        if (textPane != null) {
            rule.addSymbolizer(textPane.toSymbolizer());
        }

        return rule;
    }
}