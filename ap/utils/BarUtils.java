 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Set; 
import java.util.concurrent.ConcurrentHashMap; 
import java.util.function.Function; 
import java.util.function.Predicate; 
import java.util.stream.Collectors; 

import ap.btn.Bar; 
import ap.btn.TPanel; 
import ap.global.gl; 
import ap.log.Logger; 

public class BarUtils { 

	 
	public static <T> Predicate<T> distinctByKey(Function<? super T,?> keyExtractor) 
	{ 
		Set<Object> seen  = ConcurrentHashMap.newKeySet(); 
		 
		return t-> seen.add(keyExtractor.apply(t)); 
		 
	} 
	 
	// Remove day of week. 
	 
	public static boolean extractDow(String source_file) 
	{ 
	 
		String msg = "Extract weekends..."; 
		 
		List<Bar> bars = new ArrayList<Bar>(); 

		String [] response = {"",""}; 
		 
		String header = Fu.get_first_row(source_file); 
		 
		if (!Bar.readFromFile(source_file, bars,response)) { 
			gl.tracex(new Object() { 
			}, String.format("%s...%s", gl.S_ERROR, msg)); 

			return false; 
		} 
		 
		int SUNDAY = 1; 
		 
		int SATURDAY = 7; 
				 
		List<Bar> weekend_bars =  bars.stream().filter( 
				(b-> ( 
						DateUtil.day_of_week(b.getDate()) == SUNDAY || 
						DateUtil.day_of_week(b.getDate()) == SATURDAY 
					))).collect(Collectors.toList()); 
		 
		/* 
		weekend_bars.forEach(a->{ 
			 
			int dow = DateUtil.getDayOfWeek(a.getDate()); 
					 
			String mes = String.format("%s...%d",a.toString(),dow); 
			 
			gl.smn(mes); 
			 
		}); 
		*/ 
			if (!bars.removeAll(weekend_bars)) 
			{ 
				gl.tracex(new Object() { 
				}, String.format("%s...%s...%s", gl.S_ERROR, msg,"while remove")); 

				return false; 
				 
			} 
			 
			 
			gl.tracex(new Object(){}, String.format("%s...%s...%s", gl.S_OK,"extract header",header)); 
		 
			String backup = Fu.getBakName(source_file,"bak"); 
			 
			Logger log = new Logger(backup,true); 
			 
			log.awrite(header); 
			 
			bars.forEach(a->{ 
				 
				//int dow = DateUtil.getDayOfWeek(a.getDate()); 
						 
				//String mes = String.format("%s...%d",a.toString(),dow); 
				 
				//gl.smn(mes); 
				 
				log.awrite(a.toStringDateOHLC()); 
				 
			}); 
			 
			 
			bars.stream().filter(distinctByKey(Bar::getType)).forEach(a-> 
			{ 
				gl.smn("Type : " + a.toStringType()); 
			} 
			); 
			 
			 
			 
			 
			return true; 
		 
	} 
	 
	 
	public BarUtils() { 
	 
	} 

	public static void main(String[] args) { 
	 
		 
		String source_file = "e:\\txt\\eurusd.txt"; 
		 
		extractDow(source_file); 
		 
	} 

} 
