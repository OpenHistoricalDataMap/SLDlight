package app.io;

import app.model.*;
import app.model.symbolizer.LineSymbolizer;
import app.model.symbolizer.PointSymbolizer;
import app.model.symbolizer.PolygonSymbolizer;
import app.model.symbolizer.TextSymbolizer;

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

    public NamedLayer parseNamedLayerFromSLDLight() {
        NamedLayer namedLayer = null;
        Rule rule = null;
        int ruleCount = 0; // used to name rules

        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                // first line is always layername
                namedLayer = new NamedLayer(lines.get(i).trim());
                continue;
            } else {
                if (lines.get(i).matches(ZOOM_REGEX)) {
                    rule = new Rule(namedLayer.getName() + "_rule_" + ruleCount);
                    namedLayer.addRule(rule);
                    ruleCount++;
                    rule.setZoom(new Zoom(lines.get(i).trim()));
                } else if (lines.get(i).matches(POLYGON_REGEX)) {
                    PolygonSymbolizer polygon = new PolygonSymbolizer(lines.get(i).trim());
                    rule.addSymbolizer(polygon);
                } else if (lines.get(i).matches(POINT_REGEX)) {
                    PointSymbolizer point = new PointSymbolizer(lines.get(i).trim());
                    rule.addSymbolizer(point);
                } else if (lines.get(i).matches(LINE_REGEX)) {
                    LineSymbolizer line = new LineSymbolizer(lines.get(i).trim());
                    rule.addSymbolizer(line);
                } else if (lines.get(i).matches(TEXT_REGEX)) {
                    TextSymbolizer text = new TextSymbolizer(lines.get(i).trim());
                    rule.addSymbolizer(text);
                } else if (lines.get(i).matches(NAME_REGEX)) {
                    Filter filter = new Filter(lines.get(i).trim());
                    rule.setFilter(filter);
                }
            }
        }


        return namedLayer;
    }


}
