package app.model;

public class PointSymbolizer implements Symbolizer {

    private String wellKnownName, fill, size;

    public PointSymbolizer(String line){
        String trim = line.substring("Point(".length(), line.length() - 1);
        String[] attributes = trim.split(",");

        wellKnownName = attributes[0];
        fill = attributes[1];
        size = attributes[2];
    }

    public String getWellKnownName() {
        return wellKnownName;
    }

    public void setWellKnownName(String wellKnownName) {
        this.wellKnownName = wellKnownName;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
