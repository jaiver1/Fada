/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.models;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Route {

    private ArrayList<Place> places;
    private int distance;
    private float profit;
    private Color color;

    public Route(Place place, int distance, float profit) {
        this.places = new ArrayList<>();
        this.places.add(place);
        this.distance = distance;
        this.profit = profit;
    }

    public Route() {
        this.places = new ArrayList<>();
        this.distance = 0;
    }

    private Route(ArrayList places, int distance, float profit) {
        this.places = places;
        this.distance = distance;
        this.profit = profit;
        this.color = Color.decode("#C2185B");
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public void addPlace(Place place, int distance, float profit) {
        this.distance = distance;
        this.profit = profit;
        Place last = this.places.get(this.places.size() - 1);
        if (last.getIndex() != place.getIndex()) {
            this.places.add(place);
        }
    }
}
