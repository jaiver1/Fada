/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author PC
 */
public class Config {

    private final HashMap<String, Place> places;
    private final ArrayList<Place> list;
    private final Point[] document;
    private final short rows;
    private final short columns;
    private final short time;
    private final short deliveries;
    private final short limit;
    private final Place office;
    private Route route;
    private int distance[][];

    public Config(short rows, short columns, short time, short deliveries,
            short limit, Place office) {
        this.rows = rows;
        this.columns = columns;
        this.office = office;
        this.time = time;
        this.deliveries = deliveries;
        this.list = new ArrayList<>();
        this.places = new HashMap<>();
        this.document = new Point[deliveries + 1];
        this.document[0] = this.office.getLocation();
        this.limit = limit;
        this.route = new Route();
    }

    public void setDistance(int[][] distance) {
        this.distance = distance;
    }

    public void setDistance(Place p1, Place p2) {
        distance[p1.getIndex()][p2.getIndex()] = Math.abs(p1.getLocation().x
                - p2.getLocation().x) + Math.abs(p1.getLocation().y
                - p2.getLocation().y);
        //System.out.println("S" + p1.getIndex() + ",S" + p2.getIndex() + " = "
        // + distance[p1.getIndex()][p2.getIndex()]);
    }

    public void initDistance(int size) {
        this.distance = new int[size][size];
    }

    public void setLocation(short index, short x, short y) {
        if (office.getLocation().x == x && office.getLocation().y == y) {
            office.getDeliveries().add(index);
        } else {
            this.document[index] = new Point(x, y);
            String key = x + "," + y;
            //si ya fue registrado ese punto se añade a la lista
            if (places.containsKey(key)) {
                places.get(key).add(index);
            } else {
                //sino se crea un nuevo lugar y se añade al hashmap
                Point point = new Point(x, y);
                Place place = new Place((short) (places.size() + 1), point, index);
                places.put(key, place);
            }
        }
    }

    public Point[] getDocument() {
        return document;
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

    public Place getOffice() {
        return office;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public HashMap<String, Place> getPlaces() {
        return places;
    }

    public ArrayList<Place> getList() {
        return list;
    }
}
