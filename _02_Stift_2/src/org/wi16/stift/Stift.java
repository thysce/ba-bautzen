package org.wi16.stift;

import java.awt.Color;

/**
 * @author Tim Trense
 */
public class Stift implements Schreibware{

    // Alt + Enter -> Import java.awt.Color
    private final Color color;

    // "Stift" + Ctrl + Space
    public Stift() {
        this(Color.BLACK);
    }

    public Stift(final Color color) {
        this.color = color;
    }

    // Alt + Insert -> Getter -> Select -> OK
    // or "getColor" + Ctrl + Space -> Generate
    public Color getColor() {
        return color;
    }

    // Alt + Instert -> toString -> Select -> OK
    @Override
    public String toString() {
        return "Stift{" + "color=" + color + '}';
    }
    

    // "equals" + Ctrl + Space -> Override
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Stift) {
            final Stift other = (Stift) obj;
            return this.color.equals(other.color);
        } else {
            return false;
        }
    }

}
