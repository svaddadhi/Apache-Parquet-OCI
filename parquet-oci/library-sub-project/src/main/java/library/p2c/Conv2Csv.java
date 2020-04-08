package library.p2c;

import library.service.util;

public class Conv2Csv {
    /**
     * To do the conversion job, use:
     * new c2p.Conv2Csv (char * csv, char * parq, int len)->init()->conv()->close();
     * Only one line is enough.
     *
     * @param csv   The address of the course csv file
     *              either absolute or relative address works
     * @param parq  The address target parquet file
     * @param len   This is the length of each entry of the desired CSV file
     */

    private String src, tar;
    private int entryLen;
    private CSVWP csvWriter;
    private ReadParquet parqRead;

    public Conv2Csv(String src, String tar, int len) {
        System.out.println("this is the parquet file: " + src);
        System.out.println("this is the csv file: " + tar);
        if (src == null || tar == null) util.segFault("C2CIN");
        if (len < 1) util.abort("Insufficient entry length expectation");
        this.src = src;
        this.tar = tar;
        this.entryLen = len;
    }

    public Conv2Csv init() {
        this.csvWriter = new CSVWP(this.tar).init();
        this.parqRead = new ReadParquet(this.src);
        return this;
    }

    public Conv2Csv conv() {
        String[][] res = parqRead.readParquetFile(this.entryLen);
        for (String entry[] : res) this.csvWriter.write(entry);
        return this;
    }

    public void close() {
        this.csvWriter.close();
    }
}