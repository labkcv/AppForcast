package id.ac.ub.filkom.se.kcv.astechlauncher.controller.mainapp;

/**
 * Created by selab on 04-Nov-16.
 */

public class Item {
    private String no,hari,hasil;

    public Item(String no, String hari, String hasil) {
        super();
        this.no = no;
        this.hari = hari;
        this.hasil=hasil;
    }

    public String getNo(){
        return  no;
    }

    public String getHari(){
        return  hari;
    }

    public String getHasil(){
        return  hasil;
    }
}
