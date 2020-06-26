package library.service;

public class Abort extends Exception {
    private String msg;
    public Abort(String str) {
        this.msg = str;
    }

    public String toString() {
        return "Abort: " + this.msg;
    }
}