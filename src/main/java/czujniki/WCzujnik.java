package czujniki;

import data.Data;
import obliczenia.ObliczCisn;
import org.json.simple.JSONObject;
import csi.CSI;

public class WCzujnik extends Czujnik{
    private double wilgotnosc;
    public WCzujnik(String miejscowosc) {
        super(miejscowosc);
        obliczenie = new ObliczCisn();
    }
    public void oblicz()
    {
        wilgotnosc = obliczenie.oblicz();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("miejscowość i typ czujnika: ", this);
        jsonObject.put("wilgotnosc: ", wilgotnosc);
        CSI.zapiszDoJSON(jsonObject);
        notify_Observers();
    }
    @Override
    public String toString() {
        return miejscowosc + " W";
    }

    public double getWilgotnosc() {
        return wilgotnosc;
    }

    @Override
    public String getMiejscowosc() {
        return super.getMiejscowosc();
    }
}
