package library.c2p;

import library.service.util;

import java.sql.Timestamp;

public class Conv2Par {
    /**
     * To do the conversion job, use:
     * new c2p.Conv2Par (char * csv, char * parq)->init()->conv()->close();
     * Only one line is enough.
     *
     * @param csv   The address of the course csv file
     *              either absolute or relative address works
     * @param parq  The address target parquet file destination
     */

    private String tarParquet, title[], srcCsv;
    private CSVRP csvReader;
    private AVSC avsc;
    private WriteParquet writer;

    public Conv2Par(String csv, String parq) {
        if (csv == null || parq == null) util.segFault("CV2PC");
        this.srcCsv = csv;
        this.tarParquet = parq;
        this.title = null;
    }

    public Conv2Par init() {
        if (this.tarParquet == null || this.srcCsv == null) util.segFault("CS2PI");
        this.csvReader = new CSVRP(this.srcCsv).open();
        this.title = new String [csvReader.readNext().length];
        for (int i = 0; i < this.title.length; i++) this.title[i] = "item" + i;
        this.csvReader.close();
        this.csvReader = new CSVRP(this.srcCsv).open();
        this.avsc = new AVSC(this.title, "avsc" + new Timestamp(System.currentTimeMillis()).toString());
        this.writer = new WriteParquet().setSrcScheme(this.avsc).setTargetParquet(this.tarParquet);
        return this;
    }

    public Conv2Par conv() {
        if (this.writer == null) util.segFault("CS2PV");
        String csvLine[];
        while((csvLine = this.csvReader.readNext()) != null)
            this.writer.exe(csvLine);
        return this;
    }

    public void close() {
        this.csvReader.close();
    }

    public static void main (String[] args) {
        new Conv2Par("~/Download/FL_insurance_sample.csv", "test.parquet").init().conv().close();
    }
}
