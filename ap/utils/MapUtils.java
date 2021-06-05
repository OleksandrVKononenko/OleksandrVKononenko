 
/** 
* 
*/ 
package ap.utils; 

import java.awt.Rectangle; 
import java.util.Arrays; 
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.ListIterator; 
import java.util.Map; 
import java.util.Spliterators; 
import java.util.Map.Entry; 
import java.util.stream.Stream; 
import java.util.stream.StreamSupport; 

import ap.btn.TCrossMetricDurability; 
import ap.btn.TDowTypeDistribution; 
import ap.global.gl; 
import ap.swing.PanelXml; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 02 ???. 2017 ?. 15:00:41 
* Project  name : Organizer 
* Package  name : ap.utils 
* File     name : MapUtils.java 
* 
*/ 
public class MapUtils { 

	 
	public MapUtils() { 
	 
	} 
	 
	 
	public static final <T> Iterator<T> revit(List<T> l) 
	{ 
		 
		ListIterator<T> li = l.listIterator(l.size()); 
		 
		return new Iterator<T>(){ 
		@Override 
		public boolean hasNext() 
		{ 
			return li.hasPrevious(); 
		} 
		@Override 
		public T next() 
		{ 
			return li.previous(); 
		} 
		 
		}; 
	} 
	public static <K, V extends Comparable<? super V>> Map<K, V> compareByValue( 
			Map<K, V> map) { 

		Map<K, V> result = new LinkedHashMap<>(); 

		Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream(); 

		mapInStream.sorted(Map.Entry.comparingByValue()).forEachOrdered( 
				x -> result.put(x.getKey(), x.getValue())); 

		return result; 

	} 

	public static <K extends Comparable<? super K>, V> Map<K, V> compareByKey( 
			Map<K, V> map) { 

		Map<K, V> result = new LinkedHashMap<>(); 
		Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream(); 

		mapInStream.sorted(Map.Entry.comparingByKey()).forEachOrdered( 
				x -> result.put(x.getKey(), x.getValue())); 

		return result; 

	} 

	public static void Test_ListReverse() 
	{ 
		List<String> l = Arrays.asList("a","b","c"); 
		 
		StreamSupport.stream(Spliterators.spliterator(revit(l),l.size(),0),false) 
		.forEachOrdered(System.out::println); 
		 
		StreamSupport.stream(Spliterators.spliterator(revit(l),l.size(),0),false) 
		.forEachOrdered(System.out::println); 
		 
		 
	} 
	 
	public static <K,V> V findValueByKey(Map<K,V> map,int key) 
	{ 
		for(Entry<K,V> e : map.entrySet()) 
		{ 
			if(e.getKey().equals(key)) 
				return e.getValue(); 
		} 
			return null; 
	} 
	 
	public static <K,V> V findValueByKey(Map<K,V> map,String key) 
	{ 
		for(Entry<K,V> e : map.entrySet()) 
		{ 
			if(e.getKey().equals(key)) 
				return e.getValue(); 
		} 
			return null; 
	} 
	 
	public static <K,V> V findValueByKey(Map<K,V> map,Rectangle key) 
	{ 
		for(Entry<K,V> e : map.entrySet()) 
		{ 
			if(e.getKey().equals(key)) 
				return e.getValue(); 
		} 
			return null; 
	} 
	 
	public static <K,V> V findValueByKey(Map<K,V> map,TDowTypeDistribution key) 
	{ 
		for(Entry<K,V> e : map.entrySet()) 
		{ 
			if(e.getKey().equals(key)) 
				return e.getValue(); 
			 
		} 
			return null; 
	} 
	 
	public static <K,V> V findValueByKey(Map<K,V> map,TCrossMetricDurability key) 
	{ 
		for(Entry<K,V> e : map.entrySet()) 
		{ 
			if(e.getKey().equals(key)) 
				return e.getValue(); 
			 
		} 
			return null; 
	} 
	 
	public static <K,V> K findKeyByValue(Map<K,V> map,String value) 
	{ 
		for(Entry<K,V> e : map.entrySet()) 
			if(e.getValue().equals(value)) 
				return e.getKey(); 
		 
		return null; 
	} 
	 
	 
	public static <K,V> K findKeyByValue(Map<K,V> map,Rectangle value) 
	{ 
		for(Entry<K,V> e : map.entrySet()) 
			if(e.getValue().equals(value)) 
				return e.getKey(); 
		 
		return null; 
	} 
	
	public static <K,V> K findKeyByValue(Map<K,V> map,int value) 
	{ 
		for(Entry<K,V> e : map.entrySet()) 
			if(e.getValue().equals(value)) 
			  return e.getKey(); 
		 
			return null; 
	} 
	 
	public static PanelXml findByName(Map<Integer,PanelXml> map, String name) 
	{ 
		for(Entry<Integer,PanelXml> e : map.entrySet()) 
			if(e.getValue().getIdo().getName().equalsIgnoreCase(name)) 
				return e.getValue(); 
		 
		return null; 
	} 
	 
	public static <K,V> String showMap(Map<K,V> map) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		for(Entry<K,V> e : map.entrySet()) 
		{	 
			sb.append(e.getValue().toString()); 
			 
			sb.append(System.lineSeparator()); 
		} 
		 
			return sb.toString(); 
		 
	} 
	 
	public static <K,V> String showMapKV(Map<K,V> map) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		for(Entry<K,V> e : map.entrySet()) 
		{	 
			 
			sb.append(String.format("%s %s",e.getKey().toString(),e.getValue().toString())); 
			 
			sb.append(System.lineSeparator()); 
		} 
		 
			return sb.toString(); 
		 
	} 
	 
	public static <K,V> String showMapV(Map<K,V> map) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		for(Entry<K,V> e : map.entrySet()) 
		{	 
			 
			sb.append(String.format("%s",e.getValue().toString())); 
			 
			sb.append(System.lineSeparator()); 
		} 
		 
			return sb.toString(); 
		 
	} 
	 
	public static <K,V> String showMapK(Map<K,V> map) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		for(Entry<K,V> e : map.entrySet()) 
		{	 
			 
			sb.append(String.format("%s",e.getKey().toString())); 
			 
			sb.append(System.lineSeparator()); 
		} 
		 
			return sb.toString(); 
		 
	} 
	 
	public static <K,V> String getFirstKey(Map<K,V> map) 
	{ 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		for(Entry<K,V> e : map.entrySet()) 
		{	 
			 
			sb.append(String.format("%s",e.getKey().toString())); 
			 
		} 
		 
			return sb.toString(); 
		 
		 
	} 
	 
	 
	public static void Test_Generic() 
	{ 
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

		System.out.println("Sort By Key..."); 
		Map<String, Integer> resultKey = compareByKey(unsortMap); 
		System.out.println(resultKey); 

		System.out.println("Sort By Value..."); 
		Map<String, Integer> resultValue = compareByValue(unsortMap); 
		System.out.println(resultValue); 
	} 
	 
	public static void main(String[] args) { 
		 
		Test_Generic(); 
	 
		// Test_ListReverse(); 
		 
	} 

} 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
