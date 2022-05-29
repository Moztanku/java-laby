import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class nmPane extends Pane {
    private cell cells[][];

    public nmPane(int n,int m, double cellWidth, double cellHeight){
        super();


        cells = new cell[n][m];
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++){
                cells[i][j] = new cell(j*cellWidth,i*cellHeight, cellWidth, cellHeight);
                getChildren().add(cells[i][j].getRect());
             }
        startThreads();
    };

    public void startThreads(){
        for(cell[] x : cells)
            for(cell c : x)
                c.setActive(true);
    }
    public void stopThreads(){
        for(cell[] x : cells)
            for(cell c : x)
                c.setActive(false);
    };
}
