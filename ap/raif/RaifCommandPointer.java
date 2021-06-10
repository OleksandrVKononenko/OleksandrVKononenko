package ap.raif;

public class RaifCommandPointer {

    private RaifCommand raifCommand;

    private Object payloadObject;

    public RaifCommand getRaifCommand() {
        return raifCommand;
    }

    public void setRaifCommand(RaifCommand raifCommand) {
        this.raifCommand = raifCommand;
    }

    public Object getPayloadObject() {
        return payloadObject;
    }

    public void setPayloadObject(Object payloadObject) {
        this.payloadObject = payloadObject;
    }

    public RaifCommandPointer(RaifCommand raifCommand, Object payloadObject) {

        this.setRaifCommand(raifCommand);
        this.setPayloadObject(payloadObject);


    }

    public static RaifCommandPointer getInstance(RaifCommand raifCommand, Object payloadObject)
    {
        return new RaifCommandPointer(raifCommand,payloadObject);
    }

}
