package app.model;

public class LineSymbolizer implements Symbolizer {

    private String stroke, strokeWidth, strokeDashArray, perpendicularOffset;

    public LineSymbolizer(String line){
        String trim = line.substring("Line(".length(), line.length() - 1);
        String[] attributes = trim.split(",");

        stroke = attributes[0];
        strokeWidth = attributes[1];
        strokeDashArray = attributes[2];
        perpendicularOffset = attributes[3];
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

    public String getPerpendicularOffset() {
        return perpendicularOffset;
    }

    public void setPerpendicularOffset(String perpendicularOffset) {
        this.perpendicularOffset = perpendicularOffset;
    }
}
