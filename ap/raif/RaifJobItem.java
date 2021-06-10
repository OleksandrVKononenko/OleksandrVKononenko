package ap.raif;

public class RaifJobItem {

    private RaifJobItemType raifJobItemType;

    private Object payloadObject;

    private Object targetObject;

    private String jobDescription;

    private RaifStatus raifJobItemStatus = RaifStatus.NONE;


    public RaifJobItemType getRaifJobItemType() {
        return raifJobItemType;
    }

    public void setRaifJobItemType(RaifJobItemType raifJobItemType) {
        this.raifJobItemType = raifJobItemType;
    }

    public RaifStatus getRaifJobItemStatus() {
        return raifJobItemStatus;
    }

    public void setRaifJobItemStatus(RaifStatus raifJobItemStatus) {
        this.raifJobItemStatus = raifJobItemStatus;
    }

    public Object getPayloadObject() {
        return payloadObject;
    }

    public void setPayloadObject(Object payloadObject) {
        this.payloadObject = payloadObject;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public RaifJobItem(RaifJobItemType raifJobItemType, Object payloadObject, Object targetObject, String jobDescription) {
        this.raifJobItemType = raifJobItemType;
        this.payloadObject = payloadObject;
        this.targetObject = targetObject;
        this.jobDescription = jobDescription;
    }

    public static RaifJobItem getInstance(RaifJobItemType raifJobItemType, Object payloadObject, Object targetObject, String jobDescription)
    {
        return new RaifJobItem(raifJobItemType,payloadObject,targetObject,jobDescription);
    }
}
