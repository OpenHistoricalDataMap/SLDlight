package app.sftp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLForSLDCreator {

    private static final String WORKSPACE_ID_TEST = "WorkspaceInfoImpl-355a8844:15b19d1e2c3:-7ffe";
    private static final String WORKSPACE_ID_DEFAULT = "WorkspaceInfoImpl-355a8844:15b19d1e2c3:-7ff8";
    private static final String WORKSPACE_ID_PRODUCTION = "WorkspaceInfoImpl-355a8844:15b19d1e2c3:-7ffa";

    public static File getXmlForSLDFile(File sldFile, Workspace workspace) {
        return createRelatedXMLFileForOHMServer(sldFile, workspace);
    }

    private static File createRelatedXMLFileForOHMServer(File sldFile, Workspace workspace) {
        try {
            File targetDirectory = new File(System.getProperty("user.dir"));
            File file = new File(targetDirectory + "/" + getFileNameWithoutExtension(sldFile) + ".xml");
            file.deleteOnExit();

            fillFileWithInfo(file, workspace);
            return file;
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
            return null;
        }
    }

    private static void fillFileWithInfo(File xmlFile, Workspace workspace) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFile));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<style>\n");
        stringBuilder.append(getIDForStyle()).append("\n");
        stringBuilder.append("  <name>" + getFileNameWithoutExtension(xmlFile) + "</name>\n" +
                "  <workspace>\n");
        stringBuilder.append(getIDForWorkspace(workspace));
        stringBuilder.append("  </workspace>\n" +
                "  <format>sld</format>\n" +
                "  <languageVersion>\n" +
                "    <version>1.0.0</version>\n" +
                "  </languageVersion>\n");

        stringBuilder.append("  <filename>" + getFileNameWithoutExtension(xmlFile) + ".sld" + "</filename>\n");
        stringBuilder.append("</style>");

        writer.write(stringBuilder.toString());

        writer.close();

    }

    private static String getIDForStyle() {
        return "<id>StyleInfoImpl-4e1160b8:160efbd149b:40ff</id>"; // TODO replace with custom id?
    }

    private static String getIDForWorkspace(Workspace workspace) {
        switch (workspace) {
            case DEFAULT:
                return "    <id>" + WORKSPACE_ID_DEFAULT + "</id>\n";
            case TEST:
                return "    <id>" + WORKSPACE_ID_TEST + "</id>\n";
            case PRODUCTION:
                return "    <id>" + WORKSPACE_ID_PRODUCTION + "</id>\n";
            default:
                return null;
        }
    }

    private static String getFileNameWithoutExtension(File file) {
        return file.getName().replaceFirst("[.][^.]+$", "");
    }
}
