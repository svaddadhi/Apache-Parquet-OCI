import java.io.File;
public class main {
    public static void main(String[] args) throws Exception {
        String namespaceName = "axvsvpirtkel";
        String bucketName ="bucket-20200129-1839";
        String objectName = "example2.csv";
        String body = "/home/david/Descargas/example2.csv";
        String saveFilePath = "/home/david/Descargas/prueba2.csv";

        UploadObject upload=new UploadObject(namespaceName, bucketName, objectName, body);
        upload.upload();

        DownloadObject download=new DownloadObject(namespaceName,bucketName,objectName,saveFilePath);
        download.download();






    }



}
