public class LinkedListQueue implements Queue {
  private java.util.LinkedList items;
  
  public LinkedListQueue() {
    items = new java.util.LinkedList();
  }
  
  public boolean isEmpty() { return items.size() == 0; }
  
  public void enqueue(Object obj) { items.addLast(obj); }
  
  public Object dequeue() { return items.removeFirst(); }
  
  public Object peekFront() { return items.getFirst(); }
}