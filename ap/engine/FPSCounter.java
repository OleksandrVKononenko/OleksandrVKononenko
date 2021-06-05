 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.engine; 

public class FPSCounter 
{ 

public FPSCounter() 
{ 
} 

public void refresh() 
{ 
c = 0; 
a = System.currentTimeMillis(); 
} 

public void calculateFPS() 
{ 
c++; 
if(System.currentTimeMillis() - a > 1000L) 
{ 
a = System.currentTimeMillis(); 
b = c; 
c = 0; 
} 
} 

public int getCurrentFPS() 
{ 
return b; 
} 

private long a; 
private int b; 
private int c; 
} 
// Revision : 10.09.2018 12:49:14 
