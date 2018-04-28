package app;

import app.pane.EditorPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditorApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        EditorPane editorPane = new EditorPane();

        primaryStage.setTitle("SLDLight Editor");
        Scene scene = new Scene(editorPane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
