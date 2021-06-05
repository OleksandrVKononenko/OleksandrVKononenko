 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
/** 
* 
*/ 
package ap.test; 
import java.io.*; 

import ap.global.gl; 
import ap.btn.TPanel; 


public class TestObjectInputStream { 

public static class Person implements Serializable { 
 
	public String name = null; 
 
	public int    age  =   0; 
} 

public static void primaryTest()  throws IOException, ClassNotFoundException 
{ 
ObjectOutputStream objectOutputStream = 
new ObjectOutputStream(new FileOutputStream("data/person.bin")); 

Person person = new Person(); 
person.name = "Jakob Jenkov"; 
person.age  = 40; 

objectOutputStream.writeObject(person); 
objectOutputStream.close(); 


ObjectInputStream objectInputStream = 
new ObjectInputStream(new FileInputStream("data/person.bin")); 

Person personRead = (Person) objectInputStream.readObject(); 

objectInputStream.close(); 

System.out.println(personRead.name); 
System.out.println(personRead.age); 

	 
} 

 
public static void secondaryTest()  throws IOException, ClassNotFoundException 
{ 
ObjectOutputStream objectOutputStream = 
new ObjectOutputStream(new FileOutputStream("data/person.bin")); 


TPanel jkp = new TPanel(); 
	 
	jkp.setId(gl.getNextId()); 
	 
objectOutputStream.writeObject(jkp); 
objectOutputStream.close(); 


ObjectInputStream objectInputStream = 
new ObjectInputStream(new FileInputStream("data/person.bin")); 

//Person personRead = (Person) objectInputStream.readObject(); 


TPanel jkr =  (TPanel) objectInputStream.readObject(); 
 
objectInputStream.close(); 

//System.out.println(personRead.name); 
//System.out.println(personRead.age); 

System.out.println(jkr.toString()); 
 

 
} 
 
public static void main(String[] args) throws IOException, ClassNotFoundException { 
	 
	// primaryTest(); 
	 
	secondaryTest(); 
	 

} 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:48 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
