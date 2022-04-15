package util;

public abstract class Romb extends Czworokat{
    public double circumference(double b){
        return super.circumference(b,b,b,b);
    };
    public double area(double b, double angle){
        return super.area(b,b,b,b,angle);
    };
};
