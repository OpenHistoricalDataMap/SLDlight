package app.model;

import app.model.symbolizer.Symbolizer;

import java.util.ArrayList;
import java.util.List;

public class Rule implements SLDObject {

    private List<Symbolizer> symbolizers = new ArrayList<>();
    private String name;

    private Zoom zoom;
    private Filter filter;

    public Rule(String ruleName) {
        name = ruleName;
    }

    public String toSLD() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("      <sld:Rule>\n");
        stringBuilder.append("       <sld:Name>" + getName() + "</sld:Name>\n");

        if (filter != null)
            stringBuilder.append(filter.toSLD());
        if (zoom != null)
            stringBuilder.append(zoom.toSLD());

        for (Symbolizer symbolizer : symbolizers) {
            stringBuilder.append(symbolizer.toSLD());
        }

        return stringBuilder.toString();
    }

    public String toSLDLight() {
        StringBuilder stringBuilder = new StringBuilder();
        if (zoom != null)
            stringBuilder.append(zoom.toSLDLight() + "\n");
        if (filter.getPropertyEqualTo() != null && filter.getPropertyEqualTo().equals("") == false)
            stringBuilder.append(filter.toSLDLight() + "\n");

        for (Symbolizer symbolizer : symbolizers) {
            stringBuilder.append(symbolizer.toSLDLight() + "\n");
        }

        return stringBuilder.toString();
    }

    public void addSymbolizer(Symbolizer symbolizer) {
        symbolizers.add(symbolizer);
    }

    public Zoom getZoom() {
        return zoom;
    }

    public void setZoom(Zoom zoom) {
        this.zoom = zoom;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Symbolizer> getSymbolizers() {
        return symbolizers;
    }
}
