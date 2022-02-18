package csi;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import com.sun.source.tree.Tree;
import czujniki.*;
import kupa.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class CSI extends Thread {
    public static JSONArray jsonArray = new JSONArray();
    private static TreeSet<Czujnik> czujniki = new TreeSet<>();
    private static TreeSet<String> miejscowosci = new TreeSet<>();
    private final int TIME_TO_SLEEP_IN_MS = 5000;
    public CSI()
    {
        super("CSI");
        File file1 = new File("miejscowosci.txt");
        if (file1.exists())
        {
            try (BufferedReader bf = new BufferedReader(new FileReader("miejscowosci.txt")))
            {
                String s;
                while ((s = bf.readLine()) != null)
                {
                    miejscowosci.add(s);
                }
            }
            catch (FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }
            catch (IOException ex)
            {
                ex.getStackTrace();
            }
        }
        else
        {
            dodajMiejsc("Wrocław południowy");
            dodajMiejsc("Wrocław północny");
            dodajMiejsc("Wrocław Wschodny");
            dodajMiejsc("Wrocław Zachodny");
            dodajMiejsc("Warszawa południowa");
            dodajMiejsc("Warszawa północna");
            dodajMiejsc("Warszawa Wschodna");
            dodajMiejsc("Warszawa Zachodna");
            dodajMiejsc("Kraków południowy");
            dodajMiejsc("Kraków północny");
            dodajMiejsc("Kraków Wschodny");
            dodajMiejsc("Kraków Zachodny");
        }

        File file = new File("czujniki.ser");

        if (file.exists())
        {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file)))
            {
                czujniki = (TreeSet<Czujnik>) is.readObject();
            }
            catch (ClassNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }
            catch (IOException ex)
            {
                ex.getStackTrace();
            }
        }
        else
        {
            dodajCzujnik(new WCzujnik("Wrocław południowy"));
            dodajCzujnik(new WCCzujnik("Wrocław południowy"));
            dodajCzujnik(new TCCzujnik("Wrocław północny"));
            dodajCzujnik(new TWCzujnik("Wrocław Wschodny"));
            dodajCzujnik(new WCzujnik("Wrocław Zachodny"));
            dodajCzujnik(new WCCzujnik("Wrocław Zachodny"));
            dodajCzujnik(new TCzujnik("Wrocław Zachodny"));
            dodajCzujnik(new TWCzujnik("Warszawa południowa"));
            dodajCzujnik(new CCzujnik("Warszawa północna"));
            dodajCzujnik(new TCzujnik("Warszawa Wschodna"));
            dodajCzujnik(new TWCzujnik("Warszawa Zachodna"));
            dodajCzujnik(new TCCzujnik("Kraków południowy"));
            dodajCzujnik(new TWCzujnik("Kraków północny"));
            dodajCzujnik(new WCzujnik("Kraków Wschodny"));
            dodajCzujnik(new WCCzujnik("Kraków Zachodny"));
        }

        File file2 = new File("data.json");
        if (file2.exists())
        {
            try (FileReader fr = new FileReader(file2))
            {
                jsonArray = (JSONArray) (new JSONParser()).parse(fr);
            }
            catch (FileNotFoundException | ParseException ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
            catch (IOException ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    public static void wyswWszystkieCzujniki()
    {
        String data = "";
        int start_index = 0;
        Czujnik[] czujniki_array = czujniki.toArray(new Czujnik[czujniki.size()]);
        for (String s : miejscowosci) {
            data += "miejscowość: " + s + "\n";
            for (int j = start_index, k = 0; j < czujniki_array.length; j++, k++) {
                if (czujniki_array[j].getMiejscowosc().equals(s)) {
                    data += czujniki_array[j].getType() + "  ";
                    start_index++;
                }
                else
                {
                    data += "\n";
                    break;
                }
            }
        }
        System.out.println(data);
    }
    public static void wyswWszystkieMiejscowosci()
    {
        if (miejscowosci != null)
        {
            Iterator<String> iterators = miejscowosci.iterator();
            while (iterators.hasNext())
            {
                System.out.println(iterators.next());
            }
        }
        else {throw new NullPointerException("miejscowosci is null");}
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

    public static void dodajMiejsc(String miejscowosc)
    {
        if (miejscowosc != null)
        {
            if (miejscowosci.add(miejscowosc))
            {
                try (BufferedWriter bf = new BufferedWriter(new FileWriter("miejscowosci.txt", true)))
                {
                    bf.write(miejscowosc + "\n");
                }
                catch (FileNotFoundException ex)
                {
                    miejscowosci.remove(miejscowosc);
                    System.out.println(ex.getMessage());
                }
                catch (IOException ex)
                {
                    miejscowosci.remove(miejscowosc);
                    System.out.println(ex.getMessage());
                }
            }
        }
        else throw new IllegalArgumentException("czujnik is null");
    }

    public static void dodajCzujnik(Czujnik czujnik)
    {
        if (czujnik != null)
        {
            if (czujniki.add(czujnik))
            {
                try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("czujniki.ser")))
                {
                    os.writeObject(czujniki);
                }
                catch (FileNotFoundException ex)
                {
                    czujniki.remove(czujnik);
                    System.out.println(ex.getMessage());
                }
                catch (IOException ex)
                {
                    czujniki.remove(czujnik);
                    System.out.println(ex.getMessage());
                }
            }
        }
        else throw new IllegalArgumentException("czujnik is null");
    }

    public static void zapiszDoJSON(JSONObject jsonObject)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        jsonObject.put("Czas: ", dtf.format(now));
        jsonArray.add(jsonObject);
        try(BufferedWriter bf = new BufferedWriter(new FileWriter("data.json")))
        {
            bf.write(jsonArray.toJSONString());
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static TreeSet<Czujnik> getCzujniki() {return czujniki;}

    public static TreeSet<String> getMiejscowości() {
        return miejscowosci;
    }
}
