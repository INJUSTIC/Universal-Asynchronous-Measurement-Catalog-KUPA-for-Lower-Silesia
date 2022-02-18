package czujniki;

import csi.CSI;
import obliczenia.Oblicz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public abstract class Czujnik implements Comparable<Czujnik>, Serializable {
    protected Oblicz obliczenie;
    protected String miejscowosc;
    protected String type;
    public Czujnik(String miejscowosc)
    {
        if (miejscowosc != null && !miejscowosc.isEmpty() && CSI.getMiejscowości().contains(miejscowosc))
        {
            this.miejscowosc = miejscowosc;
        }
        else {throw new IllegalArgumentException("miejscowosc is null or empty ot there is no such miejscowosc");}
    }
    public abstract void oblicz();

    @Override
    public int compareTo(Czujnik o) {
        if (o.miejscowosc.compareTo(miejscowosc) == 0)
        {
            return o.type.compareTo(this.type);
        }
        return miejscowosc.compareTo(o.miejscowosc);
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public String getType() {return type;}
}

