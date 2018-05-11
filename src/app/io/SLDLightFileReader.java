package app.io;

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
        int ruleCount = 0; // used to name rules

        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                // first line is always layername
                layername = lines.get(i).trim();
                addLayernameTo(layername, stringBuilder);
                continue;
            } else {
                if (lines.get(i).matches(ZOOM_REGEX)) {
                    addRuleTo(layername + "_rule_" + ruleCount, stringBuilder);
                    ruleCount++;
                    addZoomTo(lines.get(i).trim(), stringBuilder);
                    continue;
                } else if (lines.get(i).matches(POLYGON_REGEX)) {
                    addPolygonTo(lines.get(i).trim(), stringBuilder);
                } else if (lines.get(i).matches(POINT_REGEX)) {
                    addPointTo(lines.get(i).trim(), stringBuilder);
                } else if (lines.get(i).matches(LINE_REGEX)) {
                    addLineTo(lines.get(i).trim(), stringBuilder);
                } else if (lines.get(i).matches(TEXT_REGEX)) {
                    addTextTo(lines.get(i).trim(), stringBuilder);
                } else if (lines.get(i).matches(NAME_REGEX)) {
                    addFilterTo(lines.get(i).trim(), stringBuilder);
                }
            }
        }

        addFooterTo(stringBuilder);

        return stringBuilder.toString();
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

    private void addTextTo(String line, StringBuilder stringBuilder) {
        String attributes = line.substring("Text(".length(), line.length() - 1);
    }

    private void addLineTo(String line, StringBuilder stringBuilder) {
        String attributes = line.substring("Line(".length(), line.length() - 1);
        String[] attributesArray = attributes.split(",");
        if (attributesArray.length < 4) {
            return;
        }

        stringBuilder.append("<sld:LineSymbolizer>\n");
        stringBuilder.append("<sld:Stroke>\n");
        stringBuilder.append("<sld:CssParameter name=\"stroke\">" + attributesArray[0] + "</sld:CssParameter>\n");
        stringBuilder.append("<sld:CssParameter name=\"stroke-width\">" + attributesArray[1] + "</sld:CssParameter>\n");
        stringBuilder.append("<sld:CssParameter name=\"stroke-dasharray\">" + attributesArray[2] + "</sld:CssParameter>\n");
        stringBuilder.append("</sld:stroke>");
        stringBuilder.append("<sld:PerpendicularOffset>" + attributesArray[3] + "</sld:PerpendicularOffset>");
        stringBuilder.append("</sld:PolygonSymbolizer>");
    }

    private void addPointTo(String line, StringBuilder stringBuilder) {
        String attributes = line.substring("Point(".length(), line.length() - 1);
        String[] attributesArray = attributes.split(",");
        if (attributesArray.length < 3) {
            return;
        }

        stringBuilder.append("      <sld:PointSymbolizer>\n");
        stringBuilder.append("        <sld:Graphic>\n");
        stringBuilder.append("         <sld:Mark>\n");
        stringBuilder.append("           <sld:Fill>\n");
        stringBuilder.append("            <sld:CssParameter name=\"fill\">" + attributesArray[0] + "</sld:CssParameter>\n");
        stringBuilder.append("           </sld:Fill>\n");
        stringBuilder.append("          </sld:Mark>\n");
        stringBuilder.append("         <sld:Size>" + attributesArray[2] + "</sld:Size>\n");
        stringBuilder.append("        </sld:Graphic>\n");
        stringBuilder.append("       </sld:PointSymbolizer>\n");
    }

    private void addPolygonTo(String line, StringBuilder stringBuilder) {
        String attributes = line.substring("Polygon(".length(), line.length() - 1);
        String[] attributesArray = attributes.split(",");
        if (attributesArray.length < 5) {
            return;
        }

        stringBuilder.append("        <sld:PolygonSymbolizer>\n");
        stringBuilder.append("<sld:Fill>\n");
        stringBuilder.append("<sld:CssParameter name=\"fill\">" + attributesArray[0] + "</sld:CssParameter>\n");
        stringBuilder.append("<sld:CssParameter name=\"fill-opacity\">" + attributesArray[1] + "</sld:CssParameter>\n");
        stringBuilder.append("</sld:Fill>\n");
        stringBuilder.append("<sld:stroke>\n");
        stringBuilder.append("<sld:CssParameter name=\"stroke\">" + attributesArray[2] + "</sld:CssParameter>\n");
        stringBuilder.append("<sld:CssParameter name=\"stroke-width\">" + attributesArray[3] + "</sld:CssParameter>\n");
        stringBuilder.append("<sld:CssParameter name=\"stroke-dasharray\">" + attributesArray[4] + "</sld:CssParameter>\n");
        stringBuilder.append("</sld:stroke>");
        stringBuilder.append("        </sld:PolygonSymbolizer>");
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
        stringBuilder.append("      </sld:FeatureTypeStyle>\n");
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
