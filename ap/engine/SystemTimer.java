 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.engine; 

import ap.engine.BaseTimer; 


public class SystemTimer implements BaseTimer 
{ 

public SystemTimer() 
{ 
a = 50; 
 
i = new FPSCounter(); 
} 

public void startTimer() 
{ 
if(h) 
stopTimer(); 
h = true; 
b = 1000 / a; 
refresh(); 
i.refresh(); 
} 

public void stopTimer() 
{ 
h = false; 
} 

public long sleep() 
{ 
d = System.currentTimeMillis(); 
e = d - c; 
f = b - e - g; 
if(f > 0L) 
{ 
try 
{ 
Thread.sleep(f); 
} 
catch(InterruptedException _ex) { } 
g = System.currentTimeMillis() - d - f; 
} else 
{ 
try 
{ 
Thread.sleep(1L); 
} 
catch(InterruptedException _ex) { } 
g = 0L; 
} 
i.calculateFPS(); 
long l; 
long l1 = (l = System.currentTimeMillis()) - c; 
c = l; 
return l1; 
} 

public boolean isRunning() 
{ 
return h; 
} 

public int getCurrentFrequency() 
{ 
return i.getCurrentFPS(); 
} 

public int getFrequency() 
{ 
return a; 
} 

public void setFrequency(int j) 
{ 
if(a != j) 
{ 
a = j; 
if(h) 
startTimer(); 
} 
} 

public long getTime() 
{ 
return System.currentTimeMillis(); 
} 

public void refresh() 
{ 
c = System.currentTimeMillis(); 
g = 0L; 
} 

private int a; 
private long b; 
private long c; 
private long d; 
private long e; 
private long f; 
private long g; 
private boolean h; 
private FPSCounter i; 
} 
// Revision : 10.09.2018 12:49:14 
