package helidon.oci.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DownloadProvider {
    String file;
    String fileLocation;
    int bucket;
    int objectStore;

    public DownloadProvider(String file, String fileLocation, int bucket, int objectStore) {
        this.file = file;
        this.fileLocation = fileLocation;
        this.bucket = bucket;
        this.objectStore = objectStore;
    }

    @JsonProperty("file")
    public String getFile() {
        return file;
    }

    @JsonProperty("file")
    public void setFile(String file) {
        this.file = file;
    }

    @JsonProperty("fileLocation")
    public String getFileLocation() {
        return fileLocation;
    }

    @JsonProperty("fileLocation")
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @JsonProperty("bucket")
    public int getBucket() {
        return bucket;
    }

    @JsonProperty("bucket")
    public void setBucket(int bucket) {
        this.bucket = bucket;
    }

    @JsonProperty("objectStore")
    public int getObjectStore() {
        return objectStore;
    }

    @JsonProperty("objectStore")
    public void setObjectStore(int objectStore) {
        this.objectStore = objectStore;
    }
}
