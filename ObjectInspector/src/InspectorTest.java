import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;


//https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println for testing console output

public class InspectorTest {

	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	Inspector ins;
	TestClass testObject;
	Class testClass;
	Field[] testFields;
	Field testField;
	
	@Before
	public void setUp() throws Exception {
			
		//setting console output to outContent
		System.setOut(new PrintStream(outContent));
	
		//setting us up the test variables
		ins = new Inspector();
		testObject = new TestClass();
		testClass = testObject.getClass();
		testFields = testClass.getDeclaredFields();
	}

	@After
	public void tearDown() throws Exception {
		
	    System.setOut(null);
		
		ins = null;
		testObject = null;
		testClass = null;
		testFields = null;
	}

	@Test
	public void testInspectArray() {
		
		//testField = testEArray
		testField = testFields[0];
		ins.inspectArray(testField, testObject);
	    assertEquals("Array not initialized\n", outContent.toString());
	    outContent.reset();
	    
		//testField = testEArray
		testField = testFields[1];
		ins.inspectArray(testField, testObject);
	    assertEquals("testIArray[0] = 1\ntestIArray[1] = 2\ntestIArray[2] = 3\n", outContent.toString());
	    outContent.reset();
	    
		//testField = testEArray
		testField = testFields[2];
		ins.inspectArray(testField, testObject);
	    assertEquals("testOArray[0] = hello\ntestOArray[1] = world!\n", outContent.toString());
	    outContent.reset();
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
		
		ins.inpectInterfaces(testClass);
	    assertEquals("---- Inspecting Implemented Interfaces ----\nInterface: interface java.io.Serializable\n", outContent.toString());
	}
}
