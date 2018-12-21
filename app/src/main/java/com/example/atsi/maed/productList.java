package com.example.atsi.maed;



public class productList {
    private String item_name;
    private int item_price;
    private int customer_id;
    private int menu_id;
    private int hotel_id;
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    public String getItem_name() {
        return item_name;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public int getCustomer_id() {return customer_id;}
    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }
    public int getItem_price() {
        return item_price;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
}
