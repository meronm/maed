package com.example.atsi.maed;


public class Product {
    private String pName;
    private String uPrice;

    public void setuPrice(String uPrice) {
        this.uPrice = uPrice;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpName() {
        return pName;
    }

    public String getuPrice() {
        return uPrice;
    }
}

