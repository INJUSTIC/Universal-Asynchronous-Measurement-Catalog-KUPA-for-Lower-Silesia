package czujniki;

import data.Data;
import obliczenia.ObliczCisn;
import org.json.simple.JSONObject;
import csi.CSI;

public class TCzujnik extends Czujnik{
    private double temperatura;
    public TCzujnik(String miejscowosc) {
        super(miejscowosc);
        obliczenie = new ObliczCisn();
    }
    public void oblicz()
    {
        temperatura = obliczenie.oblicz();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("miejscowość i typ czujnika: ", this);
        jsonObject.put("temperatura: ", temperatura);
        CSI.zapiszDoJSON(jsonObject);
        notify_Observers();
    }
    @Override
    public String toString() {
        return miejscowosc + " T";
    }

    public double getTemperatura() {
        return temperatura;
    }
}
