package ap.explorer;

import ap.btn.TDateRange;

public class Quote {
	
	private String 	paper;
	
	private String 	path;
	
	private long 	strategy;
	
	private Range  	range;
	
	private int 	point_precision;
	
	private int 	brew;

	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public long getStrategy() {
		return strategy;
	}

	public void setStrategy(long strategy) {
		this.strategy = strategy;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public int getPoint_precision() {
		return point_precision;
	}

	public void setPoint_precision(int point_precision) {
		this.point_precision = point_precision;
	}

	public int getBrew() {
		return brew;
	}

	public void setBrew(int brew) {
		this.brew = brew;
	}
	
	
	public Quote(String paper, String path,long strategy,Range range,int point_precision,int brew)
	{
		
		this.setPaper(paper);
		
		this.setPath(path);
		
		this.setStrategy(strategy);
		
		this.setRange(range);
		
		this.setPoint_precision(point_precision);
		
		this.setBrew(brew);
	}
	
	
	public static Quote get_instance(String paper,String path,long strategy,Range range,int point_precision,int brew)
	{
		return new Quote(paper,path,strategy,range,point_precision,brew);
			
	}
	
}
