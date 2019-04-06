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
public class Place {
    short index;
    Point location;

    public Place(short index, Point location) {
        this.index = index;
        this.location = location;
    }
    
}
