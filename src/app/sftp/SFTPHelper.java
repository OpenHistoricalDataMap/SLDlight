package app.sftp;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.userauth.UserAuthException;
import net.schmizz.sshj.xfer.FileSystemFile;

import java.io.File;
import java.io.IOException;

import static app.sftp.SSHConstants.PASSWORD;
import static app.sftp.SSHConstants.USERNAME;

public class SFTPHelper {

    private static final String LOCAL_DIRECTORY = System.getProperty("user.home") + File.separator + "Downloads/";

    public static void main(String[] args) {
        getFileFromOHMServer(Workspace.TEST, "admin_boundary_4_teilstaaten.sld");

        File file = new File(LOCAL_DIRECTORY + "admin_boundary_test.sld");
        uploadFileToOHMServer(Workspace.TEST, file);
    }

    public static void getFileFromOHMServer(Workspace workspace, String destinationFileName) {
        final SSHClient ssh = new SSHClient();
        if (tryConnectToServer(ssh)) {
            if (tryAuthentication(ssh)) {
                final SFTPClient sftp = tryGetSFTPClient(ssh);

                if (sftp != null) {
                    tryDownloadFileFromSFTPClient(sftp, workspace.getPath() + destinationFileName);
                    tryCloseSFTPClient(sftp);
                }
            }
        }
        tryDisconnectSSHClient(ssh);

    }

    public static void uploadFileToOHMServer(Workspace workspace, File localFile) {
        final SSHClient ssh = new SSHClient();
        if (tryConnectToServer(ssh)) {
            if (tryAuthentication(ssh)) {
                final SFTPClient sftp = tryGetSFTPClient(ssh);

                if (sftp != null) {
                    tryUploadFileWithSFTPClient(sftp, localFile.getAbsolutePath(), workspace.getPath() + localFile.getName());
                    tryCloseSFTPClient(sftp);
                }
            }
        }
        tryDisconnectSSHClient(ssh);

    }

    private static void tryUploadFileWithSFTPClient(SFTPClient sftpClient, String localPath, String destinationPath) {
        try {
            sftpClient.put(new FileSystemFile(localPath), destinationPath);
            System.out.println("Upload successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean tryConnectToServer(SSHClient ssh) {
        try {
            ssh.loadKnownHosts();
            ssh.connect("ohm.f4.htw-berlin.de");
            return true;
        } catch (IOException e) {
            System.out.println("Can not connect to server!");
            return false;
        }
    }

    private static boolean tryAuthentication(SSHClient ssh) {
        try {
            ssh.authPassword(USERNAME, PASSWORD);
            return true;
        } catch (UserAuthException e) {
            e.printStackTrace();
            System.out.println("User and password rejected!");
            return false;
        } catch (TransportException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static SFTPClient tryGetSFTPClient(SSHClient ssh) {
        try {
            return ssh.newSFTPClient();
        } catch (IOException e) {
            System.out.println("Can not create SFTPClient");
            return null;
        }
    }

    private static void tryDownloadFileFromSFTPClient(SFTPClient sftpClient, String path) {
        try {
            sftpClient.get(path, new FileSystemFile(LOCAL_DIRECTORY));
            System.out.println("Download erfolgreich");
        } catch (IOException e) {
            System.out.println("Can not download File");
        }
    }

    private static void tryCloseSFTPClient(SFTPClient sftpClient) {
        try {
            sftpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tryDisconnectSSHClient(SSHClient sshClient) {
        try {
            sshClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
