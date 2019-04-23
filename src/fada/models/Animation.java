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
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Animation extends JPanel implements MouseWheelListener, Runnable {

    private final ArrayList<Place> route;
    private final int distance;
    private final float profit;
    private final Place office;
    private final long sleep;
    private double zoom = 1;
    private int gx;
    private int gy;
    private final short space = 120;
    private final short border = 80;
    private final short fill = 78;
    String label;
    String sub_label;
    private Color office_color;
    private final Color place_color;
    private final Color blue_color;
    private final Color now_color;
    private JButton btn_iniciar;
    private JButton btn_calcular;

    public Animation(JButton btn_iniciar, JButton btn_calcular) {
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        setOpaque(false);
        zoom = 0.5;
        gx = 0;
        gy = 0;;
        route = Controller.config.getRoute().getPlaces();
        distance = Controller.config.getRoute().getDistance();
        profit = Controller.config.getRoute().getProfit();
        office = Controller.config.getOffice();

        addMouseWheelListener((MouseWheelListener) this);
        addMouseMotionListener(new Adaptador_Mouse());

        blue_color = Color.decode("#3F51B5");
        office_color = Color.decode("#3F51B5");
        sleep = 1000;

        place_color = Color.decode("#C2185B");
        now_color = Color.decode("#008080");
        this.btn_calcular = btn_calcular;
        this.btn_iniciar = btn_iniciar;

    }

    public void start() {
        new Thread(this).start();
    }

    public void buttons(boolean state) {
        this.btn_calcular.setEnabled(state);
        this.btn_iniciar.setEnabled(state);
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

        g.setColor(office_color);
        g.fillRect(inix + gx + (office.getLocation().y) * space + 21, gy
                + (office.getLocation().x) * space + 21, fill, fill);
        g.setColor(Color.WHITE);
        ;
        g.setFont(new Font("Trebuchet MS", 3, 18));
        g.drawString("Oficina", inix + gx + office.getLocation().y * space + 28,
                gy + office.getLocation().x * space + 90);
        g.setFont(new Font("Trebuchet MS", 3, 9));
        // g.drawString("0", inix + gx
        //        + office.getLocation().y * space + 91,
        //       gy + office.getLocation().x * space + 30);
        g.setColor(Color.BLACK);

        for (int i = 0; i < Controller.config.getRows(); i++) {
            for (int j = 0; j < Controller.config.getColumns(); j++) {
                g.drawRect(inix + gx + j * space + 20, gy + i * space + 20,
                        border, border);
            }
        }

        label = "[";
        for (int i = route.size() - 1; i >= 0; i--) {

            sub_label = "[";
            route.get(i).getDeliveries().forEach((Short delivery) -> {
                sub_label += delivery + ",";
            });
            sub_label = sub_label.substring(0, sub_label.length() - 1);
            sub_label += "]";
            g.setColor(route.get(i).getColor());
            if (route.get(i).getDeliveries().size() > 0 ) {
            g.fillRect(inix + gx + route.get(i).getLocation().y * space + 21,
                    gy + route.get(i).getLocation().x * space + 21,
                    fill, fill);
            }
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(route.get(i).getIndex()), inix + gx
                    + route.get(i).getLocation().y * space + 23,
                    gy + route.get(i).getLocation().x * space + 30);
            if (route.get(i).getDeliveries().size() > 0 ) {
            g.drawString(sub_label, inix + gx
                    + route.get(i).getLocation().y * space + 23,
                    gy + route.get(i).getLocation().x * space + 62);
            }
            label += route.get(i).getIndex() + ",";
        }
        label = label.substring(0, label.length() - 1);
        label += "]";
        g.setColor(Color.BLACK);
        g.setFont(new Font("Trebuchet MS", 3, 9));
        g.drawString("Ruta: " + label, inix + gx + 20,
                gy - 10);
        g.drawString("Distancia: " + distance, inix + gx + 20,
                gy + 0);
        g.drawString("Beneficio: " + profit, inix + gx + 20,
                gy + 10);
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

    @Override
    public void run() {
        try {
            buttons(false);
            office_color = now_color;
            repaint();
            Thread.sleep(sleep);
            office_color = blue_color;
            repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(Animation.class.getName()).
                    log(Level.SEVERE, null, ex);
            buttons(true);
        }
        for (int i = route.size() - 2; i >= 0; i--) {
            try {
                route.get(i).setColor(now_color);
                repaint();
                Thread.sleep(sleep);
                route.get(i).setColor(place_color);
                repaint();
            } catch (InterruptedException ex) {
                Logger.getLogger(Animation.class.getName()).
                        log(Level.SEVERE, null, ex);
                buttons(true);
            }
        }
        buttons(true);
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
