import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class fxCanvas extends Canvas {
    public State currState = State.IDLE;
    public boolean isDrawing = false;

    private double startX;
    private double startY;
    Shape shape;


    public fxCanvas(){
        super(500,464);
        setTranslateY(36);
        setOnMousePressed(new fxMouseEventHandler());
        setOnMouseDragged(new fxMouseEventHandler());
        setOnMouseReleased(new fxMouseEventHandler());

        getGraphicsContext2D().setFill(Color.ANTIQUEWHITE);
        getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());
        getGraphicsContext2D().setFill(Color.FORESTGREEN.brighter());
        getGraphicsContext2D().setStroke(Color.FORESTGREEN.brighter());
    }
    private class fxMouseEventHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED) && event.getButton().equals(MouseButton.PRIMARY)){
                isDrawing = true;
                startX = event.getX();
                startY = event.getY();
                System.out.println("START DRAG: "+startX+" "+startY);
            }
            if(event.getEventType().equals(MouseEvent.MOUSE_RELEASED) && isDrawing){
                isDrawing = false;
                System.out.println("END DRAG: "+startX+" "+startY);
                
            }
        }
    }
}

