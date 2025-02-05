package com.example.project;

import javafx.beans.property.*;
import javafx.scene.image.ImageView;

public class Phones extends Items {
    private final IntegerProperty storage;
    private final IntegerProperty RAM;
    private final StringProperty desc;

    //private double price;

    public Phones(double price, String name, int quantityInStock, String brand, ImageView img, int storage, int RAM) {
        super(price, name, quantityInStock, brand, img);
        this.storage = new SimpleIntegerProperty(storage > 0 ? storage : 0);
        this.RAM = new SimpleIntegerProperty(RAM > 0 ? RAM : 0);
        this.desc = new SimpleStringProperty();
        setDesc();
    }

    public IntegerProperty storageProperty() {
        return storage;
    }

    public int getStorage() {
        return storage.get();
    }

    public void setStorage(int storage) {
        this.storage.set(storage > 0 ? storage : 0);
    }

    public IntegerProperty RAMProperty() {
        return RAM;
    }

    public int getRAM() {
        return RAM.get();
    }

    public void setRAM(int RAM) {
        this.RAM.set(RAM > 0 ? RAM : 0);
    }

    public StringProperty descProperty() {
        return desc;
    }

    public String getDesc() {
        return desc.get();
    }

    public void setDesc() {
        this.desc.set("This is the Brand New: " + this.getName() + "\nFrom: " + this.getBrand() +
                "\nSpecs: " + this.getStorage() + " GB" + "\n" + this.getRAM() + " GB RAM" +
                "\nFor a total price of: \"" + this.getPrice() + "$\"" +
                "\nP.S: One year warranty + free delivery, under 72 hours, all over Lebanon, are included in the offer!");
}
}
