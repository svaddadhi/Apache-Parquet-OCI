package library;


import java.io.File;
import java.io.InputStream;
import java.util.Map;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.GetObjectResponse;
import com.oracle.bmc.objectstorage.transfer.UploadConfiguration;
import com.oracle.bmc.objectstorage.transfer.UploadManager;
import com.oracle.bmc.objectstorage.transfer.UploadManager.UploadRequest;
import com.oracle.bmc.objectstorage.transfer.UploadManager.UploadResponse;


public class UploadObject {
    String namespaceName;
    String bucketName;
    String objectName;
    File body;

    public UploadObject(String namespaceName, String bucketName, String objectName,String body){
        this.namespaceName=namespaceName;
        this.bucketName=bucketName;
        this.objectName=objectName;
        this.body=new File(body);



    }

    public void upload() throws Exception {
        String configurationFilePath = "~/.oci/config";
        String profile = "DEFAULT";

        Map<String, String> metadata = null;
        String contentType = "text/csv";
        String contentEncoding = "UTF-8";
        String contentLanguage = "en-US";

    /*
        String namespaceName = "axvsvpirtkel";
        String bucketName ="bucket-20200129-1839";
        String objectName = "example.csv";
        File body = new File("/home/david/Descargas/example.csv");

     */

        AuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configurationFilePath, profile);

        ObjectStorage client = new ObjectStorageClient(provider);
        client.setRegion(Region.US_PHOENIX_1);

        // configure upload settings as desired
        UploadConfiguration uploadConfiguration =
                UploadConfiguration.builder()
                        .allowMultipartUploads(true)
                        .allowParallelUploads(true)
                        .build();

        UploadManager uploadManager = new UploadManager(client, uploadConfiguration);

        PutObjectRequest request =
                PutObjectRequest.builder()
                        .bucketName(bucketName)
                        .namespaceName(namespaceName)
                        .objectName(objectName)
                        .contentType(contentType)
                        .contentLanguage(contentLanguage)
                        .contentEncoding(contentEncoding)
                        .opcMeta(metadata)
                        .build();

        UploadRequest uploadDetails = null;

        try {
            uploadDetails = UploadRequest.builder(body).allowOverwrite(true).build(request);
        }
        catch(Exception e) {
            throw new Exception("{404}");
        }
        // upload request and print result
        // if multi-part is used, and any part fails, the entire upload fails and will throw BmcException
        UploadResponse response = uploadManager.upload(uploadDetails);
        System.out.println(response);

        // fetch the object just uploaded
        GetObjectResponse getResponse =
                client.getObject(
                        GetObjectRequest.builder()
                                .namespaceName(namespaceName)
                                .bucketName(bucketName)
                                .objectName(objectName)
                                .build());

        // stream contents should match the file uploaded
        try (final InputStream fileStream = getResponse.getInputStream()) {
            // use fileStream
        } // try-with-resources automatically closes fileStream
    }
}