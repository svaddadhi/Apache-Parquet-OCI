package library.service;

import java.lang.ProcessBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Shell {
    public static boolean exe(String str) {
        System.out.println(str);
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", str);

        Process process = null;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                    output.append(line + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        int exitVal = 0;
        try {
            exitVal = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (exitVal == 0) {
                System.out.print(output);
                return true;
            } else {
                System.out.println("error: " + exitVal);
                return false;
            }
    }
}
