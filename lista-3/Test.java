import util.Figura;
import util.Kolo;
import util.Czworokat;
import util.Kwadrat;
import util.Pieciokat;
import util.Prostokat;
import util.Romb;
import util.Szesciokat;

import java.util.Vector;

public class Test {
    public static void main(String[] args) {
        char[] figureStr = args[0].toCharArray();
        int index = 1;
        try{
            for(char c : figureStr){
                switch(c){
                    case 'o':{
                        try{
                            double temp = Double.parseDouble(args[index++]);
                            if(temp<0)
                                throw new NumberFormatException();
                                System.out.println(
                                    "Kolo, pole: "+
                                    Kolo.area(temp)+", Obwod: "+
                                    Kolo.circumference(temp));
                        }catch(NumberFormatException ex){
                            System.out.println(args[index-1]+" - Nieprawidlowa wartosc.");
                        }
                    }
                        break;
                    case 'p':{
                        try{
                            double temp = Double.parseDouble(args[index++]);
                            if(temp<0)
                                throw new NumberFormatException();
                                System.out.println(
                                    "Kolo, pole: "+
                                    Pieciokat.area(temp)+", Obwod: "+
                                    Pieciokat.circumference(temp));
                        }catch(NumberFormatException ex){
                            System.out.println(args[index-1]+" - Nieprawidlowa wartosc.");
                        }
                    }
                        break;
                    case 's':{
                        try{
                            double temp = Double.parseDouble(args[index++]);
                            if(temp<0)
                                throw new NumberFormatException();
                                System.out.println(
                                    "Kolo, pole: "+
                                    Szesciokat.area(temp)+", Obwod: "+
                                    Szesciokat.circumference(temp));
                        }catch(NumberFormatException ex){
                            System.out.println(args[index-1]+" - Nieprawidlowa wartosc.");
                        }
                    }
                        break;
                    case 'r':{
                        double tab[] = {1.0,180.0};
                        try{
                            double temp = Double.parseDouble(args[index++]);
                            if(temp < 0)
                                throw new NullPointerException();
                            tab[0] = temp;
                        }
                        catch(NumberFormatException ex){
                            System.out.println(args[index-1]+" - Nieprawidlowa wartosc.");
                        }
                        try{
                            double temp = Double.parseDouble(args[index++]);
                            if(temp < 0 || temp > 360)
                                throw new NullPointerException();
                            tab[1] = temp;
                        }
                        catch(NumberFormatException ex){
                            System.out.println(args[index-1]+" - Nieprawidlowa wartosc.");
                        }
                        System.out.println(
                            "Kolo, pole: "+
                            Romb.area(tab[0],tab[1])+", Obwod: "+
                            Romb.circumference(tab[0],tab[1]));
                    }
                        break;
                    case 't':{
                        double tab[] = {1.0,1.0};
                        try{
                            double temp = Double.parseDouble(args[index++]);
                            if(temp < 0)
                                throw new NullPointerException();
                            tab[0] = temp;
                        }
                        catch(NumberFormatException ex){
                            System.out.println(args[index-1]+" - Nieprawidlowa wartosc.");
                        }
                        try{
                            double temp = Double.parseDouble(args[index++]);
                            if(temp < 0)
                                throw new NullPointerException();
                            tab[1] = temp;
                        }
                        catch(NumberFormatException ex){
                            System.out.println(args[index-1]+" - Nieprawidlowa wartosc.");
                        }
                        System.out.println(
                            "Kolo, pole: "+
                            Prostokat.area(tab[0],tab[1])+", Obwod: "+
                            Prostokat.circumference(tab[0],tab[1]));
                    }
                        break;
                    case 'k':{
                        try{
                            double temp = Double.parseDouble(args[index++]);
                            if(temp<0)
                                throw new NumberFormatException();
                                System.out.println(
                                    "Kolo, pole: "+
                                    new Kwadrat.area(temp)+", Obwod: "+
                                    new Kwadrat.circumference(temp));
                        }catch(NumberFormatException ex){
                            System.out.println(args[index-1]+" - Nieprawidlowa wartosc.");
                        }
                    }
                        break;
                    default:{
                        System.out.println(c+" - Nieprawidlowy parametr.");
                    }
                        break;
                }
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println(" Wprowadzono za malo parametrow.");
        }
        }
}

// o - koło
// c - czworokat
// p - pieciokat
// s - szesciokat
// r - romb
// t - prostokat
// k -kwadrat
//
