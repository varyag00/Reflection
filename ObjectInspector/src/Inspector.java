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
		
			//objects to inspect within out 
		Vector objectsToInspect = new Vector();
		Class ObjectClass = obj.getClass();
		
		System.out.println("recursive = " + recursive + "\n\nObject argument: " + obj);

		//TODO:
		/* 1. Inspect 
		 * 		declaring class
		 * 		name of immediate superclass
		 * 		name of the interface the class implements
		 * 		methods the class declares, with
		 * 			exceptions thrown
		 * 			parameter types
		 * 			return types
		 * 			modifiers
		 * 		constructors, with
		 * 			parameter types
		 * 			modifiers
		 * 		
		 */
		//inspect all of the above before inspecting fields
		
		
		inspectFields(obj, ObjectClass, objectsToInspect);
		
		if (recursive && objectsToInspect.size() > 0)
			inspectFieldRecursive(obj, ObjectClass, objectsToInspect);
	}
	
	//inspects fields 
	public void inspectFields(Object obj, Class ObjectClass, Vector objectsToInspect){

			//if there is at least one field to inspect
		if (ObjectClass.getDeclaredFields().length >= 1){
			
			Field field = ObjectClass.getDeclaredFields()[0];
			field.setAccessible(true);
			
				//if an object's field is not primitive and recursive is true, then that field is an object that must be inspected
			if (!field.getType().isPrimitive() && recursive){
				objectsToInspect.addElement(field);
			}
				
				//attempt to print the field's name
			try{
					//Name
				System.out.println("Field: " + field.getName() + " = " + field.get(obj));
					//Type
				System.out.println("Type: " + field.getType().toString());
					//Modifiers
				System.out.println("Modifiers: " + parseFieldModifiers(field));
					//Current value TODO: make it work for recursive
					//For primitive fields, print current value
				if (field.getType().isPrimitive())
					System.out.println("Current value: " + field.get(obj));
					//for object fields, 
				else
					System.out.println("Resursive function not yet implemented");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
				//if the object is a child of another class, inspect that superclass' 
			if (ObjectClass.getSuperclass() != null){
				inspectFields(obj, ObjectClass.getSuperclass(), objectsToInspect);
			}
		}
	}
	
	public String parseFieldModifiers(Field field){ 
		
		return Modifier.toString(field.getModifiers());
	}
	
	//TODO
	public void inspectFieldRecursive(Object obj, Class ObjectClass, Vector objectsToInspect){
		
	}
}
