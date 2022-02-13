package kupa;

import csi.CSI;
import czujniki.Czujnik;
import data.Data;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable, Observator{
    private String login;
    private String password;
    transient public boolean isLogined = false;
    public static ArrayList<String> subscribedMiejsc = new ArrayList<>();
    private ArrayList<Data> current_Data = new ArrayList<Data>();
    private ArrayList<Czujnik> subscribed_czujniki = new ArrayList<>();
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
    public void subscribe(String miejscowosc)
    {
        if (CSI.getMiejscowości().contains(miejscowosc))
        {
            if(!subscribedMiejsc.contains(miejscowosc))
            {
                subscribedMiejsc.add(miejscowosc);
                Collections.sort(subscribedMiejsc);
                for(Czujnik czujnik : CSI.getCzujniki())
                {
                    if (czujnik.getMiejscowosc().equals(miejscowosc))
                    {

                        czujnik.register_Observer(this);
                        subscribed_czujniki.add(czujnik);
                    }
                }
            }
            else
            {
                System.out.println("Użytkownik już jest subskrybowany do tej lokalizacji");
            }
        }
        else
        {
            System.out.println("W CSI nie ma takiej lokalizacji");
        }
    }

    public void unsubscribe(String miejscowosc)
    {
        for (int i = 0 ; i < subscribed_czujniki.size(); i++)
        {
            if (subscribed_czujniki.get(i).getMiejscowosc().equals(miejscowosc))
            {
                subscribed_czujniki.get(i).remove_Observer(this);
                subscribed_czujniki.remove(i);
            }
        }
        current_Data.removeIf((data) -> miejscowosc.equals(data.getCzujnik().getMiejscowosc()));
        subscribedMiejsc.remove(miejscowosc);
    }

    public String getLogin() {
        return login;
    }

    @Override
    public void update(Data data) {
        for (int i = 0; i < current_Data.size(); i++)
        {
            if(data.getCzujnik() == current_Data.get(i).getCzujnik())
            {
                current_Data.set(i, data);
            }
        }
    }

    public void showWeather()
    {
        Collections.sort(current_Data);
        int start_index = 0;
        for (int i = 0; i < subscribedMiejsc.size(); i++)
        {
            String miejsc = subscribedMiejsc.get(i);
            System.out.println("miejscosość:" + miejsc + "\n");
            for (int j = start_index; j < current_Data.size(); j++)
            {
                if (current_Data.get(j).getCzujnik().getMiejscowosc().equals(miejsc))
                {
                    System.out.println("  " + (j + 1) + " czujnik\n" + "    " + current_Data.get(j) + "\n");
                    start_index++;
                }
                else break;
            }
        }
    }
}
