package ap.sec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import ap.global.gl;
import ap.utils.DU;
import ap.utils.Fu;

public class Num {
	
	int 	adsh;
	String 	tag;
	String 	version;
	String 	coreg;
	String 	ddate;
	int		qtrs;
	String	uom;
	double	value;
	String	footnote;

	public static List<Num> nums = new ArrayList<Num>();

	public int 	getAdsh() {return adsh;}
	public void 	setAdsh(int adsh) {this.adsh = adsh;}
	public String 	getTag() {return tag;}
	public void 	setTag(String tag) {this.tag = tag;}
	public String 	getVersion() {return version;}
	public void 	setVersion(String version) {this.version = version;}
	public String 	getCoreg() {return coreg;}
	public void 	setCoreg(String coreg) {this.coreg = coreg;}
	public String 	getDdate() {return ddate;}
	public void 	setDdate(String ddate) {this.ddate = ddate;}
	public int 		getQtrs() {return qtrs;}
	public void 	setQtrs(int qtrs) {this.qtrs = qtrs;}
	public String 	getUom() {return uom;}
	public void 	setUom(String uom) {this.uom = uom;}
	public double 	getValue() {return value;}
	public void 	setValue(double value) {this.value = value;}
	public String 	getFootnote() {return footnote;}
	public void 	setFootnote(String footnote) {this.footnote = footnote;}

	
	public static List<Num> getNums() {
		return nums;
	}
	public static void setNums(List<Num> nums) {
		Num.nums = nums;
	}
	
	public Num() {
		
	}
	
	public Num(
				int 	adsh,
				String 	tag,
				String 	version,
				String 	coreg,
				String 	ddate,
				int		qtrs,
				String	uom,
				double	value,
				String	footnote
			 )
	{
		
		this.setAdsh(adsh);
		this.setTag(tag);
		this.setVersion(version);
		this.setCoreg(coreg);
		this.setDdate(ddate);
		this.setQtrs(qtrs);
		this.setUom(uom);
		this.setValue(value);
		this.setFootnote(footnote);
		
	}
	

	public static Num get_instance(
			int 	adsh,
			String 	tag,
			String 	version,
			String 	coreg,
			String 	ddate,
			int		qtrs,
			String	uom,
			double	value,
			String	footnote
		 )
	{
		return new  Num(adsh,tag,version,coreg,ddate,qtrs,uom,value,footnote);
		
	}

	@Override
	public String toString()
	{
		Locale.setDefault(new Locale("en", "US")); 
		
			return 
					gl.sf(
						"%5d\t%s\t%s\t%s\t%s\t%d\t%s\t%s\t%s",
						this.getAdsh(),
						this.getTag(),
						this.getVersion(),
						this.getCoreg(),
						this.getDdate(),
						this.getQtrs(),
						this.getUom(),
						gl.norm4f_base_18(this.getValue()),
						this.getFootnote() == null ? "" : this.getFootnote()
						);
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
		
		   int ADSH =  0,
                TAG =  1,
            VERSION =  2,
              COREG =  3,
              DDATE =  4,
               QTRS =  5,
                UOM =  6,
              VALUE =  7,
           FOOTNOTE =  8;

		
		try { 
			
			int i = 1;
			
			int		e_adsh_pk 		= 0, 
					e_tag_pk 		= 0,
					e_version_pk 	= 0,
					e_period_pk 	= 0,
					e_uom_pk 		= 0,
					e_coreg_pk 		= 0;
					
			
			// Используем Adsh словарь для оптимизации размера Num.
			
			Map<String,Integer> adsh_pk = Sec.get_adsh_pk();
			
			// Используем Tag словарь для оптимизации размера Num.
			
			Map<String,Integer> tag_pk = Sec.get_tags_pk();
			
			// Используем Version словарь для оптимизации размера Num.
			
			Map<String,Integer> version_pk = Sec.get_versions_pk();
			
			// Формируем дополнительный Version словарь.
			
			//Map<String,Integer> version_add_pk = new LinkedHashMap<String,Integer>();
			
						
			// Используем Period словарь для оптимизации размера Num.
			
			Map<String,Integer> period_pk = Sec.get_periods_pk();
						
			
			// Формируем UOM словарь.
		
			Map<String,Integer> uom_pk = Sec.get_uoms_pk();
			
			// Формируем COREG словарь.
			
			Map<String,Integer> coreg_pk = Sec.get_coregs_pk();
	
						
			gl.sfn("Словари в помощь \n"
					+ "\nAdsh...   [%5d]"
					+ "\nTag...    [%5d]"
					+ "\nVersion...[%5d]"
					+ "\nPeriod... [%5d]"
					+ "\nUom...    [%5d]"
					+ "\nCoregs... [%5d]",
					adsh_pk.size(),
					tag_pk.size(),
					version_pk.size(),
					period_pk.size(),
					uom_pk.size(),
					coreg_pk.size()
					);
		
			
			
			Fu.to_file("num_x.txt","");
			
			Fu.to_file("num_x_error.txt","");
			
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
				
				int al = arr.length;
				
				//gl.sfn("%s",sc);
				
				//gl.sfn("Record...[%12d]...Fields...[%1d]",i,al);
				
				int i_adsh_pk 		 = gl.E_ERROR,
					i_tag_pk 		 = gl.E_ERROR,
					i_version_pk 	 = gl.E_ERROR,
					i_period_pk 	 = gl.E_ERROR,
					i_uom_pk 		 = gl.E_ERROR,
					i_coreg_pk 		 = gl.E_ERROR,
					i_version_add_pk = gl.E_ERROR;
				
				if(adsh_pk.containsKey(arr[ADSH]))
				{
					i_adsh_pk = adsh_pk.get(arr[ADSH]);
					
				}
				else
				{
					
					String m = gl.sf("Не найдено Adsh...[%s]",arr[ADSH]);
					
					Fu.to_file("num_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
					
					e_adsh_pk++;
				}
				
				
				if(tag_pk.containsKey(arr[TAG]))
				{
					i_tag_pk = tag_pk.get(arr[TAG]);
					
				}
				else
				{

					String m = gl.sf("Не найдено Tag...[%s]",arr[TAG]);
					
					Fu.to_file("num_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
					
					e_tag_pk++;
				}
				
				if(version_pk.containsKey(arr[VERSION]))
				{
					i_version_pk = version_pk.get(arr[VERSION]);
				}
				else
				{
					/*
			
					if(!version_add_pk.containsKey(arr[VERSION]))
					{
						version_add_pk.put(arr[VERSION],i_version_add_pk);
						
						i_version_add_pk++;
					}
						
					*/
					
						String m = gl.sf("Не найдено Version...[%s]",arr[VERSION]);
					
						Fu.to_file("num_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
					
						e_version_pk++;
				}
				

				if(period_pk.containsKey(arr[DDATE]))
				{
					i_period_pk = period_pk.get(arr[DDATE]);
				}
				else
				{
					String m = gl.sf("Не найдено Period...[%s]",arr[DDATE]);
					
					Fu.to_file("num_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
					
					e_period_pk++;
					
					/*
					if(!period_add_pk.containsKey(arr[DDATE]))
					{
						period_add_pk.put(arr[DDATE],-2);

						String m = gl.sf("Не найдено Period...[%s]",arr[DDATE]);
						
						Fu.to_file("num_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
						
						e_period_pk++;
					}
					else
					{
						i_period_pk = period_add_pk.get(arr[DDATE]);
					}
					*/
				}
				
				
				if(!uom_pk.containsKey(arr[UOM]))
				{
				 
					//uom_pk.put(arr[UOM],e_uom_pk);

					String m = gl.sf("Не найдено Uom...[%s]",arr[UOM]);
					
					Fu.to_file("num_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);

					e_uom_pk++;
					
				}
				else
				{
					i_uom_pk = uom_pk.get(arr[UOM]);
				}

				if(!coreg_pk.containsKey(arr[COREG]))
				{
				 
					//coreg_pk.put(arr[COREG],e_coreg_pk);
					
					 if(arr[COREG] != null && arr[COREG].trim().length() != 0)
					 {
												
						String m = gl.sf("Не найдено Coreg...[%s]",arr[COREG]);
							
						Fu.to_file("num_x_error.txt",gl.sf("%s%s",m,System.lineSeparator()),true);
							
					    e_coreg_pk++;
					 }
				}
				else
				{
					   i_coreg_pk = coreg_pk.get(arr[COREG]);
				}

				
				
					Num num = Num.get_instance(
					      i_adsh_pk,
				          gl.sf("%6d",i_tag_pk),
						  gl.sf("%4d",i_version_pk),
						  i_coreg_pk == gl.E_ERROR ? "" : gl.sf("%4d",i_coreg_pk),
						  gl.sf("%3d",i_period_pk),
				          DU.to_int(arr[QTRS]),
				          gl.sf("%4d",i_uom_pk),
				          al == 7 ? 0.00 : DU.to_float(arr[VALUE]),
				          al == 9 ? arr[FOOTNOTE] : null
						);
						
				
					//Num.getNums().add(num);
			
					Fu.to_file("num_x.txt",gl.sf("%s%s",num.toString(),System.lineSeparator()),true);
					
					i++;
						
				} // while.
					

				if(e_adsh_pk != gl.E_EMPTY)
				gl.sfn("Не оптимизированых Adsh...[%5d]",e_adsh_pk);
		
				if(e_tag_pk != gl.E_EMPTY)
				gl.sfn("Не оптимизированых Tag_pk...[%5d]",e_tag_pk);
			

				if(e_version_pk != gl.E_EMPTY)
				{
					gl.sfn("Не оптимизированых Version_pk...[%5d]",e_version_pk);

					/*
					// Итоговый набор.
					
					Map<String,Integer> c = new LinkedHashMap<String,Integer>();

					// Добавляем значения из исходной и плюс новый из Num.
					
					gl.tx(new Object(){}, (version_pk.size() != 0 ) ,
							gl.sf("Обогащение Version перед слиянием...[%d]...[%d]",
							version_pk.size(),version_add_pk.size()));
				
					version_add_pk.forEach((k, v) -> version_pk.putIfAbsent(k, v));
					
					gl.tx(new Object(){},( period_pk.size() != 0 ),
							gl.sf("Обогащение Version после слияния...[%d]...[%d]",
							version_pk.size(),version_add_pk.size()));
				
					// Сортировка.
					
					 version_pk.entrySet().stream()
		                .sorted(Map.Entry.comparingByKey())
		                .forEachOrdered(x -> c.put(x.getKey(), x.getValue()));
				
					 gl.tx(new Object(){}, (period_pk.size() != 0 ) ,
								gl.sf("Обогащение Version после сортировки...[%d]",
								c.size()));
					
					 // Индексируем.
					 
					 int [] j = {0};
					 
					 // Выгружаем.
					 
					 Fu.to_file("versions_pk.txt","");
					 
					 c.forEach((k,v)->{
				        	
						 Fu.to_file("versions_pk.txt",gl.sf("%s\t%d%s",k,j[0],System.lineSeparator()),true);
						 
						 j[0]++;
			        });
				
					 */
				}
				
				
				if(e_period_pk != gl.E_EMPTY)
				{
				
					gl.sfn("Не оптимизированых Period_pk...[%5d]",e_period_pk);
				
					/*
					// Обогащаем Period.
			
					// Итоговый набор.
					
					Map<String,Integer> c = new LinkedHashMap<String,Integer>();

					// Добавляем значения из исходной и плюс новый из Num.
					
					gl.tx(new Object(){}, period_pk.size() != 0 && period_add_pk.size() != 0 ,
							gl.sf("Обогащение Period перед слиянием...[%d]...[%d]",
							period_pk.size(),period_add_pk.size()));
				
					period_add_pk.forEach((k, v) -> period_pk.putIfAbsent(k, v));
					
					gl.tx(new Object(){}, period_pk.size() != 0 && period_add_pk.size() != 0 ,
							gl.sf("Обогащение Period после слияния...[%d]...[%d]",
							period_pk.size(),period_add_pk.size()));
				
					// Сортировка.
					
					 period_pk.entrySet().stream()
		                .sorted(Map.Entry.comparingByKey())
		                .forEachOrdered(x -> c.put(x.getKey(), x.getValue()));
				
					 gl.tx(new Object(){}, period_pk.size() != 0 && period_add_pk.size() != 0 ,
								gl.sf("Обогащение Period после сортировки...[%d]",
								c.size()));
					
					 // Индексируем.
					 
					 int [] j = {0};
					 
					 // Выгружаем.
					 
					 Fu.to_file("periods_pk.txt","");
					 
					 c.forEach((k,v)->{
				        	
						 Fu.to_file("periods_pk.txt",gl.sf("%s\t%d%s",k,j[0],System.lineSeparator()),true);
						 
						 j[0]++;
			        });
			        
			        */
				
				} // periods suite.
				
				
				if(e_uom_pk != gl.E_EMPTY)
				{
				
					gl.sfn("Не оптимизированых Uom_pk...[%d]",e_uom_pk);
				
					/*
					// Итоговый набор.
					
					Map<String,Integer> c = new LinkedHashMap<String,Integer>();
				
					// Сортировка.
					
					 uom_pk.entrySet().stream()
		                .sorted(Map.Entry.comparingByKey())
		                .forEachOrdered(x -> c.put(x.getKey(), x.getValue()));
				
					 gl.tx(new Object(){},c.size() != 0 ,
								gl.sf("Формирование Uom после сортировки...[%d]",
								c.size()));
					
					 // Индексируем.
					 
					 int [] j = {0};
					 
					 // Выгружаем.
					 
					 Fu.to_file("uoms_pk.txt","");
					 
					 c.forEach((k,v)->{
				        	
						 Fu.to_file("uoms_pk.txt",gl.sf("%s\t%d%s",k,j[0],System.lineSeparator()),true);
						 
						 j[0]++;
			        });
					 
					 */
					
				} // uoms suite.
				
				if(e_coreg_pk != gl.E_EMPTY)
				{
				
					gl.sfn("Не оптимизированых Coreg_pk...[%d]",e_coreg_pk);
				
					/*
					// Итоговый набор.
					
					Map<String,Integer> c = new LinkedHashMap<String,Integer>();
				
					// Сортировка.
					
					 coreg_pk.entrySet().stream()
		                .sorted(Map.Entry.comparingByKey())
		                .forEachOrdered(x -> c.put(x.getKey(), x.getValue()));
				
					 gl.tx(new Object(){},c.size() != 0 ,
								gl.sf("Формирование Coreg после сортировки...[%d]",
								c.size()));
					
					 // Индексируем.
					 
					 int [] j = {0};
					 
					 // Выгружаем.
					 
					 Fu.to_file("coregs_pk.txt","");
					 
					 c.forEach((k,v)->{
				        	
						 Fu.to_file("coregs_pk.txt",gl.sf("%s\t%d%s",k,j[0],System.lineSeparator()),true);
						 
						 j[0]++;
			        });
					 
					 */
				} // coregs suite.
								
				return (getNums().size() != gl.E_EMPTY);
			
			} // try.
			 
    finally { 
			scanner.close(); 
		} 
	} 

	public static void test_read()
	{
		
		String num_source 	= "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\Num.txt";
		
		gl.tx(new Object(){},Num.read(num_source),gl.sf("Чтение Num...[%5d]",Num.getNums().size()));

	}
	
	public static void main(String[] args) {
		
		test_read();
		
	}

}
