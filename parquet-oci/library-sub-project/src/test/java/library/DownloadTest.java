package library;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Parameterized.class)
public class DownloadTest {
    private String nameSpace;
    private String fileName;
    private String bucket;
    private String filePath;
    private OciObject object;
    private Boolean expectedResults;

    @Before
    public void initialize() {
        object = new OciObject();
    }

    public DownloadTest(String nameSpace, String bucket, String fileName, String filePath, Boolean expectedResults) {
        this.expectedResults = expectedResults;
        this.nameSpace = nameSpace;
        this.fileName = fileName;
        this.bucket = bucket;
        this.filePath = filePath;
    }

    @Parameterized.Parameters
    public static Collection fillData() {
        return Arrays.asList(new Object[][] {
                {"axovcbqne66q", "sample-bucket", "file_to_convert.csv", "/home/phvle/file_to_convert.csv", true},
                {"axovcbqne66q", "sample-bucket", "sample2.csv", "/home/phvle/sample2.csv", true}
        });
    }

    @Test
    public void downloadTest() throws Exception {
        System.out.println("File to Download: " + filePath);
        boolean results = object.download(this.nameSpace, this.bucket, this.fileName, this.filePath);
        assertEquals(expectedResults, results);
    }
}
