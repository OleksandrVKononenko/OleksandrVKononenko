 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.utils; 

import java.io.IOException; 

import ap.global.gl; 

public class DsaUtils { 

	 
	// Generate a script with the many tables, like a test_dwh_rest, tdf_rest_pkg ... 
	 
	public static boolean Test_generate_DSA(String file_name,String file_template,String file_main_template,String file_output) 
	  { 

		  Object v = new Object(){}; 
		 
		  gl.tracex(v,String.format("%s...%s...%s...%s...%s...%s","Input",file_name,"Template",file_template,"Output",file_output)); 
		 
		  if(!Fu.isaFile(file_name) || !Fu.isaFile(file_template) || !Fu.isaFile(file_main_template)) 
		  { 
			  gl.tracex(v,String.format("%s...%s...%s...%s","Input or template file ",file_name,file_template,"Error")); 
			 
			  return false; 
		  } 
		 
		  try { 
			 
			  String source = Fu.get_str_from_file(file_name); 
			 
			  String src_template = Fu.get_str_from_file(file_template); 
			 
			  String src_main_template = Fu.get_str_from_file(file_main_template); 
				 
			 
			  if(source.length()==gl.E_EMPTY || src_template.length()==gl.E_EMPTY || src_main_template.length()==gl.E_EMPTY) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s...%s","Source or template is empty ",file_name,file_template,"Error")); 
				 
				  return false; 
				 
			  } 
			 
			  String[]  arr = source.split(System.lineSeparator()); 
			 
			  if(arr.length ==gl.E_EMPTY) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s","Source has no rows ",file_name,"Error")); 
				 
				  return false; 
				 
			  } 
			 
			  StringBuilder sb = new StringBuilder(); 
			 
			  String tmp = ""; 
			 
			  for(int i=0; i < arr.length;i++) 
			  { 
				  if(arr[i].trim().length() != gl.E_EMPTY) 
				  { 
					  tmp = String.format(src_template,arr[i]); 
					 
					  sb.append(tmp); 
				  } 
				 
			  } 
			 
			 
			 
			  String general = String.format(src_main_template,sb.toString()); 
			 
			  //gl.smn(general.toString()); 
			 
			  if(!Fu.writeToFile(file_output,general)) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s...%s","Process","write to",file_output,"Error")); 
				 
				  return false; 
			  } 
			 
			  sb.setLength(gl.E_EMPTY); 
			 
			  gl.tracex(v,String.format("%s...%s","Process","Ok")); 
			 
			  return true; 
			 
			 
			 
		} catch (IOException e) { 

			  gl.tracex(v,String.format("%s...%s...%s...%s","Input file ",file_name,"Exception",""+e.toString())); 
			 
			  return false; 
		} 
	  } 
	 
	public static boolean Test_gen_DSA_single(String file_name,String file_template,String prefix_job,String db_name,String run_job_type) 
	  { 

		  Object v = new Object(){}; 
		 
		  gl.tracex(v,String.format("%s...%s...%s...%s", 
				  "Input",file_name, 
				  "Template",file_template)); 
		 
		  String root_path = String.format("data/dsa/xml/%s",db_name); 
		 
		  String t_file_name = String.format("%s/in/%s",root_path,file_name); 
		 
		  String t_file_template = String.format("%s/in/%s",root_path,file_template); 
		 
		  if(!Fu.isaFile(t_file_name)/* || !FileUtil.isaFile(t_file_template)*/) 
		  { 
			  gl.tracex(v,String.format("%s...%s...%s...%s","Input or template files ",t_file_name,t_file_template,"Error")); 
			 
			  return false; 
		  } 
		 
		  		 
		 
		  try { 
			 
			  String source = Fu.get_str_from_file(t_file_name); 
			 
			  String src_template = Fu.get_str_from_file(t_file_template); 
			 
			  if(source.length()==gl.E_EMPTY || src_template.length()==gl.E_EMPTY ) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s...%s","Source or template is empty ",file_name,file_template,"Error")); 
				 
				  return false; 
				 
			  } 
			 
			  String[]  arr = source.split(System.lineSeparator()); 
			 
			  if(arr.length ==gl.E_EMPTY) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s","Source has no rows ",file_name,"Error")); 
				 
				  return false; 
				 
			  } 
			 
			 
			  StringBuilder sb = new StringBuilder(); 
			 
			  String tmp = ""; 
			 
			 
			 
			  String create_job_batch_file = String.format("%s/a_create_job_%s.txt",root_path,db_name); 
			 
			  String run_job_batch_file = String.format("%s/a_run_job_%s.txt",root_path,db_name); 
			 
			  if (!Fu.deleteFile(create_job_batch_file)) 
			  { 
				  gl.tracex(v,String.format("%s...%s...%s","Error while delete of the file",file_name,"Error")); 
				 
				  return false; 
			  } 
			 
			  String   create_job_batch_mask = "dsc create_job -n %s -f %s.xml < param.txt\n"; 
			 
			  String   run_job_mask = "dsc run_job -n  %s -b %s < param.txt\n"; 

			 
			  for(int i=0; i < arr.length;i++) 
			  { 
				  if(arr[i].trim().length() != gl.E_EMPTY) 
				  { 
					  String job_name = String.format("%s_%s",prefix_job,arr[i].trim()); 
					 
					  String job_file_name = String.format("%s/%s.%s",root_path,job_name,"xml"); 
					 
					  String create_job_batch_row = String.format(create_job_batch_mask,job_name,job_name); 

					  String run_job_batch_row = String.format(run_job_mask,job_name,run_job_type); 
					 
					  tmp = String.format(src_template,job_name,arr[i],db_name.toUpperCase()); 
					 
					  sb.append(tmp); 
					 
					  if(!Fu.writeToFile(job_file_name,sb.toString()) || 
						 !Fu.appendToFile(create_job_batch_file,create_job_batch_row) || 
						 !Fu.appendToFile(run_job_batch_file,run_job_batch_row) 
							  ) 
					  { 
						  gl.tracex(v,String.format("%s...%s...%s...%s","Process","write to",job_file_name,"Error")); 
						 
						  return false; 
					  } 
					  else 
						  gl.tracex(v,String.format("%s...%s...%s...%s","Generating","job",job_file_name,"Ok")); 
					 
					  	  sb.setLength(gl.E_EMPTY); 
					  					 
					 
				  } 
				 
			  } 
			 
			 
			  gl.tracex(v,String.format("%s...%s","Process","Ok")); 
			 
			  return true; 
			 
			 
			 
		} catch (IOException e) { 

			  gl.tracex(v,String.format("%s...%s...%s...%s","Input file ",file_name,"Exception",""+e.toString())); 
			 
			  return false; 
		} 
	  } 
	 
	 
	 
	public static void main(String[] args) { 
		 
		// List of rest tables. 
		 
		// Test_generate_DSA("data/dsa/list.txt","data/dsa/item_template.txt","data/dsa/gen_template.txt","data/dsa/td_rest_pkg.xml"); 
		 
		// Test_gen_DSA_single("list.txt","template.txt","td","test_dmart","full"); 
		 
	} 

} 
// Revision : 11.08.2017 17:33:39 
// Revision : 10.09.2018 12:49:16 
