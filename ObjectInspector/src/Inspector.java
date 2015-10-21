/* Name:		J. Daniel Gonzalez
 * UCID:		10058656
 * Class:		CPSC 501
 * Ass:			2
 * 
 * Sources:		I used Jordan Kidney's ObjectInspector.java as a basis for the field introspection section of this code. I did not directly use any of his code.
 */


import java.util.*;

import javax.jws.Oneway;
import javax.swing.tree.ExpandVetoException;

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
		
		System.out.println("\nrecursive = " + recursive + "\nObject argument: " + obj);

		//TODO: write tests
		
		System.out.println("\nImmediate superclass: " + objectClass.getSuperclass().toString() + "\n");
		
		inpectInterfaces(objectClass);
		inspectMethods(objectClass);
		inspectConstructors(objectClass);
		inspectFields(obj, objectClass, objectsToInspect);
		
			//if we want recursive inspection, inspect objects
		if (recursive && objectsToInspect.size() > 0)
			inspectFieldRecursive(obj, objectClass, objectsToInspect);
	}
		
	public void inspectArray(Field field, Object obj){
		try{ 
			Object array = field.get(obj);
		
			for (int i = 0; i < Array.getLength(array); i ++){
				System.out.println(field.getName() + "[" + i + "] = " + Array.get(array, i));
			}
		}
			//reached end of array
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void printFieldInfo(Field field, Object obj){
		
		try{
				//Name
			System.out.println("Field Name: " + field.getName());
				//Type
			System.out.println("Type: " + field.getType().toString());
				//Modifiers
			System.out.println("Modifiers: " + Modifier.toString(field.getModifiers()));
			
				//For primitive fields, print current value
			if (field.getType().isPrimitive())
				System.out.println("Current value: " + field.get(obj));
				//for object fields, print pointer value when recursion is off
			else if (!field.getType().isPrimitive() && !recursive)
				System.out.println("Current value (pointer): " + field.get(obj));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void inspectFields(Object obj, Class ObjectClass, Vector objectsToInspect){
		
			//if there is at least one field to inspect
		if (ObjectClass.getDeclaredFields().length >= 1){
						
			System.out.println("---- Inspecting Declared Fields ---");

			Field[] declaredFields = ObjectClass.getDeclaredFields();
			
				//inspect every field in the class 
			for (Field field : declaredFields){

				//Field field = ObjectClass.getDeclaredFields()[0];
				field.setAccessible(true);
									
				try{
					System.out.println("Field Declaration: " + field.toString() + " = " + field.get(obj));
					System.out.println("Declaring class: " + ObjectClass.getName()); 	
				}
				catch(Exception e){
					e.printStackTrace();
				}
					
					//if field is an array, inspect each element of the array
				if (field.getType().isArray()){
					inspectArray(field, obj);
				}
				//field is not an array
				else{
					
					printFieldInfo(field, obj);
					
					//if an object's field is not primitive and recursive is true, then that field is an object that must be inspected
					if (!field.getType().isPrimitive() && recursive)
						objectsToInspect.addElement(field);
				}
				
				System.out.println("");
			}
		}
		
		//if the object is a child of another class, inspect that superclass' value
		if (ObjectClass.getSuperclass() != null){
			System.out.println("Inspecting superclass: " + ObjectClass.getSuperclass());
			inspectFields(obj, ObjectClass.getSuperclass(), objectsToInspect);
		}
	}
	
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
	
	public void inspectMethods(Class objectClass){
		
		System.out.println("\n---- Inspecting Declared Methods ----");
		
		Method[] methods = objectClass.getDeclaredMethods();
		if (methods.length == 0)
			System.out.println("No methods declared");
		
			//print out components of methods in the field
		for (Method method : methods){
			
			//Method
			System.out.println("Method: " + method.toString());
			
			//Name
			System.out.println("Name: " + method.getName());
			
			//Exceptions thrown
			System.out.println("Exception types: ");
			
			Type[] exceptions = method.getExceptionTypes();
			if (exceptions.length == 0)
				System.out.println("None");
			for (int i = 0; i < exceptions.length; i++){
				System.out.println("    Exception " + i + ": " + exceptions[i].toString());
			}
			
			//parameter types
			System.out.println("Parameter types: ");
			
			Type[] parameters = method.getParameterTypes();
			if (parameters.length == 0)
				System.out.println("None");
			for (int i = 0; i < parameters.length; i++){
				System.out.println("    Parameter " + i + ": " + parameters[i].toString());
			}
			
			//return types
			System.out.println("Return type: " + method.getReturnType().toString());
			
			//modifiers
			System.out.println("Modifiers: " + Modifier.toString((method.getModifiers())));
			
			System.out.println("");
		}
	}
	
	public void inspectConstructors(Class objectClass){
	
		System.out.println("---- Inspecting Declared Constructors ----");

		Constructor[] constructors = objectClass.getConstructors();
		if (constructors.length == 0)
			System.out.println("No constructors declared");
		
		for (Constructor con : constructors){
			
			//constructor
			System.out.println("Constructor: " + con.toString());
			
			//parameter types
			System.out.println("Parameter types: ");

			Type[] parameters = con.getParameterTypes();
			if (parameters.length == 0)
				System.out.println("None");
			for (int i = 0; i < parameters.length; i++){
				System.out.println("    Parameters " + i + ": " + parameters[i].toString());
			}
			
			//modifiers
			System.out.println("Modifiers: " + Modifier.toString((con.getModifiers())));

			System.out.println("");
		}
	}
	
	public void inpectInterfaces(Class objectClass){

		System.out.println("---- Inspecting Implemented Interfaces ----");
		
		Class[] interfaces = objectClass.getInterfaces();
		if (interfaces.length == 0)
			System.out.println("No interfaces implemented");
		for (Class inter : interfaces){
			System.out.println("Interface: " + inter.toString());
		}
	}
	
	
}
