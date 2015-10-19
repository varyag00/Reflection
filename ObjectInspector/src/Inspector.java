/* Name:		J. Daniel Gonzalez
 * UCID:		10058656
 * Class:		CPSC 501
 * Ass:			2
 * 
 * Sources:		I used Jordan Kidney's ObjectInspector.java as a basis for the field introspection section of this code. I did not directly use any of his code.
 */


import java.util.*;
import java.lang.reflect.*;

public class Inspector {
	private boolean recursive;
	
	public Inspector (){
		//do work, or not
	}
	
	//does introspection on a object
	public void inspect(Object obj, boolean recursive){
		
		//set recursive
		try{
			this.recursive = recursive;
		}
		catch(Exception e){
			recursive = false;
		}
		
		Vector objectsToInspect = new Vector();
		Class ObjectClass = obj.getClass();
		
		System.out.println("recursive = "+ recursive + "\n\nObject argument: " + obj);
		
		InspectFields(obj, ObjectClass, objectsToInspect);
	}
	
	public void InspectFields(Object obj, Class ObjectClass, Vector objectsToInspect){
		
	}
}
