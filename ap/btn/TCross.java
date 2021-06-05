 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.util.Date; 
import java.util.Locale; 

import ap.global.gl; 
import ap.utils.DateUtil; 

public class TCross { 

	public static final int UP = gl.E_OK; 
	 
	public static final int ZERO = gl.E_ERROR; 
	 
	public static final int DOWN = gl.E_EMPTY; 
	 
	private Date dt; 
	 
	private int st; 

	private int m3; 
	 
	private int m5; 
	 
	private int m7; 
	 
	private int m10; 
	 
	private int m14; 
	 
	private int m21; 
	 
	private int m33; 
	 
	private int m55; 
	 
	private int m100; 
	 
	private int m144; 
	 
	private int m200; 
	 
	 
	private double am3; 
	 
	private double am5; 
	 
	private double am7; 
	 
	private double am10; 
	 
	private double am14; 
	 
	private double am21; 
	 
	private double am33; 
	 
	private double am55; 
	 
	private double am100; 
	 
	private double am144; 
	 
	private double am200; 
	 
	 
	public void setDt(Date dt) { 
		this.dt = dt; 
	} 

	public double getAm3() { 
		return am3; 
	} 

	public void setAm3(double am3) { 
		this.am3 = am3; 
		 
		this.setM3(sign(this.getAm3())); 
	} 

	public double getAm5() { 
		return am5; 
	} 

	public void setAm5(double am5) { 
		this.am5 = am5; 
		 
		this.setM5(sign(this.getAm5())); 
	} 

	public double getAm7() { 
		return am7; 
	} 

	public void setAm7(double am7) { 
		this.am7 = am7; 
		 
		this.setM7(sign(this.getAm7())); 
	} 

	public double getAm10() { 
		return am10; 
	} 

	public void setAm10(double am10) { 
		this.am10 = am10; 
		 
		this.setM10(sign(this.getAm10())); 
	} 

	public double getAm14() { 
		return am14; 
	} 

	public void setAm14(double am14) { 
		this.am14 = am14; 
		 
		this.setM14(sign(this.getAm14())); 
	} 

	public double getAm21() { 
		return am21; 
	} 

	public void setAm21(double am21) { 
		this.am21 = am21; 
		 
		this.setM21(sign(this.getAm21())); 
	} 

	public double getAm33() { 
		return am33; 
	} 

	public void setAm33(double am33) { 
		this.am33 = am33; 
		 
		this.setM33(sign(this.getAm33())); 
	} 

	public double getAm55() { 
		return am55; 
	} 

	public void setAm55(double am55) { 
		this.am55 = am55; 
		 
		this.setM55(sign(this.getAm55())); 
	} 

	public double getAm100() { 
		return am100; 
	} 

	public void setAm100(double am100) { 
		this.am100 = am100; 
		 
		this.setM100(sign(this.getAm100())); 
	} 

	public double getAm144() { 
		return am144; 
	} 

	public void setAm144(double am144) { 
		this.am144 = am144; 
		 
		this.setM144(sign(this.getAm144())); 
	} 

	public double getAm200() { 
		return am200; 
	} 

	public void setAm200(double am200) { 
		this.am200 = am200; 
		 
		this.setM200(sign(this.getAm200())); 
	} 

	public Date getDt() { 
		return dt; 
	} 

	 

	public int getSt() { 
		return st; 
	} 

	public void setSt(int st) { 
		this.st = st; 
	} 

	public int getM3() { 
		return m3; 
	} 

	public void setM3(int m3) { 
		this.m3 = m3; 
	} 

	public int getM5() { 
		return m5; 
	} 

	public void setM5(int m5) { 
		this.m5 = m5; 
	} 

	public int getM7() { 
		return m7; 
	} 

	public void setM7(int m7) { 
		this.m7 = m7; 
	} 

	public int getM10() { 
		return m10; 
	} 

	public void setM10(int m10) { 
		this.m10 = m10; 
	} 

	public int getM14() { 
		return m14; 
	} 

	public void setM14(int m14) { 
		this.m14 = m14; 
	} 

	public int getM21() { 
		return m21; 
	} 

	public void setM21(int m21) { 
		this.m21 = m21; 
	} 

	public int getM33() { 
		return m33; 
	} 

	public void setM33(int m33) { 
		this.m33 = m33; 
	} 

	public int getM55() { 
		return m55; 
	} 

	public void setM55(int m55) { 
		this.m55 = m55; 
	} 

	public int getM100() { 
		return m100; 
	} 

	public void setM100(int m100) { 
		this.m100 = m100; 
	} 

	public int getM144() { 
		return m144; 
	} 

	public void setM144(int m144) { 
		this.m144 = m144; 
	} 

	public int getM200() { 
		return m200; 
	} 

	public void setM200(int m200) { 
		this.m200 = m200; 
	} 

	public TCross() { 
		 
	} 
	 
	public TCross(int m3,int m5,int m7,int m10,int m14,int m21, 
			   int m33,int m55,int m100,int m144,int m200) 
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
	 
	public TCross(double m3,double m5,double m7,double m10,double m14,double m21, 
			   double m33,double m55,double m100,double m144,double m200) 
	{ 
			this.setAm3(m3); 
			 
			this.setAm5(m5); 
			 
			this.setAm7(m7); 
			 
			this.setAm10(m10); 
			 
			this.setAm14(m14); 
			 
			this.setAm21(m21); 
			 
			this.setAm33(m33); 
			 
			this.setAm55(m55); 
			 
			this.setAm100(m100); 
			 
			this.setAm144(m144); 
			 
			this.setAm200(m200); 
	} 
	 
	public TCross(int dt,int st,int m3,int m5,int m7,int m10,int m14,int m21, 
			   int m33,int m55,int m100,int m144,int m200) 
	{ 
		this( m3, m5, m7, m10, m14, m21, m33, m55, m100, m144, m200); 
	} 
	 
	 
	public int cmp(Double a,Double b) 
	{ 
		if(a > b) 
		{ 
			return gl.E_OK; 
		} 
		else if(a < b) 
		{ 
			return gl.E_ERROR; 
		} 
		else 
		{ 
			return gl.E_EMPTY; 
		} 
	} 
	 
	 
	public static int getValueByType(TCross a,int type) 
	{ 
		 
		int t = type; 
		 
		int NONE = 3; 
		 
		switch(t) 
		{ 
				case TCrossMetric.M3: 
				return a.getM3(); 
				 
				case TCrossMetric.M5: 
				return a.getM5(); 
				 
				case TCrossMetric.M7: 
				return a.getM7(); 
				 
				case TCrossMetric.M10: 
				return a.getM10(); 
				 
				case TCrossMetric.M14: 
				return a.getM14(); 
				 
				case TCrossMetric.M21: 
				return a.getM21(); 
				 
				case TCrossMetric.M33: 
				return a.getM33(); 
				 
				case TCrossMetric.M55: 
				return a.getM55(); 
				 
				case TCrossMetric.M100: 
				return a.getM100(); 
					 
				case TCrossMetric.M144: 
				return a.getM144(); 
				 
				case TCrossMetric.M200: 
				return a.getM200(); 
					 
		} 
				return NONE; 
		 
	} 
	 
	public int sign(Double a) 
	{ 
		double b = 0.0f; 
		 
		if(a > b) 
		{ 
			return gl.E_OK; 
		} 
		else 
		{ 
			return gl.E_EMPTY; 
		} 
	} 
	 
	public double abs(Double a,Double b) 
	{ 
		return (a-b); 
				 
	} 
	 
	public void cmp(double o, double m3, double m5, double m7, double m10, double m14, double m21, double m33, double m55, double m100, double m144, double m200) 
	{ 
		 
		this.setAm3(abs(o,m3)); 
		 
		this.setAm5(abs(o,m5)); 
		 
		this.setAm7(abs(o,m7)); 
		 
		this.setAm10(abs(o,m10)); 
		 
		this.setAm14(abs(o,m14)); 
		 
		this.setAm21(abs(o,m21)); 
		 
		this.setAm33(abs(o,m33)); 
		 
		this.setAm55(abs(o,m55)); 
		 
		this.setAm100(abs(o,m100)); 
		 
		this.setAm144(abs(o,m144)); 
		 
		this.setAm200(abs(o,m200)); 
		 
	} 
	 
	public void cmp(double o, TMa ma ) 
	{ 
		 
		this.setAm3(abs(o,ma.getM3())); 
		 
		this.setAm5(abs(o,ma.getM5())); 
		 
		this.setAm7(abs(o,ma.getM7())); 
		 
		this.setAm10(abs(o,ma.getM10())); 
		 
		this.setAm14(abs(o,ma.getM14())); 
		 
		this.setAm21(abs(o,ma.getM21())); 
		 
		this.setAm33(abs(o,ma.getM33())); 
		 
		this.setAm55(abs(o,ma.getM55())); 
		 
		this.setAm100(abs(o,ma.getM100())); 
		 
		this.setAm144(abs(o,ma.getM144())); 
		 
		this.setAm200(abs(o,ma.getM200())); 
		 
				 
	} 
	 
	 
	@Override 
	public String toString() 
	{ 
		Locale.setDefault(new Locale("en", "US")); 
		 
		return String.format( 
				"%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,"+ 
				"%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f", 
				this.getM3(),this.getM5(),this.getM7(),this.getM10(),this.getM14(),this.getM21(),this.getM33(),this.getM55(),this.getM100(),this.getM144(),this.getM200(), 
				this.getAm3(),this.getAm5(),this.getAm7(),this.getAm10(),this.getAm14(),this.getAm21(),this.getAm33(),this.getAm55(),this.getAm100(),this.getAm144(),this.getAm200() 
				); 
	} 
	 
	public String toStringNess() 
	{ 
		return String.format( 
				"%s,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d", 
				DateUtil.to_string(this.getDt()), 
				this.getSt(), 
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
	 
public static TCross getInstance() 
{ 
	return new TCross(gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR, 
			          gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR,gl.E_ERROR, 
			          gl.E_ERROR,gl.E_ERROR,gl.E_ERROR); 
} 
public static TCross getInstance(Date date) 
{ 
		TCross cr = TCross.getInstance(); 

		cr.setDt(date); 

		return cr; 

} 
public static TCross getInstance(double m3,double m5,double m7,double m10,double m14,double m21, 
			   double m33,double m55,double m100,double m144,double m200) 
{ 
	return new TCross(  m3,  m5,  m7,  m10,  m14,  m21, 
			     m33,  m55,  m100,  m144,  m200); 
	 
} 
	 
	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:13 
