
import java.util.*;

public class BinaryTree  {
    private TreeNode myRoot;
    // create no other instance/state variables

    public BinaryTree() { myRoot = null; }

    /*public void testTree(){
        TreeNode j=new TreeNode("J",null,null);
        TreeNode i=new TreeNode("I",null,null);
        TreeNode h=new TreeNode("H",null,null);
        TreeNode g=new TreeNode("G",null,null);
        TreeNode f=new TreeNode("F",i,j);
        TreeNode e=new TreeNode("E",null,null);
        TreeNode d=new TreeNode("D",null,h);
        TreeNode c=new TreeNode("C",f,g);
        TreeNode b=new TreeNode("B",d,e);
        myRoot=new TreeNode("A",b,c);
    }

    public void test(){
        testTree();
        System.out.println("Leaves:"+numLeaves());
        System.out.println("Nodes:"+numNodes());
        System.out.println("Height:"+height());
        System.out.println("Full:"+isFull());
        System.out.println("Width:"+width());
        System.out.println("PreOrder:"+preOrder());
        System.out.println("PostOrder:"+postOrder());
        System.out.println("InOrder:"+toString());
        System.out.println("LevelOrder:"+levelOrderTraversal());
    }*/

    // -----------------------------------------------------------------------------------
    // Part 1 - Binary Tree Methods Only
    // -----------------------------------------------------------------------------------

    public int numNodes() {
        // solve recursively by calling helper
        return numNodesHelper(myRoot);
    }

    private int numNodesHelper(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.getLeft()==null&&root.getRight()==null){
            return 1;
        }
        else{
            return 1+numNodesHelper(root.getLeft())+numNodesHelper(root.getRight());
        }
    }

    public int numLeaves() {
        // recursive sol'n by calling helper
        return numLeavesHelper(myRoot);
    }

    private int numLeavesHelper(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.getLeft()==null&&root.getRight()==null){
            return 1;
        }
        else{
            return numLeavesHelper(root.getLeft())+numLeavesHelper(root.getRight());
        }
    }

    public int height() {
        // recursive sol'n - see handout 
        if(myRoot==null){
            return 0;
        }
        return heightHelper(myRoot);
    }

    private int heightHelper(TreeNode root){
        if(root==null){
            return 0;
        }
        int a=0;
        int b=0;
        if(root.getLeft()==null&&root.getRight()==null){
            a=1;
            return a;
        }else{
            a= 1+heightHelper(root.getLeft());
            b= 1+heightHelper(root.getRight());
        }
        if(a>b){
            return a;
        }else{
            return b;
        }
    }

    public int width() {
        // recursive sol'n - see handout 
        return widthHelper(myRoot);
    }

    private int widthHelper(TreeNode root){
        int a=0;
        int b=0;
        int c=0;
        if(root==null){
            a=0;
            return a;
        }else{
            a=heightHelper(root.getLeft())+1+heightHelper(root.getRight());
            b=widthHelper(root.getLeft());
            c=widthHelper(root.getRight());
        }
        if(c>=a&&c>=b){
            return c;
        }else if(b>=c&&b>=a){
            return b; 
        }else{
            return a;
        }
    }

    public boolean isDescendant(Comparable ancestor, Comparable possibleDescendant) {
        // return true if possibleDescendant is a descendant of ancestor
        // precond: ancestor and possibleDescendant not necessarily in the tree
        TreeNode a=isDescendantHelper(myRoot, ancestor);
        if(isDescendantHelper(myRoot, ancestor)!=null){
            a=isDescendantHelper(a,possibleDescendant);
        }
        return a!=null;
    }

    public TreeNode isDescendantHelper(TreeNode root, Comparable find){
        TreeNode b,c;
        if(root==null){
            return null;
        }
        if(find.compareTo(root)==0){
            return root;
        }else{
            b=isDescendantHelper(root.getLeft(), find); 
            c=isDescendantHelper(root.getRight(), find); 
        }
        if(b!=null){
            return b;
        }else{
            return c;
        }
    }

    public boolean isFull() {
        // note: an empty tree is full
        if(myRoot==null){
            return true;
        }
        return isFullHelper(myRoot);
    }

    private boolean isFullHelper(TreeNode root) {
        if(root.getLeft()==null&&root.getRight()==null){
            return true;
        }else if(root.getLeft()==null&&root.getRight()!=null){
            return false;
        }else if(root.getLeft()!=null&&root.getRight()==null){
            return false;
        }else{
            return isFullHelper(root.getLeft())&&isFullHelper(root.getRight());
        }
    }

    public void clear() {
        myRoot = null;  // this method is done
    }

    // -----------------------------------------------------------------------
    // Part 2 - Binary Tree Traversal
    // -----------------------------------------------------------------------

    public String preOrder() {
        // should be formatted the same way as described in toString() above
        return preOrderRecursiveHelper(myRoot);
    }

    private String preOrderRecursiveHelper(TreeNode root){
        if(root==null){
            return "";
        }
        String s="";
        s+=preOrderRecursiveHelper(root.getLeft())+preOrderRecursiveHelper(root.getRight());  
        return root.getValue()+""+s;
    }

    public String postOrder() {
        // should be formatted the same way as described in toString() above
        return postOrderRecursiveHelper(myRoot);
    }

    private String postOrderRecursiveHelper(TreeNode root){
        if(root==null){
            return "";
        }
        String s="";
        s+=postOrderRecursiveHelper(root.getLeft())+postOrderRecursiveHelper(root.getRight());  
        return s+""+root.getValue();
    }

    public String levelOrderTraversal() {
        // no recursion - you must use a stack or a queue (you figure out which)
        Stack stk = new ArrayStack();    // if you use a stack, uncomment this line
        Stack stk2 = new ArrayStack();
        TreeNode top=null;
        Queue q = new LinkedListQueue();
        stk.push(myRoot);
        TreeNode root=myRoot;
        do{
            if(!stk.isEmpty()){
                top=(TreeNode)stk.peekTop();
            }
            while(!stk.isEmpty()){
                root=(TreeNode)stk.pop();
                if(root.getRight()!=null){
                    stk2.push(root.getRight());
                }
                if(root.getLeft()!=null){
                    stk2.push(root.getLeft());
                }
                if(root==myRoot){
                    stk2.push(root);
                }
            }
            while(!stk2.isEmpty()){
                stk.push(stk2.pop());
            }
        }
        while(top!=stk.peekTop());
        // format should be the same as described in toString() above 
        while(!stk.isEmpty()){
            stk2.push(stk.pop());
        }
        while(!stk2.isEmpty()){
            q.enqueue(stk2.pop());
        }
        String a="";
        while(!q.isEmpty()){
            root=(TreeNode)q.dequeue();
            if(root!=null){
                a+=root.getValue();
            }
        }
        return a;
    }

    public String toString() {
        // solve recursively for an in-order traversal

        // there should be a comma and single space between each item
        // there should be square brackets around the whole thing
        // e.g., if you had a tree with String data items A, B, and C, then your toString()
        // would return  [A, B, C]
        // do an in-order traversal
        // an empty tree should return []

        // use toStringHelper so you can solve this recursively  
        return toStringHelper(myRoot);
    }

    private String toStringHelper(TreeNode root){
        if(root==null){
            return "";
        }
        return toStringHelper(root.getLeft())+root.getValue()+toStringHelper(root.getRight());
    }

}