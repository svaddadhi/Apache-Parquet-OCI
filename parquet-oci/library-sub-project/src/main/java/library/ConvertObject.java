package library;

import library.p2c.Conv2Csv;
import library.c2p.Conv2Par;

public class ConvertObject {

    public ConverterObject(String src, String tar) {
        new Conv2Par(src, tar).init().conv().close();
    }

    public ConverterObject(String src, String tar, int len) {
        new Conv2Csv(src, tar, len).init().conv().close();
    }
}
