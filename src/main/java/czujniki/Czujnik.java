package czujniki;

import csi.CSI;
import data.Data;
import kupa.Observator;
import obliczenia.Oblicz;

import java.util.ArrayList;

public abstract class Czujnik implements Subject{
    protected Oblicz obliczenie;
    protected String miejscowosc;
    private ArrayList<Observator> observators = new ArrayList<>();
    public Czujnik(String miejscowosc)
    {
        if (miejscowosc != null && !miejscowosc.isEmpty() && CSI.getMiejscowości().contains(miejscowosc))
        {
            this.miejscowosc = miejscowosc;
            CSI.dodajCzujnik(this);
        }
        else {throw new IllegalArgumentException("miejscowosc is null or empty ot there is no such miejscowosc");}
    }
    public void register_Observer(Observator observator)
    {
        if(observator != null)
        {
            observators.add(observator);
        }
        else {throw new IllegalArgumentException("observator is null");}
    }
    public void notify_Observers()
    {
        if(observators != null)
        {
            for(Observator observator : observators)
            {
                observator.update(new Data(this));
            }
            return;
        }
        throw new NullPointerException("observators is null");
    }
    public void remove_Observer(Observator observator)
    {
        if (observator != null)
        {
            if (observators != null)
            {
                observators.remove(observator);
            }
        }
    }
    public abstract void oblicz();

    public String getMiejscowosc() {
        return miejscowosc;
    }
}
