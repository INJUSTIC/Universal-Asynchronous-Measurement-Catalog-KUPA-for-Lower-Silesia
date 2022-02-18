package czujniki;

import data.Data;
import obliczenia.ObliczCisn;
import obliczenia.ObliczWilg;
import org.json.simple.JSONObject;
import csi.CSI;

public class WCzujnik extends Czujnik{
    private double wilgotnosc;
    public WCzujnik(String miejscowosc) {
        super(miejscowosc);
        obliczenie = new ObliczWilg();
        type = "W";
    }
    public void oblicz()
    {
        wilgotnosc = obliczenie.oblicz();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("miejscowość i typ czujnika: ", this.toString());
        jsonObject.put("wilgotnosc: ", wilgotnosc);
        CSI.zapiszDoJSON(jsonObject);
    }
    @Override
    public String toString() {
        return miejscowosc + " " + type;
    }

    public double getWilgotnosc() {
        return wilgotnosc;
    }

    @Override
    public String getMiejscowosc() {
        return super.getMiejscowosc();
    }
}
