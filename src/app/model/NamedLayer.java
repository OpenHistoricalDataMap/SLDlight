package app.model;

import java.util.ArrayList;
import java.util.List;

public class NamedLayer {

    private String name;

    private List<Rule> rules = new ArrayList<>();

    public NamedLayer(String name) {
        this.name = name;
    }

    public String toSLD() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuilder.append("<sld:StyledLayerDescriptor xmlns=\"http://www.opengis.net/sld\" xmlns:sld=\"http://www.opengis.net/sld\" " +
                "xmlns:gml=\"http://www.opengis.net/gml\" xmlns:ogc=\"http://www.opengis.net/ogc\" version=\"1.0.0\">\n");

        stringBuilder.append(" <sld:NamedLayer>\n");
        stringBuilder.append("  <sld:Name>" + getName() + "</sld:Name>\n");
        stringBuilder.append("   <sld:UserStyle>\n");
        stringBuilder.append("    <sld:Name>Default Styler</sld:Name>\n");
        stringBuilder.append("    <sld:FeatureTypeStyle>\n");
        stringBuilder.append("     <sld:Name>name</sld:Name>\n");

        for (Rule rule : rules) {
            stringBuilder.append(rule.toSLD());
            stringBuilder.append("      </sld:Rule>\n");
        }

        stringBuilder.append("     </sld:FeatureTypeStyle>\n");
        stringBuilder.append("    </sld:UserStyle>\n");
        stringBuilder.append("  </sld:NamedLayer>\n");
        stringBuilder.append("</sld:StyledLayerDescriptor>\n");

        return stringBuilder.toString();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
