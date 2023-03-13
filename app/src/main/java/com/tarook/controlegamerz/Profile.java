package com.tarook.controlegamerz;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Profile {

    public static ArrayList<Profile> profiles = new ArrayList<>();

    private int id;
    private String name;
    private String age;
    private String address;
    private String email;

    private Bitmap picture;

    public Profile(int id, String name, String age, String address, String email, Bitmap picture) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Bitmap getPicture() {
        return picture;
    }
}
