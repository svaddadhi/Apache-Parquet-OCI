package com.mycompany.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String csvPath = "/Users/vvaddadhi/Desktop/Apache-Parquet-OCI/my-app/SalesJan2009.csv";
        String line = "";
        String commaSplit = ",";


        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))){
            while ((line = br.readLine()) != null) {
                String[] city = line.split(commaSplit);

                System.out.println("City = " + city[4]);
            }
        } catch (IOException e) {
            e.printStackTrace();
    }
    }
}
