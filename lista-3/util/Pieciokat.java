package util;

public abstract class Pieciokat implements Figura {
    public double circumference(double bok){
        return 5.0*bok;
    };
    public double area(double bok){
        return 0.25*Math.sqrt(5*(5+2*Math.sqrt(5)))*Math.pow(bok, 2.0);
    };
}

//  72o 36o 54o  
//  1/2*h*1/2b * 10
// 
