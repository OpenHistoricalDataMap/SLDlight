import app.EditorApplication;
import app.io.SLDLightFileReader;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Application.launch(EditorApplication.class, args);

        String result = new SLDLightFileReader("example.txt").parseLinesToSLD();
        System.out.println(result);
    }
}
