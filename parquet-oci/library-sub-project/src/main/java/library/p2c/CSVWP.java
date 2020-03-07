package library.p2c;

import com.opencsv.CSVWriter;
import library.service.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWP {
    private String tarF;
    private CSVWriter writer;

    public CSVWP(String tar) {
        if (tar == null) util.segFault("CSWPIC");
        this.tarF = tar;
    }

    public CSVWP init() {
        if (this.tarF == null) util.segFault("CSWPI");
        try {
            new File(this.tarF).createNewFile();
            this.writer = new CSVWriter(new FileWriter(this.tarF));
        } catch (IOException e) {
            util.abort("Failed to open target CSV file");
        }
        return this;
    }

    public void write(String [] str) {
        if (this.writer == null) util.segFault("CSWPR");
        writer.writeNext(str);
    }

    public void close() {
        try {
            this.writer.close();
        } catch (IOException e) {
            util.abort("Failed to close CSV file (P2C)");
        }
    }
}
