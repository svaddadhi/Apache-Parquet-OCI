package library.service;

public class AddUtil {

    /**
     * Unified Address manager
     */

    private String exeAdd, dbsAdd;

    public AddUtil (String add) {
        this.exeAdd = add;
        this.dbsAdd = add;
    }

    public AddUtil (String exe, String rmt) {
        this.exeAdd = exe;
        this.dbsAdd = rmt;
    }

    public String getAdd () {return this.dbsAdd;}
    public String getSvc () {return this.exeAdd;}
}
