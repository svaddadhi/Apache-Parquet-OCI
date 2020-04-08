package helidon.oci.library;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException(String fileName) {
        super("Bad Request: " + fileName);
    }
}
