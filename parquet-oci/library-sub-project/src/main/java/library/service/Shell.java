package library.service;

import java.lang.ProcessBuilder;
import java.io.IOException;

public class Shell {
    public static boolean exe(String str) throws IOException, InterruptedException {
        return new ProcessBuilder().command("bash", "-c", str).start().waitFor() == 0;
    }
}
