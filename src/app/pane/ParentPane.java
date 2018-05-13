package app.pane;

import javafx.scene.layout.BorderPane;

public class ParentPane extends BorderPane {

    public ParentPane() {
        super();

        PreviewPane previewPane = new PreviewPane();
        EditorPane editorPane = new EditorPane(previewPane);

        setCenter(editorPane);
        setRight(previewPane);

    }
}
