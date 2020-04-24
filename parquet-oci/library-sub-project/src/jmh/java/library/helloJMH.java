package library;

import org.openjdk.jmh.annotations.Benchmark;

public class helloJMH extends JMH_Abs {

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