package helidon.oci.library;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException(String fileName) {
        super(fileName + " was not found!\n");
    }
}
