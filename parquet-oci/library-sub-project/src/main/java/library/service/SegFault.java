package library.service;

public class SegFault extends Exception {
    public String toString() {
        return "Segmentation Fault";
    }
}