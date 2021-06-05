package ap.sec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ap.global.gl;
import ap.utils.DU;

public class Tag {

	String 	tag;
	String	version;
	int		custom;
	int		abstracts;
	String	datatype;
	String	iord;
	String 	crdr;
	String	tlabel;	
	String	doc;
	
	public String 	getTag() 					{return 			tag;}
	public void 	setTag(String tag) 			{this.tag 		= 	tag;}
	public String 	getVersion() 				{return 			version;}
	public void 	setVersion(String version) 	{this.version 	= 	version;}
	public int 		getCustom() 				{return 			custom;}
	public void 	setCustom(int custom) 		{this.custom 	= 	custom;}
	public int 		getAbstracts() 				{return 			abstracts;}
	public void 	setAbstracts(int abstracts) {this.abstracts = 	abstracts;}
	public String 	getDatatype() 				{return 			datatype;}
	public void 	setDatatype(String datatype){this.datatype 	= 	datatype;}
	public String 	getIord() 					{return 			iord;}
	public void 	setIord(String iord) 		{this.iord 		= 	iord;}
	public String 	getCrdr() 					{return 			crdr;}
	public void 	setCrdr(String crdr) 		{this.crdr 		= 	crdr;}
	public String 	getTlabel() 				{return 			tlabel;}
	public void 	setTlabel(String tlabel) 	{this.tlabel 	= 	tlabel;}
	public String 	getDoc() 					{return 			doc;}
	public void 	setDoc(String doc) 			{this.doc 		= doc;}


	public static List<Tag> tags = new ArrayList<Tag>();
	
	public static List<Tag> getTags() {
		return tags;
	}
	public static void setTags(List<Tag> tags) {
		Tag.tags = tags;
	}
	
	public Tag() {
	
		
	}
	
	public Tag(
		String 	tag,
		String	version,
		int		custom,
		int		abstracts,
		String	datatype,
		String	iord,
		String 	crdr,
		String	tlabel,	
		String	doc
	)
	{
		
		this.setTag(tag);
		this.setVersion(version);
		this.setCustom(custom);
		this.setAbstracts(abstracts);
		this.setDatatype(datatype);
		this.setIord(iord);
		this.setCrdr(crdr);
		this.setTlabel(tlabel);
		this.setDoc(doc);
		
	}
	
	public static Tag get_instance(
			String 	tag,
			String	version,
			int		custom,
			int		abstracts,
			String	datatype,
			String	iord,
			String 	crdr,
			String	tlabel,	
			String	doc
		)
		{
		
			return new Tag(
					tag,               
					version,           
					custom,    
					abstracts, 
					datatype,          
					iord,              
					crdr,              
					tlabel,	           
					doc
					);
		}
	
	@Override
	public String toString()
	{
		
		
			return 
					gl.sf("%s %s %1d %1d %s %s %s %s %s",
						this.getTag(),
						this.getVersion(),
						this.getCustom(),
						this.getAbstracts(),
						this.getDatatype(),
						this.getIord(),
						this.getCrdr(),
						this.getTlabel(),
						this.getDoc()
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
		
				int	TAG  =  0,
			     VERSION =  1,
			      CUSTOM =  2,
			    ABSTRACT =  3,
			    DATATYPE =  4,
			        IORD =  5,
			        CRDR =  6,
			      TLABEL =  7,
			         DOC =  8;

		
		try { 
			
			int i = 1;
			
			while (scanner.hasNextLine()) { 
		
				String sc = scanner.nextLine();
				
				String [] arr = sc.split("\t");
				
				//gl.sfn("Record...[%5d]...Fields...[%1d]",i,arr.length);
				
				int al = arr.length;
				
				//gl.sfn("%s",sc);
				
		
				Tag	tag = Tag.get_instance(
							arr[TAG],
							arr[VERSION],
					        DU.to_int(arr[CUSTOM]),
					        DU.to_int(arr[ABSTRACT]),
					        al==4  ? null : arr[DATATYPE],
					        al==4 || al==5 ? null : arr[IORD],
					        al==6 || al==4 || al==5 ? null : arr[CRDR],
					        al==6 || al==4 || al==5 ? null : arr[TLABEL],
					        al == 9 ? arr[DOC] : null
					        );
					
				//gl.sfn("%s",tag.toString());
				
				Tag.getTags().add(tag);
				
				i++;
						
				} // while.
							
				
				return (getTags().size() != gl.E_EMPTY);
			
			} // try.
			 
    finally { 
			scanner.close(); 
		} 
		
	} 

	public static void test_read()
	{
		String tag_source 	= "C:\\bin\\eclipse\\wsp\\Organizer\\data\\sec\\tag.txt";
		
		gl.tx(new Object(){},Tag.read(tag_source),gl.sf("Чтение Tag...[%5d]",Tag.getTags().size()));

	}
	
	public static void main(String[] args) {
		
		test_read();
	}

}
