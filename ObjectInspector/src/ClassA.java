public final class ClassA implements java.io.Serializable, Runnable
{
	int[] literalArray = {1,2,3,4};
	
//	double[] doubleArray = {1.0, 2.0, 3.0, 3.14};
//
	int hello = 1;

	int world = 2;
	
	Object[] objectArray = {new Object(), new String("hello"), new String("world")};
	
	double[] literalArray2 = {1.0, 2.0, 3.0, 3.14};
	
    public ClassA() { val=3; }

    public ClassA(int i) 
	{

	    try { setVal(i); } catch(Exception e){}
	}

    public void run() { }

    public int getVal(){ return val; }
    public void setVal(int i) throws Exception
	{
	    if ( i < 0 ) 
		throw new Exception("negative value");

	    val = i;
	}

    public String toString() { return "ClassA"; }

    private void printSomething() { System.out.println("Something"); }

    private int val=3;
    private double val2 = 0.2;
    private boolean val3 = true;
}
