package library.c2p;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import library.service.util;

public class WriteParquet {

    private AVSC avscScheDes;
    private String targetParquet;
    private Schema schema;

    public void exe(String val[]) {
        if (this.avscScheDes == null || this.targetParquet == null || this.schema == null)
            util.segFault("EXINP");
        this.parseSchema();
        writeToParquet(getRecords(this.avscScheDes.title, val, this.schema, null), this.schema, targetParquet);
    }

    public WriteParquet setSrcScheme(AVSC tar) {
        this.avscScheDes = tar;
        return this;
    }

    public WriteParquet setTargetParquet(String str) {
        this.targetParquet = str;
        return this;
    }

    private void parseSchema() {
        Schema.Parser parser = new Schema.Parser();
        this.schema = null;
        try {
            // pass path to schema
            this.schema = parser.parse(ClassLoader.getSystemResourceAsStream(
                    this.avscScheDes.name + ".avsc"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<GenericData.Record> getRecords(String title[], String val[], Schema schema, List<GenericData.Record> recordList) {
        if (recordList == null) recordList = new ArrayList<GenericData.Record>();
        GenericData.Record record = new GenericData.Record(schema);
        for (int i = 0; i < (Math.min(title.length, val.length)); i++) record.put(title[i], val[i]);
        recordList.add(record);

        return recordList;
    }


    private void writeToParquet(List<GenericData.Record> recordList, Schema schema, String tarFile) {
        // Path to Parquet file in HDFS
        Path path = new Path(tarFile);
        ParquetWriter<GenericData.Record> writer = null;
        // Creating ParquetWriter using builder
        try {
            writer = AvroParquetWriter.
                    <GenericData.Record>builder(path)
                    .withRowGroupSize(ParquetWriter.DEFAULT_BLOCK_SIZE)
                    .withPageSize(ParquetWriter.DEFAULT_PAGE_SIZE)
                    .withSchema(schema)
                    .withConf(new Configuration())
                    .withCompressionCodec(CompressionCodecName.SNAPPY)
                    .withValidation(false)
                    .withDictionaryEncoding(false)
                    .build();

            for (GenericData.Record record : recordList) {
                writer.write(record);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}