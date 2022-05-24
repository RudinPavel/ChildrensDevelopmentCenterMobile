package com.samsung.childrensdevelopmentcentermobile.model;

// import java.sql.Date; todo

public class Tutor {

    private Long id;

    private String name;

    // private Date startDate; todo


    public Tutor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
