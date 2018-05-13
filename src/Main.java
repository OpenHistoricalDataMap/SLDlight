import app.EditorApplication;
import app.io.SLDLightFileReader;
import app.model.NamedLayer;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Application.launch(EditorApplication.class, args);

        NamedLayer layer = new SLDLightFileReader("example.txt").parseNamedLayerFromSLDLight();
        System.out.println(layer.toSLD());
    }
}
