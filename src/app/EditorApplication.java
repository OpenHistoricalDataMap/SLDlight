package app;

import app.pane.ParentPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditorApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        ParentPane pane = new ParentPane();

        primaryStage.setTitle("SLDLight Editor");
        Scene scene = new Scene(pane, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
