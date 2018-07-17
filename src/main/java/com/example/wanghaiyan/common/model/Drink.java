package com.example.wanghaiyan.common.model;

import com.example.wanghaiyan.myfirstapp.R;

import java.io.Serializable;

public class Drink implements Serializable{
    private String name;
    private String description;
    private int imageId;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drink(String name, String description, int imageId) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
    }

    public static final Drink[] drinks = {
            new Drink("Latte","A cpuple of es[resso shots with steamed milk", R.mipmap.coffee1),
            new Drink("Cappuccino","Espresso,hot milk,and a steamed milk foam", R.mipmap.coffee2),
            new Drink("Filter","Highest quality beans", R.mipmap.coffee3)
    };

    public String toString(){
        return this.name;
    }
}
