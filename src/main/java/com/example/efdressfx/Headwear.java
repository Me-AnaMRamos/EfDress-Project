package com.example.efdressfx;

import javafx.scene.paint.Color;
import java.util.HashMap;

/**
 *
 * @author calon
 */
public class Headwear extends Item {
    private String estampado;
    private double talla;

    public Headwear(String nombre, String material, HashMap<String, Color> paletaColores,
                    String[] ocasion, String tipo, String ubicacion) {
        super(nombre, material, paletaColores, ocasion, tipo, ubicacion);
    }

    public String getEstampado() {
        return estampado;
    }

    public void setEstampado(String estampado) {
        this.estampado = estampado;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

}