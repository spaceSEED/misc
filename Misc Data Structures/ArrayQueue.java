

public class ArrayQueue implements Queue
{
   private int j[];
   private int strt=0;
   private int end=0;
   public ArrayQueue(){
       j = new int[199999];
    }
   public boolean isEmpty(){
       return end==strt;
    }

	/** Return the value that dequeue would give without modifying
	* the queue. Throw an Exception if the queue is empty. */
	public int peekFront()throws QueueException{
	    return j[strt];
	   }

	/** Remove and return the value that has been in the queue the
	* most time. Throw an Exception if the queue is empty. */
	public int dequeue() throws QueueException{
	   int c= j[strt];
	   strt++;
	   return c;
	   }

	/** Add the given value to the Queue. */
	public void enqueue (int ob){
	  j[end]=ob;
	  end++;
	   }
	   
	   
}

