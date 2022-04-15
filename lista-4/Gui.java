import java.awt.*;
import java.awt.event.*;
import java.util.Vector;



public class Gui extends Frame{
    private int width;
    private int height;

    private TextField tf;
    private Button btn;
    private Container labelContainer;
    private Vector<Label> label;

    Gui(int _width,int _height){
        width = _width;
        height = _height;
        setSize(width, height);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
             }
         }
        );
    };
    Gui(){
        this(640,480);
    };

    void addLabel(String str){
        Label temp = new Label(str);
        temp.setText(str);
        temp.setFont(new Font("serif",Font.PLAIN,32));
        label.add(temp);
        labelContainer.setLayout(new GridLayout(1,label.size()));
        labelContainer.add(temp);
        add(labelContainer,BorderLayout.CENTER);
    };

    public static void main(String[] args) {
        new Gui();
    }
}