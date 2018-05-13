package app.model;

public class TextSymbolizer implements Symbolizer {

    private String label, fontSize, fontWeight, fill, anchorPointX, anchorPointY, displacementX, displacementY;

    public TextSymbolizer(String line) {
        String trim = line.substring("Line(".length(), line.length() - 1);
        String[] attributes = trim.split(",");

        label = attributes[0];
        fontSize = attributes[1];
        fontWeight = attributes[2];
        fill = attributes[3];
        anchorPointX = attributes[4];
        anchorPointY = attributes[5];
        displacementX = attributes[6];
        displacementY = attributes[7];
    }

    public String toSLD() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("         <sld:TextSymbolizer>\n");
        stringBuilder.append("          <sld:Label>\n");
        stringBuilder.append("             <ogc:PropertyName>" + getLabel() + "</ocg:PropertyName>\n");
        stringBuilder.append("          </sld:Label>\n");
        stringBuilder.append("          <sld:Font>\n");
        stringBuilder.append("           <sld:CssParameter name=\"font-family\">Arial</sld:CssParameter>\n");
        stringBuilder.append("           <sld:CssParameter name=\"font-size\">" + getFontSize() + "</sld:CssParameter>\n");
        stringBuilder.append("           <sld:CssParameter name=\"font-style\">normal</sld:CssParameter>\n");
        stringBuilder.append("           <sld:CssParameter name=\"font-weight\">" + fontWeight + "</sld:CssParameter>\n");
        stringBuilder.append("          </sld:Font>\n");
        stringBuilder.append("          <sld:LabelPlacement>\n");
        stringBuilder.append("           <sld:PointPlacement>\n");
        stringBuilder.append("            <sld:AnchorPoint>\n");
        stringBuilder.append("             <sld:AnchorPointX>" + getAnchorPointX() + "</sld:AnchorPointX>\n");
        stringBuilder.append("             <sld:AnchorPointY>" + getAnchorPointY() + "</sld:AnchorPointY>\n");
        stringBuilder.append("            </sld:AnchorPoint>\n");
        stringBuilder.append("            <sld:Displacement>\n");
        stringBuilder.append("             <sld:DisplacementX>" + getDisplacementX() + "</sld:DisplacementX>\n");
        stringBuilder.append("             <sld:DisplacementY>" + getDisplacementY() + "</sld:DisplacementY>\n");
        stringBuilder.append("            </sld:Displacement>\n");
        stringBuilder.append("           </sld:PointPlacement>\n");
        stringBuilder.append("          </sld:LabelPlacement>\n");
        stringBuilder.append("          <sld:Fill>\n");
        stringBuilder.append("           <sld:CssParameter name=\"fill\">" + fill + "</sld:CssParameter>\n");
        stringBuilder.append("          </sld:Fill>\n");
        stringBuilder.append("         </sld:TextSymbolizer>\n");

        return stringBuilder.toString();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getAnchorPointX() {
        return anchorPointX;
    }

    public void setAnchorPointX(String anchorPointX) {
        this.anchorPointX = anchorPointX;
    }

    public String getAnchorPointY() {
        return anchorPointY;
    }

    public void setAnchorPointY(String anchorPointY) {
        this.anchorPointY = anchorPointY;
    }

    public String getDisplacementX() {
        return displacementX;
    }

    public void setDisplacementX(String displacementX) {
        this.displacementX = displacementX;
    }

    public String getDisplacementY() {
        return displacementY;
    }

    public void setDisplacementY(String displacementY) {
        this.displacementY = displacementY;
    }
}
