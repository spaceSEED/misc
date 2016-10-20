// don't modify

public class TreeNode implements Comparable
{
    public TreeNode(Object initValue,Object initkey, TreeNode initLeft, TreeNode initRight) { 
        value = initValue; 
        key=initkey;
        left = initLeft; 
        right = initRight; 
    }

    public TreeNode(Object initValue,Object initkey){
        value = initValue;
        key=initkey;
        left = right = null;
    }
    
    public Object getKey() { return key; }
    public Object getValue() { return value; }
    public TreeNode getLeft() { return left; }
    public TreeNode getRight() { return right; }
    public void setValue(Object theNewValue) { value = theNewValue; }
    public void setLeft(TreeNode theNewLeft) { left = theNewLeft;  }
    public void setRight(TreeNode theNewRight) { right = theNewRight;  }

    public int compareTo(Object a){
        TreeNode b= (TreeNode)a;
        Comparable c=(Comparable) key;
        if(c.compareTo(b.getKey())>0){
            return 1;
        }else if(c.compareTo(b.getKey())<0){
            return -1;
        }
        return 0;
     }
    private Object key;
    private Object value;
    private TreeNode left;
    private TreeNode right;
} 