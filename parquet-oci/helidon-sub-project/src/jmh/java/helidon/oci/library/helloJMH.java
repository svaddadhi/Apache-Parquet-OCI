package helidon.oci.library;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 5)
public class helloJMH {

    @Benchmark
    public void hello() {
        System.out.println("Hello World!");
    }

    public void tsetJML() {
        this.hello();
    }

    public static void main (String[] args) {
        new helloJMH().hello();
    }
}