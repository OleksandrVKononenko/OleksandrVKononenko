 
package ap.utils; 

import ap.explorer.Group;
import ap.global.gl; 

public class Biu { 

	public static int OFF(int target,int mask) 
{ 
		int result = target &= ~(1<<mask); 
				 
	return result; 
} 
	public static String show(byte[] bytes) 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		for(int i=0;i<bytes.length;i++) 
		{ 
			sb.append(bytes[i]); 
		} 
			return sb.toString(); 
			 
	} 
	 
public static int ON(int target,int mask) 
{ 
	return (target | (1<<mask)); 
} 
 
public static long OFF(long target,int mask) 
{ 
		long result = target &= ~(1<<mask); 
				 
	return result; 
} 
 
public static long ON(long target,int mask) 
{ 
	return (target | (1<<mask)); 
} 
 
public static int TEST(int target,int mask) 
{ 
	return ((target&(1<<mask) )>> mask); 
} 
 

public static boolean TESTB(int target,int mask) 
{ 
	int value = target;
			
	return (((value&(1<<mask) )>> mask) == gl.E_OK); 
} 


public static boolean ISA(int target,int mask) 
{ 
	return TESTB(target,mask); 
} 

	public Biu() { 
		 
	} 
	
	public static void test_long()
	{
		long value = 0x0000000000000000b;
		
		long mask = 0L;
		
		for(int i =0;i<64;i++)
		{
			mask = (1<<i);
			
			value |= mask;
					
			gl.sfn("Index...%2d...Mask...%32d...Result...%32d...%64s",i,(long)mask,value,Long.toBinaryString(value));
			
		}
		
	}
	
	public static void test_long_str()
	{
		String value = gl.replicate('0',63);
		
		for(int i =0;i<value.length();i++)
		{
			String m = Su.replace(value,i,'1');//value.replace(value.charAt(i),'1');
			
			gl.sfn("Index...%2d...%s...%32d...%s",i,m,Bau.to_long_from_binary_str(m),Bau.to_bin_str_from_long_enrich(Bau.to_long_from_binary_str(m)));
			
		}
		
	}
	
	
	
	public static void test_mask()
	{
		int value = Group.SHORT_MASK; 
		 
		int MASK = Group.TICKER; 
		
		
		int test_value = Group.SHORT_MASK; 
				 
		//value = ON(value,MASK); 
		  
		
		gl.sfn("ON...Input...%d...Mask...%d...Test...%d...Short...%s...Full...%s...Test value...%s...Check...%s...%s...%s...%s",
				value,1<<MASK,TEST(value,MASK),
				
				Bau.to_bin_str_from_int_enrich(value),
				Bau.to_bin_str_from_int_enrich(Group.FULL_MASK),
				Bau.to_bin_str_from_int_enrich(test_value),
				
				Biu.TESTB(test_value,Group.LOSE_DOWN_AVG),
				Biu.TESTB(test_value,Group.DATE),
				
				Biu.TESTB(test_value,Group.TICKER),
				Biu.TESTB(test_value,Group.RESULT_DEALS)
				
				); 
		
		//gl.smn(msg); 
		
		
		//value = OFF(value,MASK); 
		 
		//msg = String.format("OFF...Input...%d...Mask...%d...Test...%d",value,1<<MASK,TEST(value,MASK)); 
				 
		//gl.smn(msg); 
		 
	
	
	}

	public static void test()
	{
		int value = 0; 
		 
		int MASK = 0; 
				 
		value = ON(value,MASK); 
		 
		String msg = String.format("ONO...Input...%d...Mask...%d...Test...%d",value,1<<MASK,TEST(value,MASK)); 
		 
		gl.smn(msg); 
		 
		value = OFF(value,MASK); 
		 
		msg = String.format("OFF...Input...%d...Mask...%d...Test...%d",value,1<<MASK,TEST(value,MASK)); 
				 
		gl.smn(msg); 
		 
	
	
	}
	
	public static void main(String[] args) { 

		// test_mask();
		
		test_long_str();
	
	} 

} 
