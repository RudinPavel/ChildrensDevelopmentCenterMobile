package com.samsung.childrensdevelopmentcentermobile.model;

//import java.sql.Date; todo

import java.io.Serializable;

public class Elective implements Serializable {

    private Long id;

    private String name;

//    private String place; todo
//
//    private String time;
//
//    private Date date;

    private Direction direction;

    private Tutor tutor;

    public Elective(Long id, String name, Direction direction, Tutor tutor) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.tutor = tutor;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Direction getDirection() {
        return direction;
    }

    public Tutor getTutor() {
        return tutor;
    }

    @Override
    public String toString() {
        return "Elective{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", direction=" + direction +
                ", tutor=" + tutor +
                '}';
    }
}
