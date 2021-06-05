package ap.test.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ap.global.gl;

public class ReflectionTest {
	
	public void print(String a , String b)
	{
		
		gl.sfn("%s %s",a,b);
		
	}
	
	public static Object launchProcess(String className, String methodName, Class<?>[] argsTypes, Object[] methodArgs)
	        throws Exception {
	
	    Class<?> processClass = Class.forName(className); // convert string classname to class
	 
	    Object process = processClass.newInstance(); // invoke empty constructor
	
	    Method aMethod = process.getClass().getMethod(methodName,argsTypes);
	    
	    Object res = aMethod.invoke(process, methodArgs); // pass arg
	    
	    return(res);
	}

	
	public static void testLaunchProcess()
	{
		long start = System.nanoTime(); 
		
		String className = "ap.test.reflection.ReflectionTest";
		
		String methodName = "print";
		
		Class<?>[] argsTypes = {String.class,  String.class};
		
		Object[] methArgs = { "hello", "world" };   
		
		try {
			
			launchProcess(className, methodName, argsTypes, methArgs);
			
			gl.sfn("Result...in...%d",(System.nanoTime() - start)/1000);
			
		} catch (Exception e) {
			
			gl.sfn("Exception...%s",e.toString());
		}
	}
	
	public static void test()
	{
		try
    	{
    		long start = System.nanoTime(); 
    		
	    	String methodName = "length";
	        
	        String valueObject = "Some object";
	
	        Method m = valueObject.getClass().getMethod(methodName, new Class[] {});
	        
	        Object ret = m.invoke(valueObject, new Object[] {});
	        
	        gl.sfn("Result...%s..in...%d", ret,System.nanoTime() - start);
	        
    	}
    	catch(NoSuchMethodException e)
    	{
    		
    	}
     	catch(InvocationTargetException e)
    	{
    		
    	}
    	catch(IllegalAccessException e)
    	{
    		
    	}
	}
	
    public static void main(String[] args) {
		
    		test();
    		
    		testLaunchProcess();
    }



}