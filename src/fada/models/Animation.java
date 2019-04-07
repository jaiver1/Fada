/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.models;

import fada.controllers.Controller;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Animation extends JPanel implements MouseWheelListener {

    private final boolean map[][];
    private final HashMap<String, Place> places;
    private final Point office;
    private double zoom = 1;
    private int gx;
    private int gy;
    private final short space = 120;
    private final short border = 80;
    private final short fill = 78;
    String label;

    public Animation() {
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        setOpaque(false);
        zoom = 0.5;
        gx = 0;
        gy = 0;
        map = Controller.config.getMap();
        places = Controller.config.getPlaces();
        office = Controller.config.getOffice();

        addMouseWheelListener((MouseWheelListener) this);
        addMouseMotionListener(new Adaptador_Mouse());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(Color.WHITE);

        int width = getWidth();
        int height = getHeight();

        double zoomWidth = width * zoom;
        double zoomHeight = height * zoom;

        double anchorx = (width - zoomWidth) / 2;
        double anchory = (height - zoomHeight) / 2;

        g2.translate(anchorx, anchory);
        g2.scale(zoom, zoom);
        g2.translate(-100, -100);
        int inix = 100;

        g.setFont(new Font("Trebuchet MS", 3, 9));

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                g.drawRect(inix + gx + j * space + 20, gy + i * space + 20, border, border);

                if (map[i][j]) {
                    g.setColor(Color.decode("#C2185B"));
                    g.fillRect(inix + gx + j * space + 21, gy + i * space + 21, fill, fill);
                    g.setColor(Color.decode("#000000"));
                }

            }
        }
        
      g.setColor(Color.decode("#FFFFFF"));
        places.values().forEach((Place place) -> {
            label = "[";
            place.getDeliveries().forEach((Short delivery) -> {
                label += delivery+",";
            });
            label = label.substring(0, label.length()- 1);
            label += "]";
            g.drawString(label, inix + gx + place.getLocation().y * space + 23,
                    gy + place.getLocation().x * space + 62);
        });

        g.setColor(Color.decode("#3F51B5"));
        g.fillRect(inix + gx + (office.y) * space + 21, gy + (office.x) * space + 21, fill, fill);
        g.setColor(Color.decode("#FFFFFF"));
        g.setFont(new Font("Trebuchet MS", 3, 18));
        g.drawString("Oficina", inix + gx + office.y * space + 28,
                    gy + office.x * space + 65);
        g.setColor(Color.decode("#000000"));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e
    ) {

        if (e.getPreciseWheelRotation() < 0) {
            zoom += 0.1;
        } else {
            zoom -= 0.1;
        }

        if (zoom < 0.1) {
            zoom = 0.1;
        }

        if (zoom > 5) {
            zoom = 5;
        }
        repaint();
    }

    private class Adaptador_Mouse extends MouseAdapter {

        int previousX;
        int previousY;

        @Override
        public void mousePressed(MouseEvent e) {
            previousX = e.getX();
            previousY = e.getY();
            gx = previousX;
            gy = previousY;
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            int y = e.getY();
            int x = e.getX();
            if (zoom != 0) {
                if (x < previousX) {
                    gx -= (10 / zoom);
                } else if (x > previousX) {
                    gx += (10 / zoom);
                }
                if (y < previousY) {
                    gy -= (10 / zoom);
                } else if (y > previousY) {
                    gy += (10 / zoom);
                }
            }
            previousX = x;
            previousY = y;
            repaint();
        }
    }
}
