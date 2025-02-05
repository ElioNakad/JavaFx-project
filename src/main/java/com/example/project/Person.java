package com.example.project;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private StringProperty name;
    private StringProperty lname;
    private StringProperty email;
    private StringProperty pass;

    public Person(String name, String lname, String email, String pass) {
        this.name = new SimpleStringProperty(name);
        this.lname = new SimpleStringProperty(lname);
        this.email = new SimpleStringProperty(email);
        this.pass = new SimpleStringProperty(pass);
    }
    public Person(){

    }

    // Property methods
    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty lnameProperty() {
        return lname;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passProperty() {
        return pass;
    }

    // Getters
    public String getName() {
        return name.get();
    }

    public String getLname() {
        return lname.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPass() {
        return pass.get();
}
}
