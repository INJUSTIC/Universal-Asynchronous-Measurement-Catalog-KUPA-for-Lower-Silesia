package czujniki;

import data.Data;
import obliczenia.Oblicz;
import obliczenia.ObliczCisn;
import obliczenia.ObliczTemp;
import obliczenia.ObliczWilg;
import org.json.simple.JSONObject;
import csi.CSI;

public class TWCzujnik extends Czujnik{
    private double temperatura;
    private double wilgotnosc;
    private Oblicz obliczenie2;

    public TWCzujnik(String miejscowosc) {
        super(miejscowosc);
        obliczenie = new ObliczWilg();
        obliczenie2 = new ObliczTemp();
        type = "TW";
    }

    public void oblicz() {
        wilgotnosc = obliczenie.oblicz();
        temperatura = obliczenie2.oblicz();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("miejscowość i typ czujnika: ", this.toString());
        jsonObject.put("temperatura: ", temperatura);
        jsonObject.put("wilgotnosc: ", wilgotnosc);
        CSI.zapiszDoJSON(jsonObject);
    }
    @Override
    public String toString() {
        return miejscowosc + " " + type;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getWilgotnosc() {
        return wilgotnosc;
    }
}
