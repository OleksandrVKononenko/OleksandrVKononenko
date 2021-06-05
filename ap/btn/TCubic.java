 
package ap.btn; 

import java.awt.geom.CubicCurve2D; 
import java.awt.geom.Point2D; 
import java.util.List; 

import ap.gen.IDGen; 

public class TCubic extends TQuad { 

	private Point2D ctrl_two; 
	 
	private TPanel owner; 
	 
	private CubicCurve2D.Double cubic = new CubicCurve2D.Double(); 

	 
	 
	public TPanel getOwner() { 
		return owner; 
	} 

	public void setOwner(TPanel owner) { 
		this.owner = owner; 
	} 

	public CubicCurve2D.Double getCubic() { 
		return cubic; 
	} 

	public void setCubic() { 
		 
		this.cubic.setCurve( 
				this.getStart(), 
				this.getCtrl(), 
				this.getCtrl_two(), 
				this.getEnd() 
				); 
		 
	} 
		 
	public Point2D getCtrl_two() { 
		return ctrl_two; 
	} 

	public void setCtrl_two(Point2D ctrl_two) { 
		this.ctrl_two = ctrl_two; 
	} 

	public TCubic(Point2D start, Point2D end, Point2D ctrl,Point2D ctrl_two) { 
		super(start, end, ctrl); 
		 
		this.setCtrl_two(ctrl_two); 
	} 
	 
	public TCubic(TPanel owner,TPanel start, TPanel end, TPanel c_one,TPanel c_two) 
	{ 
		 
		 
		this( 
				 
		new Point2D.Double( 
				start.getBounds().x - owner.getBounds().x, 
				start.getBounds().y - owner.getBounds().y), 
		 
		new Point2D.Double( 
				end.getBounds().x - owner.getBounds().x, 
				end.getBounds().y - owner.getBounds().y), 
		 
		 
		new Point2D.Double( 
				c_one.getBounds().x - owner.getBounds().x, 
				c_one.getBounds().y - owner.getBounds().y), 
		 
		new Point2D.Double( 
				c_two.getBounds().x - owner.getBounds().x, 
				c_two.getBounds().y - owner.getBounds().y) 
		 
		); 
		 
		this.setId(start.getGid()); 
		 
		this.setOwner(owner); 
						 
	} 
		 
	public TCubic(TPanel owner,TPanel start, TPanel end, TPanel c_one) 
	{ 
		 
		 
		this.setStart( 
				 
		new Point2D.Double( 
				start.getBounds().x - owner.getBounds().x, 
				start.getBounds().y - owner.getBounds().y) 
		 
				); 
		 
		this.setEnd( 
		new Point2D.Double( 
				end.getBounds().x - owner.getBounds().x, 
				end.getBounds().y - owner.getBounds().y) 
		 
		 ); 
		 
		this.setCtrl( 
		new Point2D.Double( 
				c_one.getBounds().x - owner.getBounds().x, 
				c_one.getBounds().y - owner.getBounds().y) 
		 
		); 
		 
		this.setId(start.getGid()); 
		 
		this.setOwner(owner); 
						 
	} 
	 
	 
	public static TCubic get_by_id(int id,List<TCubic> list) 
	{ 
		return 

		list.stream().filter( 
				( 
						b -> ( 
								b.getId() == id 
						     ) 
								 
						)) 
				.findFirst().orElse(null);				 
			 
	} 
	 
	public static TCubic getInstance(TPanel owner,TPanel start,TPanel end,TPanel ctrl_one,TPanel ctrl_two) 
	{ 
		TCubic object_to_return = null; 
		 
		if(owner.getFunction().equalsIgnoreCase("TCubic")) 
			object_to_return = new TCubic( 
					owner, 
					start, 
					end , 
					ctrl_one, 
					ctrl_two 
					); 
		else if(owner.getFunction().equalsIgnoreCase("TQuad")) 
			object_to_return = new TCubic( 
					owner, 
					start, 
					end , 
					ctrl_one 
					); 
			 
			return  object_to_return; 
		 
	} 
	 
	public void setPointHub(String parent_function,String function,Point2D p2d) 
	{ 
		if(function.equalsIgnoreCase("start_point")) 
			 this.setStart(p2d); 
			if(function.equalsIgnoreCase("control_point_1")) 
				this.setCtrl(p2d); 
				if(function.equalsIgnoreCase("end_point")) 
						this.setEnd(p2d); 
				if(parent_function.equalsIgnoreCase("TCubic") && function.equalsIgnoreCase("control_point_2")) 
					this.setCtrl_two(p2d); 
				 
		// Finally setting. 
				 
		if(parent_function.equalsIgnoreCase("TCubic")) 
			this.setCubic(); 
		else if(parent_function.equalsIgnoreCase("TQuad")) 
			this.setQuad(); 
				 

	} 
	 
	@Override 
	public String toString() 
	{ 
		return String.format("id=%d,start=%s,ctrl_1=%s,ctrl_2=%s,end=%s",this.getId(),this.getStart(),this.getCtrl(),this.getCtrl_two(),this.getEnd()); 
		 
	} 

	public TCubic() { 
		 
		this.setId(IDGen.nextId()); 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
