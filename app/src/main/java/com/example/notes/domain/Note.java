package com.example.notes.domain;

import java.util.Date;

public class Note {
    private String name, description;
    private Date dateCreated;

    public Note(String name, String description) {
        this.name = name;
        this.description = description;
        dateCreated = new Date();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}
