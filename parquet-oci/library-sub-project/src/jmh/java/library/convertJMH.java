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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class convertJMH {
    private ParquetTransform drillObj;
    private ParquetTransform nativeObj;
    private String src;
    private String drillDest;
    private String nativeDest;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(convertJMH.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        src = "/home/phvle/sample2.csv";
        drillDest = "/home/phvle/drill_convert.parquet";
        nativeDest = "/home/phvle/native_convert.parquet";
        nativeObj = new NativeTransform(src, nativeDest);
        drillObj = new DrillTransform(src, drillDest);
    }

    @Benchmark
    public void nativeConvert(Blackhole bh) {
        nativeObj.convertToCSV();
    }

    @Benchmark
    public void drillConvert(Blackhole bh) {
        drillObj.convertToCSV();
    }
}
