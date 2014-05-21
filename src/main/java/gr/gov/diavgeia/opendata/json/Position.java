package gr.gov.diavgeia.opendata.json;

/**
 *
 * @author Diavgeia Developers
 */
public class Position {

    private String uid;
    private String label;

    public Position() {
    }

    public Position(String uid, String label) {
        this.uid = uid;
        this.label = label;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
