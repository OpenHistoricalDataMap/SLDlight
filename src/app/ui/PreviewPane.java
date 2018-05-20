package app.ui;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PreviewPane extends ScrollPane {

    private Text sldPreviewText, sldLightPreviewText;

    public PreviewPane() {
        super();

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        VBox vBox = new VBox();
        setContent(vBox);


        GridPane sldLightGrid = new GridPane();
        Text headline = new Text();
        headline.setFont(new Font(20));
        headline.setText("SLD Light Preview (Copy to clipboard by clicking on text)");

        sldLightPreviewText = new Text();
        sldLightPreviewText.setFont(new Font(12));
        sldLightPreviewText.setWrappingWidth(500);
        sldLightPreviewText.setText("The quick brown fox jumps over the lazy dog");
        sldLightPreviewText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                content.putString(sldLightPreviewText.getText());
                clipboard.setContent(content);
            }
        });

        sldLightGrid.add(headline, 0, 0);
        sldLightGrid.add(sldLightPreviewText, 0, 1);

        GridPane sldGrid = new GridPane();
        headline = new Text();
        headline.setFont(new Font(20));
        headline.setText("SLD Preview (Copy to clipboard by clicking on text)");

        sldPreviewText = new Text();
        sldPreviewText.setFont(new Font(12));
        sldPreviewText.setWrappingWidth(500);
        sldPreviewText.setText("The quick brown fox jumps over the lazy dog");
        sldPreviewText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                content.putString(sldPreviewText.getText());
                clipboard.setContent(content);
            }
        });

        sldGrid.add(headline, 0, 0);
        sldGrid.add(sldPreviewText, 0, 1);


        vBox.getChildren().add(sldLightGrid);
        vBox.getChildren().add(sldGrid);

    }

    public void showSLDLightPreviewText(String text) {
        sldLightPreviewText.setText(text);
    }

    public void showSLDPreviewText(String text) {
        sldPreviewText.setText(text);
    }
}
