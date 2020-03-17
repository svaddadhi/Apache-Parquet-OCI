package library;

//import library.p2c.Conv2Csv;
import library.c2p.Conv2Par;

public class ConvertObject {

    public ConvertObject(String src, String tar) {
        new Conv2Par(src, tar).init().conv().close();
    }

//    public ConvertObject(String src, String tar, int len) {
//        new Conv2Csv(src, tar, len).init().conv().close();
//    }

    public static void main (String argv[]) {
        new ConvertObject(System.getProperty("user.home")+ "/sample2.csv",System.getProperty("user.home")+ "/sample2.parquet");
    }
}
