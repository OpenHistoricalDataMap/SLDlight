package app.ui;

import app.io.MyFileWriter;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;

public class ParentPane extends BorderPane {

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
        MenuItem open = new MenuItem("Datei öffnen");
        open.setOnAction(t -> {
            fileChooser.setTitle("Datei öffnen");
            File file = fileChooser.showOpenDialog(getScene().getWindow());
            if (file != null) {
                //openFile(file);
            }

        });
        MenuItem saveAsSLD = new MenuItem("SLD speichern");
        saveAsSLD.setOnAction(t -> {
            fileChooser.setTitle("SLD speichern");
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
        MenuItem saveAsSLDLight = new MenuItem("SLDLight speichern");
        saveAsSLDLight.setOnAction(t -> {
            fileChooser.setTitle("SLDLight speichern");
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
        menuFile.getItems().addAll(open, saveAsSLD, saveAsSLDLight);


        menuBar.getMenus().addAll(menuFile);


        setTop(menuBar);
    }
}
