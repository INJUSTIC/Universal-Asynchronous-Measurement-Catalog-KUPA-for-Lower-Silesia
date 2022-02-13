package data;

import czujniki.*;

import java.lang.reflect.Type;
import java.security.KeyPair;
import java.util.Map;

public class Data implements Comparable<Data>{
    /*private String[] attributes;
    private String[] values;
    private String miejscowosc;*/
    private Czujnik czujnik;
    public Data(Czujnik czujnik /*String[] attributes, String[] values, String miejscowosc*/)
    {
        if (czujnik != null)
        {
            this.czujnik = czujnik;
        }
        else
        {
            throw new IllegalArgumentException("czujnik is null");
        }
        /*if(attributes != null && values != null && attributes.length == values.length && !miejscowosc.isEmpty())
        {
            this.miejscowosc = miejscowosc;
            this.attributes = attributes;
            this.values = values;
        }
        else {throw new IllegalArgumentException();}*/
    }

    @Override
    public String toString() {
        switch (czujnik.getClass().getName()) {
            case "CCzujnik" -> {
                return "ciśnienie: " + ((CCzujnik) czujnik).getCisnienie();
            }
            case "TCCzujnik" -> {
                return "temperatura: " + ((TCCzujnik) czujnik).getTemperatura() + "\n" + "ciśnienie: " + ((TCCzujnik) czujnik).getCisnienie();
            }
            case "TCzujnik" -> {
                return "temperatura: " + ((TCzujnik) czujnik).getTemperatura();
            }
            case "TWCzujnik" -> {
                return "temperatura: " + ((TWCzujnik) czujnik).getTemperatura() + "\n" + "wilgotność: " + ((TWCzujnik) czujnik).getWilgotnosc();
            }
            case "WCCzujnik" -> {
                return "wilgotność: " + ((WCCzujnik) czujnik).getWilgotnosc() + "\n" + "ciśnienie: " + ((WCCzujnik) czujnik).getCisnienie();
            }
            case "WCzujnik" -> {
                return "wilgotność: " + ((WCzujnik) czujnik).getWilgotnosc();
            }
            default -> throw new IllegalArgumentException("Nie ma takiego czujniku");
        }
    }

    public Czujnik getCzujnik() {
        return czujnik;
    }

    @Override
    public int compareTo(Data o) {
        return czujnik.getMiejscowosc().compareTo(o.czujnik.getMiejscowosc());
    }
}
