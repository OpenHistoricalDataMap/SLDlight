package app.ui;

import app.sftp.SFTPHelper;
import app.sftp.Workspace;
import app.sftp.XMLForSLDCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UploadDialog extends Stage {

    private static final ObservableList<Workspace> WORKSPACE_OBSERVABLE_SET = FXCollections.observableArrayList(Workspace.getWorkspaces());
    private static FileChooser.ExtensionFilter sldFilter = new FileChooser.ExtensionFilter("SLD (*.sld)", "*.sld");
    private GridPane mainPane;
    private FileChooser fileChooser = new FileChooser();

    private ChoiceBox<Workspace> workspaceChoiceBox;
    private Label filePathLabel;
    private File selectedFile;

    public UploadDialog() {
        setTitle("Datei hochladen");
        mainPane = new GridPane();
        mainPane.setHgap(20);
        mainPane.setVgap(20);
        mainPane.setPadding(new Insets(20, 20, 20, 20));

        setupFileChooserButton();
        setupFileChooserLabel();
        setupWorkspaceChoiceBox();
        setupUploadButton();

        Scene scene = new Scene(mainPane, 500, 300);
        setScene(scene);
    }

    private void setupUploadButton() {
        Button uploadButton = new Button("Hochladen");
        uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleUploadButtonClick();
            }
        });

        mainPane.add(uploadButton, 2, 5);
    }

    private void handleUploadButtonClick() {
        if (selectedFile != null) {
            SFTPHelper.uploadFileToOHMServer(workspaceChoiceBox.getValue(), selectedFile);

            File xmlFile = XMLForSLDCreator.getXmlForSLDFile(selectedFile, workspaceChoiceBox.getValue());
            SFTPHelper.uploadFileToOHMServer(workspaceChoiceBox.getValue(), xmlFile);
        }
    }


    private void setupWorkspaceChoiceBox() {
        Label label = new Label("Workspace");
        workspaceChoiceBox = new ChoiceBox(WORKSPACE_OBSERVABLE_SET);
        workspaceChoiceBox.getSelectionModel().select(0);

        mainPane.add(label, 1, 2);
        mainPane.add(workspaceChoiceBox, 2, 2);
    }

    private void setupFileChooserButton() {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        Button showFileChooserButton = new Button("Datei auswählen");
        showFileChooserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleFileChooserButtonClick();
            }
        });

        mainPane.add(showFileChooserButton, 1, 1);
    }

    private void handleFileChooserButtonClick() {
        selectedFile = getFileFromFileChooser();
        if (selectedFile != null) {
            filePathLabel.setText("Pfad: " + selectedFile.getAbsolutePath());
        }
    }

    private void setupFileChooserLabel() {
        filePathLabel = new Label("Pfad: ");
        mainPane.add(filePathLabel, 2, 1);
    }

    private File getFileFromFileChooser() {
        fileChooser.setTitle("Datei öffnen");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(sldFilter);

        File file = fileChooser.showOpenDialog(getScene().getWindow());
        return file;
    }
}
