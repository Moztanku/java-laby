package util;

public abstract class Szesciokat implements Figura {
    public double circumference(double bok){
        return 6.0*bok;
    };
    public double area(double bok){
        return 1.5*Math.pow(bok, 2.0)*Math.sqrt(3);
    };
}

// h = b sqrt(3) / 2
// T = a^2 sqrt(3) / 4
// P = 3/2