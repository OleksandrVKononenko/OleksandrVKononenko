 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
/** 
* 
*/ 
package ap.test; 

import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.Map; 

public class MapSortByValue { 

public static void main(String[] argv) { 

Map<String, Integer> unsortMap = new HashMap<>(); 
unsortMap.put("z", 10); 
unsortMap.put("b", 5); 
unsortMap.put("a", 6); 
unsortMap.put("c", 20); 
unsortMap.put("d", 1); 
unsortMap.put("e", 7); 
unsortMap.put("y", 8); 
unsortMap.put("n", 99); 
unsortMap.put("j", 50); 
unsortMap.put("m", 2); 
unsortMap.put("f", 9); 

System.out.println("Original..."); 
System.out.println(unsortMap); 

Map<String, Integer> result = new LinkedHashMap<>(); 

//sort by value, and reserve, 10,9,8,7,6... 
unsortMap.entrySet().stream() 
.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) 
.forEachOrdered(x -> result.put(x.getKey(), x.getValue())); 

System.out.println("Sorted..."); 
System.out.println(result); 

} 

} 

// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:48 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
