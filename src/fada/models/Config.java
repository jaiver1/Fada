/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.models;

import java.awt.Point;

/**
 *
 * @author PC
 */
public class Config {

    private final boolean map[][];
    private final short distance[][];
    private final Point places[];
    private final short rows;
    private final short columns;
    private final short time;
    private final short deliveries;
    private final short limit;
    private final Point start;

    public Config(short rows, short columns, short time, short deliveries, short limit, Point start) {
        this.rows = rows;
        this.columns = columns;
        this.map = new boolean[rows][columns];
        this.distance = new short[rows][columns];  
        this.start = start;
        this.time = time;
        this.deliveries = deliveries;
        this.places = new Point[deliveries];
        this.limit = limit;
    }

    public void setDistance(short x, short y, short value) {
        distance[(x-1)][(y-1)] = value;
    }
    
    public void setPoint(short x, short y, boolean value) {
        map[(x-1)][(y-1)] = value;
    }
    
    public void setLocation(short index, Point location) {
        places[index] = location;
    }
    
    public Point[] getPlaces() {
        return places;
    }

    public boolean[][] getMap() {
        return map;
    }

    public short[][] getDistance() {
        return distance;
    }

    public short getRows() {
        return rows;
    }

    public short getColumns() {
        return columns;
    }

    public short getTime() {
        return time;
    }

    public short getDeliveries() {
        return deliveries;
    }

    public short getLimit() {
        return limit;
    }

    public Point getStart() {
        return start;
    }
    
}
