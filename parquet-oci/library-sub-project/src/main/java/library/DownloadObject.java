package library;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.responses.GetObjectResponse;


import java.io.InputStream;
import java.io.FileOutputStream;

public class DownloadObject {

    String namespaceName;
    String bucketName ;
    String objectName;
    String saveFilePath;

    public DownloadObject(String namespaceName, String bucketName, String objectName, String saveFilePath){
        this.bucketName = bucketName;
        this.namespaceName = namespaceName;
        this.objectName = objectName;
        this.saveFilePath = saveFilePath;
    }

    public void download() throws Exception {
        // TODO: make config file path and profile configurable
        String configurationFilePath = "~/.oci/config";
        String profile = "DEFAULT";

        AuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configurationFilePath, profile);

        ObjectStorage client = new ObjectStorageClient(provider);

        // TODO: make region configurable
        client.setRegion(Region.US_PHOENIX_1);

        // fetch the object just uploaded
        GetObjectResponse getResponse =
                client.getObject(
                        GetObjectRequest.builder()
                                .namespaceName(namespaceName)
                                .bucketName(bucketName)
                                .objectName(objectName)
                                .build());

        // opens input stream from the HTTP connection
        InputStream inputStream = getResponse.getInputStream();


        // opens an output stream to save into file
        FileOutputStream outputStream = new FileOutputStream(saveFilePath);

        int bytesRead = -1;
        byte[] buffer = new byte[4096];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();

        // stream contents should match the file uploaded
        try (final InputStream fileStream = getResponse.getInputStream()) {
            // use fileStream
        } // try-with-resources automatically closes fileStream
    }
}
