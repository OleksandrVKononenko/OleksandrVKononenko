 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.engine; 


public interface BaseTimer 
{ 

public abstract void startTimer(); 

public abstract void stopTimer(); 

public abstract long sleep(); 

public abstract long getTime(); 

public abstract void refresh(); 

public abstract boolean isRunning(); 

public abstract int getCurrentFrequency(); 

public abstract int getFrequency(); 

public abstract void setFrequency(int i); 
} 
// Revision : 10.09.2018 12:49:14 
