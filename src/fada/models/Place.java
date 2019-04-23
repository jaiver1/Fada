/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.models;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Place {

    private Point location;
    private ArrayList<Short> deliveries;
    private Color color;
    private final short index;

    public Place(short index, Point location, short index_delivery) {
        this.location = location;
        this.deliveries = new ArrayList<>();
        this.deliveries.add(index_delivery);
        this.index = index;
        this.color = Color.decode("#C2185B");
    }

    public Place(short index, Point location) {
        this.location = location;
        this.deliveries = new ArrayList<>();
        this.index = index;
        this.color = Color.decode("#C2185B");
    }

    public int getIndex() {
        return index;
    }

    public void add(short index) {
        deliveries.add(index);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public ArrayList<Short> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(ArrayList<Short> deliveries) {
        this.deliveries = deliveries;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
