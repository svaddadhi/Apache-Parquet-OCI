package library;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public abstract class JMH_Abs {

    private static final Integer MEASUREMENT_ITERATIONS = 1;
    private static final Integer WARMUP_ITERATIONS = 1;

    /**
     * Any benchmark, by extending this class, inherits this single @Test
     method for JUnit to run.
     */
    @Test
    public void executeJmhRunner() throws RunnerException {
        new Runner(new OptionsBuilder()
                // set the class name regex for benchmarks to search for to the current class
                .include(getClass().getSimpleName())
                .warmupIterations(WARMUP_ITERATIONS)
                .measurementIterations(MEASUREMENT_ITERATIONS)
                .measurementBatchSize(1)
                .measurementTime(TimeValue.seconds(1))
                .forks(0)
                .threads(1)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .resultFormat(ResultFormatType.JSON)
                .result("/tmp/jmh-result") // set this to a valid filename if you want reports
                .jvmArgs("-server")
                .build()).run();
    }
}
