import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class app extends Application{
    /**
     *
     */


    /**
     * 
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        final double windowW = 1280.0;
        final double windowH = 960.0;

        final int m = 100;
        final double cellW = windowW/m;
        final int n = 100;
        final double cellH = windowH/n;

        final int k = 100;
        double p = 0.5;
        cell.setKP(k,p);
        cell.setDefaultColour(Color.GRAY);

        nmPane root = new nmPane(n,m,cellW,cellH);
        Scene scene = new Scene(root, windowW, windowH);

        mainStage.setTitle("Cell's");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
        mainStage.setOnCloseRequest(event->{
            root.stopThreads();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
};
