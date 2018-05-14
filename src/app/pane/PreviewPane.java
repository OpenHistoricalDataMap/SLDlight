package app.pane;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PreviewPane extends ScrollPane {

    private Text sldPreviewText, sldLightPreviewText;

    public PreviewPane() {
        super();

        VBox vBox = new VBox();
        setContent(vBox);

        GridPane sldLightGrid = new GridPane();
        Text headline = new Text();
        headline.setFont(new Font(20));
        headline.setText("SLD Light Preview");

        sldLightPreviewText = new Text();
        sldLightPreviewText.setFont(new Font(12));
        sldLightPreviewText.setWrappingWidth(500);
        sldLightPreviewText.setText("The quick brown fox jumps over the lazy dog");

        sldLightGrid.add(headline, 0, 0);
        sldLightGrid.add(sldLightPreviewText, 0, 1);

        GridPane sldGrid = new GridPane();
        headline = new Text();
        headline.setFont(new Font(20));
        headline.setText("SLD Preview");

        sldPreviewText = new Text();
        sldPreviewText.setFont(new Font(12));
        sldPreviewText.setWrappingWidth(500);
        sldPreviewText.setText("The quick brown fox jumps over the lazy dog");

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
