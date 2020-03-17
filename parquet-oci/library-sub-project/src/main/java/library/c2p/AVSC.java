package library.c2p;

import library.service.util;

import java.io.IOException;

public class AVSC {
    String title[];
    String name;

    public AVSC(String t[], String n) {
        if (t == null || n == null) util.segFault("AVITN");
        if (n.length() < 1) util.abort("Insufficient scheme name length");
        this.title = t;
        this.name = n;
    }

    public AVSC build() {
        StringBuilder ret = new StringBuilder("{\n\t\"type\": \"record\",\n\t\"name\": \""
                + this.name + "\",\n\t\"doc\": \"" + this.name + "\",\n\t\"fields\":\n\t[");
        for (int i = 0; i < title.length; i++) {
            if (i != 0) ret.append(",\n\t");
            ret.append("{\n\t\t\"name\": \"" + this.title[i] + "\",\n\t\t\"type\": \"string\"\n\t}");
        } ret.append("]\n}\n");
        util.wrtieFile("/tmp/avsc/", this.name + ".avsc", ret.toString());
        return this;
    }

    public static void main(String args[]) {
        new AVSC(new String[] {"test", "test1"}, "testName").build();
    }
}
