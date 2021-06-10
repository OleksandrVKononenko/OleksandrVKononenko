package ap.raif;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class RaifUtil {

    public static final int EMPTY   = 0;

    public static final int OK      = 1;

    public static final int ERROR   = -1;


    public static void sm(Object object)
    {
        sw(String.format("%s%s",object,System.lineSeparator()));
    }

    public static RaifStatus smt(Object object,RaifStatus raifStatus)
    {
            sm(sf("%s...[%s]",object,raifStatus.toString()));

            return raifStatus;
    }

    public static void sw(Object object)
    {
        System.out.print(object);
    }

    public static String sf(String format,Object...args)
    {
        return String.format(format, args);
    }

    public static List<String> get_as_list(String source)
    {
        return get_as_list(source,",");
    }

    public static List<String> get_list_from_file(String path)
    {
        return Arrays.asList(get_str_array_from_file(path));
    }

    public static String get_str_from_list(List<String> items)
    {
        if(items.size() == EMPTY)
            return null;

            return String.join(" ",items);
    }

    public static String[] get_str_array_from_file(String path)
    {
        String source = "";

        try {

            source = get_str_from_file(path);

            String[] arr = source.split(System.lineSeparator());

            return arr;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
    public static String get_str_from_file(String pathname)
            throws IOException {


        File file = new File(pathname);

        StringBuilder fileContents = new StringBuilder((int) file.length());

        Scanner scanner = new Scanner(file);

        String lineSeparator = System.lineSeparator();

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine());

                fileContents.append(lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }

    public static String AfterAt(String value, String what) {

        int index = value.lastIndexOf(what);

        if (index == -1)
            return value;

        return value.substring(index + what.trim().length()).trim();

    }

    public static String AfterAtFirst(String value, String what) {
        int index = value.indexOf(what);

        if (index == -1)
            return value;

        return value.substring(index + what.trim().length()).trim();

    }
    public static String BeforeAtFirst(String value, char what, int offset) {
        int index = value.indexOf(what, offset);

        if (index == -1)
            return value;

        return value.substring(0, index).trim();

    }

    public static String BeforeAt(String value, String what) {

        int index = value.lastIndexOf(what);

        if (index == -1)
            return value;

        return value.substring(0, index).trim();

    }

    public static String BeforeAtFirst(String value, String what) {
        int index = value.indexOf(what);

        if (index == -1)
            return value;

        return value.substring(0, index).trim();

    }
    public static boolean to_file(String fileName,String value)
    {
        return to_file(fileName,value,false);
    }

    public static boolean to_file_append(String fileName,String value)
    {
        return to_file(fileName,value,true);
    }

    public static boolean to_file(String fileName,String value,boolean append)
    {

        FileWriter fw = null;

        try {

            fw = new FileWriter(new File(fileName),append);

            fw.write(value);

        } catch (IOException e) {

            return false;
        }
        finally
        {

            try {

                fw.close();

            } catch (IOException e) {

                e.printStackTrace();

                return false;
            }
        }

        return true;
    }

    public static List<String> get_list(String source,String delimiter)
    {
        return Arrays.stream(source.split(delimiter)).collect(Collectors.toList());
    }

    public static List<String> get_list(String source)
    {
        return Arrays.stream(source.split(",")).collect(Collectors.toList());
    }
    public static List<String> get_as_list(String source,String dlm)
    {

        if( source == null )
        {
            return null;
        }

        String [] arr = source.split(dlm);

        if(arr == null || arr.length == 0)
        {
            return null;
        }

        return Arrays.asList(arr);
    }
    public static String get_str_from_list(List<String> items,String dlm)
    {
        return String.join(dlm,items);
    }

    public static String get_str_between(String source,String a, String b)
    {
        return BeforeAt(AfterAt(source,a),b);
    }

    public static String after_at_period(String value) {
        return AfterAt(value, ".");
    }

    public static ArrayList<File> get_files_in_dir(File rootDirectory,List<String> masks)
            throws IOException {

        ArrayList<File> m_result = null;

        try {

            m_result = new ArrayList<File>();

            File files[] = rootDirectory.listFiles();

            for (int i = 0; i < files.length; i++) {

                File file = files[i];


                if (file.isFile())
                {

                    // Include * mask

//                    if( masks.length == OK &&
//                            (masks[EMPTY].equalsIgnoreCase("*")))
//                    {
//                        m_result.add(file);
//                    }
//
                    if(masks.size() == EMPTY)
                    {

                    }
                    else
                    {
                        String extension = after_at_period(file.getName()).trim();

                        /*
                        for(int j=0;j<masks.length;j++)
                        {
                            if (masks[j].equalsIgnoreCase(extension))
                                m_result.add(file);
                        }
                        */

                        for (String mask : masks) {
                            if(mask.equalsIgnoreCase(extension))
                            {
                                m_result.add(file);
                            }
                        }
                    }

                }

                if (file.isDirectory()) {

                    java.util.List<File> subDirFiles = get_files_in_dir(file,masks);

                    m_result.addAll(subDirFiles);
                }
            }

            return m_result;

        } catch (IOException e) {

           e.printStackTrace();

            return null;
        }
    }

    // TODO 10.06.2021 find_of_tag()

    public static boolean find_of_tag(String source_dir, String mask,String word,boolean save,boolean verbose)
    {
            return find_of_tag(source_dir,get_list(mask),word,save,verbose);
    }
    public static boolean find_of_tag(String source_dir,List<String> mask,String word,boolean save,boolean verbose)
    {

        try {

            String m_file_log = sf("%s_log.txt",word.trim());


            if(save)
            {
                to_file(m_file_log,sf("Поиск ключа [%s] в директории [%s] по маске [%s]",
                        word,source_dir,get_str_from_list(mask)));
            }

            StringBuilder 	sb  = new StringBuilder();

            Map<Integer,String> map = new LinkedHashMap<Integer,String>();

            get_files_in_dir(new File(source_dir),mask).forEach(a->{

                List<String> rows = get_list_from_file(a.getAbsolutePath());

//				 gl.smn(gl.sf("File...[%s]...Rows...[%5d]",
//						 a.getAbsolutePath(),
//						 rows.size()));
//
                // Find word.

                int row_num  = 1;

                String 		m_file = sf("\n[%s] [%5d]\n\n",a,map.size());

                sb.append(m_file);

                for(String s : rows)
                {
                    if(s.contains(word))
                    {
                        String 			m_find = sf("\t\t%5d  %s\n",row_num,s);

                        sb.append(m_find);
                    }

                    row_num++;
                }

                if(verbose)
                    sm(sb.toString());



                if(save){to_file_append(m_file_log,sb.toString());}

                sb.setLength(EMPTY);


            });

                sm("Done.");

                return true;

        } catch (IOException e) {

            return false;
        }
}

    public static void main(String[] args)  {

        //find_of_tag("C:\\bin\\test\\OleksandrVKononenko\\ap\\","java"," main(",true,false);

        find_of_tag("C:\\Users\\IUAD1GJO\\IdeaProjects\\rice-api-gateway-service\\","java,yml","clients",false,true);

    }



}
