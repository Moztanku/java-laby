package util;

public abstract class Czworokat implements Figura {
    public double circumference(double b1, double b2, double b3, double b4){
        return b1+b2+b3+b4;
    }
    public double area(double b1, double b2, double b3, double b4, double kat){ 
        final double s = circumference(b1,b2,b3,b4)/2.0;
        return Math.sqrt((s-b1)*(s-b2)*(s-b3)*(s-b4)-b1*b2*b3*b4*(Math.pow(Math.cos(Math.toRadians(kat)/2),2.0)));
    };
}
