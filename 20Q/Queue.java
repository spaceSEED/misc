// don't modify

public interface Queue 
{ 
  boolean isEmpty(); 
  void enqueue(Object x); 
  Object dequeue(); 
  Object peekFront(); 
} 

// exercise: use a LinkedList to implement