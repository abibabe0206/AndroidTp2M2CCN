package com.example.androidtp1;

public class Person {

    String image;
    String nom;
    String prenom;
    String ddn;
    String vdn;
    String phone;

    public Person(String image, String nom, String prenom, String ddn, String vdn, String phone) {
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.ddn = ddn;
        this.vdn = vdn;
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDdn() {
        return ddn;
    }

    public String getVdn() {
        return vdn;
    }

    public String getPhone() {
        return phone;
    }
}
