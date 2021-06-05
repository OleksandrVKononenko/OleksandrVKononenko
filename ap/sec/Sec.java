package ap.sec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import ap.btn.Bar;
import ap.global.gl;
import ap.utils.DU;
import ap.utils.Fu;
import ap.utils.Su;

public class Sec {

	
	
	public Sec() {
		
	}
	
	public static void scan_sec_file(String pathname) 
    { 

		File file = new File(pathname); 

		Scanner scanner = null;
		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
				 
		 int 		 TAG =  0,
	             VERSION =  1,
	              CUSTOM =  2,
	            ABSTRACT =  3, // Если 1 , то статья не предназначена для представления численного факта.
	            DATATYPE =  4, // If abstract=1, then NULL, otherwise the data type (e.g., monetary) for the tag.
	                IORD =  5, // I - value is point-in time , D - duration. 
	                CRDR =  6, // If datatype = monetary, then the tag’s natural accounting balance (debit or credit); if not defined, then NULL.
	              TLABEL =  7,
	                 DOC =  8;
		
		
		try { 
			
			int i 			 = 1;
			
			int i_max_tag 	 = 0;
			
			int i_max_tlabel = 0;
			
			int i_max_doc 	 = 0;
			
			StringBuilder sb = new StringBuilder();
			

			// Create file.
			
			Fu.to_file("sub_i.txt",sb.toString());
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
				
				// Для ТАГов.

				// gl.sfn("[%2d]", arr.length);
				
				// 14333 for I.
			
				if( arr[ABSTRACT].trim().equalsIgnoreCase("0") && arr[IORD] != null && ( 
						arr[IORD].equalsIgnoreCase("I") //||
						//arr[IORD].equalsIgnoreCase("D") 
						)
						)
				{
						
					// Выводим TAG,TLABEL,DOC.
					
					//gl.sfn("%5d. %s %s %s",i,arr[TAG],arr[TLABEL],arr[DOC]);
					
					// Debug.
					
					if(arr.length == 8 )
					{
						sb.append(
						gl.sf("%5d. %s ",i,arr[TAG])
						);
					}
					else if(arr.length == 9)
					{
						sb.append(
						
								/*
								gl.sf("%5d. %s%s %s%s %s",
								i,
								arr[TAG],
								gl.replicate(' ',150 - arr[TAG].length()),
								arr[TLABEL],
								gl.replicate(' ',404 - arr[TLABEL].length()),
								arr[DOC])
								*/
								
								gl.sf("%5d. %s\t%s\t%s",
										i,
										arr[TAG],
										arr[TLABEL],
										arr[DOC])
										
						);
						
						// Get max values.
						
						if (arr[TAG].length() > i_max_tag)
						{
							i_max_tag = arr[TAG].length();
						}
						
						if (arr[TLABEL].length() > i_max_tlabel)
						{
							i_max_tlabel = arr[TLABEL].length();
						}
						
						if (arr[DOC].length() > i_max_doc)
						{
							i_max_doc = arr[DOC].length();
						}
						
						
					} else
					{
						//gl.sfn("-> %5d. %s ",i,arr[TAG]);
						
					}
						
					i++;
					
					sb.append(System.lineSeparator());
					
					Fu.to_file("sub_i.txt",sb.toString(),true);
					
					sb.setLength(gl.E_EMPTY);
					
				}
				
			} // while.
			
					
					gl.sfn("Max field length...Tag...[%3d]...TLabel...[%3d]...Doc...[%3d]", 
							i_max_tag,
							i_max_tlabel,
							i_max_doc
							);
			
			 
		} finally { 
			scanner.close(); 
		} 
	} 
	
	public static void scan_sec_file_v(String pathname) 
    { 

		File file = new File(pathname); 

		Scanner scanner = null;
		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
				 
		 int 		 TAG =  0,
	             VERSION =  1,
	              CUSTOM =  2,
	            ABSTRACT =  3, // Если 1 , то статья не предназначена для представления численного факта.
	            DATATYPE =  4, // If abstract=1, then NULL, otherwise the data type (e.g., monetary) for the tag.
	                IORD =  5, // I - value is point-in time , D - duration. 
	                CRDR =  6, // If datatype = monetary, then the tag’s natural accounting balance (debit or credit); if not defined, then NULL.
	              TLABEL =  7,
	                 DOC =  8;
		
		
		try { 
			
			int i 			 = 1;

			StringBuilder sb = new StringBuilder();
			

			// Create file.
			
			Fu.to_file("sub_i.txt",sb.toString());
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
				
				// Для ТАГов.

				// gl.sfn("[%2d]", arr.length);
				
				// 14333 for I.
			
				if( arr[ABSTRACT].trim().equalsIgnoreCase("0") && arr[IORD] != null && ( 
						arr[IORD].equalsIgnoreCase("I") //||
						//arr[IORD].equalsIgnoreCase("D") 
						)
						)
				{
						
					// Выводим TAG,TLABEL,DOC.
					
					//gl.sfn("%5d. %s %s %s",i,arr[TAG],arr[TLABEL],arr[DOC]);
					
					// Debug.
					
					if(arr.length == 8 )
					{
						sb.append(
						gl.sf("%5d. %s ",i,arr[TAG])
						);
					}
					else if(arr.length == 9)
					{
						sb.append(
						
								/*
								gl.sf("%5d. %s%s %s%s %s",
								i,
								arr[TAG],
								gl.replicate(' ',150 - arr[TAG].length()),
								arr[TLABEL],
								gl.replicate(' ',404 - arr[TLABEL].length()),
								arr[DOC])
								*/
								
								gl.sf("%5d. %s\t%s\t%s",
										i,
										arr[TAG],
										arr[TLABEL],
										arr[DOC])
										
						);
						

			
					} else
					{
						//gl.sfn("-> %5d. %s ",i,arr[TAG]);
						
					}
						
					i++;
					
					sb.append(System.lineSeparator());
					
					Fu.to_file("sub_i.txt",sb.toString(),true);
					
					sb.setLength(gl.E_EMPTY);
					
				}
				
			} // while.
			


			 
		} finally { 
			scanner.close(); 
		} 
	} 
	
	public static void scan_sec_pre(String pathname) 
    { 

		File file = new File(pathname); 

		Scanner scanner = null;
		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		
		
		try { 
			
			int i 			 = 1;
			
			Map<String,String> map = new LinkedHashMap<String,String>();
	
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
				
				map.put(arr[0],arr[3]);
								
				}
							
			 
			gl.sfn("Size of map...[%d]",map.size());
				
			
			} // while.
			 
    finally { 
			scanner.close(); 
		} 
		
	} 
	
	public static void scan_sec_filter(String pathname,String adsh,String stmt,boolean ignore) 
    { 

		File file = new File(pathname); 

		Scanner scanner = null;
		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		
		
		try { 
			
			int i = 0;
			
			List<Pre>  pre = new ArrayList<Pre>();
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
			
				// Фильтруем по TAG.
				
				// Выводим  1,2,3 report	line	stmt 
				
				 if(adsh.equalsIgnoreCase(arr[0]) && (( ignore == false)? arr[3].equalsIgnoreCase(stmt) : true))
				 {
						
					 /*
					 Pre p = Pre.get_instance(
							 arr[0],
							 DU.to_int(arr[1]),
							 DU.to_int(arr[2]),
							 arr[3],
							 DU.to_int(arr[4]),
							 arr[5],
							 arr[6],
							 arr[7],
							 arr[8],
							 DU.to_int(arr[9])
							 );
					 
					 pre.add(p);
					 
					 */
					 
					 i++;
				 }
				 
				}  // while.
			
				pre.forEach(a->{
				
					//gl.sfn("%s",a.toString());
					
				});
				
				// Сортировка.
				
				List<Pre> pre_sorted = pre.stream()
			            .sorted(Comparator.comparingInt(Pre::getLine))
			            //.reversed())
			            .collect(Collectors.toList());
			 
			        pre_sorted.forEach(System.out::println);
				
				
				/*
				// Группировка.
				// По количеству строк в стейтменте.
				
				Map<String, Long> map = pre.stream().collect(
		                Collectors.groupingBy(Pre::getStmt, Collectors.counting()));
				
				map.forEach((k,v)->{
					
					gl.sfn("%2s %3d",k,v);
					
				});
				
				*/
				
				
				
				gl.sfn("Записей...[%d]",i);
			}
			 
    finally { 
			scanner.close(); 
		} 
		
	} 
	
	public static Map<String,Integer> get_dic_for_tag_(String source) 
    { 

		File 					file 	= new File(source); 

		Scanner 				scanner = null;
		
		Map<String,Integer> 	map 	= null;
		
		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		
		
		try { 
			
			int i 			 = 1;
			
			int r            = 0;   
			
			map = new LinkedHashMap<String,Integer>();
	
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
				
				// Adsh Accession Number must be unique primary key.
				
				if(map.containsKey(arr[0]))
				{
					//gl.sfn("Повтор...[%s]...[%d]",arr[0],map.get(arr[0]));
					
					int re = map.get(arr[0]);
						
					map.remove(arr[0]);
					
					int re_up = re+1; 
							
					map.put(arr[0],re_up);
					
					//gl.sfn("*Повтор...[%s]...[%d]",arr[0],map.get(arr[0]));
				
					
					r++;
				}else
				{
				
				    map.put(arr[0],0);
				
				}
				
				} // while.
							
			 
				if(r != gl.E_EMPTY)
				gl.sfn("Записей...[%d]...Дубликатов...[%d]",map.size(),r);
				
				return map;
			
			} // try.
			 
    finally { 
			scanner.close(); 
		} 
		
	} 
	
	public static List<Object> get_dic_for_sub(String source) 
    { 
		
		File 					file 	= new File(source); 

		Scanner 				scanner = null;
		
		
		
		Map<String,Integer> 	map_adsh_cik 	= null;
		
		Map<Integer,String> 	map_cik_name 	= null;
		
		
		Map<Integer,Integer> 	map_cik_pk 		= null;
		
		Map<String,Integer> 	map_adsh_pk 	= null;
		
		
		Map<String,Integer> 	map_form_pk 	= null;
		
		Map<String,Integer> 	map_period_pk 	= null;
		
		Map<Integer,Integer> 	map_fy_pk 		= null;
		
		Map<String,Integer> 	map_fp_pk 		= null;
		

		Map<String,String> 		map_sub_adsh_form 		= null;

		Map<String,String> 		map_sub_adsh_period 	= null;

		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		
		
		try { 
			
			int pk_cik = 0, pk_adsh = 0,pk_form=0,pk_period=0,pk_fy=0,pk_fp=0;
			
			int a            = 0,b=0;   
			
			map_adsh_cik 	= new LinkedHashMap<String,Integer>();
	
			map_cik_name  	= new LinkedHashMap<Integer,String>();
			
			
			map_cik_pk  	= new LinkedHashMap<Integer,Integer>();

			map_adsh_pk  	= new LinkedHashMap<String,Integer>();
		
			
			map_form_pk  	= new LinkedHashMap<String,Integer>();
			
			map_period_pk  	= new LinkedHashMap<String,Integer>();
			
			map_fy_pk  		= new LinkedHashMap<Integer,Integer>();

			map_fp_pk  		= new LinkedHashMap<String,Integer>();
		
			
			map_sub_adsh_form 		= new LinkedHashMap<String,String>();

			map_sub_adsh_period 	= new LinkedHashMap<String,String>();

			
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
			
				
				 map_adsh_cik.put(arr[0],DU.to_int(arr[1]));
				
				 map_cik_name.put(DU.to_int(arr[1]),arr[2]);
				 
				 
				 map_sub_adsh_form.put(arr[0],arr[25]);
					
				 map_sub_adsh_period.put(arr[0],arr[26]);
				

				 if(!map_adsh_pk.containsKey(arr[0]))
				 {

					 map_adsh_pk.put(arr[0],pk_adsh);
					
					 pk_adsh ++;
				 }
				 
				 if(!map_cik_pk.containsKey(DU.to_int(arr[1])))
				 {
					 
					 map_cik_pk.put(DU.to_int(arr[1]),pk_cik);
						 
					 pk_cik++;
				 }
				 
				 if(!map_form_pk.containsKey(arr[25]))
				 {
					 
					 map_form_pk.put(arr[25],pk_form);
						 
					 pk_form++;
				 }
				 
				 if(!map_period_pk.containsKey(arr[26]))
				 {
					 
					 map_period_pk.put(arr[26],pk_period);
						 
					 pk_period++;
				 }
				

				 if(!map_fy_pk.containsKey(DU.to_int(arr[27])))
				 {
					 
					 map_fy_pk.put(DU.to_int(arr[27]),pk_fy);
						 
					 pk_fy++;
				 }
				

				 if(!map_fp_pk.containsKey(arr[28]))
				 {
					 
					 map_fp_pk.put(arr[28],pk_fp);
						 
					 pk_fp++;
				 }
				
								
				} // while.
							
						
				List<Object> 	l = new ArrayList<Object>();
				
								l.add(map_cik_name);
				
								l.add(map_adsh_cik);

								l.add(map_cik_pk);
				
								l.add(map_adsh_pk);
				

								l.add(map_form_pk);
				
								l.add(map_period_pk);

								l.add(map_fy_pk);
				
								l.add(map_fp_pk);
								
								l.add(map_sub_adsh_form);
									
								l.add(map_sub_adsh_period);
								
				
				
						return 	l;
				
			
			} // try.
			 
    finally { 
			scanner.close(); 
		} 
		
	} 

	public static List<Object> get_dic_for_tag(String source) 
    { 
		
		File 					file 	= new File(source); 

		Scanner 				scanner = null;
		
		
		
		Map<String,Integer> 	map_tag_pk 			= null;
		
		Map<String,Integer> 	map_version_pk 		= null;
		
		Map<String,Integer> 	map_datatype_pk 	= null;
		
		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		
		
		try { 
			
			int pk_tag = 0, pk_version = 0,pk_datatype=0;
			
			
			map_tag_pk 			= new LinkedHashMap<String,Integer>();
	
			map_version_pk  	= new LinkedHashMap<String,Integer>();
	
			map_datatype_pk  	= new LinkedHashMap<String,Integer>();
	
						
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
				
				
				 if(!map_tag_pk.containsKey(arr[0]))
				 {

					 map_tag_pk.put(arr[0],pk_tag);
					
					 pk_tag ++;
				 }

				 if(!map_version_pk.containsKey(arr[1]))
				 {

					 map_version_pk.put(arr[1],pk_version);
					
					 pk_version ++;
				 }
				 
				 //gl.sfn("Fields..[%d]",arr.length);
				 
				 if(arr.length != 4 && !map_datatype_pk.containsKey(arr[4]))
				 {

					 map_datatype_pk.put(arr[4],pk_datatype);
					
					 pk_datatype ++;
				 }


								
				} // while.
							
			
				List<Object> 	l = new ArrayList<Object>();
				
								l.add(map_tag_pk);
				
								l.add(map_version_pk);

								l.add(map_datatype_pk);
				
						return 	l;
				
			
			} // try.
			 
    finally { 
			scanner.close(); 
		} 
		
	} 

	
	public static void test_sec_data()
	{
				// Открываем входные файлы для теста.
		
				List<String> m_files = Fu.files_open_dlg(".");
				
				m_files.forEach(file->{
					
					gl.tx(new Object() {},Fu.isaFile(file),gl.sf("Файл для чтения...[%s]",file));
				
					//Fu.scan_sec_file(file);
					
					Sec.scan_sec_pre(file);
					
					/*
					List<String> data = Fu.getListOfStringFromFile(file);//Fu.read_utf_8(file);
					
					int []  i = {1};
					
					StringBuilder sb = new StringBuilder();
					
					data.forEach(d->{
					
						
						Arrays.asList(d.split("\t")).forEach(f->{
						
						sb.append(gl.sf("%s ",f));
						
						
						});
					

						//sb.append(System.lineSeparator());
						
						gl.sfn("%5d %s",i[0],sb.toString());
						
						sb.setLength(gl.E_EMPTY);
						
						i[0]++;
						
					}); //data
					
					*/
				});
	}


	public static void quality_suite() {
	

		String tag_source 	= "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\tag.txt";

		Map<String,Integer> m = null;//get_dic_for_tag(tag_source);
		
		Fu.to_file("tag_quality_dub.txt","");
		
		Fu.to_file("tag_quality_pk.txt","");
		
		m.forEach((k,v)->{
			
			String s = gl.sf("%-100s %3d\n",k,v);
			
			if(v > 0)
			{
				Fu.to_file("tag_quality_dub.txt",s,true);
			} else
			{
				Fu.to_file("tag_quality_pk.txt",s,true);
			}
			
		});
	
	}
	
	public static void filter_suite() {
	
		String pre_source 	= "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\pre.txt";

		String adsh 		= "0001493152-20-022072";//"0000355019-20-000051";//0001721868-20-000530";//"0001721868-20-000670";
	
		// 10-Q ?
		
		//BS = Balance Sheet, 
		//IS = Income Statement, 
		//CF = Cash Flow, 
		//EQ = Equity, 
		
		//BS  45 
		//CF  41
		//IS  24
		//EQ  29
		//CP  13
		
		//BS  81
		//CF  38
		//IS  41
		//EQ  21
		//CP  28
		
		//BS  33
		//CF  20
		//IS  19
		//EQ  13
		//CP  16
		
		
		
		
		// 8-K ?
		
		//CI = Comprehensive Income, 
		//UN = Unclassifiable Statement).
		
		scan_sec_filter(pre_source,adsh,"BS",false);
	
	}
	
	public static Map<String,Integer> get_tag_pk() {
	

		String tag_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\tag.txt";
		
		List<Object> m = get_dic_for_tag(tag_source);
		
		return (Map<String,Integer>)m.get(0);
		
	}
	
	// TODO 04.02.2021 get_tag_pk_enrich()
	

	public static void enrich_suite() {
	
		Map<String,Integer> a = get_tag_pk_enrich();
		
		Map<String,Integer> b = get_tag_pk_no();
		
		Map<String,Integer> c = get_tags_pk();
		
		gl.tx(new Object(){}, a.size() != 0 && b.size() != 0 ,
				gl.sf("Массивы...[%6d]...[%6d]...Результат...[%6d]",
				a.size(),b.size(),c.size()));
		/*
		b.forEach((k, v) -> a.putIfAbsent(k, v));
		
		
		gl.tx(new Object(){}, a.size() != 0 && b.size() != 0 ,
				gl.sf("Результат...[%6d]",
						a.size()));
		
		Map<String, Integer> sorted = new LinkedHashMap<>();
        a.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
		
        Fu.to_file("tags_pk.txt","");

		// Set pk.
		 
        int [] i = {1};
        
        sorted.forEach((k,v)->{
        	
			 Fu.to_file("tags_pk.txt",gl.sf("%s\t%6d%s",k,i[0],System.lineSeparator()),true);
			 
			 i[0]++;
        });
		
        */
		
	}
	
	

	public static Map<String,Integer> get_tag_pk_enrich() {
		

		String tag_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\tag.txt";
		
		return (Map<String,Integer>) get_dic_for_tag(tag_source).get(0);

	}
	
	public static Map<String,Integer> get_tag_pk_no()
	{
		
		String tag_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\num_tag_pk_no.txt";
		
		File 					file 	= new File(tag_source); 

		Scanner 				scanner = null;
	
		Map<String,Integer> 	map 	= null;
	
	
		try {
		
			scanner = new Scanner(file);
		
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} 
	
	
	try { 
		
		int r            = 0;   
		
		map = new LinkedHashMap<String,Integer>();

		while (scanner.hasNextLine()) 
		{ 
	
			String 		sc 	= scanner.nextLine();
			
			String [] 	arr = sc.split("\t");
			
			if(!map.containsKey(arr[0]))
			{	
				map.put(arr[0],r);
			
				r++;
			}
			
		} // while.
			
			return map;
		
		} // try.
		 
		finally { 
				scanner.close(); 
				} 
		
		} 

	public static Map<String,Integer> get_tags_pk()
	{

		String tag_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\tags_pk.txt";
		
		return get_any_pk(tag_source);
	}
	
	public static Map<String,Integer> get_adshs_pk()
	{

		String adsh_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\adshs_pk.txt";
		
		return get_any_pk(adsh_source);
	}
	
	public static Map<String,Integer> get_versions_pk()
	{

		String version_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\versions_pk.txt";
		
		return get_any_pk(version_source);
	}

	public static Map<String,Integer> get_plabels_pk()
	{

		String lbl_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\plabels_pk.txt";
		
		return get_any_pk(lbl_source);
	}
	
	public static Map<String,Integer> get_periods_pk()
	{
		String period_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\periods_pk.txt";
		
		return get_any_pk(period_source);
	}
	public static Map<String,Integer> get_uoms_pk()
	{
		String uom_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\uoms_pk.txt";
		
		return get_any_pk(uom_source);
	}

	public static Map<String,Integer> get_coregs_pk()
	{
		String coregs_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\coregs_pk.txt";
		
		return get_any_pk(coregs_source);
	}
	
	
	public static Map<String,Integer> get_any_pk(String source)
	{
		
		File 					file 	= new File(source); 

		Scanner 				scanner = null;
	
		Map<String,Integer> 	map 	= null;
	
	
		try {
		
			scanner = new Scanner(file);
		
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} 
	
	
	try { 
		
		map = new LinkedHashMap<String,Integer>();
		
		long start = System.currentTimeMillis();
		
		while (scanner.hasNextLine()) 
		{ 
	
			String 		sc 	= scanner.nextLine();
			
			String [] 	arr = sc.split("\t");
			
			if(!map.containsKey(arr[0]))
			{	
			
				//gl.sfn("Debug...[%s]...[%s]...[%6d]",arr[0],arr[1],DU.to_int(arr[1].trim()));
				
				int value = DU.to_int(arr[1].trim());
				
				map.put(arr[0],value);
				
			}
		
			
		} // while.
			
			gl.tx(new Object(){},(map.size() != gl.E_EMPTY), 
					gl.sf("Загружен файл...[%s]...[%d] ms.",
						   Su.AfterAt(source,"\\"),
						   (System.currentTimeMillis() - start)
							
						)
					);
			return map;
		
		} // try.
		 
		finally 
		{ 
			scanner.close(); 
		} 
		
} 
	
	
	public static List<Integer> get_any_list_int(String source)
	{
		
		File 					file 	= new File(source); 

		Scanner 				scanner = null;
	
		List<Integer> 			map 	= null;
	
	
		try {
		
			scanner = new Scanner(file);
		
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} 
	
	
	try { 
		
		map = new ArrayList<Integer>();
		
		long start = System.currentTimeMillis();
		
		while (scanner.hasNextLine()) 
		{ 
	
			String 		sc 	= scanner.nextLine();
			
			String [] 	arr = sc.split("\t");
			
			map.add(DU.to_int(arr[0]));
			
		} // while.
			
			gl.tx(new Object(){},(map.size() != gl.E_EMPTY),
					
					gl.sf("Загружен файл...[%s]...размер...[%d]...[%d] ms.",
						   Su.AfterAt(source,"\\"),
						   map.size(),
						   (System.currentTimeMillis() - start)
							
						)
					);
			
			return map;
		
		} // try.
		 
		finally 
		{ 
			scanner.close(); 
		} 	
} 
	
	public static List<String> get_any_list_str(String source)
	{
		
		File 					file 	= new File(source); 

		Scanner 				scanner = null;
	
		List<String> 			map 	= null;
	
	
		try {
		
			scanner = new Scanner(file);
		
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} 
	
	
	try { 
		
		map = new ArrayList<String>();
		
		long start = System.currentTimeMillis();
		
		while (scanner.hasNextLine()) 
		{ 
	
			String 		sc 	= scanner.nextLine();
			
			String [] 	arr = sc.split("\t");
			
			map.add(arr[0]);
			
		} // while.
			
			gl.tx(new Object(){},(map.size() != gl.E_EMPTY),
					
					gl.sf("Загружен файл...[%s]...размер...[%d]...[%d] ms.",
						   Su.AfterAt(source,"\\"),
						   map.size(),
						   (System.currentTimeMillis() - start)
							
						)
					);
			
			return map;
		
		} // try.
		 
		finally 
		{ 
			scanner.close(); 
		} 
		
} 
	
	
	public static Map<String,Integer> get_version_pk() {
		

		String tag_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\tag.txt";
		
		List<Object> m = get_dic_for_tag(tag_source);
		
		return (Map<String,Integer>)m.get(1);
		
	}
	
	public static Map<String,Integer> get_adsh_pk() {
		

		String sub_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\sub.txt";
		
		List<Object> m = get_dic_for_sub(sub_source);
		
		return (Map<String,Integer>)m.get(3);
		
	}
	
	public static Map<String,Integer> get_period_pk() {
		

		String sub_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\sub.txt";
		
		List<Object> m = get_dic_for_sub(sub_source);
		
		return (Map<String,Integer>)m.get(5);
		
	}

	
	public static Map<String,String> get_adsh_form_sub() {
		

		String sub_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\sub.txt";
		
		List<Object> m = get_dic_for_sub(sub_source);
		
		return (Map<String,String>)m.get(8);
		
	}


	public static Map<String,String> get_adsh_period_sub() {
		

		String sub_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\sub.txt";
		
		List<Object> m = get_dic_for_sub(sub_source);
		
		return (Map<String,String>)m.get(9);
		
	}
	
	public static void check_suite() {
	
		String pre_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\pre.txt";
	
		String num_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\num.txt";
	
		Map<String,Integer> adsh_pk 	= get_adsh_pk();
	
		Map<String,Integer> tag_pk  	= get_tag_pk();
		
		Map<String,Integer> version_pk  = get_version_pk();
		
		Map<String,String>  adsh_form 	= get_adsh_form_sub();
		
		Map<String,String>  adsh_period = get_adsh_period_sub();
		
		gl.sfn("Загружен\n...adsh_pk...[%5d]"
				+ "\n...tag_pk...[%5d]"
				+ "\n...version_pk...[%5d]"
				+ "\n...adsh_form...[%5d]"
				+ "\n...adsh_period...[%5d]",
						
				adsh_pk.size(),
				tag_pk.size(),
				version_pk.size(),
				adsh_form.size(),
				adsh_period.size()
				
				);
		
		// For pre : 0,6,7
		
		// For num : 0,1,2
		
		//check_pre(pre_source,tag_pk,adsh_pk,version_pk);
	
		check_num(num_source,tag_pk,adsh_pk,version_pk,adsh_form);
	
	}
	
	public static void check_pre(String path,Map<String,Integer> tag_pk,Map<String,Integer> adsh_pk,Map<String,Integer> version_pk,Map<String,String> adsh_form_pk) 
    {
		check(path,tag_pk,adsh_pk,version_pk,adsh_form_pk,new Integer[]{0,6,7});
    }


	public static void check_num(String path,Map<String,Integer> tag_pk,Map<String,Integer> adsh_pk,Map<String,Integer> version_pk,Map<String,String> adsh_form_pk) 
    {
		check(path,tag_pk,adsh_pk,version_pk,adsh_form_pk,new Integer[]{0,1,2});
    }

	
	public static void check(String pathname,Map<String,Integer> tag_pk,Map<String,Integer> adsh_pk,Map<String,Integer> version_pk,Map<String,String> adsh_form_pk,Integer [] fields) 
    { 

		File 	file 	= new File(pathname); 

		Scanner scanner = null;
		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		
		
			int 	e_adsh 				= 0,
					e_tag 				= 0,
					e_version 			= 0,
					
					e_num_tag_pk 		= 1,
					e_num_version_pk 	= 1,
					e_form_types_pk		= 1;
			
			
			Map<String,Integer> map_num_tag_pk 		= new LinkedHashMap<String,Integer>();
			
			Map<String,Integer> map_num_version_pk 	= new LinkedHashMap<String,Integer>();
						
			Map<String,Integer> map_form_types 		= new LinkedHashMap<String,Integer>();
			
		
		try { 
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
							
				if(!adsh_pk.containsKey(arr[fields[0]]))
				{
					// gl.sfn("Skip adsh...[%s]",arr[fields[0]]);
					
					e_adsh++;
				}
				
				if(!tag_pk.containsKey(arr[fields[1]]))
				{
					// gl.sfn("Skip tag...[%s]",arr[fields[1]]);
					
						e_tag++;

						
					// Filter form type by adsh.
					
					String form = adsh_form_pk.get(arr[fields[0]]);
							
					if(!map_form_types.containsKey(form))
					{
						map_form_types.put(form,e_form_types_pk);
						
						e_form_types_pk++;	
					}	
					
						
					if(!map_num_tag_pk.containsKey(arr[fields[1]]))
					 {

						 map_num_tag_pk.put(arr[fields[1]],e_num_tag_pk);
						
						 e_num_tag_pk ++;
					 }
					
				}
				
				if(!version_pk.containsKey(arr[fields[2]]))
				{
					// gl.sfn("Skip version...[%s]",arr[fields[2]]);
					
					if(arr[fields[2]].contains("-20-"))
					{
						if(!adsh_pk.containsKey(arr[fields[2]]))
						{
								e_version++;
							
							 if(!map_num_version_pk.containsKey(arr[fields[2]]))
							 {

								 map_num_version_pk.put(arr[fields[2]],e_num_version_pk);
								
								 e_num_version_pk ++;
							 }
							
						}
					}
				}
				
				
				
			} // while
			
					gl.sfn("Ошибок...adsh...[%d]...tag...[%d][%d]...version...[%d][%d]",
							e_adsh,
							
							e_tag,
							map_num_tag_pk.size(),
							
							e_version,
							map_num_version_pk.size()
							
							);
					
					// Report about errors.
					
					Fu.to_file("num_version_pk_no.txt","");
					
					Fu.to_file("num_tag_pk_no.txt","");
							
					Fu.to_file("num_form_types_no.txt","");
					
					
					map_num_version_pk.forEach((k,v)->{
						 Fu.to_file("num_version_pk_no.txt",gl.sf("%s\t%3d%s",k,v,System.lineSeparator()),true);
					});
					
					map_num_tag_pk.forEach((k,v)->{
						 Fu.to_file("num_tag_pk_no.txt",gl.sf("%s\t%5d%s",k,v,System.lineSeparator()),true);
					});
					

					map_form_types.forEach((k,v)->{
						 Fu.to_file("num_form_types_no.txt",gl.sf("%-20s\t%5d%s",k,v,System.lineSeparator()),true);
					});
					
					
			
			} 
			 
		
		
    finally { 
			scanner.close(); 
		} 
		
	} 
	
	
	public static void dic_suite() {
	
				// План оптимизации EDGAR данных.
		
				// 1.Сформировать справочники.
		
				// 2.Нормализовать БАЗу.
				
				// 3. Создать представления по формам.(BS,IN)
				
				// 4.Тестовые выборки
				
				String sub_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\sub.txt";
						
				String tag_source = "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\tag.txt";
				
				
				List<Object> l = get_dic_for_sub(sub_source);
				
				List<Object> m = get_dic_for_tag(tag_source);
				
				
				Map<String,Integer> 	map_adsh_cik 	= (Map<String,Integer>)l.get(gl.E_OK);
				
				Map<Integer,String> 	map_cik_name 	= (Map<Integer,String>)l.get(gl.E_EMPTY);
			
				Map<Integer,Integer> 	map_cik_pk 		= (Map<Integer,Integer>)l.get(2);
				
				Map<String,Integer> 	map_adsh_pk 	= (Map<String,Integer>)l.get(3);
			
				
				Map<String,Integer> 	map_form_pk 	= (Map<String,Integer>)l.get(4);
				
				Map<String,Integer> 	map_period_pk 	= (Map<String,Integer>)l.get(5);
				
				Map<Integer,Integer> 	map_fy_pk 		= (Map<Integer,Integer>)l.get(6);
				
				Map<String,Integer> 	map_fp_pk 		= (Map<String,Integer>)l.get(7);
				
				
				Map<String,Integer> 	map_tag_pk 		= (Map<String,Integer>)m.get(0);
				
				Map<String,Integer> 	map_version_pk 	= (Map<String,Integer>)m.get(1);
				
				Map<String,Integer> 	map_datatype_pk = (Map<String,Integer>)m.get(2);
				
				
				gl.sfn("Adsh_cik size...[%5d]",map_adsh_cik.size());
				
				gl.sfn("Cik name size...[%5d]",map_cik_name.size());
				
				gl.sfn("Adsh_pk size...[%5d]",map_adsh_pk.size());
				
				gl.sfn("Cik_pk  size...[%5d]",map_cik_pk.size());
				
				
				gl.sfn("Form_pk size...[%5d]",map_form_pk.size());
				
				gl.sfn("Period_pk size...[%5d]",map_period_pk.size());
				
				gl.sfn("Fy_pk  size...[%5d]",map_fy_pk.size());
				
				gl.sfn("Fp_pk  size...[%5d]",map_fp_pk.size());
				
				
				gl.sfn("Tag_pk      size...[%5d]",map_tag_pk.size());
				
				gl.sfn("Version_pk  size...[%5d]",map_version_pk.size());
				
				gl.sfn("Datatype    size...[%5d]",map_datatype_pk.size());
				
				
				// Dics.
				
				Fu.to_file("sub_adsh_cik_dic.txt","");
				
				Fu.to_file("sub_cik_name_dic.txt","");
				
			
				// Pk's.
				
				Fu.to_file("sub_adsh_pk_dic.txt","");
				
				Fu.to_file("sub_cik_pk_dic.txt","");
	
				
				Fu.to_file("sub_form_pk_dic.txt","");
				
				Fu.to_file("sub_period_pk_dic.txt","");
				
				Fu.to_file("sub_fy_pk_dic.txt","");
				
				Fu.to_file("sub_fp_pk_dic.txt","");
					
				
				Fu.to_file("tag_tag_pk_dic.txt","");
				
				Fu.to_file("tag_version_pk_dic.txt","");
				
				Fu.to_file("tag_datatype_pk_dic.txt","");
				
				
				map_adsh_cik.forEach((k,v)->{
				 
					//Fu.to_file("sub_adsh_cik_dic.txt",gl.sf("%s\t%7d%s",k,v,System.lineSeparator()),true);
					
					Fu.to_file("sub_adsh_cik_dic.txt",gl.sf("%5d\t%4d%s",map_adsh_pk.get(k),map_cik_pk.get(v),System.lineSeparator()),true);
				
					
				});
			
				map_cik_name.forEach((k,v)->{
					
				 //Fu.to_file("sub_cik_name_dic.txt",gl.sf("%7d\t%s%s",k,v,System.lineSeparator()),true);
					
					Fu.to_file("sub_cik_name_dic.txt",gl.sf("%4d\t%s%s",map_cik_pk.get(k),v,System.lineSeparator()),true);
					
				});
				
				map_adsh_pk.forEach((k,v)->{
					 Fu.to_file("sub_adsh_pk_dic.txt",gl.sf("%s\t%7d%s",k,v,System.lineSeparator()),true);
				});
				
				map_cik_pk.forEach((k,v)->{
					 Fu.to_file("sub_cik_pk_dic.txt",gl.sf("%7d\t%7d%s",k,v,System.lineSeparator()),true);
				});


				map_form_pk.forEach((k,v)->{
					 Fu.to_file("sub_form_pk_dic.txt",gl.sf("%s\t%2d%s",k,v,System.lineSeparator()),true);
				});


				map_period_pk.forEach((k,v)->{
					 Fu.to_file("sub_period_pk_dic.txt",gl.sf("%s\t%2d%s",k,v,System.lineSeparator()),true);
				});
				

				map_fy_pk.forEach((k,v)->{
					 Fu.to_file("sub_fy_pk_dic.txt",gl.sf("%4d\t%2d%s",k,v,System.lineSeparator()),true);
				});


				map_fp_pk.forEach((k,v)->{
					 Fu.to_file("sub_fp_pk_dic.txt",gl.sf("%s\t%2d%s",k,v,System.lineSeparator()),true);
				});

				
				map_tag_pk.forEach((k,v)->{
					 Fu.to_file("tag_tag_pk_dic.txt",gl.sf("%-100s\t%5d%s",k,v,System.lineSeparator()),true);
				});
				

				map_version_pk.forEach((k,v)->{
					 Fu.to_file("tag_version_pk_dic.txt",
							 
							 //gl.sf("%-14s\t%3d%s", k.contains("-20-") ? map_adsh_pk.get(k) : k,v,System.lineSeparator()),
							 gl.sf("%-20s\t%4d%s",k,v,System.lineSeparator()),
							 
							 true);
				});


				map_datatype_pk.forEach((k,v)->{
					 Fu.to_file("tag_datatype_pk_dic.txt",gl.sf("%-30s\t%3d%s",k,v,System.lineSeparator()),true);
				});
				
				
			
	}
	
	public static void main(String[] args) {
		
				// dic_suite();
			
				// filter_suite();
		
				// quality_suite();
		
				// check_suite() ;
		
				//	enrich_suite();
	}
	
	
	

}
