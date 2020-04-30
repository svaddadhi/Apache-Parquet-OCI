package library;

import library.c2p.Conv2Par;

public class NativeTransform implements ParquetTransform {
    @Override
    public void filterByColumns(Parquet parquet) {

    }

    @Override
    public void filterByRows(Parquet parquet) {

    }

    @Override
    public void convertToCsv(String src, String tar) {
        new Conv2Par(src, tar).init().conv().close();
    }
}
