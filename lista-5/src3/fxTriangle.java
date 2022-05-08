import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class fxTriangle extends  Polygon implements fxShape{
    private double anchorX;
    private double anchorY;
    private Color color;
    
    private double[] vertexA;
    private double[] vertexB;

    public fxTriangle(double x,double y,double _x,double _y){
        super();
        anchorX = x;
        anchorY = y;
        vertexA = new double[]{x,y};
        vertexB = new double[]{_x,_y};
        getPoints().addAll(
            vertexA[0],vertexA[1],
            vertexB[0],vertexB[1],
            2*vertexB[0]-vertexA[0],vertexA[1]
        );

        Random random = new Random();
        color = new Color(random.nextDouble(1), random.nextDouble(1), random.nextDouble(1), 1.0);
        setFill(color);
        setOnMousePressed(new fxMouseEventHandler());
        setOnMouseDragged(new fxMouseEventHandler());
        setOnScroll(new fxScrollEventHandler());
    }

    public void setAnchor(double x,double y){
        anchorX = x;
        anchorY = y;
    }
    public boolean ishit(double x,double y){
        return getBoundsInLocal().contains(x,y);
    }
    public void resize(double x,double y){
        getPoints().set(2, x);
        getPoints().set(3, y);
        getPoints().set(4, 2*x-getPoints().get(0));
        getPoints().set(5, getPoints().get(1));
    }
    public void move(double x,double y){
        getPoints().set(0, x-(anchorX-getPoints().get(0)));
        getPoints().set(1, y-(anchorY-getPoints().get(1)));
        getPoints().set(2, x-(anchorX-getPoints().get(2)));
        getPoints().set(3, y-(anchorY-getPoints().get(3)));
        getPoints().set(4, x-(anchorX-getPoints().get(4)));
        getPoints().set(5, y-(anchorY-getPoints().get(5)));
    }
    public void scale(double x){
        if(getScaleX()<0)
            setVisible(false);
        setScaleX(getScaleX()+x);
        setScaleY(getScaleY()+x);
    }
    public void rotate(double x){
        setRotate(getRotate()+x);
    }

    public void setColor(Color _color){
        color = _color;
        setFill(color);
    }
    public Color getColor(){
        return color;
    }
    public Shape getShape(){
        return this;
    }

    private class fxMouseEventHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
                if(event.getButton() == MouseButton.PRIMARY){
                    setAnchor(event.getSceneX(),event.getSceneY());
                }
                if(event.getButton() == MouseButton.MIDDLE){
                    setAnchor(event.getSceneX(),event.getSceneY());
                }
            }
            if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
                if(event.getButton() == MouseButton.PRIMARY){
                    move(event.getSceneX(),event.getSceneY());
                    setAnchor(event.getSceneX(),event.getSceneY());
                }
                if(event.getButton() == MouseButton.MIDDLE){
                    rotate((event.getSceneY()-anchorY)*0.1);
                    setAnchor(event.getSceneX(),event.getSceneY());
                }
            }
        }
    }
    private class fxScrollEventHandler implements EventHandler<ScrollEvent>{
        @Override
        public void handle(ScrollEvent event) {
            System.out.println("SCROLL: "+event.getDeltaY());
            scale(event.getDeltaY()*0.0004);
        }
    }
}
