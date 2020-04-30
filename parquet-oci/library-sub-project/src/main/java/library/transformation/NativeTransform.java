package library.transformation;

import library.c2p.Conv2Par;
import library.p2c.Conv2Csv;

import java.sql.SQLException;

public class NativeTransform implements ParquetTransform {
    String src;
    String dest;
    int len;

    public NativeTransform(String src, String dest) {
        this.src = src;
        this.dest = dest;
    }

    public NativeTransform(String src, String dest, int len) {
        this.src = src;
        this.dest = dest;
        this.len = len;
    }

    @Override
    public void filterByColumns() {

    }

    @Override
    public void filterByRows() {

    }

    @Override
    public void convertToParquet() {
        new Conv2Par(src, dest).init().conv().close();
    }

    @Override
    public void convertToCSV(){ new Conv2Csv(src, dest, len).init().conv().close(); };
}
