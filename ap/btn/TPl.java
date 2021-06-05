 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

package ap.btn; 

import ap.global.gl; 



public class TPl { 

	private double take_profit; 
	 
	private double stop_loss; 
	 
	private int direction; 
	 
	private int target; 
	 
	 
	 
	public int getTarget() { 
		return target; 
	} 

	public void setTarget(int target) { 
		this.target = target; 
	} 

	public int getDirection() { 
		return direction; 
	} 

	public void setDirection(int direction) { 
		this.direction = direction; 
	} 

	public double getTake_profit() { 
		return take_profit; 
	} 

	public void setTake_profit(double take_profit) { 
		this.take_profit = take_profit; 
	} 

	public double getStop_loss() { 
		return stop_loss; 
	} 

	public void setStop_loss(double stop_loss) { 
		this.stop_loss = stop_loss; 
	} 

	public TPl() { 
	 
	} 
	 
	public TPl(double take_profit,double stop_loss) { 
		 
		this.setTake_profit(take_profit); 
		 
		this.setStop_loss(stop_loss); 
		 
		this.setDirection(gl.E_ERROR); 
		 
	} 
	 
	public TPl(double take_profit,double stop_loss,int direction) { 

		this(take_profit,stop_loss); 
		 
		this.setDirection(direction); 
		 
	} 
	 
	public TPl(double take_profit,double stop_loss,int direction,int target) { 

		this(take_profit,stop_loss,direction); 
		 
		this.setTarget(target); 
		 
	} 
	 
	 
	 
	 
	@Override 
	public  String toString() 
	{ 
		if(this.getDirection() != gl.E_ERROR) 
		return String.format("%.4f %.4f",this.getStop_loss(),this.getTake_profit()); 
		else 
		return String.format("%.4f %.4f %1d %1d",this.getStop_loss(),this.getTake_profit(),this.getDirection(),this.getTarget()); 
		 
			 
	} 
	 

	public static void main(String[] args) { 
	 

	} 

} 
// Revision : 10.09.2018 12:49:14 
