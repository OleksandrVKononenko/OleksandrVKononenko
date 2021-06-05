 
 
 
 
 
package ap.btn; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics2D; 
import java.awt.Rectangle; 
import java.awt.geom.GeneralPath; 
import java.awt.geom.Line2D; 
import java.awt.geom.Point2D; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
import java.util.Map; 

import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.GU; 

public class TGeneralPathSuite { 

	private TPanel owner; 
	 
	private List<String> series; 
	 
	private Map<String,List<TMaPoint>> points; 
	 
	public TPanel getOwner() { 
		return owner; 
	} 

	public void setOwner(TPanel owner) { 
		this.owner = owner; 
	} 

	public List<String> getSeries() { 
		return series; 
	} 

	public void setSeries(List<String> series) { 
		this.series = series; 
	} 

	public Map<String,List<TMaPoint>> getPoints() { 
		return points; 
	} 

	public void setPoints(Map<String,List<TMaPoint>> points) { 
		this.points = points; 
	} 

	public TGeneralPathSuite() { 
		 
	} 
	 
	public TGeneralPathSuite(TChart owner,List<String> series,Map<String,List<TMaPoint>> points) 
	{ 
		this.setOwner(owner); 
		 
		this.setSeries(series); 
		 
		this.setPoints(points); 
	 
	} 
	 
	public static TGeneralPathSuite getInstance(TChart owner,List<String> series,Map<String,List<TMaPoint>> points) 
	{ 
		return new TGeneralPathSuite(owner,series,points); 
	} 
	 
	 /* 
	public TGeneralPath get_general_path(String series,int type) 
	{ 
		List<TGeneralPath> list = this.getOwner().getGeneral_paths().get(series); 
		 
		if(list == null || list.size() == gl.E_EMPTY) 
			return null; 
		 
		return list.stream().filter((g-> 
		( 
				g.getType()   == type 
				 
		))).findFirst().orElse(null); 
		 
	} 
	*/ 
	 
	 
	/* 
	public void drawTo(Graphics2D g2) 
	{ 
		 
		this.getSeries().forEach(sr-> { 
			 
			this.getOwner().getGeneral_paths().get(sr).forEach(g->{ 
			 
				if(!g.isBl_select()) 
				g.draw(g2); 
				 
			}); 
			 
		}); 
	} 
	*/ 
	 
	/* 
	public void drawPaths(Graphics2D g2) 
	{ 
		init(); 
		 
		moveTo(); 
		 
		drawTo(g2); 
	} 
	 
	 */ 
	/* 
	public void init() 
	{ 
		List<TGeneralPath> generals = new ArrayList<TGeneralPath>(); 
		 
		this.getSeries().forEach(sr-> { 
			 
		gl.ma_types.forEach(ma->{ 
			 			 
			TGeneralPath gp = new TGeneralPath(new GeneralPath(GeneralPath.WIND_EVEN_ODD,this.getPoints().get(sr).size()),sr,ma,gl.ma_styles[ma]); 
			 
			generals.add(gp); 
			 
		}); 
		 
			 
		if (owner.getGeneral_paths().containsKey(sr)) 
			owner.getGeneral_paths().replace(sr,generals); 
		else 
			owner.getGeneral_paths().put(sr,generals); 

		}); 
	} 
	 
	 */ 
	 
	/* 
	public void moveTo() 
	{ 
		 
		Rectangle bounds = this.getOwner().getBounds(); 
		 
		this.getSeries().forEach(sr-> { 
			 
			List<TGeneralPath> generals = this.getOwner().getGeneral_paths().get(sr); 
			 
			generals.forEach(g->{ 
				 
					TMaPoint[]  tmp = {null}; 
					 
					this.getPoints().get(sr).forEach(p->{ 
						 
						TMaPoint r = tmp[gl.E_EMPTY]; 
						 
						if(r == null) 
						{ 
							g.getOrigin().moveTo( 
									ARect.normPoint(bounds, 
											p.getValueByType(g.getType()) 
											).x, 
									ARect.normPoint(bounds, 
											p.getValueByType(g.getType()) 
											).y); 
						} 
						else 
						{ 
							 
							g.getOrigin().lineTo( 
									ARect.normPoint(bounds, 
											p.getValueByType(g.getType()) 
											).x, 
									ARect.normPoint(bounds, 
											p.getValueByType(g.getType()) 
											).y); 
							 
						} 
						 
						tmp[gl.E_EMPTY] = p; 
					 
					 
					}); 
			}); 
		 
		}); 
	} 
	 
	 */ 
	 

	public static void main(String[] args) { 
		 

	} 

} 
