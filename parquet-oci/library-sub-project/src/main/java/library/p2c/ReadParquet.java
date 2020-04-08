package library.p2c;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.avro.generic.GenericData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;
import library.service.util;

public class ReadParquet {

    private String parqTar;

    public ReadParquet(String tar) {
        if (tar == null) util.segFault("RPCIN");
        this.parqTar = tar;
    }

    public String[][] readParquetFile(int len) {
        ArrayList<String[]> retArrL = new ArrayList<>();
        if (this.parqTar == null) util.segFault("RPFRD");
        ParquetReader<GenericData.Record> reader = null;
        Path path = new Path(this.parqTar);
        try {
            reader = AvroParquetReader
                    .<GenericData.Record>builder(path)
                    .withConf(new Configuration())
                    .build();
            GenericData.Record record;
            while ((record = reader.read()) != null) {
                String tmpRetArr[] = new String[len];
                for (int i = 0; i < len; i++) tmpRetArr[i] = record.get("item" + i).toString();
                retArrL.add(tmpRetArr);
            }
        }catch(IOException e) {
            util.abort("Failed to read from Parquet (C2P)");
        }finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    util.abort("Failed to close Parquet reader (C2P)");
                }
            }
        } Object retObj[] = retArrL.toArray();
        String ret[][] = new String[retObj.length][len];
        for (int i = 0; i < retObj.length; i++)
            ret[i] = (String[]) retObj[i];
        return ret;
    }
}