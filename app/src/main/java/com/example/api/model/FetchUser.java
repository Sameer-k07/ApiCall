package com.example.api.model;

public class FetchUser {
    private String name;
    private int id;
    private String title;
    private String body;

    /**
     *
     * @param name - name of the user
     * @param id - id of the user
     * @param title - title of the user
     * @param body - body of the user
     */
    public FetchUser(String name, int id, String title, String body ){
        this.name=name;
        this.id=id;
        this.title=title;
        this.body=body;
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

    /**
     *
     * @return - title of the user
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return - body of the user
     */
    public String getBody() {
        return body;
    }
}
