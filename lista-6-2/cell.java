import java.util.Random;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Jacek Zub
 */
public class cell extends Thread {
    private static final Random r = new Random();
    private static int k = 1000;
    private static double p = 0.5;
    private static GraphicsContext gc;

    private cell[] neighbours;
    private Color color = Color.WHITE;
    private double x,y,w,h;
    private boolean kill = false;
    public void killThread(){
        kill = true;
    }
    private boolean active = false;
    public boolean getActive(){
        return active;
    }
    public void setActive(boolean b){
        active = b;
    }

    static public void setGraphicsContext(GraphicsContext x){
        gc = x;
    }
    /**
     * Sets the average wait time in ms for cells to update and their chance to get a random colour
     * @param _k time in ms between colour updates
     * @param _p chance to update to a random colour
     */
    static public void setKP(int _k,double _p){
        k=_k;
        p=_p;
    }

    
    public cell(double _x, double _y, double _w, double _h){
        super();
        neighbours = new cell[4];
        x=_x;
        y=_y;
        w=_w;
        h=_h;
        color = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        Platform.runLater(()->{
            gc.setFill(color);
            gc.fillRect(x, y, w, h);
        });
    }
    /**
     * Method running in this thread until killThread() is run
     */
    @Override
    public void run() {
        try {
            final int sleeptime = r.nextInt(k)+k/2;
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
        }
        if(active){
            updateColor();
            Platform.runLater(()->{
                gc.setFill(color);
                gc.fillRect(x, y, w, h);
            });
        }
        if(!kill)
            run();
    }
    /**
     * Updates the cell colour, with p chance to be set to a random and 1-p for one that
     * is calculated based on its neighbours
     */
    private void updateColor(){
        if(r.nextDouble(1.0)<=p) //ERQREQ
            color = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        else{
            int red = 0; int green = 0; int blue = 0; int activeCount = 0;
            for(int i=0; i<4; i++)
                if(neighbours[i].active){
                    red+=neighbours[i].color.getRed()*255;
                    green+=neighbours[i].color.getGreen()*255;
                    blue+=neighbours[i].color.getBlue()*255;
                    activeCount++;
                }
            if(activeCount!=0){
                color = Color.rgb(red/activeCount, green/activeCount, blue/activeCount);
            }else{
                color = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            }
        }
    };

    public void setColor(Color c){
        color = c;
    }
    public Color getColor(){
        return color;
    }
    public void setNeighbourW(cell c){
        neighbours[0]=c;
    }
    public void setNeighbourN(cell c){
        neighbours[1]=c;
    }
    public void setNeighbourE(cell c){
        neighbours[2]=c;
    }
    public void setNeighbourS(cell c){
        neighbours[3]=c;
    }
}
