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
    private int cisnienie;
    public CCzujnik(String miejscowosc) {
        super(miejscowosc);
        obliczenie = new ObliczCisn();
        type = "C";
    }
    public void oblicz()
    {
        cisnienie = (int)obliczenie.oblicz();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("miejscowość i typ czujnika: ", this.toString());
        jsonObject.put("ciśnienie: ", cisnienie);
        CSI.zapiszDoJSON(jsonObject);
    }
    @Override
    public String toString() {
        return miejscowosc + " " + type;
    }

    public int getCisnienie() {
        return cisnienie;
    }
}
