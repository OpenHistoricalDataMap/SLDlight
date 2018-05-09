package app.config;

import java.util.HashMap;
import java.util.Map;

public class PolygonAttributes {

    private String fill, fillOpacity, stroke, strokeWidth, strokeDashArray;

    private Map<String, String> attributesMap = new HashMap<>();

    public PolygonAttributes() {

    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public void setFillOpacity(String fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public void setStrokeWidth(String strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setStrokeDashArray(String strokeDashArray) {
        this.strokeDashArray = strokeDashArray;
    }
}
