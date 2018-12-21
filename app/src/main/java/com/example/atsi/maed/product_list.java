package com.example.atsi.maed;



public class product_list
{
    private String proName;
    private int unitPrice;
    private String id;

    public void setunitPrice(int uPrice) {
        this.unitPrice = uPrice;
    }

    public void setproName(String pName) {
        this.proName = pName;
    }

    public String getproName() {
        return proName;
    }

    public int getunitPrice() {
        return unitPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
