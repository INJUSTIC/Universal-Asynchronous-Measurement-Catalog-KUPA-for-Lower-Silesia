package csi;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import czujniki.*;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class CSI extends Thread{
    public static JSONObject jsonObject = new JSONObject();
    private static ArrayList<Czujnik> czujniki = new ArrayList<>();
    private static ArrayList<String> miejscowości = new ArrayList<>();
    private final int TIME_TO_SLEEP_IN_MS = 5000;
    public CSI()
    {
        super("CSI");
        dodajCzujnik(new TCzujnik("Wrocław południowy"));
        dodajCzujnik(new TCCzujnik("Wrocław północny"));
        dodajCzujnik(new TWCzujnik("Wrocław Wschodny"));
        dodajCzujnik(new WCzujnik("Wrocław Zachodny"));
        dodajCzujnik(new TWCzujnik("Warszawa południowa"));
        dodajCzujnik(new CCzujnik("Warszawa północna"));
        dodajCzujnik(new TCzujnik("Warszawa Wschodna"));
        dodajCzujnik(new TWCzujnik("Warszawa Zachodna"));
        dodajCzujnik(new TCCzujnik("Kraków południowy"));
        dodajCzujnik(new TWCzujnik("Kraków północny"));
        dodajCzujnik(new WCzujnik("Kraków Wschodny"));
        dodajCzujnik(new WCCzujnik("Kraków Zachodny"));
    }

    public static void wyswLokalizacji()
    {
        czujniki.forEach((System.out::println));
    }
    public void zacznijPomiar()
    {
        start();
    }
    public void run()
    {
        while (true)
        {
            for(Czujnik czujnik : czujniki)
            {
                czujnik.oblicz();
            }
            try
            {
                Thread.sleep(TIME_TO_SLEEP_IN_MS);
            }
            catch(InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void dodajCzujnik(Czujnik czujnik)
    {
        czujniki.add(czujnik);
        if(!miejscowości.contains(czujnik.getMiejscowosc())) {miejscowości.add(czujnik.getMiejscowosc());}
    }

    public static void zapiszDoJSON(JSONObject jsonObject)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        jsonObject.put("Czas: ", dtf.format(now));
        try(BufferedWriter bf = new BufferedWriter(new FileWriter("data.json")))
        {
            bf.write(jsonObject.toJSONString());
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static ArrayList<Czujnik> getCzujniki() {return czujniki;}

    public static ArrayList<String> getMiejscowości() {
        return miejscowości;
    }
}
