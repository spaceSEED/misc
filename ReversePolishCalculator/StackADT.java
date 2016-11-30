public interface StackADT // not in the Sun library
{
	/** Tell whether the stack has no more elements. 
	*/
	public boolean isEmpty();

	/** Return the value that pop would give, without modifying
	* the stack. Throw an Exception if the stack is empty. 
	*/
	public String peekTop() throws StackException;

	/** Remove and return the value that has been in the stack the
	* least time. Throw an Exception if the stack is empty. 
	*/
	public String pop() throws StackException;

	/** Add the given value to the stack. */
	public void push (String ob);
}

