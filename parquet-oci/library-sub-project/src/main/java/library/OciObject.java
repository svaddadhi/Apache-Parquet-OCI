package library;

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
import library.service.GetPropertyValues;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

public class OciObject {
    /***
     * Downloads an OCI object
     * @param namespaceName
     * @param bucketName
     * @param objectName
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean download(String namespaceName, String bucketName, String objectName, String filePath) throws Exception {
        // get the property values
        GetPropertyValues propertyObj = new GetPropertyValues();

        String configurationFilePath = propertyObj.getPropValue("configurationFilePath");
        String profile = propertyObj.getPropValue("profile");

        AuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configurationFilePath, profile);

        ObjectStorage client = new ObjectStorageClient(provider);

        // String region = propertyObj.getPropValue("region");
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
        FileOutputStream outputStream = new FileOutputStream(filePath);

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

        return true;
    }

    /***
     * Uploads an OCI object
     * @param namespaceName
     * @param bucketName
     * @param objectName
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean upload(String namespaceName, String bucketName, String objectName, String filePath) throws Exception {
        File file = new File(filePath);

        GetPropertyValues propertyObj = new GetPropertyValues();

        String configurationFilePath = propertyObj.getPropValue("configurationFilePath");
        String profile = propertyObj.getPropValue("profile");;

        Map<String, String> metadata = null;
        String contentType = propertyObj.getPropValue("contentType");
        String contentEncoding = propertyObj.getPropValue("contentEncoding");
        String contentLanguage = propertyObj.getPropValue("contentLanguage");

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

        UploadManager.UploadRequest uploadDetails =
                UploadManager.UploadRequest.builder(file).allowOverwrite(true).build(request);

        // upload request and print result
        // if multi-part is used, and any part fails, the entire upload fails and will throw BmcException
        UploadManager.UploadResponse response = uploadManager.upload(uploadDetails);
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

        return true;
    }
}
