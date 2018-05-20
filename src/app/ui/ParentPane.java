package app.ui;

import app.io.MyFileReader;
import app.io.MyFileWriter;
import app.model.NamedLayer;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;

public class ParentPane extends BorderPane {

    private static FileChooser.ExtensionFilter sldlFilter = new FileChooser.ExtensionFilter("SLDlight (*.sldl)", "*.sldl");
    private static FileChooser.ExtensionFilter sldFilter = new FileChooser.ExtensionFilter("SLD (*.sld)", "*.sld");

    public ParentPane() {
        super();

        PreviewPane previewPane = new PreviewPane();
        EditorPane editorPane = new EditorPane(previewPane);
        DragResizer.makeResizable(previewPane);

        setCenter(editorPane);
        setRight(previewPane);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        MenuBar menuBar = new MenuBar();

        // --- Menu File
        Menu menuFile = new Menu("Datei");
        MenuItem newFile = new MenuItem("Neu");
        newFile.setOnAction(t -> {
            editorPane.init();

        });
        MenuItem open = new MenuItem("Datei öffnen");
        open.setOnAction(t -> {
            fileChooser.setTitle("Datei öffnen");
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(sldlFilter);

            File file = fileChooser.showOpenDialog(getScene().getWindow());
            if (file != null) {
                NamedLayer layer = MyFileReader.parseNamedLayerFromSLDLight(file.getAbsolutePath());
                editorPane.generateControlsFromNamedLayer(layer);
                editorPane.showPreview();
            }

        });

        MenuItem saveAsSLDLight = new MenuItem("SLDLight speichern");
        saveAsSLDLight.setOnAction(t -> {
            fileChooser.setTitle("SLDLight speichern");
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(sldlFilter);

            if (editorPane.getNamedLayerName() != null) {
                fileChooser.setInitialFileName(editorPane.getNamedLayerName() + ".sldl");
            } else {
                fileChooser.setInitialFileName(".sldl");
            }

            File file = fileChooser.showSaveDialog(getScene().getWindow());
            if (file != null) {
                MyFileWriter.writeFile(file.getAbsolutePath(), editorPane.getSLDLightContent());
            }

        });

        MenuItem saveAsSLD = new MenuItem("SLD speichern");
        saveAsSLD.setOnAction(t -> {
            fileChooser.setTitle("SLD speichern");
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(sldFilter);

            if (editorPane.getNamedLayerName() != null) {
                fileChooser.setInitialFileName(editorPane.getNamedLayerName() + ".sld");
            } else {
                fileChooser.setInitialFileName(".sld");
            }

            File file = fileChooser.showSaveDialog(getScene().getWindow());
            if (file != null) {
                MyFileWriter.writeFile(file.getAbsolutePath(), editorPane.getSLDContent());
            }

        });
        menuFile.getItems().addAll(newFile, open, saveAsSLDLight, saveAsSLD);


        menuBar.getMenus().addAll(menuFile);


        setTop(menuBar);
    }
}
