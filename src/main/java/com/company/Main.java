package com.company;

import csi.CSI;
import czujniki.CCzujnik;
import czujniki.Czujnik;
import kupa.KUPA;
import kupa.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static KUPA kupa;
    public static void main(String[] args)
    {
        CSI csi = new CSI();
        kupa = new KUPA();
        csi.zacznijPomiar();
        startMenu();
    }
    private static void startMenu()
    {
        while (true)
        {
            System.out.println("Wybierz akcję:\n1. Zaloguj się\n2. Zarejestruj się\n3. Wyświetl wszystkie czujniki\n4. Wyjść");
            try
            {
                int menu = new Scanner(System.in).nextInt();
                switch (menu) {
                    case 1 -> logowanie();
                    case 2 -> rejestrowanie();
                    case 3 -> CSI.wyswWszystkieCzujniki();
                    case 4 -> System.exit(0);
                    default -> System.out.println("Nie ma takiej opcji");
                }
            }
            catch (InputMismatchException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void rejestrowanie()
    {
        System.out.println("Napisz login:");
        Scanner sc = new Scanner(System.in);
        String login = sc.next();
        System.out.println("Napisz hasło:");
        String haslo = sc.next();
        try
        {
            if (kupa.registrate(login,haslo))
            {
                user_menu();
            }
        }
        catch (IllegalArgumentException ex) { System.out.println(ex.getMessage());}
    }

    private static void user_menu()
    {
        while (kupa.isLogined())
        {
            System.out.println("Wybierz akcję:\n1. Wyświetl pogodę\n2. Zasubskrybuj się do miejscowości\n3. Odsubskrybuj się od miejscowości\n" +
                    "4. Wyświetl wszystkie czujniki\n5. Wyświetl listę moich lokalizacji\n6. Wyloguj się\n7. Wyjść");
            try
            {
                int menu = new Scanner(System.in).nextInt();
                switch (menu) {
                    case 1 -> kupa.showWeather();
                    case 2 -> subscribe();
                    case 3 -> unsubscribe();
                    case 4 -> {
                        System.out.println("Wszystkie czujniki:");
                        CSI.wyswWszystkieCzujniki();
                    }
                    case 5 -> {
                        System.out.println("Lista moich lokalizacji:");
                        kupa.wyswietlSubskrybowaneLokalizacje();
                    }
                    case 6 -> kupa.log_out();
                    case 7 -> System.exit(0);
                    default -> System.out.println("Nie ma takiej opcji");
                }
            }
            catch (InputMismatchException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void subscribe()
    {
        System.out.println("Napisz miejscowosc");
        CSI.wyswWszystkieMiejscowosci();
        try
        {
            kupa.subscribe(new Scanner(System.in).nextLine());
        }
        catch (IllegalAccessException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private static void unsubscribe()
    {
        System.out.println("Napisz miejscowosc");
        kupa.wyswietlSubskrybowaneLokalizacje();
        try
        {
            kupa.unsubscribe(new Scanner(System.in).nextLine());
        }
        catch (IllegalAccessException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private static void logowanie()
    {
        System.out.println("Napisz login:");
        Scanner sc = new Scanner(System.in);
        String login = sc.next();
        System.out.println("Napisz hasło:");
        String haslo = sc.next();
        try
        {
            if (kupa.log_in(login,haslo))
            {
                user_menu();
            }
        }
        catch (IllegalArgumentException | IllegalAccessException ex) { System.out.println(ex.getMessage());}
    }
}
