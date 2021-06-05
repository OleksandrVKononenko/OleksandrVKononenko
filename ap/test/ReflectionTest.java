package ap.test;

public class ReflectionTest {

	
	
	/*
	
	
	
	
	 java.lang.reflect.Method method;
try {
  method = obj.getClass().getMethod(methodName, param1.class, param2.class, ..);
} catch (SecurityException e) { ... }
  catch (NoSuchMethodException e) { ... }
  
  try {
  method.invoke(obj, arg1, arg2,...);
} catch (IllegalArgumentException e) { ... }
  catch (IllegalAccessException e) { ... }
  catch (InvocationTargetException e) { ... }
  
	 */
	
	/*
	Class<?> c = Class.forName("class name");
	Method method = c.getDeclaredMethod("method name", parameterTypes);
	method.invoke(objectToInvokeOn, params);
	*/
	
	/*
	 package com.mypackage.demo;

	import java.lang.reflect.*;

	public class ReflectionDemo {

    public static void main(String[] args) throws Exception {
        String dogClassName = "com.mypackage.bean.Dog";
        Class<?> dogClass = Class.forName(dogClassName); // convert string classname to class
        Object dog = dogClass.newInstance(); // invoke empty constructor

        String methodName = "";

        // with single parameter, return void
        methodName = "setName";
        Method setNameMethod = dog.getClass().getMethod(methodName, String.class);
        setNameMethod.invoke(dog, "Mishka"); // pass arg

        // without parameters, return string
        methodName = "getName";
        Method getNameMethod = dog.getClass().getMethod(methodName);
        String name = (String) getNameMethod.invoke(dog); // explicit cast

        // with multiple parameters
        methodName = "printDog";
        Class<?>[] paramTypes = {String.class, int.class};
        Method printDogMethod = dog.getClass().getMethod(methodName, paramTypes);
        printDogMethod.invoke(dog, name, 3); // pass args
    }
}
	 */

	
	/*
	 You can invoke the constructor with parameters this way:

Constructor<?> dogConstructor = dogClass.getConstructor(String.class, int.class);
Object dog = dogConstructor.newInstance("Hachiko", 10);
Alternatively, you can remove

String dogClassName = "com.mypackage.bean.Dog";
Class<?> dogClass = Class.forName(dogClassName);
Object dog = dogClass.newInstance();
and do

Dog dog = new Dog();

Method method = Dog.class.getMethod(methodName, ...);
method.invoke(dog, ...);

	 */
	
	/*
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

public class ReflectionTest {

    private String methodName = "length";
    private String valueObject = "Some object";

    @Test
    public void testGetMethod() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        Method m = valueObject.getClass().getMethod(methodName, new Class[] {});
        Object ret = m.invoke(valueObject, new Object[] {});
        Assert.assertEquals(11, ret);
    }



}
	 */
	
	/*
	 	Object obj;

		Method method = obj.getClass().getMethod("methodName", null);

		method.invoke(obj, null);
	*/
	
	/*
	 //Step1 - Using string funClass to convert to class
String funClass = "package.myclass";
Class c = Class.forName(funClass);

//Step2 - instantiate an object of the class abov
Object o = c.newInstance();
//Prepare array of the arguments that your function accepts, lets say only one string here
Class[] paramTypes = new Class[1];
paramTypes[0]=String.class;
String methodName = "mymethod";
//Instantiate an object of type method that returns you method name
 Method m = c.getDeclaredMethod(methodName, paramTypes);
//invoke method with actual params
m.invoke(o, "testparam");
	 */
	
	/*
	@FunctionalInterface
	public interface Method {
	    double execute(int number);
	}

	public class ShapeArea {
	    private final static double PI = 3.14;

	    private Method[] methods = {
	        this::square,
	        this::circle
	    };

	    private double square(int number) {
	        return number * number;
	    }

	    private double circle(int number) {
	        return PI * number * number;
	    }

	    public double run(int methodIndex, int number) {
	        return methods[methodIndex].execute(number);
	    }
	}
	*/
	
	/*
			 Lambda syntax
		
		You can also use lambda syntax:
		
		public class ShapeArea {
		    private final static double PI = 3.14;
		
		    private Method[] methods = {
		        number -> {
		            return number * number;
		        },
		        number -> {
		            return PI * number * number;
		        },
		    };
		
		    public double run(int methodIndex, int number) {
		        return methods[methodIndex].execute(number);
		    }
		}
	 */
	
	/*
			Object obj = new Point( 100, 200 );
			String methodName = "toString";  
			Class<String> resultType = String.class;
			
			MethodType mt = MethodType.methodType( resultType );
			MethodHandle methodHandle = MethodHandles.lookup().findVirtual( obj.getClass(), methodName, mt );
			String result = resultType.cast( methodHandle.invoke( obj ) );
			
			System.out.println( result );  // java.awt.Point[x=100,y=200]
			
			---
			methodHandle = methodHandle.asType(methodHandle.type().changeParameterType(0, Object.class)); 
			
			and then invoke like 
			
			String result = (String) methodHandle.invokeExact(obj);
	 */
	
	/*
	 To invoke a method, without Arguments:
			
			public static void callMethodByName(Object object, String methodName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
			    object.getClass().getDeclaredMethod(methodName).invoke(object);
			}
			To invoke a method, with Arguments:
			
			    public static void callMethodByName(Object object, String methodName, int i, String s) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
			        object.getClass().getDeclaredMethod(methodName, int.class, String.class).invoke(object, i, s);
			    }
			Use the above methods as below:
			
			package practice;
			
			import java.io.IOException;
			import java.lang.reflect.InvocationTargetException;
			
			public class MethodInvoke {
			
			    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
			        String methodName1 = "methodA";
			        String methodName2 = "methodB";
			        MethodInvoke object = new MethodInvoke();
			        callMethodByName(object, methodName1);
			        callMethodByName(object, methodName2, 1, "Test");
			    }
			
			    public static void callMethodByName(Object object, String methodName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
			        object.getClass().getDeclaredMethod(methodName).invoke(object);
			    }
			
			    public static void callMethodByName(Object object, String methodName, int i, String s) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
			        object.getClass().getDeclaredMethod(methodName, int.class, String.class).invoke(object, i, s);
			    }
			
			    void methodA() {
			        System.out.println("Method A");
			    }
			
			    void methodB(int i, String s) {
			        System.out.println("Method B: "+"\n\tParam1 - "+i+"\n\tParam 2 - "+s);
			    }
			}
	 */
	
	/*
		public static Method method[];
		public static MethodClass obj;
		public static String testMethod="A";
		
		public static void main(String args[]) 
		{
		    obj=new MethodClass();
		    method=obj.getClass().getMethods();
		    try
		    {
		        for(int i=0;i<method.length;i++)
		        {
		            String name=method[i].getName();
		            if(name==testMethod)
		            {   
		                method[i].invoke(name,"Test Parameters of A");
		            }
		        }
		    }
		    catch(Exception ex)
		    {
		        System.out.println(ex.getMessage());
		    }
		}
	 */
	
	/*
	 Student.java

class Student{
    int rollno;
    String name;

    void m1(int x,int y){
        System.out.println("add is" +(x+y));
    }

    private void m3(String name){
        this.name=name;
        System.out.println("danger yappa:"+name);
    }
    void m4(){
        System.out.println("This is m4");
    }
}
StudentTest.java

import java.lang.reflect.Method;
public class StudentTest{

     public static void main(String[] args){

        try{

            Class cls=Student.class;

            Student s=(Student)cls.newInstance();


            String x="kichha";
            Method mm3=cls.getDeclaredMethod("m3",String.class);
            mm3.setAccessible(true);
            mm3.invoke(s,x);

            Method mm1=cls.getDeclaredMethod("m1",int.class,int.class);
            mm1.invoke(s,10,20);

        }
        catch(Exception e){
            e.printStackTrace();
        }
     }
}
	 */
	
	/*
			 public class MethodInvokerClass {
		    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, InvocationTargetException, InstantiationException {
		        Class c = Class.forName(MethodInvokerClass.class.getName());
		        Object o = c.newInstance();
		        Class[] paramTypes = new Class[1];
		        paramTypes[0]=String.class;
		        String methodName = "countWord";
		         Method m = c.getDeclaredMethod(methodName, paramTypes);
		         m.invoke(o, "testparam");
		}

		public void countWord(String input){
		    System.out.println("My input "+input);
		}
		
	 */
	
	/*

		using import java.lang.reflect.*;
		
		public static Object launchProcess(String className, String methodName, Class<?>[] argsTypes, Object[] methodArgs)
		        throws Exception {
		
		    Class<?> processClass = Class.forName(className); // convert string classname to class
		    Object process = processClass.newInstance(); // invoke empty constructor
		
		    Method aMethod = process.getClass().getMethod(methodName,argsTypes);
		    Object res = aMethod.invoke(process, methodArgs); // pass arg
		    return(res);
		}
		and here is how you use it:
		
		String className = "com.example.helloworld";
		String methodName = "print";
		Class<?>[] argsTypes = {String.class,  String.class};
		Object[] methArgs = { "hello", "world" };   
		launchProcess(className, methodName, argsTypes, methArgs);

*/
	public ReflectionTest() {
		
	}

	public static void main(String[] args) {
		
	}

	
}
