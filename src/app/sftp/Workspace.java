package app.sftp;

public enum Workspace {
    DEFAULT("ohdm"), TEST("ohdm_t"), PRODUCTION("ohdm_p"), INTEGRATION("ohdm_i");

    private static final String PATH_TO_WORKSPACES = "/var/lib/tomcat7/webapps/geoserver/data/workspaces/";
    private String path;

    Workspace(String pathEnding) {
        path = PATH_TO_WORKSPACES + pathEnding + "/styles/";
    }

    public String getPath() {
        return path;
    }
}
