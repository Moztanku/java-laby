public class Test {
    public static void main(String[] args) {
        Tree<Integer> t = new Tree<Integer>();

        t.insertAll(new Integer[]{10,7,11,4,8,2,5,9,1});

        t.delete(11);

        System.out.println("Wypisywanie elementow drzewa");
        t.draw();
        //System.out.println(d);
    }
}
