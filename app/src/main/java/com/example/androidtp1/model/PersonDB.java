package com.example.androidtp1.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "person_table")
public class PersonDB {

    @PrimaryKey(autoGenerate = true)
    public int personId;

    @ColumnInfo(name = "nom")
    public  String nom;

    @ColumnInfo(name = "prenom")
    public String prenom;

    @ColumnInfo(name = "ddn")
    public String ddn;

    @ColumnInfo(name = "vdn")
    public String vdn;

    @ColumnInfo(name = "phone")
    public String phone;

    @ColumnInfo(name = "depdn")
    public String depdn;

    public PersonDB(String nom, String prenom,
                    String ddn, String vdn, String phone,
                    String depdn) {
        this.nom = nom;
        this.prenom = prenom;
        this.ddn = ddn;
        this.vdn = vdn;
        this.phone = phone;
        this.depdn = depdn;
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

    public String getDepdn() {
        return depdn;
    }
}
