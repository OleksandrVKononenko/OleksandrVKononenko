 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
 
package ap.force; 

public class ARender implements Runnable { 
		 
	 
		public Odin odin; 
		 
		public Controller controller; 
		 
		public Render render; 
		 
		/** 
		 * @return the controller 
		 */ 
		public Controller getController() { 
			return controller; 
		} 

		/** 
		 * @param controller the controller to set 
		 */ 
		public void setController(Controller controller) { 
			this.controller = controller; 
		} 

		/** 
		 * @return the render 
		 */ 
		public Render getRender() { 
			return render; 
		} 

		/** 
		 * @param render the render to set 
		 */ 
		public void setRender(Render render) { 
			this.render = render; 
		} 

		/** 
		 * @return the odin 
		 */ 
		public Odin getOdin() { 
			return odin; 
		} 

		/** 
		 * @param odin the odin to set 
		 */ 
		public void setOdin(Odin odin) { 
			this.odin = odin; 
		} 

		public ARender(Odin odin,Controller cont,Render rend) { 
			this.setOdin(odin); 
			this.setController(cont); 
			this.setRender(rend); 
		} 

		public void run() { 

			try { 
				 
				while (true) { 

					// Entry point for canvas. 
					 
					controller.step(); 
					 
					render.repaint(); 

					 
					try { 
					    synchronized(odin) 
					    { 
					    	this.wait(odin.getReal_tock()); 
					    } 
					} 
					catch(java.lang.IllegalMonitorStateException e) 
					{ 
						System.out.println("Render flow exception : " + e.toString()); 
						 
					} 

				} 
				 
			} catch (Exception e) { 
				System.out.println("Render flow run section exception : " + e.toString()); 

			} 
		} 

	public static void main(String[] args) { 
		 
		 

	} 

} 
// Revision : 20.01.2017 15:56:37 
// Revision : 28.01.2017 15:14:42 
// Revision : 11.08.2017 17:33:38 
// Revision : 10.09.2018 12:49:15 
