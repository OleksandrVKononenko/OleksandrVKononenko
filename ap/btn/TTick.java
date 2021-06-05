 
package ap.btn; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.event.ComponentEvent; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Collections; 
import java.util.Comparator; 
import java.util.Date; 
import java.util.List; 

import ap.area.AcceptorPair; 
import ap.area.AreaAcceptor; 
import ap.area.AreaManager; 
import ap.config.TConfig; 
import ap.global.gl; 
import ap.prop.BaseProperty; 
import ap.prop.SBounds; 
import ap.prop.SDate; 
import ap.shape.Ru; 
import ap.utils.CandleUtil; 
import ap.utils.CU; 
import ap.utils.Fu; 
import ap.utils.GU; 

public class TTick extends TPanel { 

	private static final long serialVersionUID = 23L; 
	 
	private Rectangle 		body; 
	 
	private Bar 			bar; 
	 
	 
	private List<Double>  	width_acceptors = null; 
	 
	private List<Double>  	height_acceptors = null; 
	 
	private double 			x_zoom; 
	 
	private double 			y_zoom; 
	 
	 
	 
	public double getX_zoom() { 
		return x_zoom; 
	} 

	public void setX_zoom(double x_zoom) { 
		this.x_zoom = x_zoom; 
	} 

	public double getY_zoom() { 
		return y_zoom; 
	} 

	public void setY_zoom(double y_zoom) { 
		this.y_zoom = y_zoom; 
	} 

	public List<Double> getWidth_acceptors() { 
		return width_acceptors; 
	} 

	public void setWidth_acceptors(List<Double> width_acceptors) { 
		this.width_acceptors = width_acceptors; 
	} 

	public List<Double> getHeight_acceptors() { 
		return height_acceptors; 
	} 

	public void setHeight_acceptors(List<Double> height_acceptors) { 
		this.height_acceptors = height_acceptors; 
	} 

	public Rectangle getBody() { 
		return body; 
	} 

	public void setBody(Rectangle body) { 
		this.body = body; 
	} 

	public Bar getBar() { 
		return bar; 
	} 

	public void setBar(Bar bar) { 
		this.bar = bar; 
		 
	} 

	public TTick() { 
		 
		 
	} 

	public TTick(String name, boolean mode) { 
		super(name, mode); 
		 
	} 

	public TTick(Rectangle rect) { 
		super(rect); 
		 
	} 

	public TTick(Rectangle rect, int index) { 
		super(rect, index); 
		 
	} 
	 
	public TTick(Rectangle rect, Color color) { 
		super(rect, color); 
		 
	} 

	public TTick(String payload) { 
		super(payload); 
		 
	} 

	public TTick(BaseProperty prop) { 
		super(prop); 
		 
		this.setData(super.getData()); 
		 
	} 
	 
	public void setColors(Color up,Color down) 
	{ 
		 
		if (this.getBar().getSign() == gl.E_ERROR) 
		{ 
			this.setUp_color(down); 
			 
			this.setDown_color(up); 
		} 
		else 
		{ 
			this.setUp_color(up); 
			 
			this.setDown_color(down); 
		} 
			 
		 
	} 
	 
	@Override 
	public void setData(String data) 
	{ 
		 
		List<Bar> items = new ArrayList<Bar>(); 
		 
		if (Bar.parseOneItem(data,items)) 
		{ 
			this.setBar(items.get(gl.E_EMPTY)); 
			 
			 
			 
			super.setData(this.getBar().toStringDateOHLC()); 
			 
			double ha_acc = (this.getBar().getHighShadow()*TChart.EUR_MULTIPLIER) / this.getAcceptedVolume(); 
					 
			double ba_acc = (this.getBar().getBody()*TChart.EUR_MULTIPLIER) /this.getAcceptedVolume(); 
			 
			double la_acc = (this.getBar().getLowShadow()*TChart.EUR_MULTIPLIER) /this.getAcceptedVolume(); 
			 
			 
			if(this.getBar().getHighShadow()==gl.E_EMPTY) 
				ha_acc = gl.E_EMPTY; 
									 
			if(this.getBar().getBody()==gl.E_EMPTY) 
				ba_acc = gl.E_EMPTY; 
			 
			if(this.getBar().getLowShadow()==gl.E_EMPTY) 
				la_acc = gl.E_EMPTY; 
						 
			/* 
			gl.tracex(new Object(){},String.format("---> Parsing...date...%s...ha...%.4f...ba...%.4f...sha...%.4f...delta...%.4f...vol...%d...%s",SDate.toString(this.getBar().getDate()),ha_acc,ba_acc,(1-(ha_acc+ba_acc)),1- (ha_acc+ba_acc + (1-(ha_acc+ba_acc))),this.getAcceptedVolume(),data)); 
			 
			gl.tracex(new Object(){},String.format("vol...%d...high...%.4f...real high...%.4f",this.getAcceptedVolume(),(ha_acc*this.getAcceptedVolume()),(this.getBar().getHighShadow()*TTick.EUR_MULTIPLIER))); 

			gl.tracex(new Object(){},String.format("vol...%d...body...%.4f...real body...%.4f",this.getAcceptedVolume(),(ba_acc*this.getAcceptedVolume()),(this.getBar().getBody()*TTick.EUR_MULTIPLIER))); 

			gl.tracex(new Object(){},String.format("vol...%d...low...%.4f...real low...%.4f",this.getAcceptedVolume(),(la_acc*this.getAcceptedVolume()),(this.getBar().getLowShadow()*TTick.EUR_MULTIPLIER))); 
			 
			*/ 
			 
			this.setWidth_acceptors(Arrays.asList(new Double[]{0.2,0.2,0.2,0.2})); 
			 
			this.setHeight_acceptors(Arrays.asList(new Double[]{ha_acc,ba_acc})); 
			 
		} 
		else 
		{ 
				gl.tracex(new Object(){},String.format("Parsing...%s...%s",data,gl.S_ERROR)); 
		} 
	} 
	 
	 
	@Override 
	/* 
	public void paintComponent_prev(Graphics g) { 
		 
		super.setSkip_draw_text(true); 
		 
		if (this.getWidth_acceptors() == null) 
			return; 
		 
		 
		Rectangle rc = ARect.norm4g(this.getBounds()); 
	 
		AreaAcceptor aa = new AreaAcceptor(new AcceptorPair(rc.width,rc.height), 
				this.getWidth_acceptors(), 
				this.getHeight_acceptors()); 
		 
		Rectangle high_shadow_rect = aa.getBounds(2, 0); 
		 
		Rectangle body_rect   = aa.getBounds(0,1); 
		 
		body_rect.width = body_rect.width*5; 
				 
		Rectangle low_shadow_rect = aa.getBounds(2, 2); 

		 
		Color body_color = this.getUp_color(); 
		 
		Color shadow_color = this.getDown_color(); 
				 
		 
		Graphics2D g2 = (Graphics2D) g; 
		 
		g2.setStroke(new BasicStroke(1.0f)); 
		 
		 
		// Shadow; 
		 
		Insets ins = new Insets(1,1,1,2); 
		 
		body_rect = ARect.getSpannedRect(body_rect,ins); 
		 
		 
		// Fill section. 

		 
		GraphicsUtil.fillAlphaRect(g2, 
				ARect.getSpannedRect(high_shadow_rect, ins), body_color, 1.0f); 

		GraphicsUtil.fillAlphaRect(g2,ARect.getSpannedRect(body_rect,new Insets(1,1,1,1)), body_color, 1.0f); 

		GraphicsUtil.fillAlphaRect(g2, 
				ARect.getSpannedRect(low_shadow_rect, ins), body_color, 1.0f); 

		 
		// Draw section. 
		GraphicsUtil.drawAlphaRect(g2, high_shadow_rect, shadow_color, 1.0f); 

		GraphicsUtil.drawAlphaRect(g2, body_rect, shadow_color, 1.0f); 

		GraphicsUtil.drawAlphaRect(g2, low_shadow_rect, shadow_color, 1.0f);		 
		 
		if(this.isBlGrid()) 
		{ 
			 
		 
		body_color= ColorUtil.getAlphaColor(Color.black,this.getBackground().getAlpha()); 
		 
		for(int y=0;y< this.getHeight_acceptors().size()+1;y++) 
			for(int x=0;x< this.getWidth_acceptors().size()+1;x++) 
			{ 
		 
				GraphicsUtil.drawAlphaRect(g2,aa.getBounds(x, y),body_color, 1.0f); 
				 
			} 
		} 
	} 
*/ 
	 
	public void paintComponent(Graphics g) { 
		 
		super.setSkip_draw_text(true); 
		 
		if (this.getWidth_acceptors() == null) 
			return; 
		 
		Graphics2D g2 = (Graphics2D) g; 
		 
		Rectangle rc = Ru.norm4g(this.getBounds()); 
	 
		AreaAcceptor aa = new AreaAcceptor(new AcceptorPair(rc.width,rc.height), 
				this.getWidth_acceptors(), 
				this.getHeight_acceptors()); 
		 
		Rectangle high_shadow_rect = aa.getBounds(2, 0); 
		 
		Rectangle body_rect   = aa.getBounds(0,1); 
		 
		body_rect.width = body_rect.width*5; 
				 
		Rectangle low_shadow_rect = aa.getBounds(2, 2); 

		 
		Color body_color = this.getUp_color(); 
		 
		g2.setStroke(new BasicStroke(1.0f)); 
		 
		if(body_rect.height == gl.E_EMPTY && (this.getBar().getBody()*TChart.EUR_MULTIPLIER) != gl.E_EMPTY) 
		{ 
			int h_tmp = (int)(this.getBar().getBody() * TChart.EUR_MULTIPLIER); 
			 
		    body_rect.height = h_tmp; 
		 
		}else if(body_rect.height == gl.E_EMPTY && (this.getBar().getBody()*TChart.EUR_MULTIPLIER) == gl.E_EMPTY) 
		{ 
			body_rect.height = gl.E_OK; 
		} 
			 
		// For any case where body empty. 
		 
		if(body_rect.height == gl.E_EMPTY) 
		{ 
			body_rect.height = gl.E_OK; 
		} 
		 
		 
		 
		/* 
		gl.tracex(new Object(){},String.format("--->y_zoom...%s...zoom...%.4f...body...%d...body check...%d...body px...%d", 
				SDate.toString(this.getBar().getDate()), 
				 this.getY_zoom(), 
				 (int)(this.getBar().getBody()*TTick.EUR_MULTIPLIER), 
				(int)((this.getBar().getBody()*TTick.EUR_MULTIPLIER)*this.getY_zoom()), 
				body_rect.height 
				 
				)); 
		*/ 
		 
		//gl.tracex(new Object(){},String.format("--->vol...%d...vol px...%d...body px...%d...real body...%.4f",this.getAcceptedVolume(),rc.height,body_rect.height,(this.getBar().getBody()*TTick.EUR_MULTIPLIER))); 
		 
		 
		// Shadow; 
		 
		//Insets ins = new Insets(1,1,1,2); 
		 
		//body_rect = ARect.getSpannedRect(body_rect,ins); 
		 
		 
		// Fill section. 

		 
		//GraphicsUtil.fillAlphaRect(g2, 
			//	ARect.getSpannedRect(high_shadow_rect, ins), body_color, 1.0f); 

		GU.drawAlphaLine(g2, 
				new Point(high_shadow_rect.x + (high_shadow_rect.width/2),high_shadow_rect.y), 
				new Point(high_shadow_rect.x + (high_shadow_rect.width/2),high_shadow_rect.y+high_shadow_rect.height), 
				body_color, 
				1.0f 
				); 
		 
		//GraphicsUtil.fillAlphaRect(g2,ARect.getSpannedRect(body_rect,new Insets(1,1,1,1)), body_color, 1.0f); 
		GU.fillAlphaRect(g2,body_rect, body_color, 1.0f); 
		 
		//GraphicsUtil.fillAlphaRect(g2, 
			//	ARect.getSpannedRect(low_shadow_rect, ins), body_color, 1.0f); 
		 
		 
		 
		GU.drawAlphaLine(g2, 
				new Point(low_shadow_rect.x + (low_shadow_rect.width/2),low_shadow_rect.y), 
				new Point(low_shadow_rect.x + (low_shadow_rect.width/2),low_shadow_rect.y+low_shadow_rect.height), 
				body_color, 
				1.0f 
				); 
		 
		//GraphicsUtil.fillAlphaRect(g2, 
		//	ARect.getSpannedRect(low_shadow_rect, ins), body_color, 1.0f); 
	 

		 
		// Draw section. 
		//GraphicsUtil.drawAlphaRect(g2, high_shadow_rect, shadow_color, 1.0f); 

		//GraphicsUtil.drawAlphaRect(g2, body_rect, shadow_color, 1.0f); 

		//GraphicsUtil.drawAlphaRect(g2, low_shadow_rect, shadow_color, 1.0f);		 
		 
		if(this.isBlGrid()) 
		{ 
			 
		 
		body_color= CU.getAlphaColor(Color.black,this.getBackground().getAlpha()); 
		 
		for(int y=0;y< this.getHeight_acceptors().size()+1;y++) 
			for(int x=0;x< this.getWidth_acceptors().size()+1;x++) 
			{ 
		 
				GU.drawAlphaRect(g2,aa.getBounds(x, y),body_color, 1.0f); 
				 
			} 
		} 
		 

		if(this.getBar().getType() == gl.E_EMPTY) 
		{ 
		 
			// For debug no type error. 
			g2.setStroke(new BasicStroke(1.0f)); 
		 
			GU.drawAlphaRect(g2,Ru.get_spanned_rect(rc,new Insets(0,0,1,1)),Color.green, 1.0f); 
		 
		} 
		 
	} 


	public int getAcceptedVolume() 
	{ 
			double volume = this.getBar().getVolume(); 
			 
			int height = 0; 
			 
			if(volume < 0.1) 
			height = (int)(volume * TChart.EUR_MULTIPLIER); 
			else 
			height = (int)(volume * 100); 
			 
			return height; 
		 
	} 

	public static double getMinLow(List<TTick> items) 
	{ 
			return items.stream().mapToDouble(a->a.getBar().getL()).min().getAsDouble(); 
	} 
	 
	public static double getMaxHigh(List<TTick> items) 
	{ 
			return items.stream().mapToDouble(a->a.getBar().getH()).max().getAsDouble(); 
	} 
	 
	public static Date getMinDate(List<TTick> items) 
	{ 
		TTick t = Collections.min(items,Comparator.comparing(a->a.getBar().getDate())); 
		 
		return t.getBar().getDate(); 
	} 
	 
	public static Date getMaxDate(List<TTick> items) 
	{ 
		TTick t = Collections.max(items,Comparator.comparing(a->a.getBar().getDate())); 
		 
		return t.getBar().getDate(); 
	} 
	 
		 
	public static List<TPanel> getInstances(int count) { 

		List<TPanel> arr = new ArrayList<TPanel>(); 

		String data_source_file = String.format("%s\\data\\%s.txt",Fu.getCurrentDir(),"eurusdw_2016"); 
		 
		List<String> data = new ArrayList<String>(); 
		 
		boolean data_on_board = false; 
		 
		String TEMPLATE_DATA_ROW = "27.04.1998,1.0950,1.1101,1.0928,1.1036"; 
		 
		int tmp_count = count; 
		 
		if(!Fu.isaFile(data_source_file)) 
		{ 
			gl.tracex(new Object(),String.format("Load data from...%s...%s",data_source_file,gl.S_WARN)); 
		} 
		else 
		{ 
			data = Fu.get_list_from_file(data_source_file); 
			 
			data_on_board = true; 
			 
			tmp_count = data.size(); 
					 
			gl.tracex(new Object(),String.format("Load data from...%s...size...%d...rows count...%06d...%s",data_source_file,data.size(),tmp_count,gl.S_OK)); 
		} 
		 
		for (int i = 0; i < tmp_count; i++) { 

			TTick sp = new TTick(new Rectangle(gl.getRandomInt(128),gl.getRandomInt(128),16,16), i); 

			sp.setGradient(CU.getAlphaColor(sp.getBackground(), 0)); 
			 
			sp.setUp_color(Color.white); 
			 
			sp.setDown_color(Color.black); 
						 
			sp.setAlign(new Rectangle(1,0,1,1)); 
			 
			if(data_on_board) 
				sp.setData(data.get(i)); 
			else 
				sp.setData(TEMPLATE_DATA_ROW); 
			 
			//sp.setText(SDate.toString(sp.getBar().getDate())); 
			 
			arr.add(sp); 
			 
		} 
		 
			arr.forEach(a-> 
			{ 
				 
				Rectangle rect = a.getBounds(); 
				 
				rect.height = ((TTick)a).getAcceptedVolume(); 
						 
				a.setBou_nds(rect); 
				 
			}); 
	 
				return arr; 
	} 
	 
		 
	@Override 
	public String toString() 
	{ 
		//return String.format("%s",this.getBar().toString()); 
		return String.format("%s,%3d %3d %3d %4d [%2d]", 
				SDate.toString(this.getBar().getDate()), 
				this.getBar().getHighShadowM(), 
				this.getBar().getBodyM(), 
				this.getBar().getLowShadowM(), 
				this.getAcceptedVolume(), 
				this.getBar().getType() 
				 
				); 
		 
	} 
	public static void startUp() { 
		 
		TConfig.start(TTick.getInstances(gl.E_OK)); 

	} 
	 
	public static void main(String[] args) { 
		 
		startUp(); 

	} 
	 
	 
} 
