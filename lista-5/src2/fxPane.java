import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;

public class fxPane extends Pane {
    fxgui ref;
    fxrectangle rec;

    public fxPane(fxgui _ref){
        super();
        rec = new fxrectangle(0, 0, 0, 0);
        rec.setColor(0, 0, 0, 0.4);
        getChildren().add(rec);
        ref = _ref;

        fxPopup pop = new fxPopup();
        
        setOnMousePressed(new fxMouseEventHandler());
        setOnMouseDragged(new fxMouseEventHandler());
        setOnMouseReleased(new fxMouseEventHandler());
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.SECONDARY)){
                    pop.showCP(event.getX(),event.getY());
                }
            }
        });
    }
    private class fxPopup extends Popup{
        ColorPicker cp;

        public fxPopup(){
            super();
            setAutoFix(true);
            setAutoHide(true);

            cp = new ColorPicker();
            getContent().add(cp);
        }
        protected void showCP(double x,double y){
            setX(x+ref.mainStage.getX());
            setY(y+ref.mainStage.getY()+55);
            show(ref.mainStage);
        };
    }

    private class fxMouseEventHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                if(ref.buttonPressed.equals("RECTANGLE")){
                    if(event.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
                        rec.anchor(event.getX(), event.getY());
                    }
                    if(event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)){
                        rec.resize(event.getX(), event.getY());
                    }
                    if(event.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
                        ref.buttonPressed = "IDLE";
                        System.out.println("x");
                        ref.addShape(new fxrectangle(rec));
                    }
                }
            }
            else if(event.getButton().equals(MouseButton.SECONDARY)){
                
            }
        }
    }
}
