package ap.raif;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static ap.raif.RaifUtil.*;

public class RaifDispatch {

    public final String PARAMETER   = "PARAMETER";

    public final String SWITCH      = "SWITCH";

    private final int CMD_COMMAND_MAP_COUNT = 5;

    private final int CMD_COMMAND_DESCRIPTION_MAP_COUNT = 4;

    private final int CMD_COMMAND_COUNT_PARAMS = 2;


    private RaifStatus commandMapStatus;

    private RaifStatus commandDescriptionMapStatus;

    private RaifStatus commandPipeLineMapStatus;

    private RaifStatus markupCommandMapStatus;

    private RaifStatus dispatchStatus;

    public RaifStatus getMarkupCommandMapStatus() {
        return markupCommandMapStatus;
    }

    public void setMarkupCommandMapStatus(RaifStatus markupCommandMapStatus) {
        this.markupCommandMapStatus = markupCommandMapStatus;
    }

    public RaifStatus getCommandDescriptionMapStatus() {
        return commandDescriptionMapStatus;
    }

    public void setCommandDescriptionMapStatus(RaifStatus commandDescriptionMapStatus) {
        this.commandDescriptionMapStatus = commandDescriptionMapStatus;
    }

    public RaifStatus getCommandPipeLineMapStatus() {
        return commandPipeLineMapStatus;
    }

    public void setCommandPipeLineMapStatus(RaifStatus commandPipeLineMapStatus) {
        this.commandPipeLineMapStatus = commandPipeLineMapStatus;
    }

    public RaifStatus getDispatchStatus() {
        return dispatchStatus;
    }

    public void setDispatchStatus(RaifStatus dispatchStatus) {
        this.dispatchStatus = dispatchStatus;
    }

    private Map<String,String>      raifMarkupCommandMap        = new LinkedHashMap<String,String>();

    //private Map<String,RaifCommand> raifRepositaryCommandMap    = new LinkedHashMap<String,RaifCommand>();

    private Map<List<String>,RaifCommand> raifRepositaryCommandMap    = new LinkedHashMap<List<String>,RaifCommand>();

    private Map<RaifCommand,String> raifCommandDescriptionMap   = new LinkedHashMap<RaifCommand,String>();

    private Map<RaifCommandPointer,Map<String,RaifJobItem>>     raifCommandPipeLineMap = new LinkedHashMap<RaifCommandPointer,Map<String,RaifJobItem>>();

    public Map<String, String> getRaifMarkupCommandMap() {
        return raifMarkupCommandMap;
    }

    public void setRaifMarkupCommandMap(Map<String, String> raifMarkupCommandMap) {
        this.raifMarkupCommandMap = raifMarkupCommandMap;
    }

    public Map<RaifCommandPointer, Map<String, RaifJobItem>> getRaifCommandPipeLineMap() {
        return raifCommandPipeLineMap;
    }

    public void setRaifCommandPipeLineMap(Map<RaifCommandPointer, Map<String, RaifJobItem>> raifCommandPipeLineMap) {
        this.raifCommandPipeLineMap = raifCommandPipeLineMap;
    }

    public Map<RaifCommand, String> getRaifCommandDescriptionMap() {
        return raifCommandDescriptionMap;
    }

    public void setRaifCommandDescriptionMap(Map<RaifCommand, String> raifCommandDescriptionMap) {
        this.raifCommandDescriptionMap = raifCommandDescriptionMap;
    }

    private RaifMap raifMap;

    public Map<List<String>, RaifCommand> getRaifRepositaryCommandMap() {
        return raifRepositaryCommandMap;
    }

    public void setRaifRepositaryCommandMap(Map<List<String>, RaifCommand> raifRepositaryCommandMap) {
        this.raifRepositaryCommandMap = raifRepositaryCommandMap;
    }

    public RaifStatus getCommandMapStatus() {
        return commandMapStatus;
    }

    public void setCommandMapStatus(RaifStatus commandMapStatus) {
        this.commandMapStatus = commandMapStatus;
    }

    public RaifMap getRaifMap() {
        return raifMap;
    }

    public void setRaifMap(RaifMap raifMap) {
        this.raifMap = raifMap;
    }

    // TODO  initRepositaryCommandMap() !!!

    public RaifStatus initRepositaryCommandMap()
    {


            // Генерация класса для CHECK(reserved);

            this.getRaifRepositaryCommandMap().put(Arrays.asList(new String[]{"-chk","--chk","/chk"}),RaifCommand.CHECK);

            // Генерация класса для PAYLOAD(reserved).

            this.getRaifRepositaryCommandMap().put(Arrays.asList(new String[]{"-pld","--pld","/pld"}),RaifCommand.PAYLOAD);

            // Генерация класса для контроллера.

            this.getRaifRepositaryCommandMap().put(Arrays.asList(new String[]{"-ctr","--ctr","/ctr"}),RaifCommand.CONTROLLER);


            // Генерация класса для ран тайма.

            this.getRaifRepositaryCommandMap().put(Arrays.asList(new String[]{"-jna","--jna","/jna"}),RaifCommand.JNA);

            // Отключить контроль количества параметров командной строки.

            this.getRaifRepositaryCommandMap().put(Arrays.asList(new String[]{"-poff","--poff","/poff"}),RaifCommand.DISABLE_PARAMS_COUNT);



        /*this.getRaifRepositaryCommandMap().forEach((k,v)->{

            sm(sf("initRepositaryCommandMap...key...%s...value...%s",k,v));

        });*/

            // TODO initRepositaryCommandMap

            //sm(sf("initRepositaryCommandMap...size...%3d...expected...%3d",this.getRaifRepositaryCommandMap().size(),CMD_COMMAND_MAP_COUNT));

            return this.getRaifRepositaryCommandMap().size() == CMD_COMMAND_MAP_COUNT ? RaifStatus.OK : RaifStatus.ERROR;

    }


    public RaifStatus initCommandDescriptionMap()
    {
        this.getRaifCommandDescriptionMap().put(RaifCommand.CHECK,"Проверка структуры входного файла");

        this.getRaifCommandDescriptionMap().put(RaifCommand.PAYLOAD,"Генерация файла класса PAYLOAD");

        this.getRaifCommandDescriptionMap().put(RaifCommand.CONTROLLER,"Генерация файла класса CONTROLLER");

        this.getRaifCommandDescriptionMap().put(RaifCommand.JNA,"Генерация файла класса RUNTIME");
/*

        this.getRaifCommandDescriptionMap().forEach((k,v)->{

            sm(sf("initCommandDescriptionMap...key...%s...value...%s",k,v));

        });
*/

        return this.getRaifCommandDescriptionMap().size() == CMD_COMMAND_DESCRIPTION_MAP_COUNT ? RaifStatus.OK : RaifStatus.ERROR;

    }

    // TODO isaCommand(String key)

    // Req: Проверить наличие определенной команды в командной строке.
    // -- Надо выбрать все команды.
    // -- В списке проверить наличие определенной в параметре.
    // -- Условие : Их может быть несколько, но нам важно ее наличие в списке.

    public int getCountOfParams()
    {
        try {
            return this.getRaifMarkupCommandMap().values().stream().filter(b -> (b.equalsIgnoreCase("PARAMETER"))).collect(Collectors.toList()).size();
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    public int getCountOfCommands()
    {

        try {
            return this.getRaifMarkupCommandMap().values().stream().filter(b -> (b.equalsIgnoreCase("SWITCH"))).collect(Collectors.toList()).size();
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    public boolean isaRaifCommand(RaifCommand raifCommand) {

        // Get lists of SWITCHS.

        try {
            RaifCommand[] returnRaifCommand = {RaifCommand.NONE};

            this.getRaifMarkupCommandMap().forEach((k, v) -> {

                RaifCommand tmpRaifCommand = isaCommand(k);

                if (tmpRaifCommand.equals(raifCommand) && v.equalsIgnoreCase("switch")) {
                    returnRaifCommand[0] = tmpRaifCommand;
                }

            });

            return !returnRaifCommand[0].equals(RaifCommand.NONE);

        } catch (Exception e) {

            return false;
        }
    }

    public RaifCommand isaCommand(String key)
    {

        final RaifCommand[][] raifCommand = {{RaifCommand.NONE}};

      this.getRaifRepositaryCommandMap().forEach((k,v)-> {

          k.forEach(l -> {

              if (l.contains(key)) {

                  raifCommand[0][0] = v;
              }

          });
      });
            return raifCommand[0][0];

    }

    // TODO Проверка входных параметров приложения.

    public RaifStatus initRaifMarkupCommandMap(){

        if(this.getRaifMap() == null)
        {
            sm(sf("Нет входных параметров."));

            return RaifStatus.ERROR;
        }
//
//        if(this.getRaifMap().size() != CMD_COMMAND_COUNT_PARAMS)
//        {
//            sm(sf("Неверное число входных параметров...[%2d]...ожидается...[%2d]",this.getRaifMap().size(),CMD_COMMAND_COUNT_PARAMS));
//
//            return RaifStatus.ERROR;
//        }

            this.getRaifMap().forEach((k,v)->{

                // TODO initRaifMarkupCommandMap()

                this.getRaifMarkupCommandMap().put(v,this.isaCommand(v) != RaifCommand.NONE ? SWITCH : PARAMETER);

            });

        this.getRaifMarkupCommandMap().forEach((k,v)->{

            sm(sf("getRaifMarkupCommandMap()...key...[%s]...value...[%s]",k,v));

        });

        // TODO DEBUG

            sm(sf("Параметров...[%d]...Команд...[%d]",this.getCountOfParams(),this.getCountOfCommands()));

            this.setMarkupCommandMapStatus(this.getRaifMarkupCommandMap().size() != 0 ? RaifStatus.OK : RaifStatus.ERROR);

            return this.getMarkupCommandMapStatus();
}

    public RaifStatus initCommandPipelineMap() {

        this.setRaifCommandPipeLineMap(new LinkedHashMap<RaifCommandPointer,Map<String,RaifJobItem>>());

        return this.getRaifCommandPipeLineMap() != null ? RaifStatus.OK : RaifStatus.ERROR;
    }

    public RaifDispatch loadCommandPipelineMap() {


        sm("loadCommandPipelineMap()...");

        // First pass to init RaifCommandPointer and RaifJobItem Map.

       this.getRaifMap().forEach((k,v)->{

           sm(sf("First pass intro ...key...%s...value...%s",k,v));

           /*RaifCommandPointer raifCommandPointer = RaifCommandPointer.getInstance(
                   this.getRaifRepositaryCommandMap().get(k),
                   v);
*/
           //this.getRaifRepositaryCommandMap().get(k);

           //sm(sf("loadCommandPipelineMap...first pass...Init pointer...%s...value...%s",raifCommandPointer.getRaifCommand().toString(),raifCommandPointer.getPayloadObject()));


           //this.getRaifCommandPipeLineMap().put(raifCommandPointer,new LinkedHashMap<String,RaifJobItem>());

        });

        this.getRaifCommandPipeLineMap().forEach((k,v)->{

            //sm(sf("loadCommandPipelineMap...first pass...key...%s...value...%s",k.getRaifCommand().toString(),k.getPayloadObject()));

        });

        // Second pass to resolve references.

        sm("Second pass...");

        this.getRaifCommandPipeLineMap().forEach((k,v)-> {

            //sm("Second pass intro...");

                if(k.getRaifCommand() == RaifCommand.CHECK) {

                    sm("Put to map..." + k.getPayloadObject());

                    RaifJobItem raifJobItem = RaifJobItem.getInstance(RaifJobItemType.CREATE_STRING_ITEMS_FROM_FILE, k.getPayloadObject(), new ArrayList<String>(), RaifJobItemType.CREATE_STRING_ITEMS_FROM_FILE.toString());

                    v.put(raifJobItem.toString(),raifJobItem);

                }



        });

            this.setDispatchStatus(getRaifCommandPipeLineMap().size() != 0 ? RaifStatus.OK : RaifStatus.ERROR);

            return this;
    }

    public RaifDispatch()
    {

    }

    public RaifDispatch(RaifMap raifMap)
    {

        this.setRaifMap(raifMap);

        this.initAllMaps();

    }

    // TODO initAllMaps()

    public RaifStatus initAllMaps()
    {
        this.setCommandMapStatus(this.initRepositaryCommandMap());

        this.setCommandDescriptionMapStatus(this.initCommandDescriptionMap());

        this.setMarkupCommandMapStatus(this.initRaifMarkupCommandMap());

        this.setDispatchStatus(

                (       this.getCommandMapStatus()              == RaifStatus.OK &&
                        this.getCommandDescriptionMapStatus()   == RaifStatus.OK &&
                        this.getMarkupCommandMapStatus()        == RaifStatus.OK

                ) ? RaifStatus.OK: RaifStatus.ERROR
        );


        //this.setCommandPipeLineMapStatus(this.loadCommandPipelineMap().getDispatchStatus());

        // Return initAllMaps().

       sm(sf("CommandMapStatus...%s...CommandDescriptionMapStatus...%s...MarkupCommandMapStatus...%s",
                this.getCommandMapStatus().toString(),
                this.getCommandDescriptionMapStatus().toString(),
                this.getMarkupCommandMapStatus().toString()
                ));

        sm("initAllMaps...return status..." + this.getDispatchStatus().toString());

        return this.getDispatchStatus();

    }

    public static RaifDispatch getInstance()
    {
                return new RaifDispatch();
    }

    public static RaifDispatch getInstance(RaifMap raifMap)
    {
        return new RaifDispatch(raifMap);
    }

    // TODO dispatch(ROOT INTRO)

    public boolean isaNoParams()
    {
        return (this.getCountOfParams() == 0);
    }

    public boolean isaNoCommands()
    {
        return (this.getCountOfCommands() == 0);
    }


    public RaifStatus dispatch()
    {

          if(this.isaNoCommands() && this.isaNoParams())
          {
              return smt("Проверка входных параметров",RaifStatus.NOT_INPUT_PARAMS);
          }

              return smt("Формирование данных",extractPackageSuite(this.getRaifMap().get(1),this.isaCommand(this.getRaifMap().get(0))));

    }

    public List<String> get_solid_list(List<String> payload)
    {
        List<String> solid = new ArrayList<String>();

        payload.forEach(l->{

            if(l.trim().length() != 0)
                solid.add(l);

        });

            return solid;
    }

    public RaifStatus extractTxtByComposeType(String source,List<RaifField> raifFields,RaifFieldComposeType raifFieldComposeType,StringBuilder stringBuilder)
    {
        try {

            if (stringBuilder == null) {

                sm(sf("Целевой приемник текстовых строк не инициализирован."));

                return RaifStatus.ERROR;
            }

            raifFields.forEach(r -> {

            String data = r.toString(raifFieldComposeType);

            stringBuilder.append(data);

            stringBuilder.append(System.lineSeparator());

        });
            return RaifStatus.OK;
        }
        catch(Exception e)
        {
            return RaifStatus.ERROR;
        }

    }

    // TODO extractPackage() (!)

    public RaifStatus extractPackageSuite(String source,RaifCommand raifCommand)
    {
        try {

                    /*JNA_INT_VARIABLES_DECLARATION,
                    JNA_STRING_VARIABLES_DECLARATION,
                    JNA_BYTE_ARRAY_DECLARATION,
                    JNA_DTOR_FILL_DECLARATION,
                    JNA_GET_FIELD_ORDER,
                    JNA_GETTERS_SETTERS,
*/
            StringBuilder totalBuilder = new StringBuilder();

            if(RaifCommand.JNA == raifCommand) {

                    String m_int = extractPackageBuilder(source, RaifFieldComposeType.JNA_INT_VARIABLES_DECLARATION);

                    String m_str = extractPackageBuilder(source, RaifFieldComposeType.JNA_STRING_VARIABLES_DECLARATION);

                    String m_byte = extractPackageBuilder(source, RaifFieldComposeType.JNA_BYTE_ARRAY_DECLARATION);

                    String m_dtor = extractPackageBuilder(source, RaifFieldComposeType.JNA_DTOR_FILL_DECLARATION);

                    String m_get = extractPackageBuilder(source, RaifFieldComposeType.JNA_GET_FIELD_ORDER);

                    String m_set_get = extractPackageBuilder(source, RaifFieldComposeType.JNA_GETTERS_SETTERS);


                   /* totalBuilder.append(m_int);

                    totalBuilder.append(m_str);

                    totalBuilder.append(m_byte);

                    totalBuilder.append(m_dtor);

                    totalBuilder.append(m_get);

                    totalBuilder.append(m_set_get);*/

                    String class_name = sf("Jna_%s",BeforeAt(source,"."));

                    String targetFile = sf("%s.java",class_name);


                String s_ready = sf(
                "package ua.aval.corebanking.currencyexchange.jna.model;\n\n"+
                "import com.sun.jna.Structure;\n"+
                "import java.nio.charset.StandardCharsets;\n"+
                "import java.util.Arrays;\n"+
                "import java.util.List;\n\n"+
                "public class %s extends Structure {\n"+
                " public static class ByReference extends %s implements Structure.ByReference {\n"+
                  "  }\n\n"+
                     "%s\n%s\n%s\n" +
                    "public %s () { \n\n"+
                    "%s\n"+
                    "}\n\n"+
                    "@Override\n"+
                    "protected List getFieldOrder() {\n"+
                        "return Arrays.asList(new String[]{\n %s\n }); }\n %s\n }\n",
                        class_name,
                        class_name,
                        m_int,
                        m_str,
                        m_byte,
                        class_name,
                        m_dtor,
                        m_get,
                        m_set_get
                        );

                    totalBuilder.append(s_ready);

                    sm(totalBuilder.toString());

                    to_file(targetFile,totalBuilder.toString());

            }

                    return RaifStatus.OK;
        }
        catch(Exception e)
        {
                    return RaifStatus.ERROR;
        }
    }

    public String extractPackageBuilder(String source,RaifFieldComposeType raifFieldComposeType)
    {
        try {

            List<RaifField> raifFields = new ArrayList<RaifField>();

            StringBuilder stringBuilder = new StringBuilder();

            if(this.get_raif_fields(source,raifFields) != RaifStatus.OK)
                return null;

            if(this.extractTxtByComposeType(source,raifFields,raifFieldComposeType,stringBuilder) != RaifStatus.OK)
                return null;

            return stringBuilder.toString();

        }
        catch(Exception e)
        {
            return null;
        }
    }

    public RaifStatus extractPackage(String source,RaifFieldComposeType raifFieldComposeType,StringBuilder stringBuilder)
    {
        try {

            List<RaifField> raifFields = new ArrayList<RaifField>();

            if(this.get_raif_fields(source,raifFields) != RaifStatus.OK)
                return RaifStatus.ERROR;

            if(this.extractTxtByComposeType(source,raifFields,raifFieldComposeType,stringBuilder) != RaifStatus.OK)
                return RaifStatus.ERROR;

            sm(stringBuilder.toString());

            return RaifStatus.OK;
        }
        catch(Exception e)
        {
            return RaifStatus.ERROR;
        }
    }

    public RaifStatus get_raif_fields(String source,List<RaifField> raifFields)
    {
        try {

            // Check source file.

            if (!Files.exists(Paths.get(source))) {

                sm(sf("Файл источник данных...[%s]...не найден.", source));

                return RaifStatus.ERROR;
            }

            // Get string from a file.

            String source_str = get_str_from_file(source);

            if (source_str.trim().length() == 0) {

                sm(sf("Файл...[%s]...не содержит данных для обработки.", source));

                return RaifStatus.ERROR;
            }

            // Get list of data rows for processing.

            List<String> source_as_list = get_solid_list(get_list_from_file(source));

            if (source_as_list.size() == 0) {

                sm(sf("Ошибка формирования массива строк из файла...[%s].", source));

                return RaifStatus.ERROR;
            }

            sm(sf("Чтение из файла...[%s]...размер...[%5d]...строк данных...[%5d]",
                    source,
                    source_str.length(),
                    source_as_list.size()));


            // Lookup of a struct word at begining of the source file.

            String s_struct = BeforeAtFirst(source_as_list.get(0).trim(), " ");

            if (!s_struct.trim().equalsIgnoreCase("struct")) {

                sm(sf("Входной файл не содержит обьявления структуры...[%s].", source));

                return RaifStatus.ERROR;
            }

            // Struct must be named.

            String s_original_source = BeforeAt(AfterAt(source_as_list.get(0).trim(), s_struct), " ");

            if (s_original_source.trim().length() == 0) {

                sm(sf("Входной файл содержит не именованную структуру...[%s].", source));

                return RaifStatus.ERROR;
            }

            sm(sf("Тип...[%s]...Наименование структуры данных...[%s]", s_struct, s_original_source));

            // Remove NL in single row of data before parsing.

            String s_ext_source = get_str_from_list(source_as_list, "").replace(System.lineSeparator(), "").replace("\r\n", "");

            // Check target storage for fields array.

            if (raifFields == null) {

                sm(sf("Целевой приемник списка полей не инициализирован."));

                return RaifStatus.ERROR;
            }

            // Perform parsing of rows.

            get_as_list(BeforeAt(AfterAtFirst(s_ext_source, "{"), "}"), ";").forEach(g -> {

                // Perform parsing of fields.

                RaifField raifField = RaifField.parseRaifField(g);

                if (raifField != null)
                    raifFields.add(raifField);

            });

            return s_struct.equalsIgnoreCase("struct") &&
                    s_original_source.trim().length() != 0 &&
                    raifFields.size() != 0 ? RaifStatus.OK : RaifStatus.ERROR;

        } catch (Exception e) {

            e.printStackTrace();

            return RaifStatus.ERROR;
        }
    }


}

