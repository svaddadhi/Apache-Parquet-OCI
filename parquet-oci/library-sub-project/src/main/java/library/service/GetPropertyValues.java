package library.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/***
 * Gets property values from config.properties file
 */
public class GetPropertyValues {
    InputStream inputStream;

    public String getPropValue(String property) throws IOException {
        String val = "";

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file " + propFileName + " not found in the classpath");
            }

            val = prop.getProperty(property);
        }
        catch(Exception e) {
            System.out.println("Exception: " + e);
        }
        finally {
            inputStream.close();
        }

        return val;
    }
}
