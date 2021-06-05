package ap.explorer;

import java.util.LinkedHashMap;
import java.util.Map;

public class Profiler {
	
	private long start 	= 0L;
	
	private long end 	= 0L;
	
	private long duration = 0L;
	
	public static  Map<String,Profiler> track;
	
	
	
	public long getStart() {
		return start;
	}



	public void setStart(long start) {
		this.start = start;
	}



	public long getEnd() {
		return end;
	}



	public void setEnd(long end) {
		this.end = end;
	}



	public long getDuration() {
		return duration;
	}



	public void setDuration(long duration) {
		this.duration = duration;
	}

	public void start() {
	
		this.setStart(System.nanoTime());
	}
	
	public void end() {
		
		this.setEnd(System.nanoTime());
		
		this.setDuration(this.getEnd()-this.getStart());
	}
	
	
	public Profiler()
	{
		 track = new LinkedHashMap<String,Profiler> ();
	}

	public static Profiler get_instance()
	{
		return new Profiler();
	}
	
	public static void main(String[] args) {
		

	}

}
