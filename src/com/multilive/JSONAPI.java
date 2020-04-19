package com.multilive;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class JSONAPI {
    public static String expressionstring;
    public static int status;
    public static String error;
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static Number x0;
    public static Number x1;
    public static Number x2;
    public static Number p;
    public static Number q;

    public static void JSONAPITEST() throws Exception {
        try {
            JSONObject json = new JSONObject(readUrl("http://localhost:8080/math/resolv/"+expressionstring));

            status = (int) json.get("status");
            System.out.println("status: " + status);
            if ((int) status == 400){
                error = (String) json.get("error");
                System.out.println("Blad: " + error);
            }
            else {
                a = (int) json.get("A");
                System.out.println("A: " + a);

                b = (int) json.get("B");
                System.out.println("B: " + b);

                c = (int) json.get("C");
                System.out.println("C: " + c);

                d = (int) json.get("delta");
                System.out.println("Delta: " + d);

                if ((int) d == 0) {
                    x0 = (Number) json.get("x");
                    System.out.println("X: " + x0);

                    p = (Number) json.get("p");
                    System.out.println("P: " + p);

                    q = 0;
                    System.out.println("Q: " + q);
                } else if ((int) d > 0) {
                    x1 = (Number) json.get("x1");
                    System.out.println("X1: " + x1);

                    x2 = (Number) json.get("x2");
                    System.out.println("X2: " + x2);

                    p = (Number) json.get("p");
                    System.out.println("P: " + p);

                    q = (Number) json.get("q");
                    System.out.println("Q: " + q);
                } else if ((int) d < 0){
                    System.out.println("Delta ujemna");

                    p = (Number) json.get("p");
                    System.out.println("P: " + p);

                    q = (Number) json.get("q");
                    System.out.println("Q: " + q);
                }
            }
            System.out.println((String) "Wzor: " + expressionstring);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String SHOWRESOLV() throws Exception {

        JSONAPITEST();

        String messageresolv = null;

        if((int) status == 200) {

            if ((int) d == 0) {
                messageresolv = "Delta: " + d + "\n\n" + "Miejsce zerowe wynosi: \n\n" + "X0: " + x0 + "\n\n" + "Wierzcholek P: " + p + "\n" + "Wierzcholek Q: " + q;
            } else if ((int) d > 0) {
                messageresolv = "Delta: " + d + "\n\n" + "Miejsca zerowe wynosza: \n\n" + "X1: " + x1 + "\n" + "X2: " + x2 + "\n\n" + "Wierzcholek P: " + p + "\n" + "Wierzcholek Q: " + q;
            } else if ((int) d < 0) {
                messageresolv = "Delta ujemna. \n\n" + "Wierzcholek P: " + p + "\n" + "Wierzcholek Q: " + q;
            }
        }
        else if((int) status == 400){
            messageresolv = "Blad: " + error;
        }
        return messageresolv;

    }

    private static String readUrl(String urlString) throws Exception {

        BufferedReader reader = null;

        try {
            URL url = new URL(urlString); // URL do parsowania
            System.setProperty("http.agent", "Chrome"); //dodatek, ktory pomaga parsowac strone www
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();

        } finally {
            if (reader != null)
                reader.close();
        }
    }

}