 
package ap.btn; 

import java.util.Date; 
import java.util.List; 

import ap.global.gl; 
import ap.utils.DateUtil; 

public class TMa { 

	public static final int M3 = 0; 
	 
	public static final int M7 = 1; 
	 
	public static final int M10 = 2; 
	 
	public static final int M14 = 3; 
	 
	public static final int M21 = 4; 
	 
	public static final int M55 = 5; 
	 
	public static final int M100 = 6; 
	 
	public static final int M200 = 7; 
	 	 
	private Date dt; 
	 
	private double m3; 
	 
	private double m5; 
	 
	private double m7; 
	 
	private double m10; 
	 
	private double m14; 
	 
	private double m21; 
	 
	private double m33; 
	 
	private double m55; 
	 
	private double m100; 
	 
	private double m144; 
	 
	private double m200; 
	 
	 
	public double getM3() { 
		return m3; 
	} 

	public void setM3(double m3) { 
		this.m3 = m3; 
	} 

	public double getM5() { 
		return m5; 
	} 

	public void setM5(double m5) { 
		this.m5 = m5; 
	} 

	public double getM7() { 
		return m7; 
	} 

	public void setM7(double m7) { 
		this.m7 = m7; 
	} 

	public double getM10() { 
		return m10; 
	} 

	public void setM10(double m10) { 
		this.m10 = m10; 
	} 

	public double getM14() { 
		return m14; 
	} 

	public void setM14(double m14) { 
		this.m14 = m14; 
	} 

	public double getM21() { 
		return m21; 
	} 

	public void setM21(double m21) { 
		this.m21 = m21; 
	} 

	public double getM33() { 
		return m33; 
	} 

	public void setM33(double m33) { 
		this.m33 = m33; 
	} 

	public double getM55() { 
		return m55; 
	} 

	public void setM55(double m55) { 
		this.m55 = m55; 
	} 

	public double getM100() { 
		return m100; 
	} 

	public void setM100(double m100) { 
		this.m100 = m100; 
	} 

	public double getM144() { 
		return m144; 
	} 

	public void setM144(double m144) { 
		this.m144 = m144; 
	} 

	public double getM200() { 
		return m200; 
	} 

	public void setM200(double m200) { 
		this.m200 = m200; 
	} 
	 
	 
	public Date getDt() { 
		return dt; 
	} 

	public void setDt(Date dt) { 
		this.dt = dt; 
	} 

	public TMa() { 
		 
	} 
	 
	public TMa(double m3,double m5,double m7,double m10,double m14,double m21, 
			   double m33,double m55,double m100,double m144,double m200) 
	{ 
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
	 
	public TMa(Date date,double m3,double m5,double m7,double m10,double m14,double m21, 
			   double m33,double m55,double m100,double m144,double m200) 
	{ 
		this (m3, m5, m7, m10, m14, m21, m33, m55, m100, m144, m200); 
		 
		this.setDt(date); 
	} 
	 
		 
	public static TMa getInstance(Date date,double m3,double m5,double m7,double m10,double m14,double m21, 
			   double m33,double m55,double m100,double m144,double m200) 
	{ 
		return new TMa(date,m3, m5, m7, m10, m14, m21, m33, m55, m100, m144, m200); 
	} 
	 
	 
	public static TMa getInstance(double m3,double m5,double m7,double m10,double m14,double m21, 
			   double m33,double m55,double m100,double m144,double m200) 
	{ 
		return new TMa(m3, m5, m7, m10, m14, m21, m33, m55, m100, m144, m200); 
	} 
	 
	public static TMa getInstance() 
	{ 
		return new TMa(-1.0d,-1.0d,-1.0d,-1.0d,-1.0d,-1.0d,-1.0d,-1.0d,-1.0d,-1.0d,-1.0d); 
	} 
	 
	 
	public static TMa getInstance(TMa a) 
	{ 
		return new TMa( 
				a.getM3(), 
				a.getM7(), 
				a.getM5(), 
				a.getM10(), 
				a.getM14(), 
				a.getM21(), 
				a.getM33(), 
				a.getM55(), 
				a.getM100(), 
				a.getM144(), 
				a.getM200() 
				); 
	} 

	@Override 
	public String toString() 
	{ 
		return String.format( 
				"%s %.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f", 
				(this.getDt()==null) ? "--" : DateUtil.to_string(this.getDt()), 
				this.getM3(), 
				this.getM5(), 
				this.getM7(), 
				this.getM10(), 
				this.getM14(), 
				this.getM21(), 
				this.getM33(), 
				this.getM55(), 
				this.getM100(), 
				this.getM144(), 
				this.getM200() 
				); 
	} 
	 
	 
	public static boolean lookUpByDate(List<TMa> items,Date filter,TMa[] out) 
	{ 
		 
		TMa r = 
				items.stream().filter( 
						(b-> ( 
								b.getDt().compareTo(filter) == gl.E_EMPTY 
						))) 
						.findFirst() 
						.orElse(null); 
		 
		out[gl.E_EMPTY] = r; 
		 
		return (r != null); 
		 
	} 
	 
	public double get_by_type(int ma_type) 
	{ 
			switch(ma_type) 
			{ 
				case TMa.M3: 
					return getM3(); 
				case TMa.M7: 
					return getM7(); 
				case TMa.M10: 
					return getM10(); 
				case TMa.M14: 
					return getM14(); 
				case TMa.M21: 
					return getM21(); 
				case TMa.M55: 
					return getM55(); 
				case TMa.M100: 
					return getM100(); 
				case TMa.M200: 
					return getM200(); 
			} 
					return 0.0d; 
		} 
	 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:14 
