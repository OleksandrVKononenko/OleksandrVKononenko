 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import java.awt.Point; 
import java.util.HashMap; 
import java.util.Map; 
import ap.global.gl; 
import ap.prop.SDate; 

public class TPivot { 

	public static final int STANDARD = gl.E_EMPTY; 
	 
	public static final int DEMARK = gl.E_OK; 
	 
	public static final int FIBONACCI = gl.E_OK*2; 
	 

	private int type = STANDARD; 
	 
	private Bar bar; 
	 
	private TPointDI R1; 
	 
	private TPointDI R2; 
	 
	private TPointDI R3; 
	 
	private TPointDI S1; 
	 
	private TPointDI S2; 
	 
	private TPointDI S3; 
	 
	private TPointDI P; 
	 
	 

	public TPointDI getR1() { 
		return R1; 
	} 

	public void setR1(TPointDI r1) { 
		R1 = r1; 
	} 

	public TPointDI getR2() { 
		return R2; 
	} 

	public void setR2(TPointDI r2) { 
		R2 = r2; 
	} 

	public TPointDI getR3() { 
		return R3; 
	} 

	public void setR3(TPointDI r3) { 
		R3 = r3; 
	} 

	public TPointDI getS1() { 
		return S1; 
	} 

	public void setS1(TPointDI s1) { 
		S1 = s1; 
	} 

	public TPointDI getS2() { 
		return S2; 
	} 

	public void setS2(TPointDI s2) { 
		S2 = s2; 
	} 

	public TPointDI getS3() { 
		return S3; 
	} 

	public void setS3(TPointDI s3) { 
		S3 = s3; 
	} 

	public TPointDI getP() { 
		return P; 
	} 

	public void setP(TPointDI p) { 
		P = p; 
	} 

	public int getType() { 
		return type; 
	} 

	public void setType(int type) { 
		this.type = type; 
	} 

	 
	public Bar getBar() { 
		return bar; 
	} 

	public void setBar(Bar bar) { 
		this.bar = bar; 
	} 

	 

	public TPivot() { 
		 
	} 
	 
	public TPivot(TPointDI r1,TPointDI s1,TPointDI p) 
	{ 
		this.setR1(r1); 

		this.setS1(s1); 
		 
		this.setP(p); 

	} 
	 
	public TPivot(TPointDI r1,TPointDI r2,TPointDI s1,TPointDI s2,TPointDI p) 
	{ 
		this.setR1(r1); 
		 
		this.setR2(r2); 
		 
		this.setS1(s1); 
		 
		this.setS2(s2); 
		 
		this.setP(p); 
		 
	} 

	 
	public TPivot(TPointDI r1,TPointDI r2,TPointDI r3,TPointDI s1,TPointDI s2,TPointDI s3,TPointDI p) 
	{ 
		this(r1,r2,s1,s2,p); 
		 
		this.setR3(r3); 
		 
		this.setS3(s3); 
	} 

	 
	 
	public TPivot(Bar bar) { 
		 
		this.setBar(bar); 
	} 
	 
	public TPivot(Bar bar,int type) { 
		 
		this(bar); 
		 
		this.setType(type); 
		 
		this.get(); 
	} 

	public void get(int type) 
	{ 
		this.setType(type); 
		 
		get(); 
	} 
	 
	public void get() 
	{ 
		 
		int type = this.getType(); 
		 
		if(type == STANDARD) 
		{ 
			double P = (this.getBar().getH() + this.getBar().getL() + this.getBar().getC())/3; 
			 
			double H = (this.getBar().getH()); 
			 
			double L = (this.getBar().getL()); 
			 
			TPointDI S1 = new TPointDI((P*2) - H); 
			 
			TPointDI S2 = new TPointDI(P - (H - L)); 
			 
			TPointDI R1 = new TPointDI((P*2) - L); 
			 
			TPointDI R2 = new TPointDI(P + (H - L)); 
			 
			this.setR1(R1); 
			 
			this.setR2(R2); 
			 
			this.setS1(S1); 
			 
			this.setS2(S2); 
			 
			this.setP(new TPointDI(P)); 
			 
		} else if(type == DEMARK) 
			{ 
				 
				//decode( 
			// sign(x.C - x.O), 
			//    -1,x.H +(2*x.L)+x.C, 
			//    0,(x.H + x.L + (2*x.C)), 
			//    1, (2*x.H)+x.L + x.C) as "X", 
			 
			 
			    double BASE = gl.E_EMPTY; 
			 
			 
			 
				if (this.getBar().getSign() == gl.E_ERROR) 
				{ 
					BASE = this.getBar().getH() + (2*this.getBar().getL()) + this.getBar().getC(); 
				} else if (this.getBar().getSign() == gl.E_EMPTY) 
				{ 
					BASE = this.getBar().getH() + this.getBar().getL() + (2*this.getBar().getC()); 
				} else if (this.getBar().getSign() == gl.E_OK) 
				{ 
					BASE = (2*this.getBar().getH()) + this.getBar().getL() + this.getBar().getC() ; 
				} 

				//round(d.X /4,4) as "PP", 
				//round((d.X /2)-d.H,4) as "S", 
				//round((d.X /2)-d.L,4) as "R" 

				TPointDI P = new TPointDI(BASE / 4); 
				 
				TPointDI S = new TPointDI((BASE / 2) - this.getBar().getH()); 
				 
				TPointDI R = new TPointDI((BASE / 2) - this.getBar().getL()); 
				 
				this.setP(P); 
				 
				this.setR1(R); 
				 
				this.setS1(S); 
							 
								 
			} else if(type == FIBONACCI) 
		{ 
			double P = (this.getBar().getH() + this.getBar().getL() + this.getBar().getC())/3; 
			 
			double H = (this.getBar().getH()); 
			 
			double L = (this.getBar().getL()); 
			 
			/* 
			round(d.P,4) PP, 
			round(d.P  - (0.382 * (d.H-d.L)),4) as "S1", 
			round(d.P  - (0.618 * (d.H-d.L)),4) as "S2", 
			round(d.P  - (1.0 * (d.H-d.L)),4) as "S3", 

			round(d.P  + (0.382 * (d.H-d.L)),4) as "R1", 
			round(d.P  + (0.618 * (d.H-d.L)),4) as "R2", 
			round(d.P  + (1 * (d.H-d.L)),4) as "R3" 
			*/ 

			 
			TPointDI S1 = new TPointDI ( P - (0.382 * (H - L))); 
			 
			TPointDI S2 = new TPointDI(P - (0.618 * (H - L))); 
			 
			TPointDI S3 = new TPointDI(P - (1.0 * (H - L))); 
			 
			 
			TPointDI R1 = new TPointDI(P + (0.382 * (H - L))); 
			 
			TPointDI R2 = new TPointDI(P + (0.618 * (H - L))); 
			 
			TPointDI R3 = new TPointDI(P + (1.0 * (H - L))); 
			 
			 
			this.setR1(R1); 
			 
			this.setR2(R2); 
			 
			this.setR3(R3); 
			 
			 
			this.setS1(S1); 
			 
			this.setS2(S2); 

			this.setS3(S3); 
			 
			this.setP(new TPointDI(P)); 
			 
		} 
			 
	} 
	 
	public void setPoint(int x,double base_point_value,double base_line_y,int owner_bounds_y,double form_y_zoom_factor) 
	{ 
		 
		int type = this.getType(); 
				 
		if(type == FIBONACCI) 
		{ 
			this.getR1().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getR1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getR2().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getR2().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getR3().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getR3().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getS1().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getS1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getS2().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getS2().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getS3().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getS3().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getP().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getP().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

		} else if(type == DEMARK) 
		{ 
			this.getR1().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getR1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getS1().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getS1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getP().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getP().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 
			 
		}else if(type == STANDARD) 
		{ 
			 
			this.getR1().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getR1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getR2().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getR2().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getS1().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getS1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getS2().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getS2().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 

			this.getP().setPoint(new Point(x,(int)(owner_bounds_y + base_line_y + (((base_point_value - this.getP().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR))); 
			 
			 
			 
			 
		} 


		 
	} 
	 
	/* 
	public static TPivot getInstance(TPivot pivot,int x,double base_point_value,double base_line_y,int owner_bounds_y,double form_y_zoom_factor) 
	{ 
		TPivot result = null; 
		 
		int type = pivot.getType(); 
				 
		if(type == FIBONACCI) 
		{ 
			TPointDI r1  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getR1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI r2  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getR2().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI r3  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getR3().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI s1  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getS1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI s2  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getS2().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI s3  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getS3().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI p  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getP().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR) ; 
			 
			 
			result = new TPivot(r1,r2,r3,s1,s2,s3,p); 
						 
			 
		} else if(type == DEMARK) 
		{ 
			 
			TPointDI r1  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getR1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR) ; 
			 
			TPointDI s1  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getS1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI p  = new TPointDI(owner_bounds_y + base_line_y + (((base_point_value - pivot.getP().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			result = new TPivot(r1,s1,p); 
			 
		}else if(type == STANDARD) 
		{ 
			TPointDI r1  = new TPointDI( owner_bounds_y + base_line_y + (((base_point_value - pivot.getR1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI r2  = new TPointDI( owner_bounds_y + base_line_y + (((base_point_value - pivot.getR2().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI s1  = new TPointDI( owner_bounds_y + base_line_y + (((base_point_value - pivot.getS1().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI s2  = new TPointDI( owner_bounds_y + base_line_y + (((base_point_value - pivot.getS2().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR); 
			 
			TPointDI p  = new TPointDI( owner_bounds_y + base_line_y + (((base_point_value - pivot.getP().getData())*TChart.EUR_MULTIPLIER) * form_y_zoom_factor)*TChart.SCALE_FACTOR) ; 
			 
			 
			result = new TPivot(r1,r2,s1,s2,p); 

			 
		} 
		 
			return result; 
		 
	} 
	*/ 
	 
	 
	public String toString() 
	{ 
		String msg = ""; 
		 
		if(this.getType() == STANDARD) 
			msg =  String.format("%s,%s,%s,%s,%s,%s", 
					SDate.toString(this.getBar().getDate()), 
					this.getR2().toString(), 
					this.getR1().toString(), 
					this.getP().toString(), 
					this.getS1().toString(), 
					this.getS2().toString() 
				); 
		else if(this.getType() == FIBONACCI) 
		{ 
			msg =  String.format("%s,%s,%s,%s,%s,%s,%s,%s", 
					SDate.toString(this.getBar().getDate()), 
					this.getR3().toString(), 
					this.getR2().toString(), 
					this.getR1().toString(), 
					this.getP().toString(), 
					this.getS1().toString(), 
					this.getS2().toString(), 
					this.getS3().toString() 
				); 
		}else if(this.getType() == DEMARK) 
		{ 
			msg =  String.format("%s,%s,%s,%s", 
					SDate.toString(this.getBar().getDate()), 
					this.getR1().toString(), 
					this.getP().toString(), 
					this.getS1().toString() 
				); 
		} 
		 
			return msg; 
		 
	} 
	 
	public static TPivot getInstance(Bar bar,int type) 
	{ 
		return new TPivot(bar,type); 
	} 

	 
	public static void main(String[] args) { 
		 

	} 

} 
