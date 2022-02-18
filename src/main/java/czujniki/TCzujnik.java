package czujniki;

import data.Data;
import obliczenia.ObliczCisn;
import obliczenia.ObliczTemp;
import org.json.simple.JSONObject;
import csi.CSI;

public class TCzujnik extends Czujnik{
    private double temperatura;
    public TCzujnik(String miejscowosc) {
        super(miejscowosc);
        obliczenie = new ObliczTemp();
        type = "T";
    }
    public void oblicz()
    {
        temperatura = obliczenie.oblicz();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("miejscowość i typ czujnika: ", this.toString());
        jsonObject.put("temperatura: ", temperatura);
        CSI.zapiszDoJSON(jsonObject);
    }
    @Override
    public String toString() {
        return miejscowosc + " " + type;
    }

    public double getTemperatura() {
        return temperatura;
    }
}
