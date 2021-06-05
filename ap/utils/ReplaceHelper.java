 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 


public class ReplaceHelper { 

	public static String ReplWord(String word) 
	{ 
		String ERROR_MESSAGE = "*** invalid input value or null ***"; 
		 
		int word_length = word.trim().length(); 
		 
		if(word == null ||  word_length== 0) 
		{ 
			return ERROR_MESSAGE; 
		} 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		if(word_length < 4) 
		{ 
			for(int i=0;i<word_length;i++) 
			{ 
				if(i==0) 
					sb.append(word.charAt(i)); 
				else 
					sb.append("*"); 
			} 
			 
		} else if (word_length >= 4) 
		{ 
		 
			for(int i=0;i<word_length;i++) 
			{ 
				if(i==0) 
					sb.append(word.charAt(i)); 
				else if(i==1 || i==2 || i==3) 
					sb.append("*"); 
				else 
					sb.append(word.charAt(i)); 
			} 
		} 
				return sb.toString(); 
	} 
	 
	public static String ReplFio(String fio) 
	{ 
		String ERROR_MESSAGE = "*** invalid input value or null ***"; 
				 
		if(fio == null || fio.trim().length() == 0) 
		{ 
			 
			return ERROR_MESSAGE; 
		} 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		try 
		{ 
			String[]  arr = fio.split(" "); 
			 
			for(int i=0;i<arr.length;i++) 
			{ 
				sb.append(ReplWord(arr[i])); 
				 
				sb.append(" "); 
				 
			} 
			 
		} 
		catch(Exception e) 
		{ 
			return ERROR_MESSAGE; 
		} 
		 
			return sb.toString(); 
	} 
	 
	public ReplaceHelper() { 
		 
	} 
	 
	public static void Test_ReplWord(String value) 
	{ 
		System.out.println(String.format("Input : %s Output : %s",value,ReplWord(value))); 
	} 

	public static void Test_ReplFio(String value) 
	{ 
		System.out.println(String.format("Input : %s Output : %s",value,ReplFio(value))); 
	} 
	 

	public static void main(String[] args) { 
		 
		 
		 
		Test_ReplFio("Ivanov Ivan Ivanovich"); 
		 
				 

	} 

} 
