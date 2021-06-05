 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
package ap.ray; 

public class I3 
{ 
 
private int a =0; 
 
private int b =0; 
 
private int c =0; 
 
 
public I3(int pa,int pb) 
{ 
this.a = pa; 
 
this.b = pb; 
 
} 
 
 
public I3(int pa,int pb,int pc) 
{ 
this(pa,pb)  ; 
 
this.c = pc; 
 
} 
 
public String ToString() 
{ 
 
return " a : " + this.getA() + " b: " + this.getB() + " c :" + this.getC(); 
 
} 
 
public I3() 
{ 
} 

public synchronized void setA(int a) 
{ 
this.a = a; 
} 

public synchronized int getA() 
{ 
return a; 
} 

public synchronized void setB(int b) 
{ 
this.b = b; 
} 

public synchronized int getB() 
{ 
return b; 
} 

public synchronized void setC(int c) 
{ 
this.c = c; 
} 

public synchronized int getC() 
{ 
return c; 
} 
} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:47 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
