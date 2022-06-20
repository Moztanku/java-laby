import java.io.Console;

public class TreeTest {
    public static void main(String[] args) {
        Tree<Integer> t = new Tree<Integer>();

        t.insert(10);
        t.insert(12);
        t.insert(8);
        t.insert(2);
        t.insert(15);
        t.insert(1);
        t.insert(3);
        t.insert(9);
        t.insert(11);
        t.insert(14);
        t.insert(16);

        t.delete(1);
        t.delete(2);
        t.delete(14);
        t.delete(11);
        t.delete(15);

        Console console = System.console();
        System.out.println("Wypisywanie elementow drzewa: "+t.draw());
        console.readLine("21321321: ");
        System.out.println("Wypisywanie elementow drzewa: "+t.draw());
        //System.out.println(d);
    }
}
