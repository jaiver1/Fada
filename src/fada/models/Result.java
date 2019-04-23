/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.models;

import fada.controllers.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Administrador
 */
public class Result {

    public int[][] A1(ArrayList<Place> places) throws Exception {
        int distances[][] = Controller.config.getDistance();
        int result[][] = new int[distances.length][distances.length];
        Place office = Controller.config.getOffice();
   for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
               result[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < places.size(); i++) {
            Place now = (Place) places.get(i);
            result[office.getIndex()][now.getIndex()]
                    = distances[office.getIndex()][now.getIndex()];
            HashMap<String, Place> copy;
            copy = (HashMap<String, Place>) places.clone();
            copy.remove(i);
           for (int j = 0; j < copy.size(); j++) {
                Place place = (Place) places.get(j);
                Place last = (Place) places.get(j-1);
                result[now.getIndex()][place.getIndex()]
                        = result[now.getIndex()][place.getIndex()]
                        +distances[now.getIndex()][place.getIndex()];
        }
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        return result;
    }

    public Route A1_R(HashMap<String, Place> places, Place now) throws Exception {
        if (places.size() == 1) {
            Place place = (Place) places.get(places.keySet().toArray()[0]);
            int distance = Controller.config.
                    getDistance()[now.getIndex()][place.getIndex()];
            float profit = ((distance > 0) ? (place.getDeliveries().size()
                    / (float) distance) : 0f);
            return new Route(place, distance, profit);
        }
        Route result = new Route();
        int min = Integer.MAX_VALUE;
        Iterator it = places.values().iterator();
        while (it.hasNext()) {
            Place place = (Place) it.next();
            HashMap<String, Place> copy;
            copy = (HashMap<String, Place>) places.clone();
            copy.remove(now.getLocation().x + "," + now.getLocation().y);
            Route route = A1_R(copy, place);
            int distance = Controller.config.
                    getDistance()[now.getIndex()][place.getIndex()]
                    + route.getDistance();
            float profit = ((distance > 0) ? (place.getDeliveries().size()
                    / (float) distance) : 0f);
            route.addPlace(now, distance, profit);
            System.out.print(route.getDistance() + ",");
            if (distance < min) {
                min = distance;
                result = route;
            }
        }
        System.out.println();
        return result;
    }

    public Route A2_R(HashMap<String, Place> places, Place now) throws Exception {
        if (places.size() == 1) {
            Place place = (Place) places.get(places.keySet().toArray()[0]);
            int distance = Controller.config.
                    getDistance()[now.getIndex()][place.getIndex()];
            float profit = ((distance > 0) ? (place.getDeliveries().size()
                    / (float) distance) : 0f);
            return new Route(place, distance, profit);
        }
        Route result = new Route();
        float max = Integer.MIN_VALUE;
        Iterator it = places.values().iterator();
        while (it.hasNext()) {
            Place place = (Place) it.next();
            HashMap<String, Place> copy;
            copy = (HashMap<String, Place>) places.clone();
            copy.remove(now.getLocation().x + "," + now.getLocation().y);
            Route route = A2_R(copy, place);
            int distance = Controller.config.
                    getDistance()[now.getIndex()][place.getIndex()]
                    + route.getDistance();
            float profit = ((distance > 0) ? (place.getDeliveries().size()
                    / (float) distance) : 0f);
            route.addPlace(now, distance, profit);
            System.out.print(route.getProfit() + ",");
            if (route.getProfit() > max) {
                max = route.getProfit();
                result = route;
            }
        }
        System.out.println();
        return result;
    }

    public Route A3_R(HashMap<String, Place> places, Place now) throws Exception {
        if (places.size() == 1) {
            Place place = (Place) places.get(places.keySet().toArray()[0]);
            int distance = Controller.config.
                    getDistance()[now.getIndex()][place.getIndex()];
            float profit = ((distance > 0) ? (place.getDeliveries().size()
                    / (float) distance) : 0f);
            return new Route(place, distance, profit);
        }
        Route result = new Route();
        int min = Integer.MAX_VALUE;
        Iterator it = places.values().iterator();
        while (it.hasNext()) {
            Place place = (Place) it.next();
            HashMap<String, Place> copy;
            copy = (HashMap<String, Place>) places.clone();
            copy.remove(now.getLocation().x + "," + now.getLocation().y);
            Route route = A3_R(copy, place);
            int distance = Controller.config.
                    getDistance()[now.getIndex()][place.getIndex()]
                    + route.getDistance();
            float profit = ((distance > 0) ? (place.getDeliveries().size()
                    / (float) distance) : 0f);
            route.addPlace(now, distance, profit);
            if (distance < min) {
                min = distance;
                result = route;
            }
        }
        return result;
    }

}
