package helidon.oci.library;

/**
 * Class file for FileNotFoundException
 *
 */
public class FileNotFoundException extends RuntimeException{
    /***
     * Returns custom bad request string
     * @param fileName
     */
    public FileNotFoundException(String fileName) {
        super("Bad Request: " + fileName);
    }
}
