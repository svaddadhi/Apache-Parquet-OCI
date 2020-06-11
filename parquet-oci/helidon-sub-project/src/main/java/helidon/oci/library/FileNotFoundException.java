package helidon.oci.library;

public class FileNotFoundException extends RuntimeException{
    /***
     * Returns custom bad request string
     * @param fileName
     */
    public FileNotFoundException(String fileName) {
        super("Bad Request: " + fileName);
    }
}
