/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16;

import java.util.Objects;

/**
 *
 * @author Tim Trense
 */
public class Name {
    
    private final String vorname;
    
    private final String nachname;
    
    public Name(final String vname, final String lname){
        this.vorname = vname;
        this.nachname = lname;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.vorname);
        hash = 89 * hash + Objects.hashCode(this.nachname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Name other = (Name) obj;
        if (!Objects.equals(this.vorname, other.vorname)) {
            return false;
        }
        if (!Objects.equals(this.nachname, other.nachname)) {
            return false;
        }
        return true;
    }
    
    
    
}
