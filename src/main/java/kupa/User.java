package kupa;

import com.sun.source.tree.Tree;
import csi.CSI;
import czujniki.Czujnik;
import data.Data;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable, Comparable<User>{
    public String login;
    private String password;
    private TreeSet<String> subscribedMiejsc = new TreeSet<>();
    private transient Data current_Data;
    public User(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password);
    }
    public boolean subscribe(String miejscowosc)
    {
        if(subscribedMiejsc.add(miejscowosc) || KUPA.isLoading) {
            if (current_Data == null) current_Data = new Data(subscribedMiejsc);
            Iterator<Czujnik> iterator = CSI.getCzujniki().iterator();
            while (iterator.hasNext())
            {
                Czujnik czujnik = iterator.next();
                if (czujnik.getMiejscowosc().equals(miejscowosc))
                {
                    current_Data.addCzujnik(czujnik);
                }
            }
            return true;
        }
        return false;
    }

    public boolean unsubscribe(String miejscowosc)
    {
        if (subscribedMiejsc.contains(miejscowosc))
        {
            if (current_Data.getSubscribed_czujniki() != null)
            {
                TreeSet<Czujnik> tempCzujniki = new TreeSet<>(current_Data.getSubscribed_czujniki());
                for (Czujnik czujnik : tempCzujniki)
                {
                    if (czujnik.getMiejscowosc().equals(miejscowosc))
                    {
                        current_Data.removeCzujnik(czujnik);
                    }
                }
            }
            subscribedMiejsc.remove(miejscowosc);
            return true;
        }
        return false;
    }

    public String getLogin() {
        return login;
    }

    public void showWeather()
    {
        System.out.println(current_Data);
    }

    public TreeSet<String> getSubscribedMiejsc() {
        return new TreeSet<>(subscribedMiejsc);
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }
}
