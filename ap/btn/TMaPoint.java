 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.Point; 
import java.util.Date; 

import ap.global.gl; 
import ap.prop.SDate; 
import ap.utils.DateUtil; 

public class TMaPoint { 

	private Date date = DateUtil.to_date("01.01.1899"); 
	 
	private Point m3; 
	 
	private Point m5; 
	 
	private Point m7; 
	 
	private Point m10; 
	 
	private Point m14; 
	 
	private Point m21; 
	 
	private Point m33; 
	 
	private Point m55; 
	 
	private Point m100; 
	 
	private Point m144; 
	 
	private Point m200; 
	 
	 
	 
	public Date getDate() { 
		return date; 
	} 

	public void setDate(Date date) { 
		this.date = date; 
	} 

	public Point getM3() { 
		return m3; 
	} 

	public void setM3(Point m3) { 
		this.m3 = m3; 
	} 

	public Point getM5() { 
		return m5; 
	} 

	public void setM5(Point m5) { 
		this.m5 = m5; 
	} 

	public Point getM7() { 
		return m7; 
	} 

	public void setM7(Point m7) { 
		this.m7 = m7; 
	} 

	public Point getM10() { 
		return m10; 
	} 

	public void setM10(Point m10) { 
		this.m10 = m10; 
	} 

	public Point getM14() { 
		return m14; 
	} 

	public void setM14(Point m14) { 
		this.m14 = m14; 
	} 

	public Point getM21() { 
		return m21; 
	} 

	public void setM21(Point m21) { 
		this.m21 = m21; 
	} 

	public Point getM33() { 
		return m33; 
	} 

	public void setM33(Point m33) { 
		this.m33 = m33; 
	} 

	public Point getM55() { 
		return m55; 
	} 

	public void setM55(Point m55) { 
		this.m55 = m55; 
	} 

	public Point getM100() { 
		return m100; 
	} 

	public void setM100(Point m100) { 
		this.m100 = m100; 
	} 

	public Point getM144() { 
		return m144; 
	} 

	public void setM144(Point m144) { 
		this.m144 = m144; 
	} 

	public Point getM200() { 
		return m200; 
	} 

	public void setM200(Point m200) { 
		this.m200 = m200; 
	} 

	public TMaPoint() { 
		 
	} 
	 
	public TMaPoint(Date date,Point m3,Point m5,Point m7,Point m10,Point m14,Point m21, 
			   Point m33,Point m55,Point m100,Point m144,Point m200) 
	{ 
		this.setDate(date); 
		 
		this.setM3(m3); 
		 
		this.setM5(m5); 
		 
		this.setM7(m7); 
		 
		this.setM10(m10); 
		 
		this.setM14(m14); 
		 
		this.setM21(m21); 
		 
		this.setM33(m33); 
		 
		this.setM55(m55); 
		 
		this.setM100(m100); 
		 
		this.setM144(m144); 
		 
		this.setM200(m200); 
		 
	} 
	 
	public Point getValueByType(int type) 
	{ 
		Point result = null; 
		 
		if(type == TMa.M3) 
			result = this.getM3(); 
		//else if(type == TMa.M5) 
		//	result = this.getM5(); 
		if(type == TMa.M7) 
			result = this.getM7(); 
		if(type == TMa.M10) 
			result = this.getM10(); 
		if(type == TMa.M14) 
			result = this.getM14(); 
		if(type == TMa.M21) 
			result = this.getM21(); 
		//if(type == TMa.M33) 
		//	result = this.getM33(); 
		if(type == TMa.M55) 
			result = this.getM55(); 
		if(type == TMa.M100) 
			result = this.getM100(); 
		//if(type == TMa.M144) 
		//	result = this.getM144(); 
		if(type == TMa.M200) 
			result = this.getM200(); 
		 
		return result; 
			 
		 
	} 
	 
	public static TMaPoint getInstance(Date date,Point m3,Point m5,Point m7,Point m10,Point m14,Point m21, 
			   Point m33,Point m55,Point m100,Point m144,Point m200 
			) 
	{ 
		return new TMaPoint(date,m3,m5,m7,m10,m14,m21,m33,  m55,  m100,  m144,  m200); 
	} 

	@Override 
	public String toString() 
	{ 
		return String.format( 
				"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", 
				DateUtil.to_string(this.getDate()), 
				this.getM3().toString(), 
				this.getM5().toString(), 
				this.getM7().toString(), 
				this.getM10().toString(), 
				this.getM14().toString(), 
				this.getM21().toString(), 
				this.getM33().toString(), 
				this.getM55().toString(), 
				this.getM100().toString(), 
				this.getM144().toString(), 
				this.getM200().toString() 
				); 
	} 
	 
	 
		public static void main(String[] args) { 
		 

	} 

} 
