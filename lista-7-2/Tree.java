/**
 * Binary tree implementation
 * @author Jacek Zub
 */
public class Tree<T extends Comparable<T>>{
    private TreeNode<T> root;
    public Tree(){
        root = null;
    }
    public boolean search(T elem){
        return isElem(elem,root);
    }
    private boolean isElem(T elem, TreeNode<T> w){
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
    private TreeNode<T> ins(T elem,TreeNode<T> w){
        if(w == null)
            return new TreeNode<T>(elem);
        if(elem.compareTo(w.elem)<0)
            w.left = ins(elem,w.left);
        else if(elem.compareTo(w.elem)>0)
            w.right = ins(elem,w.right);
        return w;
    }
    public void delete(T elem){
        root = removeNode(elem,root);
    }
    private TreeNode<T> removeNode(T elem, TreeNode<T> w){
        if(w == null)
            return null;
        else if(elem.compareTo(w.elem)==0){ //  If this node is to be deleted
            if(w.right == null) //  Sets it to left node if right is non existent
                w = w.left;
            else if(w.left == null) //  To right if left is
                w = w.right;
            else{
                w = concatNode(w.left, w.right);    //  Or connects left node to right's node lowest left node
            }
        }
        else if(elem.compareTo(w.elem)<0)
            w.left = removeNode(elem, w.left);
        else if(elem.compareTo(w.elem)>0)
            w.right = removeNode(elem, w.right);
        return w;
    }
    private TreeNode<T> concatNode(TreeNode<T> l,TreeNode<T> r){
        if(r.left == null)
            r.left = l;
        else
            r.left = concatNode(l, r.left);
        return r;
    }
    public String draw(){
        return drawNode(root);
    }
    public String drawNode(TreeNode<T> w){
        if(w==null)
            return "";
        else
            return "{"+drawNode(w.left)+" "+w.elem.toString()+" "+drawNode(w.right)+"}";
    }
}

/**
 * Binary Tree Node
 * @author Jacek Zub
 */
class TreeNode<T extends Comparable<T>>{
    final T elem;
    TreeNode<T> left = null;
    TreeNode<T> right = null;

    public TreeNode(T elem){
        this.elem = elem;
    };
    public String toString(){
        return elem.toString();
    };
}