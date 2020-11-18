package com.poko.pi.car.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NODES")
public class Node implements GraphNode {

    private double x;
    private double y;
    @Id
    private String id;
    private boolean occupied;

    public  Node() {}

    public Node(double x, double y, String id, boolean occupied) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.occupied = occupied;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
