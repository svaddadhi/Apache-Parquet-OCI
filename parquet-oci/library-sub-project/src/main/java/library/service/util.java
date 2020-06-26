package library.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class util {

    /***
     * Segmentation Fault
     * @param err   char *  Error Code
     * @throws      SegFault
     * @return      void
     */
    public static void segFault throws SegFault (String err) {
        throw new SegFault();
    }

    /***
     * Abort
     * @param err   char *  Error message
     * @throws      Abort
     * @return      void
     */
    public static void abort throws Abort (String err) {
        throw new Abort(err);
    }

    /***
     * Attempts to write to destinated file. If not exist, create the directory or file, or both.
     * @param dir   char *  Directory to write to
     * @param fname char *  File name to file to
     * @param cont  char *  content to write to
     * @throws      Abort
     * @return      void
     */
    public static void writeFile throws Abort (String dir, String fname, String cont) {
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

    /***
     * Attempts to make new directory
     * @param uri   char *  directory to make
     * @return      void
     */
    public static void mkdir (String uri) {
        File f = new File (uri);
        if (! f.exists()) f.mkdir();
    }
}
