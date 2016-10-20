public class ArrayStack implements Stack {
  
  private java.util.ArrayList items;
  
  public ArrayStack() {
    items = new java.util.ArrayList(); 
  }
  
  public boolean isEmpty() { return items.size() == 0; }
 
  public void push(Object obj) { items.add(obj); }
 
  public Object pop() { return items.remove(items.size() - 1); }
 
  public Object peekTop() { return items.get(items.size() - 1); }
}
    