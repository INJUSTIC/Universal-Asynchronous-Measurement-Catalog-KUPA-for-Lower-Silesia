package kupa;

import csi.CSI;
import czujniki.Czujnik;

import java.io.*;
import java.util.ArrayList;

public class KUPA {
    private ArrayList<User> users = new ArrayList<>();
    private User currentUser;
    public KUPA()
    {
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("users.ser")))
        {
            Object object;
            while((object = is.readObject()) != null)
            {
                users.add((User)object);
            }
        }
        catch (FileNotFoundException  | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public void log_out()
    {
        currentUser = null;
    }
    public void unsubscribe(String miejscowosc)
    {
        currentUser.unsubscribe(miejscowosc);
    }
    public void registrate(String login, String password)
    {
        if(login != null && password != null && !login.isEmpty() && !password.isEmpty() && users != null)
        {
            for(User us : users)
            {
                if(us.getLogin().equals(login))
                {
                    System.out.println("This login is already taken");
                    return;
                }
            }
            try(ObjectOutputStream is = new ObjectOutputStream(new FileOutputStream("users.ser")))
            {
                User user = new User(login,password);
                is.writeObject(user);
                users.add(user);
            }
            catch (FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
       else
       {
            throw new IllegalArgumentException("argument is null or empty");
       }
    }
    public void log_in(String login, String password)
    {
        if(login != null && password != null && !login.isEmpty() && !password.isEmpty())
        {
            if (users != null)
            {
                User user = new User(login,password);
                for(User us : users)
                {
                    if(us.equals(user))
                    {
                        currentUser = us;
                        return;
                    }
                }
            }
            System.out.println("Incorrect login or password");
        }
        else
        {
            throw new IllegalArgumentException("user is null");
        }
    }
}
