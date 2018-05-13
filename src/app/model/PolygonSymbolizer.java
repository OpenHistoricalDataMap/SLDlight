package app.model;

public class PolygonSymbolizer implements Symbolizer {

    private String fill, fillOpacity, stroke, strokeWidth, strokeDashArray;

    public PolygonSymbolizer(String line){
        String trim = line.substring("Polygon(".length(), line.length() - 1);
        String[] attributes = trim.split(",");

        fill = attributes[0];
        fillOpacity = attributes[1];
        stroke = attributes[2];
        strokeWidth = attributes[3];
        strokeDashArray = attributes[4];
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getFillOpacity() {
        return fillOpacity;
    }

    public void setFillOpacity(String fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(String strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeDashArray() {
        return strokeDashArray;
    }

    public void setStrokeDashArray(String strokeDashArray) {
        this.strokeDashArray = strokeDashArray;
    }
}
