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

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Warmup(iterations = 10)
@Measurement(iterations = 20)
public class convertJMH {
    int i, j, k, l;
    static final String testFile = "stdcsv1500k.csv";

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
        src = System.getProperty("user.home") + File.separator + testFile;
        nativeObj = new NativeTransform();
        drillObj = new DrillTransform(src, "drill.yg-home.site", "drillbit");
        i = 0;
        j = 0;
        k = 0;
        l = 0;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testNativeOne() throws SQLException {
        nativeConvert(src, System.getProperty("user.home") + File.separator + "parquetTest" + File.separator + "test" + i++ + ".parquet");
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

    public void nativeConvert(String src, String dest) throws SQLException {
        nativeObj.convertToParquet(src, null, dest);
    }

    public void drillConvert(String tableName, String src) throws SQLException {
        drillObj.convertToParquet("/home/drill/"+ testFile, src, tableName);
    }
}
