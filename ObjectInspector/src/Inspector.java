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
		Class objectClass = obj.getClass();
		
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
		
		inspectMethods(objectClass);
		
		inspectFields(obj, objectClass, objectsToInspect);
		
		if (recursive && objectsToInspect.size() > 0)
			inspectFieldRecursive(obj, objectClass, objectsToInspect);
	}
	
	//inspects fields 
	public void inspectFields(Object obj, Class ObjectClass, Vector objectsToInspect){

		System.out.println("Declaring class: " + ObjectClass.getDeclaringClass());

		
			//if there is at least one field to inspect
		if (ObjectClass.getDeclaredFields().length >= 1){
			
			//System.out.println("Inspecting " + ObjectClass.getName());
			
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
					//for object fields, print pointer value when recursion is off
				else if (!field.getType().isPrimitive() && !recursive)
					System.out.println("Current value (pointer): " + field.get(obj));
				
				System.out.println("");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
				//if the object is a child of another class, inspect that superclass' value
			if (ObjectClass.getSuperclass() != null){
				System.out.println("Inspecting superclass: " + ObjectClass.getSuperclass());
				inspectFields(obj, ObjectClass.getSuperclass(), objectsToInspect);
			}
		}
	}
	
	public void inspectMethods(Class objectClass){
		
		System.out.println("---- Inspecting Declared Methods ----");
		
		Method[] methods = objectClass.getDeclaredMethods();
		
			//print out components of methods in the field
		for (Method method : methods){
			
			//Method
			System.out.println("Method: " + method.toString());
			
			//Name
			System.out.println("Name: " + method.getName());
			
			//Exceptions thrown
			System.out.println("Exception types: ");
			
			Type[] exceptions = method.getExceptionTypes();
			for (Type ex : exceptions){
				System.out.println(ex.toString());
			}
			
			//parameter types
			System.out.println("Parameter types: ");
			
			Type[] parameters = method.getParameterTypes();
			for (Type par : parameters){
				System.out.println(par.toString());
			}
			
			//return types
			System.out.println("Return type: " + method.getReturnType().toString());
			
			//modifiers
		}
	}
	
	//TODO
	public void inspectConstructors(Class ObjectClass){
	
		System.out.println("---- Inspecting Declared Constructors ----");
		System.out.println("TODO!!!!");

		
		//getDeclaredConstructors
	}
	
	public void inpectInterfaces(Class objectClass){
		//getInterface
		
		System.out.println("---- Inspecting Implemented Interfaces ----");
		System.out.println("TODO!!!!");

		
	}
	
	public String parseFieldModifiers(Field field){ 
		
		return Modifier.toString(field.getModifiers());
	}
	
	//TODO
	public void inspectFieldRecursive(Object obj, Class ObjectClass, Vector objectsToInspect){
		if (objectsToInspect.size() > 0)
		    System.out.println("---- Inspecting Field Objects ----");

		Enumeration elements = objectsToInspect.elements();

		//loop until every element has been inspected
		while(elements.hasMoreElements()){
			
			Field field = (Field) elements.nextElement();
			field.setAccessible(true);
			
			try{
				System.out.println("******************");
				inspect(field.get(obj), recursive);
				System.out.println("******************");		
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
