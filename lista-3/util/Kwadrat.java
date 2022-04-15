package util;

public abstract class Kwadrat extends Czworokat {
    public double circumference(double b){
        return super.circumference(b,b,b,b);
    };
    public double area(double b){
        return super.area(b,b,b,b,180.0);
    };
}
