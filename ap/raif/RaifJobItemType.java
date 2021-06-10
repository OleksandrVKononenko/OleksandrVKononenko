package ap.raif;

public enum RaifJobItemType {


    CREATE_STRING_ITEMS_FROM_FILE("Create string items from file"),
    CREATE_PRIMARY_INDEX_BY_ORIGIN_NAME("Create primary index by origin name");

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private RaifJobItemType (String title)
    {
        this.setTitle(title);
    }



}
