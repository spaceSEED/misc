

public class ArrayStackStr implements StackADT
{
   String j[];
   public ArrayStackStr(){
       j = new String[1];
    }
   public boolean isEmpty(){
       int n=0;
       for(int y=0; y<j.length;y++){
           if(j[y]!=null){
               n++;
            }
        }
       return n==0;
    }

	/** Return the value that pop would give, without modifying
	* the stack. Throw an Exception if the stack is empty. 
	*/
	public String peekTop()throws StackException{
	    return j[0];
	   }

	/** Remove and return the value that has been in the stack the
	* least time. Throw an Exception if the stack is empty. 
	*/
	public String pop()throws StackException{
	   String a=j[0];
	   String qwerty[]=new String[j.length-1];
	   for(int i=0;i<j.length-1;i++){
	       qwerty[i]=j[i+1];
	   }
	   j=qwerty;
	   return a;
	   }

	/** Add the given value to the stack. */
	public void push (String ob){
	    String qwerty[]=new String[j.length+1];
	     qwerty[0]=ob;
	  for(int i=0;i<j.length;i++){
	       qwerty[i+1]=j[i];
	   }
	   j=qwerty;
	   }
	   
	   
}

