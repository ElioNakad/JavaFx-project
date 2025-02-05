package com.example.project;

import javafx.scene.image.ImageView;

public class Laptops extends Items{
    private int storage,RAM;
    private String CPU;
    private String desc;
    public Laptops(double price, String name, int quantityInStock,
                   String brand, ImageView img, int storage, int RAM, String CPU) {
        super(price, name, quantityInStock, brand,img);
        setStorage(storage);
        setRAM(RAM);
        setCPU(CPU);
        setDesc();
    }

    public void setStorage(int storage){
        if(storage>0) {
            this.storage = storage;
        }
        else storage=0;
    }
    public int getStorage(){
        return storage;
    }

    public void setRAM(int RAM){
        if(RAM>0) {
            this.RAM = RAM;
        }
        else RAM=0;
    }
    public int getRAM(){
        return RAM;
    }

    public void setCPU(String CPU){
        this.CPU=CPU;
    }
    public String getCPU(){
        return this.CPU;
    }

    public void setDesc(){
        this.desc=("This is the Brand New: "+this.getName()+" \nFrom: "+this.getBrand()+
                "\nSpecs:"+this.getStorage()+" GB"+"\n"+this.getRAM()+"GB RAM"+"\n "+this.getCPU()+
                "\nFor a total price of: \""+this.getPrice()+"$\""+
                "\nP.S: One year waranty + free delivery ,under 72 hours," +
                "all over Lebanon,Are included in the offer! ");
    }
    public String getDesc(){
        return this.desc;
    }
}
