package util;

public abstract class Prostokat extends Czworokat {
    public double circumference(double b1, double b2){
        return super.circumference(b1,b2,b1,b2);
    };
    public double area(double b1,double b2){
        return super.area(b1,b1,b2,b2,180.0);
    };
}
