import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;


public class InspectorTest {

	Inspector ins;
	TestClass testObject;
	Class testClass;
	Field[] testFields;
	Field testField;
	
	@Before
	public void setUp() throws Exception {
	
		//setting us up the test variables
		ins = new Inspector();
		testObject = new TestClass();
		testClass = testObject.getClass();
		testFields = testClass.getDeclaredFields();
	}

	@After
	public void tearDown() throws Exception {
		
		ins = null;
		testObject = null;
		testClass = null;
		testFields = null;
	}

	@Test
	public void testInspectArray() {
		
			//testField = testEArray
		testField = testFields[0];
		
		
		
		
	}
	
	@Test
	public void testInspectMethods(){
		
	}
	
	@Test
	public void testInspectConstructor(){
		
	}
	
	@Test 
	public void testPrintFieldInfo(){
		
	}

	@Test
	public void testInspectInterfaces(){
		
	}
}
