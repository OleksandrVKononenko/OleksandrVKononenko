 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 
 
 
 
/** 
* 
*/ 
package ap.xml; 

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 

import javax.xml.bind.JAXBContext; 
import javax.xml.bind.Marshaller; 
import javax.xml.bind.Unmarshaller; 

import ap.global.gl; 
import ap.utils.Fu; 

/** 
*  Author       : Oleksandr V. Kononenko 
* 
* 15 ???. 2016 ?. 11:01:55 
* Project  name : Organizer 
* Package  name : ap.xml 
* File     name : XmlUtil.java 
* 
*/ 
public class XmlUtil { 

	/** 
	 * 
	 */ 
	public XmlUtil() { 
		// REPLACE Auto-generated constructor stub 
	} 

	 
	  public static Config readCfg(String pcfgfile) 
	  { 

		Config cfg = null; 

		try { 
			JAXBContext context = JAXBContext.newInstance(Config.class); 

			Unmarshaller um = context.createUnmarshaller(); 

			if (!Fu.isaFile(pcfgfile)) { 
				 
				gl.smn("<readCfg()> Error read file : " + pcfgfile); 

				return null; 
			} 

			cfg = (Config) um.unmarshal(new FileInputStream(pcfgfile)); 

		} catch (javax.xml.bind.JAXBException e) { 
			gl.smn("<readCfg()> : " + e.toString()); 
		} catch (FileNotFoundException e) { 
			gl.smn("<readCfg()> : " + e.toString()); 
		} 

		return cfg; 

	  } 


	  public static boolean writeCfg(Config pcfg,String pcfgfile) 
	  { 
	 
	    try 
	    { 

	      JAXBContext context = JAXBContext.newInstance(Config.class); 

	      Marshaller m = context.createMarshaller(); 
	 
	      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
	 
	      m.marshal(pcfg, new FileOutputStream(pcfgfile)); 

	    } 
	    catch (javax.xml.bind.JAXBException e) 
	    { 
	      gl.smn("writeCfg()> : " + e.toString()); 
	    } 

	    catch (FileNotFoundException e) 
	    { 
	      gl.smn("writeCfg() > : " + e.toString()); 
	    } 

	      if(readCfg(pcfgfile) != null) 
	      { 
	        return true; 
	      } 
	 
	        return false; 
	  } 

	 
	 
	/** 
	 * @param args 
	 */ 
	public static void main(String[] args) { 
		// REPLACE Auto-generated method stub 

	} 

} 
// Revision : 20.01.2017 15:56:39 
// Revision : 28.01.2017 15:14:50 
// Revision : 11.08.2017 17:33:40 
// Revision : 10.09.2018 12:49:17 
