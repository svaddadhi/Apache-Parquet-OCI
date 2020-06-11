package library.c2p;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import library.service.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVRP {
    private String tar;
    private CSVReader reader;

    public CSVRP(String fname) {
        if (fname == null) util.segFault("CVRIN");
        this.tar = fname;
    }

    public CSVRP open() {
        if (this.tar == null) util.segFault("CVROP");
        try {
            new File(this.tar).createNewFile();
            this.reader = new CSVReader(new FileReader(this.tar));
        } catch (IOException e) {
            util.abort("Failed to read designated CSV file");
        } return this;
    }

    public String[] readNext() {
        if (this.reader == null) util.segFault("CVRRN");
        try {
            return reader.readNext();
        } catch (IOException e) {
            util.abort("Failed to read next CSV line");
        } return null;
    }

    public void close() {
        if (this.reader == null) util.segFault("CVRCL");
        try {
            reader.close();
        } catch (IOException e) {
            util.abort("Failed to close CSV file stream");
        }
    }

    public CSVRP standardize(String tar) throws IOException {
        CSVWriter cw = new CSVWriter(new FileWriter(new File(tar)));
        List<String[]> csvCont = new ArrayList<>();
        String arr[];
        while ((arr = this.readNext()) != null) csvCont.add(arr);
        cw.writeAll(csvCont);
        cw.close();
        return this;
    }
}
