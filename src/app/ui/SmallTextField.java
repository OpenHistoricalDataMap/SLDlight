package app.ui;

import javafx.scene.control.TextField;

public class SmallTextField extends TextField {

    private static final int PREF_WIDTH = 70;
    private static final int PREF_HEIGHT = 20;

    public SmallTextField() {
        setPrefWidth(PREF_WIDTH);
        setPrefHeight(PREF_HEIGHT);
    }

    public SmallTextField(String text) {
        super(text);
        setPrefWidth(PREF_WIDTH);
        setPrefHeight(PREF_HEIGHT);
    }
}
