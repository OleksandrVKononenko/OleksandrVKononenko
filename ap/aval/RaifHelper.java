package ap.aval;

public class RaifHelper {
	
	public static void sm(Object object)
	{
		sw(String.format("%s%s",object,System.lineSeparator()));
	}
	
	
	public static void sw(Object object)
	{
		System.out.print(object);
	}

	
	public static void main(String[] args) {
		
		sm("Hello RAIF !!!");
		
		//smn(map_to_str(get_cmd_map(args)));
		
		//sm(RaifMap.getInstance(args).get().toString());
		
		//sm(RaifMap.getInstance(args).get().getMirror().toString());
	
		// Get command line.
		
		RaifMap cmds = RaifMap.getInstance(args).get();
		
		sm(String.format("Size...%03d...[%s]",cmds.size(),cmds.toString(",")));
		
		if(cmds.isaCommand() == RaifCommand.PAYLOAD)
		{
			sm("Payload case...");
		}
		
		
		
		
	}

}
