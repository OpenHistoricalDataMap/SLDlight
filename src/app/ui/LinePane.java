package app.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class LinePane extends GridPane {

    public LinePane() {
        setPadding(new Insets(20, 20, 20, 20));
        setHgap(5);
        setVgap(5);

        Label titleLabel = new Label("Linie");
        titleLabel.setFont(new Font(15));

        Label strokeLabel = new Label("Strichfarbe");
        SmallTextField strokeText = new SmallTextField("#000000");

        Label strokeWidthLabel = new Label("Dicke des Striches");
        SmallTextField strokeWidthText = new SmallTextField("1.0");

        Label strokeDashLabel = new Label("Strich-Muster");
        SmallTextField strokeDashText = new SmallTextField("5 2");

        Label offsetLabel = new Label("Senkrechte Verschiebung");
        SmallTextField offsetText = new SmallTextField("0");

        add(titleLabel, 0, 0);
        add(strokeLabel, 1, 1);
        add(strokeText, 2, 1);
        add(strokeWidthLabel, 3, 1);
        add(strokeWidthText, 4, 1);
        add(strokeDashLabel, 1, 2);
        add(strokeDashText, 2, 2);
        add(offsetLabel, 3, 2);
        add(offsetText, 4, 2);
    }
}
