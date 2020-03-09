package library.c2p;

import java.io.*;
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
    private ParquetWriter<GenericData.Record> writer;

    public WriteParquet init () {
        if (this.targetParquet == null) util.segFault("WPINI");
        if (new File (this.targetParquet).exists()) util.abort("Target parquet exists");
        Path path = new Path(this.targetParquet);
        try {
            this.writer = AvroParquetWriter.
                    <GenericData.Record>builder(path)
                    .withRowGroupSize(ParquetWriter.DEFAULT_BLOCK_SIZE)
                    .withPageSize(ParquetWriter.DEFAULT_PAGE_SIZE)
                    .withSchema(schema)
                    .withConf(new Configuration())
                    .withCompressionCodec(CompressionCodecName.SNAPPY)
                    .withValidation(false)
                    .withDictionaryEncoding(false)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            util.abort("Failed to init parquet writer");
        } return this;
    }

    public void exe(String val[]) {
        if (this.avscScheDes == null || this.targetParquet == null || this.schema == null)
            util.segFault("EXINP");
        writeToParquet(getRecords(this.avscScheDes.title, val, this.schema, null), this.schema);
    }

    public WriteParquet setSrcScheme(AVSC tar) {
        this.avscScheDes = tar;
        return this;
    }

    public WriteParquet setTargetParquet(String str) {
        this.targetParquet = str;
        return this;
    }

    public WriteParquet parseSchema() {
        Schema.Parser parser = new Schema.Parser();
        this.schema = null;
        try {
            // pass path to schema
            InputStream is = new FileInputStream(this.avscScheDes.name + ".avsc");
            if (is == null) util.segFault("WPPSN");
            this.schema = parser.parse(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private List<GenericData.Record> getRecords(String title[], String val[], Schema schema, List<GenericData.Record> recordList) {
        if (recordList == null) recordList = new ArrayList<GenericData.Record>();
        GenericData.Record record = new GenericData.Record(schema);
        for (int i = 0; i < (Math.min(title.length, val.length)); i++) record.put(title[i], val[i]);
        recordList.add(record);

        return recordList;
    }


    private void writeToParquet(List<GenericData.Record> recordList, Schema schema) {
        try {
            for (GenericData.Record record : recordList) writer.write(record);
        } catch (IOException e) {
            util.abort("Filed to write to Parquet");
        }
    }

    public void close() {
        try {
            this.writer.close();
        } catch (IOException e) {
            util.abort("Failed to close parquet writer");
        }
    }
}