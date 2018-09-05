package app.io;

import app.model.Filter;
import app.model.NamedLayer;
import app.model.Rule;
import app.model.Zoom;
import app.model.symbolizer.LineSymbolizer;
import app.model.symbolizer.PointSymbolizer;
import app.model.symbolizer.PolygonSymbolizer;
import app.model.symbolizer.TextSymbolizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MyFileReader {

    public static final String ZOOM_REGEX = "\\d+-\\d+";
    public static final String POLYGON_REGEX = "Polygon([\\d|\\D]*)";
    public static final String POINT_REGEX = "Point([\\d|\\D]*)";
    public static final String LINE_REGEX = "Line([\\d|\\D]*)";
    public static final String TEXT_REGEX = "Text([\\d|\\D]*)";
    public static final String NAME_REGEX = "[\\d|\\D]+";

    private static List<String> readFileToList(String fileName) throws Exception {
        String line = null;
        List<String> records = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        while ((line = bufferedReader.readLine()) != null) {
            records.add(line);
        }

        bufferedReader.close();
        return records;
    }

    public static NamedLayer parseNamedLayerFromSLDLight(String fileName) {
        List<String> lines = null;
        try {
            lines = readFileToList(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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
