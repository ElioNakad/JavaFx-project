package com.example.project;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Items {
    protected double price;
    protected String name;
    protected int quantityInStock;
    protected String brand;
    protected ImageView img=new ImageView();
    int id;
    public Items(double price,String name,int quantityInStock,String brand,ImageView img){
        setPrice(price);
        setName(name);
        setQuantityInStock(quantityInStock);
        setBrand(brand);
        setImage(img);

    }

    public Items(){

    }
    public void generateId(){
        File file = new File("C:\\Users\\Administrator\\Desktop\\counter.txt");

        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Read data from a file
        while (input.hasNext()) {
           this.id=Integer.parseInt(String.valueOf(input.nextInt()));
           break;
        }


        try {
            java.io.PrintWriter output = new java.io.PrintWriter(file);
            output.print((id+1)+"");
            output.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Close the file
        input.close();
    }

    public void setPrice(double price){
        if(price>0){
            this.price=price;
        }
        else price=0;
    }
    public double getPrice(){
        return this.price;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }



    public void setQuantityInStock(int qty){
        if(qty>0){
           this.quantityInStock=qty;
        }
        else this.quantityInStock=0;
    }
    public int getQuantityInStock(){
        return this.quantityInStock;
    }

    public void setBrand(String brand){
        this.brand=brand;
    }
    public String getBrand(){
        return this.brand;
    }

    public void setImage(ImageView img){
        this.img=img;
    }
    public ImageView getImgage(){
        return this.img;
    }


}
