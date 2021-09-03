package com.chesteve.last.operators;

public class Operators {
    private String taxisname;
    private String location;
    private String email;

    public Operators(String taxiname, String location, String email) {
        this.taxisname = taxiname;
        this.location = location;
        this.email = email;
    }


    public Operators(){}


    public String getTaxisname() {
        return taxisname;
    }

    public void setTaxisname(String taxisname) {
        this.taxisname = taxisname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
