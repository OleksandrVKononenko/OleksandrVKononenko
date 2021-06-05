 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.btn; 

import ap.global.gl; 
import ap.utils.Su; 

public class TExpression { 


	private String source; 
	 
	private String lvalue; 
	 
	private String rvalue; 
	 
	private String op; 
	 
	 
	 
	public String getSource() { 
		return source; 
	} 

	public void setSource(String source) { 
		this.source = source; 
	} 

	public String getLvalue() { 
		return lvalue; 
	} 

	public void setLvalue(String lvalue) { 
		this.lvalue = lvalue; 
	} 

	public String getRvalue() { 
		return rvalue; 
	} 

	public void setRvalue(String rvalue) { 
		this.rvalue = rvalue; 
	} 

	public String getOp() { 
		return op; 
	} 

	public void setOp(String op) { 
		this.op = op; 
	} 

	@Override 
	public String toString() 
	{ 
		return String.format("%s %s %s",this.getLvalue(),this.getOp(),this.getRvalue()); 
	} 
	 
	public boolean parseCall() 
	{ 
	 
		gl.tracex(new Object(){},String.format("%s...%s","Try to parse calling",this.getSource().trim().toUpperCase().replace("\r\n",""))); 
		 
		return false; 
		 
	} 
	 
	public boolean parse() 
	{ 

		String msg = ""; 
		 
		String acceptor = "="; 
		 
		String open_parenthesis = "("; 
		 
		String close_parenthesis = ")"; 
				 
		if(this.getSource().trim().length() == gl.E_EMPTY ) 
		{ 
			return false; 
		} 
		 
		String name  = ""; 
		 
		String value = ""; 
		 
		String op    = ""; 
		 
		String ops   = ""; 
		 
		boolean func = ( 
							this.getSource().indexOf(open_parenthesis) != gl.E_ERROR  && 
							( 
									this.getSource().indexOf("++") == gl.E_ERROR  && 
									this.getSource().indexOf("--") == gl.E_ERROR 
							) 
							 
						); 
		 
		boolean unary_operation_inc = ( 
				! func  && 
				( 
						this.getSource().indexOf("++") != gl.E_ERROR 
				) 
				 
			); 
		 
		boolean unary_operation_dec = ( 
				! func  && !unary_operation_inc&& 
				( 
						this.getSource().indexOf("--") != gl.E_ERROR 
				) 
				 
			); 



		 
			// Name section. 
		 
			if(func) 
			{ 
				name  = Su.BeforeAt(this.getSource(),open_parenthesis); 
			} 
			else if (unary_operation_inc) 
				{ 
					// Try to get post operation. 

					name = Su.BeforeAt(this.getSource(),"++"); 
					 
					ops = "post_inc"; 
					 
					if(name.trim().length() == gl.E_EMPTY) 
					{ 
						name = Su.AfterAt(this.getSource(),"++"); 
						 
						ops = "pre_inc"; 
					} 
					 
					gl.smn("--->  Name for unary inc " + name + " Type " + ops + "\nSource " + this.getSource()); 
					 
				} 
			else if (unary_operation_dec) 
				{ 
				name = Su.BeforeAt(this.getSource(),"--"); 
				 
				ops = "post_dec"; 
				 
				if(name.trim().length() == gl.E_EMPTY) 
				{ 
					name = Su.AfterAt(this.getSource(),"--"); 
					 
					ops = "pre_dec"; 
				} 
				 
				gl.smn("--->  Name for unary dec " + name + " Type " + ops + "\nSource " + this.getSource()); 
				 
				} 
				else 
					name  = Su.BeforeAt(this.getSource(),acceptor); 
		 
			//Value section. 
			 
			if(func) 
			{ 
				value = Su.Between(source,open_parenthesis,close_parenthesis); 
			} 
			else 
				value = Su.AfterAt(this.getSource(),acceptor); 
		 
			name = name.trim().replace("+","").replace("-","").replace("=",""); 

			// Operation section. 
			 
			if(!func && !unary_operation_dec && !unary_operation_inc ) 
			op = Su.Between(source,name,value); 
			else if(!func && (unary_operation_dec ||unary_operation_inc)) 
				op = String.format("%s",ops); 
			else 
				op = String.format("%s%s",open_parenthesis,close_parenthesis); 
				 
					 
			this.setLvalue(name); 
			 
			value = value.trim().replace("+","").replace("-","").replace("=",""); 
		 
			this.setRvalue(value); 
		 
			this.setOp(op); 
		 
			 
		return ( 
					this.getLvalue().trim().length() != gl.E_EMPTY 
			    ); 
				 
	} 
	 
	public TExpression() { 
		 
	} 

	public TExpression(String source) { 
		 
		this.setSource(source); 
	} 

	public static void main(String[] args) { 
		 

	} 

} 
// Revision : 10.09.2018 12:49:13 
