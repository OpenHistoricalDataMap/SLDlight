package app.model.symbolizer;

public class PointSymbolizer implements Symbolizer {

    private String wellKnownName, fill, size;

    public PointSymbolizer() {
    }

    public PointSymbolizer(String line) {
        String trim = line.substring("Point(".length(), line.length() - 1);
        String[] attributes = trim.split(",");

        wellKnownName = attributes[0];
        fill = attributes[1];
        size = attributes[2];
    }


    public String toSLD() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("       <sld:PointSymbolizer>\n");
        stringBuilder.append("         <sld:Graphic>\n");
        stringBuilder.append("          <sld:Mark>\n");
        stringBuilder.append("            <sld:WellKnownName>" + getWellKnownName() + "</sld:WellKnownName>\n");
        stringBuilder.append("            <sld:Fill>\n");
        stringBuilder.append("             <sld:CssParameter name=\"fill\">" + getFill() + "</sld:CssParameter>\n");
        stringBuilder.append("            </sld:Fill>\n");
        stringBuilder.append("           </sld:Mark>\n");
        stringBuilder.append("          <sld:Size>" + getSize() + "</sld:Size>\n");
        stringBuilder.append("         </sld:Graphic>\n");
        stringBuilder.append("        </sld:PointSymbolizer>\n");

        return stringBuilder.toString();
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
