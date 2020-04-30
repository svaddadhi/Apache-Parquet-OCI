package library;

public interface ParquetTransform {
    void filterByColumns(Parquet parquet);
    void filterByRows(Parquet parquet);
    void convertToCsv(String src, String tar);
}
