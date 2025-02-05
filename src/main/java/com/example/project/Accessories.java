package com.example.project;

import javafx.scene.image.ImageView;

public class Accessories extends Items{
    private String desc;
    public Accessories(double price, String name, int quantityInStock,
                       String brand, ImageView img) {
        super(price, name,  quantityInStock, brand,img);
        setDesc();
    }

    public void setDesc(){
        this.desc=("This is the Brand New: "+this.getName()+" \nFrom: "+this.getBrand()+
                "\nFor a total price of: \""+this.getPrice()+"$\""+
                "\nP.S: One year waranty + free delivery ,under 72 hours," +
                "all over Lebanon,Are included in the offer! ");
    }
    public String getDesc(){
        return this.desc;
    }
}
