package ap.sec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ap.global.gl;
import ap.utils.DU;
import ap.utils.Fu;

public class Pre {

	int 	adsh ;
	int 	report;
	int 	line;
	String 	stmt;
	int		inpth;
	String  rfile;
	int		tag;
	int 	version;
	int 	plabel;
	int 	negating;
	
	public int 		getAdsh() 					{return adsh;}
	public void 	setAdsh(int adsh)	 		{this.adsh = adsh;}
	public int 		getReport() 				{return report;}
	public void 	setReport(int report) 		{this.report = report;}
	public int 		getLine() 					{return line;}
	public void 	setLine(int line) 			{this.line = line;}
	public String 	getStmt() 					{return stmt;}
	public void 	setStmt(String stmt) 		{this.stmt = stmt;}
	public int 		getInpth() 					{return inpth;}
	public void 	setInpth(int inpth) 		{this.inpth = inpth;}
	public String 	getRfile() 					{return rfile;}
	public void 	setRfile(String rfile) 		{this.rfile = rfile;}
	public int 		getTag() 					{return tag;}
	public void 	setTag(int tag) 			{this.tag = tag;}
	public int 		getVersion() 				{return version;}
	public void 	setVersion(int version) 	{this.version = version;}
	public int 		getPlabel() 				{return plabel;}
	public void 	setPlabel(int plabel) 		{this.plabel = plabel;}
	public int 		getNegating() 				{return negating;}
	public void 	setNegating(int negating) 	{this.negating = negating;}
	
	public static List<Pre> pres = new ArrayList<Pre>();
	
	public static List<Pre> getPres() { return pres;}
	public static void setPres(List<Pre> pres) { Pre.pres = pres;}
	
	public Pre() {
	
	}

	public Pre(
			int 	adsh ,
			int 	report,
			int 	line,
			String 	stmt,
			int		inpth,
			String  rfile,
			int		tag,
			int		version,
			int 	plabel,
			int 	negating
	)
	{
		this.setAdsh(adsh);
		this.setReport(report);
		this.setLine(line);
		this.setStmt(stmt);
		this.setInpth(inpth);
		this.setRfile(rfile);
		this.setTag(tag);
		this.setVersion(version);
		this.setPlabel(plabel);
		this.setNegating(negating);
	}

	
	public static Pre get_instance(
			int 	adsh ,
			int 	report,
			int 	line,
			String 	stmt,
			int		inpth,
			String  rfile,
			int		tag,
			int		version,
			int 	plabel,
			int 	negating
			)
	{
		
		return new Pre(adsh ,  
				report, 
				line,   
				stmt,   
				inpth,  
				rfile,  
				tag,    
				version,
				plabel, 
				negating
				);
		
	}

	@Override
	public String toString()
	{
	
		return gl.sf(
				  "%d\t"
				+ "%d\t"
				+ "%d\t"
				+ "%s\t"
				+ "%d\t"
				+ "%s\t"
				+ "%d\t"
				+ "%d\t"
				+ "%d\t"
				+ "%d\t",
				
				this.getAdsh(),
				this.getReport(),
				this.getLine(),
				this.getStmt(),
				this.getInpth(),
				this.getRfile(),
				this.getTag(),
				this.getVersion(),
				this.getPlabel(),
				this.getNegating()
				);
		}
	

	public static boolean read_dic(String dir) 
    {
		
		List<Integer>  	m_adsh 		= Sec.get_any_list_int(gl.sf("%s\\pre.adsh.txt",dir));
		
		List<Integer>  	m_report 	= Sec.get_any_list_int(gl.sf("%s\\pre.report.txt",dir));
		
		List<Integer>  	m_line 		= Sec.get_any_list_int(gl.sf("%s\\pre.line.txt",dir));
		
		List<Integer>  	m_inpth 	= Sec.get_any_list_int(gl.sf("%s\\pre.inpth.txt",dir));

		List<Integer> 	 m_tag 		= Sec.get_any_list_int(gl.sf("%s\\pre.tag.txt",dir));

		List<Integer>  	m_version	= Sec.get_any_list_int(gl.sf("%s\\pre.version.txt",dir));

		List<Integer>  	m_plabel 	= Sec.get_any_list_int(gl.sf("%s\\pre.plabel.txt",dir));

		List<Integer>  	m_negating 	= Sec.get_any_list_int(gl.sf("%s\\pre.negating.txt",dir));

		
		List<String>  	m_stmt 		= Sec.get_any_list_str(gl.sf("%s\\pre.stmt.txt",dir));

		List<String>  	m_rfile 	= Sec.get_any_list_str(gl.sf("%s\\pre.rfile.txt",dir));

		
		return true;
    }
	
	public static boolean read(String source) 
    { 

		File 					file 	= new File(source); 

		Scanner 				scanner = null;
		
		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		
				int	ADSH =  0,
	              REPORT =  1,
	                LINE =  2,
	                STMT =  3,
	               INPTH =  4,
	               RFILE =  5,
	                 TAG =  6,
	             VERSION =  7,
	              PLABEL =  8,
	            NEGATING =  9;

		
		try { 
			
			int i = 1;
			
			int		e_adsh_pk 		= 0, 
					e_tag_pk 		= 0,
					e_version_pk 	= 0,
					e_plabel_pk 	= 0;
					
			
			// Используем Adsh словарь.
			
			Map<String,Integer> adsh_pk = Sec.get_adshs_pk();
			
			// Используем Tag словарь.
			
			Map<String,Integer> tag_pk = Sec.get_tags_pk();
			
			// Используем Version словарь.
			
			Map<String,Integer> version_pk = Sec.get_versions_pk();
								
			long lines_count = Fu.get_lines_count(source);
			
			Map<String,Integer> plabel_pk = Sec.get_plabels_pk();
			
					
			gl.sfn("Словари в помощь \n"
					+ "\nAdsh...   [%5d]"
					+ "\nTag...    [%5d]"
					+ "\nVersion...[%5d]"
					+ "\nLabel  ...[%5d]"
					+ "\nСтрок  ...[%5d]",
					
					adsh_pk.size(),
					tag_pk.size(),
					version_pk.size(),
					plabel_pk.size(),
					lines_count
					
					);
		
			
			
			Fu.to_file("pre_x.txt","");
			
			Fu.to_file("pre_x_error.txt","");
			
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
				
				int al = arr.length;
				
				int i_adsh_pk 		 = gl.E_ERROR,
					i_tag_pk 		 = gl.E_ERROR,
					i_version_pk 	 = gl.E_ERROR,
					i_plabel_pk 	 = gl.E_ERROR;
				
				if(adsh_pk.containsKey(arr[ADSH]))
				{
					i_adsh_pk = adsh_pk.get(arr[ADSH]);
					
				}
				else
				{
					
					String m = gl.sf("Не найдено Adsh...[%s]",arr[ADSH]);
					
					Fu.to_file("pre_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
					
					e_adsh_pk++;
				}
				
				
				if(tag_pk.containsKey(arr[TAG]))
				{
					i_tag_pk = tag_pk.get(arr[TAG]);
					
				}
				else
				{

					String m = gl.sf("Не найдено Tag...[%s]",arr[TAG]);
					
					Fu.to_file("pre_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
					
					e_tag_pk++;
				}
				
				if(version_pk.containsKey(arr[VERSION]))
				{
					i_version_pk = version_pk.get(arr[VERSION]);
				}
				else
				{			
					String m = gl.sf("Не найдено Version...[%s]",arr[VERSION]);
					
					Fu.to_file("pre_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
					
					e_version_pk++;
				}
				
				/*
				
				if(!plabel_pk.containsKey(arr[PLABEL]))
				{
					plabel_pk.put(arr[PLABEL],i_plabel_pk);
					
					i_plabel_pk++;
				}
				
				*/
				
				if(plabel_pk.containsKey(arr[PLABEL]))
				{
					i_plabel_pk = plabel_pk.get(arr[PLABEL]);
				}
				else
				{			
					String m = gl.sf("Не найдено plabel...[%s]",arr[PLABEL]);
					
					Fu.to_file("pre_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
					
					e_plabel_pk++;
				}
				
				
					// Строка для экспорта.
				
					Pre pre = Pre.get_instance(
							i_adsh_pk,
							DU.to_int(arr[REPORT]),
							DU.to_int(arr[LINE]),
							arr[STMT],
							DU.to_int(arr[INPTH]), 
							arr[RFILE],
							i_tag_pk,
							i_version_pk,
							i_plabel_pk,
							DU.to_int(arr[NEGATING]));
					
					//gl.sfn("%s",sc);
					
					//gl.sfn("%s",pre.toString());
				
					Fu.to_file("pre_x.txt",gl.sf("%s%s",pre.toString(),System.lineSeparator()),true);
					
					i++;
						
				} // while.
					

				if(e_adsh_pk != gl.E_EMPTY)
				gl.sfn("Не оптимизированых Adsh...[%5d]",e_adsh_pk);
		
				if(e_tag_pk != gl.E_EMPTY)
				gl.sfn("Не оптимизированых Tag_pk...[%5d]",e_tag_pk);
			
				if(e_version_pk != gl.E_EMPTY)
				gl.sfn("Не оптимизированых Version_pk...[%5d]",e_version_pk);
			
				if(plabel_pk.size() != gl.E_EMPTY)
				{
					gl.sfn("Не оптимизированых PLabel_pk...[%5d]",plabel_pk.size());
				
				/*
					Map<String,Integer> c = new LinkedHashMap<String,Integer>();

					// Добавляем значения из исходной и плюс новый из Num.
					
					gl.tx(new Object(){}, (plabel_pk.size() != 0 ) ,
							gl.sf("Обогащение PLabel перед слиянием...[%d]",
									plabel_pk.size()));
				
					// Сортировка.
					
					plabel_pk.entrySet().stream()
		                .sorted(Map.Entry.comparingByKey())
		                .forEachOrdered(x -> c.put(x.getKey(), x.getValue()));
				
					 gl.tx(new Object(){}, (plabel_pk.size() != 0 ) ,
								gl.sf("Обогащение PLabel после сортировки...[%d]",
								c.size()));
					
					 // Индексируем.
					 
					 int [] j = {1};
					 
					 // Выгружаем.
					 
					 Fu.to_file("plabels_pk.txt","");
					 
					 c.forEach((k,v)->{
				        	
						 Fu.to_file("plabels_pk.txt",gl.sf("%s\t%d%s",k,j[0],System.lineSeparator()),true);
						 
						 j[0]++;
			        });
				
				 */
					
				}
				
				
					return (getPres().size() != gl.E_EMPTY);
			
			} // try.
			 
		finally { 
				scanner.close(); 
		} 
	} 
	
	
	public static boolean write(String target,String dir,int index,String data)
	{
		
		/*
		 
		return Fu.to_file(gl.sf("%s\\%s",dir,target),gl.sf("%d\t%s%s",
				index,
				data,
				System.lineSeparator()
				),true);
		
		*/
		
		return Fu.to_file(gl.sf("%s\\%s",dir,target),gl.sf("%s%s",
				data,
				System.lineSeparator()
				),true);
		

		
	}
	public static boolean export(String source,String dir) 
    { 

		File 					file 	= new File(source); 

		Scanner 				scanner = null;
		
		
		try {
			
			scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		
				int	ADSH =  0,
	              REPORT =  1,
	                LINE =  2,
	                STMT =  3,
	               INPTH =  4,
	               RFILE =  5,
	                 TAG =  6,
	             VERSION =  7,
	              PLABEL =  8,
	            NEGATING =  9;

		
		try { 
			
			int i = 1;
			
			long l = Fu.get_lines_count(source);
			
			Fu.to_file(gl.sf("%s\\pre.adsh.txt",dir),"");
			
			Fu.to_file(gl.sf("%s\\pre.report.txt",dir),"");
			
			Fu.to_file(gl.sf("%s\\pre.line.txt",dir),"");
			
			Fu.to_file(gl.sf("%s\\pre.stmt.txt",dir),"");
			
			Fu.to_file(gl.sf("%s\\pre.inpth.txt",dir),"");

			Fu.to_file(gl.sf("%s\\pre.rfile.txt",dir),"");

			Fu.to_file(gl.sf("%s\\pre.tag.txt",dir),"");

			Fu.to_file(gl.sf("%s\\pre.version.txt",dir),"");

			Fu.to_file(gl.sf("%s\\pre.plabel.txt",dir),"");

			Fu.to_file(gl.sf("%s\\pre.negating.txt",dir),"");
			
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
			
				write("pre.adsh.txt",dir,i,arr[ADSH]);
				
				write("pre.report.txt",dir,i,arr[REPORT]);
				
				write("pre.line.txt",dir,i,arr[LINE]);
				
				write("pre.stmt.txt",dir,i,arr[STMT]);
				 
				write("pre.inpth.txt",dir,i,arr[INPTH]);
				
				write("pre.rfile.txt",dir,i,arr[RFILE]);
				
				write("pre.tag.txt",dir,i,arr[TAG]);
				
				write("pre.version.txt",dir,i,arr[VERSION]);
				
				write("pre.plabel.txt",dir,i,arr[PLABEL]);
				
				write("pre.negating.txt",dir,i,arr[NEGATING]);
				
				
				//gl.sfn("Строк...обработано...[%7d]...остаток...[%7d]",i,l-i);
				
				i++;
			
			}
				
				
					return true;
			
			} // try.
			 
		finally { 
				scanner.close(); 
		} 
	} 
	public static void test_read()
	{
		
		String pre_source 	= "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\Pre.txt";
		
		gl.tx(new Object(){},Pre.read(pre_source),gl.sf("Чтение Pre...[%5d]",Pre.getPres().size()));

	}
	
	public static void test_read_dic()
	{
		
		String pre_source 	= "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\pre";
		
		gl.tx(new Object(){},Pre.read_dic(pre_source),gl.sf("Чтение Pre dic..."));
			

	}
	
	public static void test_export()
	{
		
		String pre_source 	= "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\pre\\pre_x.txt";
		
		String pre_dir 		= "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\pre";
		
		gl.tx(new Object(){},Pre.export(pre_source,pre_dir),gl.sf("Експорт Pre.txt..."));

	}
	
	
	public static void main(String[] args) {

		// test_read();
		
		// test_export();
		
		test_read_dic();
		
	}

}
