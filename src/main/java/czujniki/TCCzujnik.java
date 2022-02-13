package czujniki;

import csi.CSI;
import data.Data;
import obliczenia.Oblicz;
import obliczenia.ObliczCisn;
import obliczenia.ObliczTemp;
import org.json.simple.JSONObject;

public class TCCzujnik extends Czujnik {
    private double temperatura;
    private double cisnienie;
    private Oblicz obliczenie2;

    public TCCzujnik(String miejscowosc) {
        super(miejscowosc);
        obliczenie = new ObliczCisn();
        obliczenie2 = new ObliczTemp();
    }

    public void oblicz() {
        cisnienie = obliczenie.oblicz();
        temperatura = obliczenie2.oblicz();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("miejscowość i typ czujnika: ", this);
        jsonObject.put("ciśnienie: ", cisnienie);
        jsonObject.put("temperatura: ", temperatura);
        CSI.zapiszDoJSON(jsonObject);
        notify_Observers();
    }
    @Override
    public String toString() {
        return miejscowosc + " TC";
    }

    public double getCisnienie() {
        return cisnienie;
    }

    public double getTemperatura() {
        return temperatura;
    }
}
