package com.samsung.childrensdevelopmentcentermobile.model;

public class Direction {

    private Long id;

    private String name;

    public Direction(Long id, String name) {
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
        return "Direction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
