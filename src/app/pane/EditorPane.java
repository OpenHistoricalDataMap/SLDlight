package app.pane;

import app.ui.SmallTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class EditorPane extends ScrollPane {

    private static final int COUNT_OF_STANDARD_ITEMS_PER_ROW = 8;

    static final ObservableList typeList = FXCollections.observableArrayList(
            "Polygon", "Line", "Point", "Text");
    static final ObservableList fontFamilyList = FXCollections.observableArrayList(
            "Times New Roman", "Arial");
    static final ObservableList fontWeightList = FXCollections.observableArrayList(
            "Normal", "Bold");

    static final ObservableList<Integer> zoomList = FXCollections.observableArrayList(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);

    // TODO min max zoom und filter vor andere dinger schieben, weil nur 1 pro regel

    public EditorPane() {
        super();

        GridPane gridPane = new GridPane();

        gridPane.add(createGlobalControls(), 0, 0);
        gridPane.add(createSubsetRow(), 0, 1);

        // Set content for ScrollPane
        setContent(gridPane);
        // Always show vertical scroll bar
        setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        // Horizontal scroll bar is only displayed when needed
        setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        setPadding(new Insets(10, 10, 10, 10));
    }


    private GridPane createGlobalControls() {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        // create name ui
        Label nameLabel = new Label("Name");
        TextField nameTextField = new TextField();


        pane.add(nameLabel, 0, 0);
        pane.add(nameTextField, 1, 0);

        return pane;
    }

    private GridPane createSubsetRow() {
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(5);

        Label typeLabel = new Label("Typ");
        ChoiceBox typeBox = new ChoiceBox(typeList);

        Label minZoomLabel = new Label("min Zoom");
        ChoiceBox minZoomBox = new ChoiceBox(zoomList);
        Label maxZoomLabel = new Label("max Zoom");
        ChoiceBox maxZoomBox = new ChoiceBox(zoomList);

        Label filterLabel = new Label("Filter");
        SmallTextField filterTextField = new SmallTextField("");

        // add listener to typeBox after ui have been initialized
        typeBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (oldValue != null) {
                        String value = (String) oldValue;
                        // remove unnecessary ui based in oldvalue
                        switch (value) {
                            case "Polygon":
                                removeAdditionalItemsFrom(pane);
                                break;
                            case "Point":
                                removeAdditionalItemsFrom(pane);
                                break;
                            case "Line":
                                removeAdditionalItemsFrom(pane);
                                break;
                            case "Text":
                                removeAdditionalItemsFrom(pane);
                                break;
                        }
                    }

                    String value = (String) newValue;
                    // add ui based on new value
                    switch (value) {
                        case "Polygon":
                            addPolygonItemsTo(pane);

                            break;
                        case "Point":
                            addPointItemsTo(pane);
                            break;
                        case "Line":

                            break;
                        case "Text":
                            addTextItemsTo(pane);

                            break;
                    }
                });


        pane.add(typeLabel, 0, 0);
        pane.add(typeBox, 1, 0);
        pane.add(minZoomLabel, 2, 0);
        pane.add(minZoomBox, 3, 0);
        pane.add(maxZoomLabel, 4, 0);
        pane.add(maxZoomBox, 5, 0);
        pane.add(filterLabel, 6, 0);
        pane.add(filterTextField, 7, 0);

        return pane;
    }

    private void addPolygonItemsTo(GridPane pane) {
        Label fillLabel = new Label("Füllfarbe");
        SmallTextField fillText = new SmallTextField("#000000");

        Label fillOpacityLabel = new Label("Deckkraft der Farbe");
        SmallTextField fillOpacityText = new SmallTextField("1.0");

        Label strokeLabel = new Label("Strichfarbe");
        SmallTextField strokeText = new SmallTextField("#000000");

        Label strokeWidthLabel = new Label("Dicke des Striches");
        SmallTextField strokeWidthText = new SmallTextField("1.0");

        Label strokeDashLabel = new Label("Strich-Muster");
        SmallTextField strokeDashText = new SmallTextField("5 2");

        pane.add(fillLabel, 2, 1);
        pane.add(fillText, 3, 1);
        pane.add(fillOpacityLabel, 4, 1);
        pane.add(fillOpacityText, 5, 1);
        pane.add(strokeLabel, 2, 2);
        pane.add(strokeText, 3, 2);
        pane.add(strokeWidthLabel, 4, 2);
        pane.add(strokeWidthText, 5, 2);
        pane.add(strokeDashLabel, 6, 2);
        pane.add(strokeDashText, 7, 2);
    }

    private void removeAdditionalItemsFrom(GridPane pane) {
        pane.getChildren().remove(COUNT_OF_STANDARD_ITEMS_PER_ROW, pane.getChildren().size());

    }

    private void addPointItemsTo(GridPane pane) {
        Label graphicNameLabel = new Label("Form");
        SmallTextField graphicNameText = new SmallTextField("circle");

        Label fillLabel = new Label("Füllfarbe");
        SmallTextField fillText = new SmallTextField("#000000");

        Label sizeLabel = new Label("Größe");
        SmallTextField sizeText = new SmallTextField("3");

        pane.add(graphicNameLabel, 2, 1);
        pane.add(graphicNameText, 3, 1);
        pane.add(fillLabel, 4, 1);
        pane.add(fillText, 5, 1);
        pane.add(sizeLabel, 6, 1);
        pane.add(sizeText, 7, 1);
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

        pane.add(labelLabel, 2,1);
        pane.add(labelText, 3,1);
        pane.add(fontSizeLabel, 4,1);
        pane.add(fontSizeText, 5,1);
        pane.add(fontWeightLabel, 6,1);
        pane.add(fontWeightBox, 7,1);
        pane.add(fillLabel, 8,1);
        pane.add(fillText, 9,1);
        pane.add(anchorXLabel, 2,2);
        pane.add(anchorXText, 3,2);
        pane.add(anchorYLabel, 4,2);
        pane.add(anchorYText, 5,2);
        pane.add(displacementXLabel, 6,2);
        pane.add(displacementXText, 7,2);
        pane.add(displacementYLabel, 8,2);
        pane.add(displacementYText, 9,2);

    }

}
