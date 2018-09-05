package app.sftp;

import java.util.HashSet;
import java.util.Set;

public enum Workspace {
    DEFAULT("ohdm"), TEST("ohdm_t"), PRODUCTION("ohdm_p");

    private static final String PATH_TO_WORKSPACES = "/var/lib/tomcat7/webapps/geoserver/data/workspaces/";
    private String path;

    Workspace(String pathEnding) {
        path = PATH_TO_WORKSPACES + pathEnding + "/styles/";
    }

    public static Set<Workspace> getWorkspaces() {
        Set<Workspace> set = new HashSet<>();
        set.add(Workspace.DEFAULT);
        set.add(Workspace.TEST);
        set.add(Workspace.PRODUCTION);
        return set;
    }

    public String getPath() {
        return path;
    }
}
