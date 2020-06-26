package library.service;

import java.lang.ProcessBuilder;
import java.io.IOException;

public class Shell {

    /***
     * Execute *nix command
     * @param str   char *  Command to be executed
     * @return      bool
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean exe(String str) throws IOException, InterruptedException {
        return new ProcessBuilder().command("bash", "-c", str).start().waitFor() == 0;
    }
}
