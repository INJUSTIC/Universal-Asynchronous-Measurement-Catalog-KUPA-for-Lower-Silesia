package kupa;

import csi.CSI;
import czujniki.Czujnik;
import data.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class KUPA {
    private HashSet<User> users = new HashSet<>();
    private User currentUser;
    public static boolean isLoading = false;
    public KUPA()
    {
        File file = new File("users.ser");
        if (file.exists())
        {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file)))
            {
                users = (HashSet<User>) is.readObject();
                isLoading = true;
                for (User user : users)
                {
                    user.getSubscribedMiejsc().forEach(user::subscribe);
                }
                isLoading = false;
            }
            catch (ClassNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }
            catch (IOException ex)
            {
                System.out.println(ex);
            }
        }
    }
    public void subscribe(String miejscowosc) throws IllegalAccessException {
        if (isLogined())
        {
            if (CSI.getMiejscowości().contains(miejscowosc))
            {
                if (currentUser.subscribe(miejscowosc)) writeUsers();
                else System.out.println("You are already subscribe to this czujnik");
            }
            else
            {
                System.out.println("There is no such miejscowosc in CSI");
            }
        }
        else
        {
            throw new IllegalAccessException("The user is not logged in");
        }
    }
    public void log_out()
    {
        currentUser = null;
    }
    public void unsubscribe(String miejscowosc) throws IllegalAccessException {
        if (isLogined())
        {
            if (CSI.getMiejscowości().contains(miejscowosc))
            {
                if (currentUser.unsubscribe(miejscowosc)) writeUsers();
                else System.out.println("You are not subscribed to this czujnik");
            }
            else
            {
                System.out.println("There is no such miejscowosc");
            }
        }
        else
        {
            throw new IllegalAccessException("The user is not logged in");
        }
    }
    private void writeUsers()
    {
        try (ObjectOutputStream br = new ObjectOutputStream(new FileOutputStream("users.ser")))
        {
            br.writeObject(users);
        }
        catch (IOException ex)
        {
            System.out.println("Error:" + ex.getMessage());
        }
    }
    public boolean registrate(String login, String password)
    {
        if(login != null && password != null && !login.isEmpty() && !password.isEmpty() && users != null)
        {
            for(User us : users)
            {
                if(us.getLogin().equals(login))
                {
                    System.out.println("This login is already taken");
                    return false;
                }
            }
            User user = new User(login,password);
            if (users.add(user))
            {
                try (ObjectOutputStream is = new ObjectOutputStream(new FileOutputStream("users.ser")))
                {
                    is.writeObject(users);
                    currentUser = user;
                    return true;
                }
                catch (FileNotFoundException ex)
                {
                    users.remove(user);
                    System.out.println(ex.getMessage());
                }
                catch (IOException ex)
                {
                    users.remove(user);
                    System.out.println(ex.getMessage());
                }
            }
            return false;
        }
       else
       {
            throw new IllegalArgumentException("argument is null or empty");
       }
    }
    public boolean log_in(String login, String password) throws IllegalAccessException {
        if(login != null && password != null && !login.isEmpty() && !password.isEmpty())
        {
            if (users != null)
            {
                if (users.size() != 0)
                {
                    User user = new User(login,password);
                    for(User us : users)
                    {
                        if(us.equals(user))
                        {
                            currentUser = us;
                            return true;
                        }
                    }
                }
               else
                {
                    throw new IllegalAccessException("There are no users yet to log in");
                }
            }
            else
            {
                throw new NullPointerException("users is null");
            }
            System.out.println("Incorrect login or password");
            return false;
        }
        else
        {
            throw new IllegalArgumentException("user is null");
        }
    }

    public void wyswietlSubskrybowaneLokalizacje()
    {
        if (isLogined())
        {
            for (String miejsc : currentUser.getSubscribedMiejsc())
            {
                System.out.println(miejsc);
            }
        }
    }

    public void showWeather()
    {
        if (isLogined()) {currentUser.showWeather();}
    }

    public boolean isLogined() {return currentUser != null;}
}
