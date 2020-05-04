package library;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void runner(Class name) {
        Result result = JUnitCore.runClasses(name);

        for(Failure failure: result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
    public static void main(String [] args) {
        runner(DownloadTest.class);
        runner(UploadTest.class);
    }
}
