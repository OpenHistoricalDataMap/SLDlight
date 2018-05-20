package app.model;

public class Zoom {

    private String minScaleNumber, maxScaleNumber;
    private String minScaleDenominator, maxScaleDenominator;

    public Zoom(String line) {
        String[] splitString = line.split("-");
        minScaleNumber = splitString[0];
        maxScaleNumber = splitString[1];

        minScaleDenominator = getScaleDenominatorForZoomLevel(minScaleNumber);
        maxScaleDenominator = getScaleDenominatorForZoomLevel(maxScaleNumber);

    }

    public Zoom(int minScale, int maxScale) {
        this.minScaleNumber = Integer.toString(minScale);
        this.maxScaleNumber = Integer.toString(maxScale);

        minScaleDenominator = getScaleDenominatorForZoomLevel(minScaleNumber);
        maxScaleDenominator = getScaleDenominatorForZoomLevel(maxScaleNumber);
    }

    private String getScaleDenominatorForZoomLevel(String string) {
        int level = Integer.parseInt(string);
        String denominator = null;

        switch (level) {
            case 0:
                denominator = null;
                break;
            case 1:
                denominator = "100000";
                break;
            case 2:
                denominator = "80000";
                break;
            case 3:
                denominator = "60000";
                break;
            case 4:
                denominator = "50000";
                break;
            case 5:
                denominator = "40000";
                break;
            case 6:
                denominator = "30000";
                break;
            case 7:
                denominator = "25000";
                break;
            case 8:
                denominator = "22000";
                break;
            case 9:
                denominator = "20000";
                break;
            case 10:
                denominator = "18000";
                break;
            case 11:
                denominator = "16000";
                break;
            case 12:
                denominator = "15000";
                break;
            case 13:
                denominator = "14000";
                break;
            case 14:
                denominator = "13000";
                break;
            case 15:
                denominator = "12000";
                break;
            case 16:
                denominator = "8000";
                break;
            default:
                denominator = "0";
                break;
        }
        return denominator;
    }

    public String toSLD() {
        StringBuilder stringBuilder = new StringBuilder();

        if (minScaleDenominator != null)
            stringBuilder.append("       <sld:MinScaleDenominator>" + minScaleDenominator + "</sld:MinScaleDenominator>\n");
        if (maxScaleDenominator != null)
            stringBuilder.append("       <sld:MaxScaleDenominator>" + maxScaleDenominator + "</sld:MaxScaleDenominator>\n");

        return stringBuilder.toString();
    }

    public String toSLDLight() {
        return minScaleNumber + "-" + maxScaleNumber;
    }

    public String getMinScaleNumber() {
        return minScaleNumber;
    }

    public String getMaxScaleNumber() {
        return maxScaleNumber;
    }
}
