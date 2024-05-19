package com.toyrobot.toyrobotsimulator.model;

import jakarta.persistence.*;

@Entity
public class Robots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer x;

    private Integer y;

    private String direction;

    public Robots() {
    }

    public Robots(Integer x, Integer y, String direction, Integer id) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.id =id;
    }
    public Robots(Integer id) {
        this.id =id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Robots{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", direction='" + direction + '\'' +
                '}';
    }
}
