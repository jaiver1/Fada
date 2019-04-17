/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.models;

import java.awt.Point;
import java.util.HashMap;

/**
 *
 * @author PC
 */
public class Config {

    private final boolean map[][];
    private final int distance[][];
    private final HashMap<String,Place> places;
    private final Point[] document;
    private final short rows;
    private final short columns;
    private final short time;
    private final short deliveries;
    private final short limit;
    private final Point office;

    public Config(short rows, short columns, short time, short deliveries, short limit, Point office) {
        this.rows = rows;
        this.columns = columns;
        this.map = new boolean[rows][columns]; 
        this.office = office;
        this.time = time;
        this.deliveries = deliveries;
        this.places = new HashMap<>();
        this.document = new Point[deliveries+1];
        this.document[0] = this.office;
        this.distance = new int[deliveries+1][deliveries+1]; 
        this.limit = limit;
    }

    public void setDistance(short p1, short p2) {
       // distance[p1][p2] = Math.abs(places[p1].x - places[p2].x) + Math.abs(places[p1].y - places[p2].y);
       // System.out.println(p1+"["+places[p1].x+","+places[p1].y+"] ,"+p2+"["+places[p2].x+","+places[p2].y+"]="+distance[p1][p2]);
    }

    
    public void setPoint(short x, short y, boolean value) {
        map[x][y] = value;
    }
    
    public void setLocation(short index, short x, short y) {
        this.document[index] = new Point(x, y);
        String key = x+","+y;
        //si ya fue registrado ese punto se añade a la lista
        if(places.containsKey(key)){
            places.get(key).add(index);
        }else{
            //sino se crea un nuevo lugar y se añade al hashmap
            Point point = new Point(x, y);
            Place place = new Place(point,index);
            places.put(key,place);
        } 
    }

    public Point[] getDocument() {
        return document;
    }
    
    public HashMap<String, Place> getPlaces() {
        return places;
    }
    
    public boolean[][] getMap() {
        return map;
    }

    public int[][] getDistance() {
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

    public Point getOffice() {
        return office;
    }
    
}
