package org.wi16.obs;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 *
 * @author Tim Trense
 */
@SuppressWarnings("serial")
public class ZahlView extends JLabel implements Observer {
    
    public ZahlView(final Zahl fs){
        fs.addObserver(this);
        update(fs, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        assert(o instanceof Zahl);
        setText("Zahl: "+String.valueOf(((Zahl)o).get()));
    }
}
