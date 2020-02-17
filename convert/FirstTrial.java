package parquet.compat.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import parquet.Log;
import parquet.Preconditions;
import parquet.column.page.PageReadStore;
import parquet.example.data.Group;
import parquet.example.data.simple.convert.GroupRecordConverter;
import parquet.hadoop.ParquetFileReader;
import parquet.hadoop.ParquetReader;
import parquet.hadoop.example.GroupReadSupport;
import parquet.hadoop.metadata.ParquetMetadata;
import parquet.io.ColumnIOFactory;
import parquet.io.MessageColumnIO;
import parquet.io.RecordReader;
import parquet.schema.MessageType;
import parquet.schema.MessageTypeParser;

public class FirstTrial {
    public private static void main(String[] args) {
        String fileInput = getSchema(SalesJan2009.csv);

        convertCsvToParquet(SalesJan2009.csv, store.parquet);
    }
}