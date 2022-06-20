class TreeElem<T extends Comparable<T>>{
    final T elem;
    TreeElem<T> left;
    TreeElem<T> right;

    TreeElem(T elem){
        this.elem = elem;
        left = null;
        right = null;
    }
    public String toString(){
        return elem.toString();
    }
}

public class Tree<T extends Comparable<T>>{
    private TreeElem<T> root;
    public Tree(){
        root = null;
    }
    public boolean search(T elem){
        return isElem(elem,root);
    }
    private boolean isElem(T elem, TreeElem<T> w){
        if(w==null)
            return false;
        else if(elem.compareTo(w.elem)==0)
            return true;
        else if(elem.compareTo(w.elem)<0)
            return isElem(elem, w.left);
        else
            return isElem(elem, w.right);
    }
    public void insertAll(T[] elements){
        for(T e : elements){
            insert(e);
        }
    }
    public void insert(T elem){
        root = ins(elem, root);
    }
    private TreeElem<T> ins(T elem,TreeElem<T> w){
        if(w == null)
            return new TreeElem<T>(elem);
        if(elem.compareTo(w.elem)<0)
            w.left = ins(elem,w.left);
        else if(elem.compareTo(w.elem)>0)
            w.right = ins(elem,w.right);
        return w;
    }
    public void delete(T elem){
        root = removeNode(elem,root);
    }
    private TreeElem<T> removeNode(T elem, TreeElem<T> w){
        if(w == null)
            return null;
        else if(elem.compareTo(w.elem)==0){
            w = null;
        }
        else if(elem.compareTo(w.elem)<0)
            w.left = removeNode(elem, w.left);
        else if(elem.compareTo(w.elem)>0)
            w.right = removeNode(elem, w.right);
        return w;
    }
    public String draw(){
        return drawNode(root);
    }
    public String drawNode(TreeElem<T> w){
        if(w==null)
            return "";
        else
            return drawNode(w.left)+" "+w.elem.toString()+" "+drawNode(w.right);
    }
}
