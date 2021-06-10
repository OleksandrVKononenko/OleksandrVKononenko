package ap.raif;

import java.util.Arrays;
import java.util.List;

import static ap.raif.RaifUtil.*;

public class RaifField {

    private int order;

    private String original_name;

    private String name;

    private String type;

    private int length;

    private int precision;

    private int  mandatory;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getMandatory() {
        return mandatory;
    }

    public void setMandatory(int mandatory) {
        this.mandatory = mandatory;
    }

    public RaifField(int order, String original_name, String type, int length) {
        this.order = order;
        this.original_name = original_name;
        this.type = type;
        this.length = length;
    }

    public static RaifField getInstance(int order, String original_name, String type, int length)
    {
        return new RaifField(order,original_name,type,length);
    }

    // TODO parseRaifField()

    public static RaifField parseRaifField(String payload) {

        // v.1

        String to_remove = sf("/* %s */",get_str_between(payload,"/*","*/"));

        String workload = payload.replace(to_remove,"").replace("\t"," ");

        // sm(sf("[%s]",workload));

        if (workload.trim().length() != 0) {

            int m_order = Gen.nextId();

            String m_name = BeforeAtFirst(AfterAt(workload, " "), "[");

            String m_type = BeforeAtFirst(workload, " ");

            int m_length = Integer.parseInt(BeforeAt(AfterAtFirst(workload, "["), "]").trim());

            return RaifField.getInstance(m_order,
                m_name,
                m_type,
                m_length
        );

        }
            return null;
    }

    @Override
    public String toString()
    {
        return sf("%3d %32s %32s %16s %3d",
                this.getOrder(),
                this.getOriginal_name(),
                this.getType(),
                this.getLength());
    }

    // TODO toString(RaifFieldComposeType type) !!!

    public String toString(RaifFieldComposeType type)
    {
        String workload = "";

        if(type == RaifFieldComposeType.ORIGIN_FULL) {
            workload = sf("%3d %32s %16s %3d",
                    this.getOrder(),
                    this.getOriginal_name(),
                    this.getType(),
                    this.getLength());
        } else
        if(type == RaifFieldComposeType.ORIGIN_PRIMARY) {
            workload = sf("%32s",
                    this.getOriginal_name()
            );
        } else
        if(type == RaifFieldComposeType.VENGRO_PRIMARY) {
            workload = sf("%8d %32s %32s ",
                    this.getOrder(),
                    this.getOriginal_name(),
                    this.getVengro(this.getOriginal_name())
            );
        } else if(type == RaifFieldComposeType.VENGRO_PRIMARY_CLASS) {
            workload = sf("%s %32s ;",
                    "private String ",
                    this.getVengro(this.getOriginal_name())
            );
        } else if(type == RaifFieldComposeType.VENGRO_PRIMARY_CLASS_TO_STRING) {
            workload = sf("%s%s%s=%s%s%s%s%s%s%s",
                    "\"",
                    ",",
                    this.getVengro(this.getOriginal_name()),
                    '\'',
                    "\"",
                    " + ",
                    this.getVengro(this.getOriginal_name()),
                    " + ",
                    "'\\''",
                    "+"
            );

        } else if(type == RaifFieldComposeType.JNA_INT_VARIABLES_DECLARATION) {

            // private static final int CURRENT_ACCOUNT_DR_SIZE = 29;

            workload = sf(
                    "private static final int %32s_SIZE = %3d;",
                    this.getOriginal_name().toUpperCase(),
                    this.getLength()
            );
        } else if(type == RaifFieldComposeType.JNA_BYTE_ARRAY_DECLARATION) {


            // public byte[] currentAccountDr = new byte[CURRENT_ACCOUNT_DR_SIZE];

            workload = sf(

                    "public byte[] %32s = new byte[%s_SIZE];",
                    this.getVengro(this.getOriginal_name()),
                    this.getOriginal_name().toUpperCase()

            );
        }
        else if(type == RaifFieldComposeType.JNA_STRING_VARIABLES_DECLARATION) {

            // private String currentAccountDrStrct;

            workload = sf(
                    "private String %sStrct;",
                    this.getVengro(this.getOriginal_name())
            );
        }
        else if(type == RaifFieldComposeType.JNA_DTOR_FILL_DECLARATION) {

            // Arrays.fill(currentAccountDr, (byte) 0x20);

            workload = sf(
                    "Arrays.fill(%s, (byte) 0x20);",
                    this.getVengro(this.getOriginal_name())
            );
        }
        else if(type == RaifFieldComposeType.JNA_GET_FIELD_ORDER) {
/*
            return Arrays.asList(new String[]{
                    "currentAccountDr", "sumDr", "currentAccountCr", "sumCr",
                    "taxSum", "exchangeRate", "narrativeLong", "clientName",
                    "alphaCode", "residence", "reportingCode", "marketRate",
                    "margin", "contractDate", "contractNumber", "customerGroup"});
*/

            workload = sf(
                    "\"%s\",",
                    this.getVengro(this.getOriginal_name())
            );
        }else if(type == RaifFieldComposeType.JNA_GETTERS_SETTERS) {
            /*
                public String getCurrentAccountDrStrct() {
                currentAccountDrStrct = new String(currentAccountDr, StandardCharsets.UTF_8);
                return currentAccountDrStrct;
            }

            */
            String vengro_c = this.getVengroComplete(this.getOriginal_name());

            String vengro = this.getVengro(this.getOriginal_name());

            String NL = System.lineSeparator();

                    workload = sf(
                "public String get%sStrct() { %s" +
                "\t%sStrct = new String(%s, StandardCharsets.UTF_8);%s" +
                "\t\treturn %sStrct;%s"+
            "}%s%s" +
                "public void set%sStrct(String %sStrct) {%s"+
                "\tthis.%sStrct = %sStrct;%s"+
                "\tbyte[] tmp = String.format(\"%%-\" + %s_SIZE + \"s\", %sStrct).getBytes(StandardCharsets.UTF_8);%s"+
                 "\tthis.%s = Arrays.copyOfRange(tmp, 0, %s_SIZE);%s" +
             "}%s%s",
                            // Getter.
                            vengro_c,NL,
                            vengro,vengro,NL,
                            vengro,NL,
                            NL,NL,

                            // Setter.
                            vengro_c,vengro,NL,
                            vengro,vengro,NL,
                            this.getOriginal_name().toUpperCase(),vengro,NL,
                            vengro,this.getOriginal_name().toUpperCase(),NL,
                            NL,NL

                    );
        }
            return workload;
    }

    public String getVengro(String payload) {
        String workload = payload;

        StringBuilder sb = new StringBuilder();

        int[] index = {0};

        get_as_list(workload, "_").forEach(a -> {

            if (index[0] == 0)
                sb.append(a);
            else {

                String tmp = sf("%s%s", a.trim().substring(0, 1).toUpperCase(), a.trim().substring(1));

                sb.append(tmp);
            }

            index[0]++;
        });
            return sb.toString();
        }

        public String getVengroComplete(String payload)
        {
            String wrk = payload;

            StringBuilder sb = new StringBuilder();

            get_as_list(wrk,"_").forEach(a->{

                    String tmp = sf("%s%s", a.trim().substring(0, 1).toUpperCase(), a.trim().substring(1));

                    sb.append(tmp);
            });

                    return sb.toString();

    }

    // TODO RaifField export data to the file.

    public static RaifStatus export_to_file(String payload,RaifFieldComposeType composeType,boolean show)
    {
        return RaifStatus.OK;
    }

    public static List<RaifField> get_raif_fields(String payload,RaifFieldComposeType composeType)
    {
        return null;
    }
}
