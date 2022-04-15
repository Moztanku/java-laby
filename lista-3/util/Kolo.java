package util;

public abstract class Kolo implements Figura {
    public double circumference(double radius){
        return 2.0*Math.PI*radius;
    };
    public double area(double radius){
        return Math.PI*Math.pow(radius, 2.0);
    };
}
