package app.pane;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PreviewPane extends ScrollPane {

    private Text sldPreviewText;

    public PreviewPane() {
        super();

        GridPane gridPane = new GridPane();
        setContent(gridPane);

        Text headline = new Text();
        headline.setFont(new Font(20));
        headline.setText("SLD Preview");

        sldPreviewText = new Text();
        sldPreviewText.setFont(new Font(12));
        sldPreviewText.setWrappingWidth(500);
        sldPreviewText.setText("The quick brown fox jumps over the lazy dog");

        gridPane.add(headline, 0, 0);
        gridPane.add(sldPreviewText, 0, 1);
    }

    public void setSldPreviewText(String text) {
        sldPreviewText.setText(text);
    }
}
