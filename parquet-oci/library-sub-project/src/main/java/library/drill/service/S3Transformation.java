package library.drill.service;

import library.service.Shell;

import java.io.File;
import java.io.IOException;

import static library.service.util.abort;

public class S3Transformation {
    String localS3Bucket, remoteS3Bucket, fileName;

    /***
     * Initializing S3 bucket transition instance
     *
     * @param local     char *      S3 bucket mounting point on local machine
     * @param remote    char *      S3 bucket mounting point on the remote machine
     * @param name      char *      The path to file from the mounting point
     * @throws Abort
     */
    public S3Transformation(String local, String remote, String name) throws Abort {
        if (local.charAt(local.length() - 1) == '/' || remote.charAt(remote.length() - 1) == '/')
            abort("S3 bucket address must not end with '/'");
        if (name.charAt(0) == '/')
            abort("File name or address must not start with a '/'");
        this.localS3Bucket = local;
        this.remoteS3Bucket = remote;
        this.fileName = name;
    }

    public String makeLocalAdd() {
        /*
        Equavlent to:
        char ret = calloc(512, sizeof(char));
        memset(ret, 0, 512);
        sprintf(ret, "%s%s%s", s3->localS3Bucket, "/", s3->fileName);
        return ret;
         */
        return String.format("%s%s%s", this.localS3Bucket, File.separator, this.fileName);
    }

    public String makeRemoteAdd() {
        return String.format("%s%s%s", this.remoteS3Bucket, File.separator, this.fileName);
    }

    /***
     * Perform the S3 transmission through *nix command
     * @param src       char *      Source file URI
     * @return          bool
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean execute(String src) throws IOException, InterruptedException {
        return Shell.exe(String.format("cp %s %s", src, this.makeLocalAdd()));
    }
}
