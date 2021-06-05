package ap.explorer;

import java.util.Arrays;
import java.util.List;

import ap.btn.Bar;
import ap.btn.TMa;
import ap.global.gl;
import ap.utils.Bau;
import ap.utils.Su;

public class Brew {
	
	public static List<String> stocks = Arrays.asList(new String[] {"AVA","FIBO","SWQ","NASDAQ","NYSE","LSE"});


	public static String indexOf(int index)
	{
		return stocks.get(index);
	}

	public static int indexOf(String value)
	{
		return stocks.indexOf(value);
	}

	public static void  main(String [] args)
	{
		// test_decode();
		
		test_ma_bit();
	}
	
	
	
	public static long decode(String value)
	{
		return Bau.to_long_from_binary_str(Su.exclude(value,"[","]"));
	}
	
	
	public static void test()
	{
		
		int [] count = {gl.E_EMPTY};
		
		stocks.forEach(a->{
			
			gl.sfn("Test of...%10s...%1d",Brew.indexOf(count[0]),Brew.indexOf(Brew.indexOf(count[0])));
			
			count[0]++;
			
		});
		
	}
	
	public static void test_ma_bit()
	{
		//31 - ((i*4)+o)
		//31 - ((((i+1)*4)-o)-1)
		
		for(int i=TMa.M3; i <= TMa.M200;i++) 
		{ 
			for(int o=Bar.O;o <= Bar.C;o++ ) 
			{ 
				int		m_bit_index_new = 31 - ((i*4)+o);//31 - ((((i+1)*4)-o)-1);
				
				int		m_bit_index_old = 31 - ((((i+1)*4)-o)-1);
				
				gl.sfn("New...%1d....Old...%1d",m_bit_index_new,m_bit_index_old);
				
				
			}
		}
		
		
		
		
		
	}
	
	
	public static void test_decode()
	{
		String m_some 		= Su.exclude("[m3] 1000 [m7] 0000 [m10] 0000 [m14] 0000 [m21] 0000 [m55] 0000 [m100] 0000 [m200] 0000","[","]");
		
		String m_any		= Su.exclude("[m3] 0000 [m7] 0000 [m10] 1000 [m14] 0000 [m21] 0000 [m55] 0000 [m100] 0000 [m200] 0000","[","]");
		
		String m_power 		= Su.exclude("[m3] 1000 [m7] 1000 [m10] 1000 [m14] 1000 [m21] 1000 [m55] 1000 [m100] 1000 [m200] 1000","[","]");
	
		String m_to = m_some;
		
		gl.sfn("Decode of...%s...is...%d",m_to,decode(m_to));
	}
}
