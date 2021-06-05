package ap.aval;

public class RaifParser {
	
	private RaifCommand command = RaifCommand.NONE;
	
	private String source;
		
	
	public RaifCommand getCommand() {
		return command;
	}

	public void setCommand(RaifCommand command) {
		this.command = command;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public RaifParser()
	{
		this.setCommand(RaifCommand.NONE);
	}
	
	public RaifParser(String cmd)
	{
		this();
		
		this.setCommand(this.toEnumCmd(cmd));
	}
	
	public RaifParser(String source,String cmd)
	{
		
		this(cmd);
		
		this.setSource(source);
		
	}
	
	public RaifCommand toEnumCmd(String  key)
	{
		
		try {
			
			if(key == null)
				return RaifCommand.NONE;
			else if (key.equalsIgnoreCase("--p") || key.equalsIgnoreCase("--payload"))
				return RaifCommand.PAYLOAD;
			else if (key.equalsIgnoreCase("--c") || key.equalsIgnoreCase("--controller"))
				return RaifCommand.CONTROLLER;
			else if (key.equalsIgnoreCase("--j") || key.equalsIgnoreCase("--jna"))
				return RaifCommand.JNA;

				return RaifCommand.NONE;
				
		} catch (Exception e) {
			
			e.printStackTrace();

			return RaifCommand.NONE;
		}

	}
	
	public static RaifParser getInstance()
	{
			return new RaifParser();
	}
	
	public static RaifParser getInstance(String source,String cmd)
	{
			return new RaifParser(source,cmd);
	}
	
}
