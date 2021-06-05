 
package ap.thread; 

import java.io.FileWriter; 
import java.io.IOException; 

import ap.gen.TIDGen; 
import ap.global.gl; 
import ap.swing.PanelXml; 


public class Grok extends Thread { 

	private int planned_fps; 

	private long planned_tock; 

	private long work_tock; 

	private long real_tock = 0L; 

	private long manage_tock = 0L; 

	private long summary_tock = 1L; 

	private long average_tock = 1L; 

	private long tick_count = 1L; 

	private FileWriter log_file = null; 

	private boolean debug = false; 

	private long m_manage_count = 0L; 

	private long m_avg_manage_value = 1L; 

	private long m_sum_manage_value = 0L; 

	private long m_sum_manage_count = 0L; 
	 
	private boolean bl_break; 
	 
	private long pid = 0L; 
	 
	final int REFRESH_STAT_COUNT = 60*60*60; 
	 
	private PanelXml panel = null; 
	 
	 

	public PanelXml getPanel() { 
		return panel; 
	} 

	public void setPanel(PanelXml panel) { 
		this.panel = panel; 
	} 

	public Grok() { 

		this.setPid(TIDGen.nextId()); 
		 
		this.setPlanned_fps(10); 
		 
	} 

	public Grok(int fps) { 
		 
		this.setPid(TIDGen.nextId()); 
		 
		this.setPlanned_fps(fps); 

	} 

	 
	 
	public long getPid() { 
		return pid; 
	} 

	public void setPid(long pid) { 
		this.pid = pid; 
	} 

	public boolean isBl_break() { 
		return bl_break; 
	} 

	public void setBl_break(boolean bl_break) { 
		this.bl_break = bl_break; 
	} 

	public int getPlanned_fps() { 
		return planned_fps; 
	} 

	public void setPlanned_fps(int planned_fps) { 
		 
		this.planned_fps = planned_fps; 
		 
		this.planned_tock = 1000 / this.planned_fps; 
	} 

	public int getFps() { 

		int fps = 1; 

		if (this.getAverage_tock() != 0) 
			fps = (int) (1000 / this.getAverage_tock()); 

		return fps; 
	} 

	public long getSum_manage_count() { 
		return m_sum_manage_count; 
	} 

	public void setSum_manage_count(long sum_manage_count) { 
		this.m_sum_manage_count = sum_manage_count; 
	} 

	public long getAvg_manage_value() { 
		return m_avg_manage_value; 
	} 

	public void setAvg_manage_value(long avg_manage_value) { 
		this.m_avg_manage_value = avg_manage_value; 
	} 

	public long getSum_manage_value() { 
		return m_sum_manage_value; 
	} 

	public void setSum_manage_value(long sum_manage_value) { 
		this.m_sum_manage_value = sum_manage_value; 
	} 

	public long getManage_count() { 
		return m_manage_count; 
	} 

	public void setManage_count(long manage_count) { 
		this.m_manage_count = manage_count; 
	} 

	public void sm(Object obj) { 
		System.out.print(String.valueOf(obj)); 
	} 

	public long getPlanned_tock() { 
		return planned_tock; 
	} 

	public void setPlanned_tock(long planned_tock) { 
		this.planned_tock = planned_tock; 
	} 

	public long getWork_tock() { 
		return work_tock; 
	} 

	public void setWork_tock(long work_tock) { 
		this.work_tock = work_tock; 
	} 

	public boolean isDebug() { 
		return debug; 
	} 

	public void setDebug(boolean debug) { 
		this.debug = debug; 
	} 

	public long getSummary_tock() { 
		return summary_tock; 
	} 

	public void setSummary_tock(long summary_tock) { 
		this.summary_tock = summary_tock; 
	} 

	public long getAverage_tock() { 
		return average_tock; 
	} 

	public void setAverage_tock(long average_tock) { 
		this.average_tock = average_tock; 
	} 

	public long getTick_count() { 
		return tick_count; 
	} 

	public void setTick_count(long tick_count) { 
		this.tick_count = tick_count; 
	} 

	public void incTick_count() { 
		this.tick_count++; 
	} 

	public long getReal_tock() { 
		return real_tock; 
	} 

	public long getManage_tock() { 
		return manage_tock; 
	} 

	public void setManage_tock(long manage_tock) { 
		this.manage_tock = manage_tock; 
	} 

	public void setReal_tock(long real_tock) { 
		this.real_tock = real_tock; 
	} 

	public void trace(String msg, boolean mirror) { 
		if (!this.isDebug()) 
			return; 

		this.trace(msg); 

		if (mirror) 
			this.sm(msg); 
	} 

	public void trace(String msg) { 
		if (!this.isDebug()) 
			return; 

		try { 

			String fileName = String.format("log_freq_%s.log", 
					this.getPlanned_fps()); 

			if (log_file == null) 
				log_file = new FileWriter(fileName); 

			log_file.write(msg); 

		} catch (IOException e) { 

			System.out.println(String.format( 
					"Error while write to log file (IOException) : %s", 
					e.getMessage())); 

		} 

	} 

	@Override 
	public void run() { 

		//sm("Grok started."); 

		String msg = ""; 
		 
		gl.smn(gl.sf("%06d...Started.",this.getPid())); 
		 
		while (true) { 

			long st = System.nanoTime(); 

			try { 

				sleep(this.getWork_tock()); 
				 
				//gl.sm("."); 
				 
				if(this.isBl_break()) 
				{ 
					gl.smn(gl.sf("%06d...Break...signal received",this.getPid())); 
					 
					break; 
				} 
				 
				if(this.getPanel() != null) 
				{ 
					this.getPanel().getDecoro().setText(gl.sf("%06d",this.getTick_count())); 
										 
				} 

			} catch (InterruptedException e) { 

				gl.smn(gl.sf("run...exception...%s",e.getMessage())); 
			} 

			long m_real_tock = (System.nanoTime() - st) / 1000000; 
					 
			this.setReal_tock(m_real_tock); 

			this.setManage_tock(this.getPlanned_tock() - this.getReal_tock()); 

			 
			if (this.getManage_tock() != 0) { 

				 
				this.m_sum_manage_count += 1; 

				this.m_sum_manage_value += Math.abs(this.getManage_tock()); 

				this.m_avg_manage_value = this.m_sum_manage_value / this.m_sum_manage_count; 
				 
				 

				long m_set_up = (this.getWork_tock() + this.getManage_tock()); 

				String m_msg = ""; 

				if (m_set_up > 0 && m_manage_count > 5) { 

					// Accept management value. 
					this.setWork_tock(m_set_up); 

					m_msg = gl.sf("setup...%03d...mng...%03d",m_set_up,this.getManage_count()); 

					//trace(m_msg); 

					// Clear influence value. 
					this.setManage_count(0); 
				} 

					//this.trace(msg); 

					m_manage_count++; 

			} // this.getManage_tock() != 0 

			this.trace(System.lineSeparator()); 

			this.summary_tock += this.getReal_tock(); 

			this.setAverage_tock(this.getSummary_tock() / this.getTick_count()); 

			if (this.getTick_count() > REFRESH_STAT_COUNT) { 
				 
				//this.setTick_count(0); 

				this.setSum_manage_count(0); 

				this.setSummary_tock(0); 

				this.setSum_manage_value(0); 

				this.setSum_manage_count(0); 
			} 

			this.incTick_count(); 

		} // while 

		 

	} // run() 

	public static void main(String[] args) { 
		 

		Grok o = new Grok(25); 
		 
		o.setDebug(true); 

		o.start(); 

	} 

} 
//Revision : 20.01.2017 15:56:37 
//Revision : 28.01.2017 15:14:43 
//Revision : 11.08.2017 17:33:38 
//Revision : 10.09.2018 12:49:15 
