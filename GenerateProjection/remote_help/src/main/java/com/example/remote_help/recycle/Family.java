package com.example.remote_help.recycle;

import java.lang.ref.PhantomReference;

public class Family {

    private int id;

    public Family(int id, String name, String phone_number) {
        this.id= id;
        this.name = name;
        this.phone_number = phone_number;
    }

    public int getIdentifier() {
        return id;
    }

    private String name;

    private String phone_number;

    public Family(String name, String phone_number){
        this.name =  name;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
