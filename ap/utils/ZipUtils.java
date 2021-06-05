package ap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import ap.global.gl;

public class ZipUtils {

    private List <String> fileList;
    
    //private static final String OUTPUT_ZIP_FILE = "ap.zip";
    
    //private static final String SOURCE_FOLDER = "C:\\bin\\eclipse\\wsp\\Organizer\\src\\ap\\"; 

    private String source;
    
    private String target;
    
    
    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public ZipUtils() {
    	
        fileList = new ArrayList < String > ();
        
    }
	
	public ZipUtils(String source,String target) {
	    
		this();
		
		this.setSource(source);
	
		this.setTarget(target);
	
	}
	
	public static ZipUtils get_instance(String source,String target)
	{
		return new ZipUtils(source,target);
	}

    public  boolean run()
    {
    	try
    	{
    	  
					this.generateFileList(new File(this.getSource()));

					this.zipIt(this.getTarget());
					
					return true;
    	}
    	catch(Exception e)
    	{
    				gl.sfn("Exception...%s",e.toString());
    				
    				return false;
    	}
    }
    
    public boolean run(boolean inject_time)
    {
    	
    	/*
    	
    	String m_pure 		= Fu.get_pure_file_name(this.getTarget());
    	
    	String m_path 		= Fu.get_path(this.getTarget());
    	
    	String m_ext 		= Fu.get_file_extension(this.getTarget());
    	
    	String m_stamp		= DateUtil.get_stamp_for_zip();
    			
    	String m_target 	= gl.sf("%s%s_%s.%s",m_path,m_pure,m_stamp,m_ext);
    	*/
    	
    	String m_target = Fu.inject_time_stamp(this.getTarget());
    	
    	gl.sfn("Target...%s",m_target);
    	    	
    	/*
    	gl.sfn("Target...%s...Path...%s...Pure...%s...Stamp...%s...Ext...%s",
    			m_target,
    			m_path,
    			m_pure,
    			m_stamp,
    			m_ext
    			);
    	
    	*/
    	
    	this.setTarget(m_target);
    	
    	return run();
    	
    }
    
    
    public static void test_zip()
    {
    	String source = "C:\\bin\\eclipse\\wsp\\Organizer\\src\\ap\\";
		
    	String target = "C:\\bin\\eclipse\\wsp\\Organizer\\zip\\ap.zip";
    	  	
    	ZipUtils Z = ZipUtils.get_instance(source, target);
    			
    	if(!Z.run(true))
    	{
    		gl.tx_e(new Object() {},gl.sf("Создание архива...%s...для раздела...%s",Z.getTarget(),Z.getSource()));
    	}
    	else 
    	{
    		gl.tx_k(new Object() {},gl.sf("Создание архива...%s...для раздела...%s",Z.getTarget(),Z.getSource()));
    		
    	}
    	
    }
    
    public static void test_unzip()
    {
    	String target = "C:\\bin\\eclipse\\wsp\\Organizer\\src\\ap1\\";
		
    	String source = "C:\\bin\\eclipse\\wsp\\Organizer\\zip\\ap_21_10_2020_22_56_32.zip";
    
    	gl.tx(new Object() {},ZipUtils.unzip(source, target),
    			gl.sf("Распаковка архива...%s...для раздела...%s",
				source,target));
		
    	
    }
    public static void main(String[] args) {
        
    	//test_zip();
    	
    	test_unzip();
    }

    public void zipIt(String zipFile) {
        
    	byte[] buffer = new byte[1024];
        
    	String source = new File(this.getSource()).getName();
        
    	FileOutputStream fos = null;
        
    	ZipOutputStream zos = null;
        
    	try {
        
    		fos = new FileOutputStream(zipFile);
            
    		zos = new ZipOutputStream(fos);

            gl.sfn("Output to Zip...%s",zipFile);
            
            FileInputStream in = null;

            for (String file: this.fileList) {
            
            	gl.sfn("File Added...%s",file);
            	
            	ZipEntry ze = new ZipEntry(source + File.separator + file);
                
            	zos.putNextEntry(ze);
                
            	try {
                
            		in = new FileInputStream(this.getSource() + File.separator + file);
                    
            		int len;
                    
            		while ((len = in .read(buffer)) > 0) {
        
            			zos.write(buffer, 0, len);
            			
                    }
            		
                } finally {
                    
                	in.close();
                }
            }

            zos.closeEntry();
            
            gl.sfn("Folder successfully compressed");
            

        } catch (IOException ex) {
            
        	ex.printStackTrace();
        	
        } finally {
            try {
            
            	zos.close();
            	
            } catch (IOException e) {
                
            	e.printStackTrace();

            }
        }
    }

    public void generateFileList(File node) {
        
    	// add file only
        if (node.isFile()) {
            
        	fileList.add(generateZipEntry(node.toString()));
        }

        if (node.isDirectory()) {
            
        	String[] subNote = node.list();
        
            for (String filename: subNote) {
                generateFileList(new File(node, filename));
            }
        }
    }

    private String generateZipEntry(String file) {
        
    	return file.substring(this.getSource().length() , file.length());
    	
    }
    
    public static boolean unzip (String p_source, String p_target)  {

    		Path source   = Paths.get(p_source);
    		
    		Path target   = Paths.get(p_target);
    		
    	
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source.toFile()))) {

                // list files in zip
                ZipEntry zipEntry = zis.getNextEntry();

                while (zipEntry != null) {

                    boolean isDirectory = false;
                    // example 1.1
                    // some zip stored files and folders separately
                    // e.g data/
                    //     data/folder/
                    //     data/folder/file.txt
                    if (zipEntry.getName().endsWith(File.separator)) {
                        isDirectory = true;
                    }

                    Path newPath = zipSlipProtect(zipEntry, target);

                    if (isDirectory) {
                        Files.createDirectories(newPath);
                    } else {

                        // example 1.2
                        // some zip stored file path only, need create parent directories
                        // e.g data/folder/file.txt
                        if (newPath.getParent() != null) {
                            if (Files.notExists(newPath.getParent())) {
                                Files.createDirectories(newPath.getParent());
                            }
                        }

                        // copy files, nio
                        Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);

                        // copy files, classic
                        /*try (FileOutputStream fos = new FileOutputStream(newPath.toFile())) {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = zis.read(buffer)) > 0) {
                                fos.write(buffer, 0, len);
                            }
                        }*/
                    }

                    zipEntry = zis.getNextEntry();

                }
                	zis.closeEntry();

                	return true;
            }
            catch(IOException e)
            {
            	return false;
            }

        }
    
    public static Path zipSlipProtect(ZipEntry zipEntry, Path targetDir)
            throws IOException {

            // test zip slip vulnerability
            // Path targetDirResolved = targetDir.resolve("../../" + zipEntry.getName());

            Path targetDirResolved = targetDir.resolve(zipEntry.getName());

            // make sure normalized file still has targetDir as its prefix
            // else throws exception
            Path normalizePath = targetDirResolved.normalize();
            if (!normalizePath.startsWith(targetDir)) {
                throw new IOException("Bad zip entry: " + zipEntry.getName());
            }

            return normalizePath;
        }

    
}