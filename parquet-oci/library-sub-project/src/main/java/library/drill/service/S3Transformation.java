package library.drill.service;

import library.service.Shell;

import java.io.File;
import java.io.IOException;

import static library.service.util.abort;

public class S3Transformation {
    String localS3Bucket, remoteS3Bucket, fileName;

    public S3Transformation(String local, String remote, String name) {
        if (local.charAt(local.length() - 1) == '/' || remote.charAt(remote.length() - 1) == '/')
            abort("S3 bucket address must not end with '/'");
        if (name.charAt(0) == '/')
            abort("File name or address must not start with a '/'");
        this.localS3Bucket = local;
        this.remoteS3Bucket = remote;
        this.fileName = name;
    }

    public String makeLocalAdd() {
        return String.format("%s%s%s", this.localS3Bucket, File.separator, this.fileName);
    }

    public String makeRemoteAdd() {
        return String.format("%s%s%s", this.remoteS3Bucket, File.separator, this.fileName);
    }

    public boolean execute(String src) throws IOException, InterruptedException {
        System.out.println(String.format("cp %s %s", src, this.makeLocalAdd()));
        return Shell.exe(String.format("cp %s %s", src, this.makeLocalAdd()));
    }
}
