

//This is a class that I used for running JUnit Tests on
public class TestClass implements java.io.Serializable{

	//for testing inspectArray()
	int[] testEArray;
	int[] testIArray = {1,2,3};
	Object[] testOArray = {new String("hello"), new String("world!")};
	
	//for testing inspectConstructors()
	public TestClass(){ }
	
	
	//for testing inspectMethods()
	public void hello(String world) throws Exception{ }

	
}
