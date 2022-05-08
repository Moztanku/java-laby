import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface fxShape{
    public void setAnchor(double x,double y);
    public boolean ishit(double x,double y);
    public void resize(double x,double y);
    public void move(double x,double y);
    public void scale(double x);
    public void rotate(double x);


    public void setColor(Color color);
    public Color getColor();
    public Shape getShape();
}