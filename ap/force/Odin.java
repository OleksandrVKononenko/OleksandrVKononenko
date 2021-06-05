 
package ap.force; 

import java.io.FileWriter; 
import java.io.IOException; 


public class Odin extends Thread { 

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

	private long manage_count = 0L; 

	private long avg_manage_value = 1L; 

	private long sum_manage_value = 0L; 

	private long sum_manage_count = 0L; 

	private long fps = 0L; 

	final int REFRESH_STAT_COUNT = 1000; 

	public Odin(int fps) { 
		this.setPlanned_fps(fps); 

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
		return sum_manage_count; 
	} 

	public void setSum_manage_count(long sum_manage_count) { 
		this.sum_manage_count = sum_manage_count; 
	} 

	public long getAvg_manage_value() { 
		return avg_manage_value; 
	} 

	public void setAvg_manage_value(long avg_manage_value) { 
		this.avg_manage_value = avg_manage_value; 
	} 

	public long getSum_manage_value() { 
		return sum_manage_value; 
	} 

	public void setSum_manage_value(long sum_manage_value) { 
		this.sum_manage_value = sum_manage_value; 
	} 

	public long getManage_count() { 
		return manage_count; 
	} 

	public void setManage_count(long manage_count) { 
		this.manage_count = manage_count; 
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

		sm("Odin started."); 

		String message = ""; 

		while (true) { 

			long st = System.nanoTime(); 

			try { 

				sleep(this.getWork_tock()); 

			} catch (InterruptedException e) { 

				this.trace(String.format( 
						"Method write.run(InterruptedException) : %s", 
						e.getMessage())); 

			} 

			this.setReal_tock((System.nanoTime() - st) / 1000000); 

			this.setManage_tock(this.getPlanned_tock() - this.getReal_tock()); 

			if (this.isDebug()) { 
				message = this.getTick_count() 
						+ "." 
						+ " Est : " 
						+ this.getPlanned_tock() 
						+ " Work :  " 
						+ this.getWork_tock() 
						+ " Real : " 
						+ this.getReal_tock() 
						+ " Mng :" 
						+ this.getManage_tock() 
						+ " Avg mgmt : " 
						+ this.getAvg_manage_value() 
						+ String.format(" Fps : %s/%s", this.getPlanned_fps(), 
								this.getFps()); 

				this.trace(message); 
			} 
			 else 
			 { 
			 message = 
			 String.format("Fps(plan/get) : %s/%s\n",this.getPlanned_fps(),this.getFps()); 

			// sm(message); 

			 } 

			if (this.getManage_tock() != 0) { 

				this.sum_manage_count += 1; 

				this.sum_manage_value += Math.abs(this.getManage_tock()); 

				this.avg_manage_value = this.sum_manage_value 
						/ this.sum_manage_count; 

				long setup_work = (this.getWork_tock() + this.getManage_tock()); 

				String msg = ""; 

				if (setup_work > 0 && manage_count > 5) { 

					this.setWork_tock(setup_work); 

					msg = "^(" + setup_work + ")" + "(" 
							+ this.getManage_count() + ")"; 

					trace(msg); 

					this.setManage_count(0); 
				} 

				this.trace(msg); 

				manage_count++; 

			} // this.getManage_tock() != 0 

			this.trace(System.lineSeparator()); 

			this.summary_tock += this.getReal_tock(); 

			this.setAverage_tock(this.getSummary_tock() / this.getTick_count()); 

			if (this.getTick_count() > REFRESH_STAT_COUNT) { 
				this.setTick_count(0); 

				this.setSum_manage_count(0); 

				this.setSummary_tock(0); 

				this.setSum_manage_value(0); 

				this.setSum_manage_count(0); 
			} 

			this.incTick_count(); 

		} // while 

		// prev_mng = this.getManage_tock(); 

	} // run() 

	public static void main(String[] args) { 
		 

		Odin o = new Odin(10); 
		 
		o.setDebug(true); 

		o.start(); 

	} 

} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:43 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
