package com.example.api.model;

public class User {
    private String name;
    private int id;

    /**
     *
     * @param name - name of the user
     * @param id - id of the user
     */
    public User(String name, int id){
        this.name=name;
        this.id=id;
    }

    /**
     *
     * @return - name of the user
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return - id of the user
     */
    public int getId() {
        return id;
    }

}
