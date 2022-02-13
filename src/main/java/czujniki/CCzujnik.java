package czujniki;

import csi.CSI;
import data.Data;
import obliczenia.ObliczCisn;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class CCzujnik extends Czujnik{
    private double cisnienie;
    public CCzujnik(String miejscowosc) {
        super(miejscowosc);
        obliczenie = new ObliczCisn();
    }
    public void oblicz()
    {
        cisnienie = obliczenie.oblicz();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("miejscowość i typ czujnika: ", this);
        jsonObject.put("ciśnienie: ", cisnienie);
        CSI.zapiszDoJSON(jsonObject);
        notify_Observers();
    }
    @Override
    public String toString() {
        return miejscowosc + " C";
    }

    public double getCisnienie() {
        return cisnienie;
    }
}
