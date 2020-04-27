package library;

import org.openjdk.jmh.annotations.Benchmark;

public class helloJMH extends JMH_Abs {
    @Benchmark
    public void hello() {
        System.out.println("Hello World!");
    }
}