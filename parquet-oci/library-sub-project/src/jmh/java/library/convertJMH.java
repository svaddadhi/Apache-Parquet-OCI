package library;

import library.transformation.DrillTransform;
import library.transformation.NativeTransform;
import library.transformation.ParquetTransform;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Warmup(iterations = 0)
@Measurement(iterations = 2)
public class convertJMH {
    int i, j, k, l;

    private ParquetTransform drillObj;
    private ParquetTransform nativeObj;
    private String src;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(convertJMH.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() throws SQLException, ClassNotFoundException {
        src = "/home/phvle/data.csv";
        nativeObj = new NativeTransform();
        drillObj = new DrillTransform(src, "localhost", "drillbit");
        i = 0;
        j = 0;
        k = 0;
        l = 0;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testNativeOne() {
        nativeConvert(src, "/home/phvle/temp/native_convert_1_" + i++ + ".parquet");
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testDrillOne() throws SQLException {
        drillConvert("table_1" + j++, src);
    }

//    @Benchmark
//    @BenchmarkMode(Mode.All)
//    @OutputTimeUnit(TimeUnit.SECONDS)
//    public void testNativeTwo() {
//        nativeConvert(src, "/home/phvle/temp/native_convert_2_" + i++ + ".parquet");
//    }
//
//    @Benchmark
//    @BenchmarkMode(Mode.All)
//    @OutputTimeUnit(TimeUnit.SECONDS)
//    public void testDrillTwo() throws SQLException {
//        drillConvert("table_2" + j++, src);
//    }

    public void nativeConvert(String src, String dest) {
        nativeObj.convertToParquet(src, dest);
    }

    public void drillConvert(String tableName, String src) throws SQLException {
        drillObj.convertToParquet(tableName, new String[] {"Activity Period", "Operating Airline", "Operating Airline IATA Code", "Published Airline", "Published Airline IATA Code", "GEO Summary", "GEO Region", "Activity Type Code", "Price Category Code", "Terminal", "Boarding Area", "Passenger Count"}, 12, src);
    }
}
