import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class fxrectangle extends Rectangle {
    private double ax,ay;
    ContextMenu cm;
    public fxrectangle(double x,double y,double w,double h){
        super(x,y,w,h);
        
        Random random = new Random();
        setFill(new Color(random.nextDouble(1), random.nextDouble(1), random.nextDouble(1), random.nextDouble(1)));
        setOnMouseClicked(new fxMouseEventHandler());
    };
    public fxrectangle(fxrectangle rec){
        this(rec.getX(),rec.getY(),rec.getWidth(),rec.getHeight());
    }

    public boolean isHit(double x,double y){
        return getBoundsInLocal().contains(x,y);
    };
    public void setColor(double r,double g,double b,double a){
        setFill(new Color(r,g,b,a));
    }
    public void anchor(double x,double y){
        ax=x;
        ay=y;
        setX(ax); setWidth(0);
        setY(ay); setHeight(0);
    }
    public void resize(double x,double y){
        if(x<ax){
            setX(x);
            setWidth(ax-x);
        }else{
            setX(ax);
            setWidth(x-ax);
        }
        if(y<ay){
            setY(y);
            setHeight(ay-y);
        }else{
            setY(ay);
            setHeight(y-ay);
        }
    }

    private class fxMouseEventHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            if(event.getButton().equals(MouseButton.SECONDARY)){
                
            }
        }
    }
}
