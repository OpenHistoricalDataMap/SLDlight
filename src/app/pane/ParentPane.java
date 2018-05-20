package app.pane;

import app.ui.DragResizer;
import javafx.scene.layout.BorderPane;

public class ParentPane extends BorderPane {

    public ParentPane() {
        super();

        PreviewPane previewPane = new PreviewPane();
        EditorPane editorPane = new EditorPane(previewPane);
        DragResizer.makeResizable(previewPane);

        setCenter(editorPane);
        setRight(previewPane);


    }
}
