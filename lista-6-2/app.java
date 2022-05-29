import java.util.Map;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * @author Jacek Zub
 * Main application class, creating space for cells
 */
public class app extends Application{
   cell[][] cells;


    /**
     * Setting up stage and creating cells
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        final double windowW = 1280.0;
        final double windowH = 960.0;
        int m = 50;
        int n = 50;
        int k = 1000;
        double p = 0.02;
        
        try{
            m = Integer.parseInt(getParameters().getNamed().get("m"));
            n = Integer.parseInt(getParameters().getNamed().get("n"));
            k = Integer.parseInt(getParameters().getNamed().get("k"));
            p = Double.parseDouble(getParameters().getNamed().get("p"));
        }catch(NumberFormatException ex){
            System.err.println("You must provide --m,--n,--k,--p parameters (for ex. --m=100 --n=100 --k=1000 --p=0.01)");
        }
        double cellW = windowW/m;
        double cellH = windowH/n;

        Group root = new Group();
        Canvas canvas = new Canvas(windowW,windowH);
        root.getChildren().add(canvas);

        cell.setGraphicsContext(canvas.getGraphicsContext2D());
        cell.setKP(k, p);
        cells = new cell[m][n];
        
        populateAllCells(cellW, cellH, n, m);
        setActiveOnAllThreads(true);
        for(int y=0; y<m; y++)
            for(int x=0; x<n; x++){
                if(x==0)
                    cells[y][x].setNeighbourW(cells[y][n-1]);
                else
                    cells[y][x].setNeighbourW(cells[y][x-1]);
                if(x==n-1)
                    cells[y][x].setNeighbourE(cells[y][0]);
                else
                    cells[y][x].setNeighbourE(cells[y][x+1]);
                if(y==0)
                    cells[y][x].setNeighbourN(cells[m-1][x]);
                else
                    cells[y][x].setNeighbourN(cells[y-1][x]);
                if(y==m-1)
                    cells[y][x].setNeighbourS(cells[0][x]);
                else
                    cells[y][x].setNeighbourS(cells[y+1][x]);
            };

        mainStage.setTitle("Cell's");
        mainStage.setScene(new Scene(root));
        mainStage.setResizable(false);
        mainStage.show();
        mainStage.setOnCloseRequest(event->{
            killAllThreads();
        });
        
        canvas.setOnMouseClicked(event->{
            int x = (int)(event.getX()/cellW);
            int y = (int)(event.getY()/cellH);
            cells[y][x].setActive(!cells[y][x].getActive());
        });
        startAllThreads();
    }
    /**
     * Starts Javafx application
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void populateAllCells(double cellW,double cellH,int n,int m){
        for(int y=0; y<m; y++)
            for(int x=0; x<n; x++)
                cells[y][x] = new cell(x*cellW, y*cellH, cellW, cellH);
    }
    private void startAllThreads(){
        for(cell[] a : cells)
            for(cell c : a)
                c.start();
    }
    /**
     * Set isActive parameter on all threads
     * @param b value to be set, either true or false
     */
    private void setActiveOnAllThreads(boolean b){
        for(cell[] a : cells)
            for(cell c : a)
                c.setActive(b);
    }
    private void killAllThreads(){
        for(cell[] a : cells)
            for(cell c : a)
                c.killThread();
    }
};
