package app.pane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class EditorPane extends ScrollPane {

    static final ObservableList typeList = FXCollections.observableArrayList(
            "Polygon", "Line", "Point", "Text");
    static final ObservableList fontFamilyList = FXCollections.observableArrayList(
            "Times New Roman", "Arial");
    static final ObservableList fontStyleList = FXCollections.observableArrayList(
            "Normal", "Bold");

    static final ObservableList<Integer> zoomList = FXCollections.observableArrayList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

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
        // create name components
        Label nameLabel = new Label("NamedLayerName");
        TextField nameTextField = new TextField();
        // create title components
        Label titleLabel = new Label("UserStyleTitle");
        TextField titleTextField = new TextField();
        // create sldname components
        Label sldNameLabel = new Label("UserStyleAbstract");
        TextField sldNameTextField = new TextField();
        // create sldliteral components
        Label sldLiteralLabel = new Label("Rule Name");
        TextField sldLiteralTextField = new TextField();


        pane.add(nameLabel, 0, 0);
        pane.add(nameTextField, 1, 0);
        pane.add(titleLabel, 2, 0);
        pane.add(titleTextField, 3, 0);
        pane.add(sldNameLabel, 0, 1);
        pane.add(sldNameTextField, 1, 1);
        pane.add(sldLiteralLabel, 2, 1);
        pane.add(sldLiteralTextField, 3, 1);

        return pane;
    }

    private GridPane createSubsetRow() {
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);

        Label typeLabel = new Label("Typ");
        ChoiceBox typeBox = new ChoiceBox(typeList);

        Label zoomLabel = new Label("Zoom");
        ChoiceBox zoomBox = new ChoiceBox(zoomList);

        Label fillLabel = new Label("Füllung");
        TextField fillTextField = new TextField("#000000");


        // text stuff
        Label fontFamilyLabel = new Label("Schriftart");
        ChoiceBox fontFamilyBox = new ChoiceBox(fontFamilyList);
        Label fontStyleLabel = new Label("Schriftstil");
        ChoiceBox fontStyleBox = new ChoiceBox(fontStyleList);


        // add listener to typeBox after components have been initialized
        typeBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (oldValue != null) {
                        String value = (String) oldValue;
                        // remove unnecessary components based in oldvalue
                        switch (value) {
                            case "Polygon":
                                pane.getChildren().remove(fillLabel);
                                pane.getChildren().remove(fillTextField);
                                break;
                            case "Point":
                                pane.getChildren().remove(fillLabel);
                                pane.getChildren().remove(fillTextField);
                                break;
                            case "Line":
                                pane.getChildren().remove(fillLabel);
                                pane.getChildren().remove(fillTextField);
                                break;
                            case "Text":
                                pane.getChildren().remove(fontFamilyBox);
                                pane.getChildren().remove(fontFamilyLabel);
                                pane.getChildren().remove(fontStyleBox);
                                pane.getChildren().remove(fontStyleLabel);
                                break;
                        }
                    }

                    String value = (String) newValue;
                    // add components based on new value
                    switch (value) {
                        case "Polygon":
                            addFillItemsToPane(pane, fillLabel, fillTextField);

                            break;
                        case "Point":
                            addFillItemsToPane(pane, fillLabel, fillTextField);

                            break;
                        case "Line":
                            addFillItemsToPane(pane, fillLabel, fillTextField);

                            break;
                        case "Text":
                            pane.add(fontFamilyLabel, 6, 0);
                            pane.add(fontFamilyBox, 7, 0);

                            pane.add(fontStyleLabel, 8, 0);
                            pane.add(fontStyleBox, 9, 0);

                            break;
                    }
                });


        pane.add(typeLabel, 0, 0);
        pane.add(typeBox, 1, 0);
        pane.add(zoomLabel, 2, 0);
        pane.add(zoomBox, 3, 0);

        return pane;
    }

    // extra method so position in row has to be changed in only one place -> here
    private void addFillItemsToPane(GridPane pane, Label fillLabel, TextField fillTextField) {
        pane.add(fillLabel, 4, 0);
        pane.add(fillTextField, 5, 0);
    }

}
