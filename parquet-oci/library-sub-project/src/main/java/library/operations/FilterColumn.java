package library.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.parquet.tools.command.*;

public class FilterColumn {
    String src;
    String dest;
    List<String> columns;

    public FilterColumn(String src, String dest, List<String> columns) {
        this.src = src;
        this.dest = dest;
        this.columns = columns;
    }

    public void filterColumn() {
    }

    public static void main(String[] args) {
        String srcFile = "home/phvle/userdata1.parquet";
        String destFile = "home/phvle/filtered.parquet";
        List<String> cols = new ArrayList<String>();
        cols.add("first_name");
        cols.add("last_name");
        cols.add("email");

        FilterColumn filterColumn = new FilterColumn(srcFile, destFile, cols);
        filterColumn.filterColumn();
    }
}
