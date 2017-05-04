package id.ramadani.pilem.model;

/**
 * Created by dani on 5/4/17.
 */

public class ItemInfo {
    private String label;
    private String value;

    public ItemInfo(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
