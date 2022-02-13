package czujniki;

import data.Data;
import obliczenia.Oblicz;
import obliczenia.ObliczCisn;
import obliczenia.ObliczTemp;
import obliczenia.ObliczWilg;
import org.json.simple.JSONObject;
import csi.CSI;

public class WCCzujnik extends Czujnik{
    private double cisnienie;
    private double wilgotnosc;
    private Oblicz obliczenie2;

    public WCCzujnik(String miejscowosc) {
        super(miejscowosc);
        obliczenie = new ObliczWilg();
        obliczenie2 = new ObliczCisn();
    }

    public void oblicz() {
        wilgotnosc = obliczenie.oblicz();
        cisnienie = obliczenie2.oblicz();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("miejscowość i typ czujnika: ", this);
        jsonObject.put("wilgotnosc: ", wilgotnosc);
        jsonObject.put("cisnienie: ", cisnienie);
        CSI.zapiszDoJSON(jsonObject);
        notify_Observers();
    }
    @Override
    public String toString() {
        return miejscowosc + " WC";
    }

    public double getCisnienie() {
        return cisnienie;
    }

    public double getWilgotnosc() {
        return wilgotnosc;
    }
}
