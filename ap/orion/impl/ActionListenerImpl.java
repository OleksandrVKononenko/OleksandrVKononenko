package ap.orion.impl;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import ap.global.gl;
import ap.mercury.components.parser.Parser;
import ap.orion.Orion; 


public class ActionListenerImpl implements ActionListener{ 
	
	/*
	
	private Orion producer;
	
	public Orion get_producer() {
		return producer;
	}


	public void set_producer(Orion producer) {
		this.producer = producer;
	}

    */
	
	private String headers;
	
	
	
	public String getHeaders() {
		return headers;
	}


	public void setHeaders(String headers) {
		this.headers = headers;
	}


	public void update_headers(String headers)
	{
	
		this.setHeaders(
				
				gl.sf("%s%s%s",
						this.getHeaders()==null ? "" : this.getHeaders(),
						this.getHeaders() == null ? "" : Parser.ITEMS_DLM,
						headers 
						)
				);
	}
	
	public ActionListenerImpl() { 
		
		super();
	}
	

	public static ActionListenerImpl get_instance()
	{
		return new ActionListenerImpl();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

} 
