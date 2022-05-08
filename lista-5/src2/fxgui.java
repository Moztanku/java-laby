import java.util.Vector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class fxgui extends Application{
    public String buttonPressed = "IDLE";
    private fxPane page;
    protected Stage mainStage;
    private Vector<Shape> shapes;
   // private Shape focusedShape;

    @Override
    public void start(Stage _mainStage) throws Exception {
        mainStage = _mainStage;
        shapes = new Vector<Shape>();
        VBox mainBox = new VBox();

        HBox menu = new HBox();
        menu.setSpacing(5);
        menu.setStyle("-fx-background-color : #eee0ee;");
        menu.setPrefHeight(55);

        page = new fxPane(this);
        page.setStyle("-fx-background-color : #f0f0f0;");

        VBox.setVgrow(page, Priority.ALWAYS);
        mainBox.getChildren().addAll(menu,page);


        menu.getChildren().addAll(
            new fxButton("RECTANGLE"),
            new fxButton("ELLIPSE"),
            new fxButton("TRIANGLE"),
            new fxButton("INFO")
        );
        
        Scene scene = new Scene(mainBox,600,500);

        mainStage.setTitle("Figury Gui");
        mainStage.setScene(scene);
        mainStage.show();
    }
    protected Shape hitShapes(double x,double y){
        for(int i=0; i<shapes.size(); i++){
            if(shapes.get(i) instanceof fxrectangle){
                if(((fxrectangle)shapes.get(i)).isHit(x, y))
                    return shapes.get(i);
            }
        }
        return null;
    };
    protected void addShape(Shape shape){
        shapes.add(shape);
        page.getChildren().add(shape);
    };

    private class fxButton extends Button{
        public fxButton(String str){
            super(str);
            setFont(new Font("Impact",26));
            setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            HBox.setHgrow(this, Priority.ALWAYS);
            setOnAction(event -> buttonPressed=str);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
