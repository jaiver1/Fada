/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fada.controllers;

import fada.models.Config;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.ImageIcon;

/**
 *
 * @author PC
 */
public final class Controller {

    public static ImageIcon check_icon = new ImageIcon(Controller.class.getResource("../views/resources/Check.png"));
    public static ImageIcon app_icon = new ImageIcon(Controller.class.getResource("../views/resources/App_Icon.png"));
    public static ImageIcon config_icon = new ImageIcon(Controller.class.getResource("../views/resources/Config_Icon.png"));
    public static ImageIcon map_icon = new ImageIcon(Controller.class.getResource("../views/resources/Map_Icon.png"));
    public static Config config;

    private Controller() {
    }

    public static void extraerDatos(File archivo) throws Exception {
        FileReader fileReader = new FileReader(archivo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Object[] lines = bufferedReader.lines().toArray();
        fileReader.close();
        validarDatos(lines);
    }

    public static void validarDatos(Object[] lines) throws Exception {
        String[] row;
        // Validar estructura del archivo.
        if (lines.length != 4) {
            throw new Exception("El archivo debe tener 4 lineas.");
        }

        // Validar estructura de la fila #1.
        row = String.valueOf(lines[0]).split(",");

        if (row.length != 3) {
            throw new Exception("Información incorrecta en la linea #1: (Filas de la ciudad, Columnas de la ciudad, Tiempo recorrido de una calle).");
        }

        short rows = Short.parseShort(row[0]);
        short columns = Short.parseShort(row[1]);
        short time = Short.parseShort(row[2]);

        // Validar estructura de la fila #2.
        row = String.valueOf(lines[1]).split(",");

        if (row.length != 2) {
            throw new Exception("Información incorrecta en la linea #2: (Cantidad de entregas, Limite de tiempo).");
        }

        short deliveries = Short.parseShort(row[0]);
        short limit = Short.parseShort(row[1]);

        // Validar estructura de la fila #3.
        row = String.valueOf(lines[2]).split(",");

        if (row.length != 2) {
            throw new Exception("Información incorrecta en la linea #3:  Punto de inicio (Coordenada X, Coordenada Y).");
        }

        int x = Integer.parseInt(row[0]);
        int y = Integer.parseInt(row[1]);
        Point start = new Point(x, y);

        // Validar estructura de la fila #4.
        row = String.valueOf(lines[3]).split(",");

        //Validar si la cantidad de datos en la fila es igual entregas
        if (row.length != deliveries * 2) {
            throw new Exception("Información incorrecta en la linea #4: La cantidad de datos debe ser el doble de la cantida de entregas (Cantidad * 2).\n"
                    + "En este caso hay \"" + (deliveries) + "\" entregas, entonces debe tener \"" + (deliveries * 2) + "\" datos indicando las coordenadas.");
        }
        config = new Config(rows, columns, time, deliveries, limit, start);
        fillMap(row);
    }

    public static void fillMap(String[] places) throws Exception {
        short x, y, index;
        for (short i = 0; i < places.length; i += 2) {
            x = Short.parseShort(places[i]);
            y = Short.parseShort(places[i + 1]);
            index = (short) (i / 2);
            config.setPoint(x, y, true);
            config.setLocation(index, new Point(x, y));
        }
    }
}