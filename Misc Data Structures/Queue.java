public interface Queue 
{
	/** Tell whether the queue has no more elements. */
	public boolean isEmpty();

	/** Return the value that dequeue would give without modifying
	* the queue. Throw an Exception if the queue is empty. */
	public int peekFront();

	/** Remove and return the value that has been in the queue the
	* most time. Throw an Exception if the queue is empty. */
	public int dequeue();

	/** Add the given value to the queue. */
	public void enqueue (int ob);
}
