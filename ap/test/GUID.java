package ap.test;

import ap.global.gl;
import ap.utils.Bau;

public class GUID {

	public GUID() {
		
	}

	public static void main(String[] args) {
		
		for(int i=0; i < 10;i++)
		{
		//gl.sfn("Guid...%s...%s",java.util.UUID.randomUUID(),Bau.to_hex_str_from_int(gl.getRandomInt(165535)).toLowerCase());
		String guid = gl.sf("%s%s%s%s",
				java.util.UUID.randomUUID(),
				java.util.UUID.randomUUID(),
				java.util.UUID.randomUUID(),
				java.util.UUID.randomUUID()).toString().replace("-","");
			
			gl.sfn("Guid...%s...%d...%d...%d",guid,guid.length(),guid.length()/8,guid.length()*8);

		}
	}

}
