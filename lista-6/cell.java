import java.util.Random;

import javafx.application.Platform;
import javafx.print.PrintColor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class cell extends Thread{
    private static int k = 1000;
    private static double p = 0.5;
    private static Color defautlColor = Color.ANTIQUEWHITE;
    private static final Random r = new Random();



    public Rectangle rect = new Rectangle(0,0,0,0);
    private int timeremaining;
    private boolean isActive = true;

    public cell(double x, double y,double w, double h){
        rect.setWidth(w);
        rect.setHeight(h);
        rect.setX(x);
        rect.setY(y);
        rect.setFill(defautlColor);
        this.start();
    };

    @Override
    public void run() {
        try {
            int sleeptime = r.nextInt(k)+k/2;
            Thread.sleep(
                sleeptime
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isActive){
            updateColour();
            this.run();
        }
    };
    private void updateColour(){
        if(r.nextDouble(1.0)<=p)
            randomColour();
        else
            calcColour();
    };
    private void randomColour(){
        Platform.runLater(()->{
            rect.setFill(
                Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256))
            );
        });
    };
    private void calcColour(){

    };

    public void setActive(boolean b){
        isActive = b;
    };
    public boolean getActive(){
        return isActive;
    };
    public Rectangle getRect(){
        return rect;
    };


    public static void setDefaultColour(Color c){
        defautlColor = c;
    };
    public static void setKP(int _k,double _p){
        k=_k;
        p=_p;
    };
}
