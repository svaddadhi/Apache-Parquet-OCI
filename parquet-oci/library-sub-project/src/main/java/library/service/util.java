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

    public static void wrtieFile (String uri, String cont) {
        try {
            new File(uri).createNewFile();
            FileWriter fw = new FileWriter(uri);
            fw.write(cont);
            fw.close();
        } catch (IOException e) {
            segFault("UTLWF");
        }
    }
}
