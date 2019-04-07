/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.models;

import java.awt.Point;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class Place {
    private Point location;
    private ArrayList<Short> deliveries;

    public Place(Point location, short index) {
        this.location = location;
        deliveries = new ArrayList<>();
        deliveries.add(index);
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
    
    
    
}
