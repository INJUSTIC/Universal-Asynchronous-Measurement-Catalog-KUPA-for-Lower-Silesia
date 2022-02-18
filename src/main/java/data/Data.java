package data;

import czujniki.*;
import kupa.User;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.security.KeyPair;
import java.util.*;

public class Data implements Serializable {
    private TreeSet<String> user_miejscowosci;
    private TreeSet<Czujnik> subscribed_czujniki = new TreeSet<>();

    public Data(TreeSet<String> user_miejscowosci)
    {
        if (user_miejscowosci != null)
        {
            this.user_miejscowosci = user_miejscowosci;
        }
    }
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        int start_index = 0;
        Czujnik[] subscribed_czujniki_array = subscribed_czujniki.toArray(new Czujnik[subscribed_czujniki.size()]);
        for (String miejsc : user_miejscowosci) {

            data.append("miejscosość: ").append(miejsc).append("\n");
            for (int j = start_index, k = 0; j < subscribed_czujniki_array.length; j++, k++) {
                if (subscribed_czujniki_array[j].getMiejscowosc().equals(miejsc)) {
                    data.append("  ").append(k + 1).append(" czujnik: ").append(czujnik_data(subscribed_czujniki_array[j])).append("\n");
                    start_index++;
                }
                else break;
            }
        }
        return data.toString();
    }
    private String czujnik_data(Czujnik czujnik)
    {
        switch (czujnik.getType()) {
            case "C" -> {
                return "ciśnienie: " + ((CCzujnik) czujnik).getCisnienie() + " mm";
            }
            case "TC" -> {
                return "temperatura: " + ((TCCzujnik) czujnik).getTemperatura() + " C" + ", ciśnienie: " + ((TCCzujnik) czujnik).getCisnienie() + " mm";
            }
            case "T" -> {
                return "temperatura: " + ((TCzujnik) czujnik).getTemperatura() + " C";
            }
            case "TW" -> {
                return "temperatura: " + ((TWCzujnik) czujnik).getTemperatura() + " C" + ", wilgotność: " + ((TWCzujnik) czujnik).getWilgotnosc() + '%';
            }
            case "WC" -> {
                return "wilgotność: " + ((WCCzujnik) czujnik).getWilgotnosc() + '%' + ", ciśnienie: " + ((WCCzujnik) czujnik).getCisnienie() + " mm";
            }
            case "W" -> {
                return "wilgotność: " + ((WCzujnik) czujnik).getWilgotnosc() + '%';
            }
            default -> throw new IllegalArgumentException("Nie ma takiego czujniku");
        }

    }
    public void addCzujnik(Czujnik czujnik)
    {
        subscribed_czujniki.add(czujnik);
    }

    public void removeCzujnik(Czujnik czujnik)
    {
        subscribed_czujniki.removeIf((czujnik1) -> czujnik.toString().equals(czujnik1.toString()));
    }

    public TreeSet<Czujnik> getSubscribed_czujniki() {
        return subscribed_czujniki;
    }
}
