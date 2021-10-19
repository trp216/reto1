package com.example.reto1;



public class Profile {

    private String name;
    private String uri;
    private String description;


    public Profile(){

    }
    public Profile(String name, String description,String uri) {
        this.name = name;
        this.description = description;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}