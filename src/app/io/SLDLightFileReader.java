package app.io;

import app.model.LineSymbolizer;
import app.model.PointSymbolizer;
import app.model.PolygonSymbolizer;
import app.model.TextSymbolizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SLDLightFileReader {

    public static final String ZOOM_REGEX = "\\d+-\\d+";
    public static final String POLYGON_REGEX = "Polygon([\\d|\\D]*)";
    public static final String POINT_REGEX = "Point([\\d|\\D]*)";
    public static final String LINE_REGEX = "Line([\\d|\\D]*)";
    public static final String TEXT_REGEX = "Text([\\d|\\D]*)";
    public static final String NAME_REGEX = "[\\d|\\D]+";

    private StringBuilder output;
    private List<String> lines;

    public SLDLightFileReader(String fileName) {
        output = new StringBuilder();
        try {
            lines = readFileToList(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> readFileToList(String filename) throws Exception {
        String line = null;
        List<String> records = new ArrayList<String>();

        // wrap a BufferedReader around FileReader
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

        // use the readLine method of the BufferedReader to read one line at a time.
        // the readLine method returns null when there is nothing else to read.
        while ((line = bufferedReader.readLine()) != null) {
            records.add(line);
        }

        // close the BufferedReader when we're done
        bufferedReader.close();
        return records;
    }

    public String parseLinesToSLD() {
        StringBuilder stringBuilder = new StringBuilder();
        addHeaderTo(stringBuilder);

        String layername = ""; // used to name rules
        int ruleCount = 1; // used to name rules

        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                // first line is always layername
                layername = lines.get(i).trim();
                addLayernameTo(layername, stringBuilder);
                continue;
            } else {
                if (lines.get(i).matches(ZOOM_REGEX)) {
                    if (ruleCount > 1) {
                        addRuleCloserTo(stringBuilder);
                    }
                    addRuleTo(layername + "_rule_" + ruleCount, stringBuilder);
                    ruleCount++;
                    addZoomTo(lines.get(i).trim(), stringBuilder);
                    continue;
                } else if (lines.get(i).matches(POLYGON_REGEX)) {
                    PolygonSymbolizer polygon = new PolygonSymbolizer(lines.get(i).trim());
                    addPolygonTo(polygon, stringBuilder);
                } else if (lines.get(i).matches(POINT_REGEX)) {
                    PointSymbolizer point = new PointSymbolizer(lines.get(i).trim());
                    addPointTo(point, stringBuilder);
                } else if (lines.get(i).matches(LINE_REGEX)) {
                    LineSymbolizer line = new LineSymbolizer(lines.get(i).trim());
                    addLineTo(line, stringBuilder);
                } else if (lines.get(i).matches(TEXT_REGEX)) {
                    TextSymbolizer text = new TextSymbolizer(lines.get(i).trim());
                    addTextTo(text, stringBuilder);
                } else if (lines.get(i).matches(NAME_REGEX)) {
                    addFilterTo(lines.get(i).trim(), stringBuilder);
                }
            }
        }

        addFooterTo(stringBuilder);

        return stringBuilder.toString();
    }

    private void addRuleCloserTo(StringBuilder stringBuilder) {
        stringBuilder.append("      </sld:Rule>\n");
    }

    private void addHeaderTo(StringBuilder stringBuilder) {
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuilder.append("<sld:StyledLayerDescriptor xmlns=\"http://www.opengis.net/sld\" xmlns:sld=\"http://www.opengis.net/sld\" " +
                "xmlns:gml=\"http://www.opengis.net/gml\" xmlns:ogc=\"http://www.opengis.net/ogc\" version=\"1.0.0\">\n");
    }

    private void addRuleTo(String ruleName, StringBuilder stringBuilder) {
        stringBuilder.append("      <sld:Rule>\n");
        stringBuilder.append("       <sld:Name>" + ruleName + "</sld:Name>\n");
    }

    private void addFilterTo(String literal, StringBuilder stringBuilder) {
        stringBuilder.append("        <ogc:Filter>\n");
        stringBuilder.append("         <ogc:PropertyIsEqualTo>\n");
        stringBuilder.append("          <ogc:PropertyName>subclassname</ogc:PropertyName>\n");
        stringBuilder.append("          <ogc:Literal>" + literal + "</ogc:Literal>\n");
        stringBuilder.append("         </ogc:PropertyIsEqualTo>\n");
        stringBuilder.append("        </ogc:Filter>\n");
    }

    private void addTextTo(TextSymbolizer text, StringBuilder stringBuilder) {
        stringBuilder.append(text.toSLD());
    }

    private void addLineTo(LineSymbolizer line, StringBuilder stringBuilder) {
        stringBuilder.append(line.toSLD());
    }

    private void addPointTo(PointSymbolizer point, StringBuilder stringBuilder) {
        stringBuilder.append(point.toSLD());
    }

    private void addPolygonTo(PolygonSymbolizer polygon, StringBuilder stringBuilder) {
        stringBuilder.append(polygon.toSLD());
    }


    private void addLayernameTo(String layerName, StringBuilder stringBuilder) {
        stringBuilder.append(" <sld:NamedLayer>\n");
        stringBuilder.append("  <sld:Name>" + layerName + "</sld:Name>\n");
        stringBuilder.append("   <sld:UserStyle>\n");
        stringBuilder.append("    <sld:Name>Default Styler</sld:Name>\n");
        stringBuilder.append("    <sld:FeatureTypeStyle>\n");
        stringBuilder.append("     <sld:Name>name</sld:Name>\n");

    }

    private void addZoomTo(String zoom, StringBuilder stringBuilder) {
        String[] splitString = zoom.split("-");
        String minScale = getScaleDenominatorForZoomLevel(splitString[0]);
        String maxScale = getScaleDenominatorForZoomLevel(splitString[1]);
        stringBuilder.append("       <sld:MinScaleDenominator>" + minScale + "</sld:MinScaleDenominator>\n");
        stringBuilder.append("       <sld:MaxScaleDenominator>" + maxScale + "</sld:MaxScaleDenominator>\n");

    }

    private void addFooterTo(StringBuilder stringBuilder) {
        addRuleCloserTo(stringBuilder);
        stringBuilder.append("     </sld:FeatureTypeStyle>\n");
        stringBuilder.append("    </sld:UserStyle>\n");
        stringBuilder.append("  </sld:NamedLayer>\n");
        stringBuilder.append("</sld:StyledLayerDescriptor>\n");
    }


    private String getScaleDenominatorForZoomLevel(String s) {
        // TODO map config
        if (s.equals("15")) {
            return "12000";
        } else if (s.equals("16")) {
            return "8000";
        }

        return "";
    }

}
