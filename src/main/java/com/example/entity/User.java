package com.example.entity;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 3092592238015885727L;

    private String name;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
