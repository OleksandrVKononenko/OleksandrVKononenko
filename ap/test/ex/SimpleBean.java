package ap.test.ex;

import java.awt.Color;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import javax.swing.JPanel;

import ap.global.gl;
import ap.prop.SColor;

public class SimpleBean
{
    private String name = "SimpleBean";
    
    private int size;

    public SimpleBean(String name,int size)
    {
    	this.setName(name);
    	
    	this.setSize(size);
    }
    
    public String getName()
    {
        return this.name;
    }
    
    
    public void setName(String name) {
    	
		this.name = name;
	}

	public int getSize()
    {
        return this.size;
    }

    public void setSize( int size )
    {
        this.size = size;
    }

    @Override
    public String toString()
    {
    	return gl.sf("{name...%s...size...%d}", this.getName(),this.getSize());
    }
    
    public static void test()  throws IntrospectionException
    {
    
    	JPanel jp = new JPanel();
    	
        BeanInfo info = Introspector.getBeanInfo(jp.getClass() );
    
        for ( PropertyDescriptor pd : info.getPropertyDescriptors() )
            //System.out.println( pd.getName() );
        	gl.sfn("%s %s %s",
        			pd.getName(),
        			pd.getPropertyType(),
        			pd.getValue(pd.getName())
        					);
        
    	
    	SimpleBean b1 = new SimpleBean("b1",1);
    	
    	gl.sfn("Before set...%s",b1.toString());
    	
    	set(b1,"name","b1_1");
    	
    	set(b1,"size",10);
    	
    	gl.sfn("After  set...%s",b1.toString());
    	
    	gl.sfn("*After set...%s...%d",get(b1,"name"),get(b1,"size"));
    	
    }
    
    public static void test_panel()
    {
    	
    	JPanel 	jp = new JPanel();
    	
    			jp.setBackground(Color.white);
    			
    			gl.sfn("Color...%s",SColor.toString(get(jp,"background")));
    			
    			set(jp,"background",Color.yellow);
    			
    			gl.sfn("Color...%s",SColor.toString(get(jp,"background")));
    			
    	    			
    }
    
    public static void main( String[] args )
           // throws IntrospectionException
    {
    	try {
    		
			test_panel();
			
    		//test();
    		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	
    }
    
    public static boolean set(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        		return false;
    }
    

@SuppressWarnings("unchecked")
public static <V> V get(Object object, String fieldName) {
    Class<?> clazz = object.getClass();
    while (clazz != null) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (V) field.get(object);
        } catch (NoSuchFieldException e) {
            clazz = clazz.getSuperclass();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    return null;
}

}

/*
 
 public static boolean set(Object object, String fieldName, Object fieldValue) {
    Class<?> clazz = object.getClass();
    while (clazz != null) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
            return true;
        } catch (NoSuchFieldException e) {
            clazz = clazz.getSuperclass();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    return false;
}
 
 
Class<?> clazz = Class.forName(className);
Object instance = clazz.newInstance();
set(instance, "salary", 15);
set(instance, "firstname", "John");


@SuppressWarnings("unchecked")
public static <V> V get(Object object, String fieldName) {
    Class<?> clazz = object.getClass();
    while (clazz != null) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (V) field.get(object);
        } catch (NoSuchFieldException e) {
            clazz = clazz.getSuperclass();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    return null;
}

Class<?> clazz = Class.forName(className);
Object instance = clazz.newInstance();
int salary = get(instance, "salary");
String firstname = get(instance, "firstname");

*/

/*
 
Field field=classHandle.getDeclaredField("firstName");
Method setter=classHandle.getMethod("setFirstName", field.getType());
setter.invoke(myObject, "new value for first name");

*/