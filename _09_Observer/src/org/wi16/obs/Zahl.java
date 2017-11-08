package org.wi16.obs;

import java.util.Observable;

/**
 *
 * @author Tim Trense
 */
public class Zahl extends Observable {

    private int z;

    private Zahl(final int z) {
        this.z = z;
    }

    public Zahl() {
        this(0);
    }

    public int get() {
        return z;
    }

    public void increment() {
        if (z < 0) {
            reset();
        }
        this.z++;
        this.setChanged();
        this.notifyObservers();
    }

    public void reset() {
        if (z != 0) {
            this.z = 0;
            this.setChanged();
        }
        this.notifyObservers();
    }

}
