package app.model;

public class Filter {

    private String propertyEqualTo;

    public Filter(String propertyEqualTo) {
        this.propertyEqualTo = propertyEqualTo;
    }

    public String toSLD() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("        <ogc:Filter>\n");
        stringBuilder.append("         <ogc:PropertyIsEqualTo>\n");
        stringBuilder.append("          <ogc:PropertyName>subclassname</ogc:PropertyName>\n");
        stringBuilder.append("          <ogc:Literal>" + getPropertyEqualTo() + "</ogc:Literal>\n");
        stringBuilder.append("         </ogc:PropertyIsEqualTo>\n");
        stringBuilder.append("        </ogc:Filter>\n");


        return stringBuilder.toString();
    }

    public String getPropertyEqualTo() {
        return propertyEqualTo;
    }

    public void setPropertyEqualTo(String propertyEqualTo) {
        this.propertyEqualTo = propertyEqualTo;
    }
}
