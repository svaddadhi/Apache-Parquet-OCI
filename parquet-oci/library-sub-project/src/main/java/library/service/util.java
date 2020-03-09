package library.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class util {
    public static void segFault (String err) {
        System.out.println("Segmentation Fault: " + err);
        System.exit(255);
    }

    public static void abort (String err) {
        System.out.println ("Abort: " + err);
        System.exit(254);
    }

    public static void wrtieFile (String dir, String fname, String cont) {
        try {
            mkdir(dir);
            if (dir.charAt(dir.length() - 1) != '/') dir = dir + "/";
            String uri = dir + fname;
            new File(uri).createNewFile();
            FileWriter fw = new FileWriter(uri);
            fw.write(cont);
            fw.close();
        } catch (IOException e) {
            util.abort("Failed to create file " + dir + fname);
        }
    }

    public static void mkdir (String uri) {
        File f = new File (uri);
        if (! f.exists()) f.mkdir();
    }
}
